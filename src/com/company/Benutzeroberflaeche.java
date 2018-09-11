package com.company;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

public class Benutzeroberflaeche {
    private JPanel panel;
    private JButton entschlüsseln;
    private JButton verschlüsseln;
    private JButton newPW;
    private JTextArea textArea;
    private JButton zwischenablage;
    private boolean pwgesetzt = false;      // Prüfen ob ein Passwort gesetzt istD+v%/7iGkhD| g4!L[A3]}€dÜ

    public static void main(String[] args) {
        JFrame frame = new JFrame("Verschlüsselung");
        frame.setContentPane(new Benutzeroberflaeche().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,500);
        frame.setLocation(670,320);
        frame.setVisible(true);
    }

    Benutzeroberflaeche() {

        entschlüsseln.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kontrolle ob ein Passwort eingegeben wurde
                if (pwgesetzt) {

                    // Prüfen ob entschlüsselt werden kann
                    if (textArea.getText().equals("") || textArea.getText().equals(" ")) {
                        JOptionPane.showMessageDialog(null, "Es gibt nichts zum entschlüsseln!");
                        return;
                    }
                    // Aufruf der Entschlüsselungsmethode
                    try {
                        Schluessel.entschluesseln(textArea);
                    } catch (InvalidKeyException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchPaddingException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    }
                }
                // Wenn kein Passwort gesetzt
                else JOptionPane.showMessageDialog(null,"Es wurde kein Passwort gesetzt");
            }
        });

        verschlüsseln.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kontrolle ob ein Passwort eingegeben wurde
                if (pwgesetzt) {

                    // Prüfen ob verschlüsselt werden kann
                    if (textArea.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Es gibt nichts zum verschlüsseln");
                        return;
                    }
                    // Aufruf der Verschlüsselungsmethode
                    try {
                        Schluessel.verschluesseln(textArea);
                    } catch (NoSuchPaddingException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    } catch (BadPaddingException e1) {
                        e1.printStackTrace();
                    } catch (IllegalBlockSizeException e1) {
                        e1.printStackTrace();
                    }
                }
                // Wenn kein Passwort gesetzt
                else JOptionPane.showMessageDialog(null, "Es wurde kein Passwort gesetzt");

            }
        });

        newPW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int passwort = 0;
                String keyStr;
                // Abfrage ob ein Passwort generiert werden soll
                passwort = JOptionPane.showConfirmDialog(null, "Möchten Sie ein Passwort generieren lassen?");

                // Ja, Passwort wird generiert
                if (passwort == 0) {
                    int bit = Bit.bitStraerke();

                    keyStr = Generieren.generiere();
                    // Zeichen dass PW gesetzt wurde
                    pwgesetzt = true;

                    // Aufruf der setSchluessel Methode
                    try {
                        Schluessel.setSchluessel(keyStr, bit);
                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    // Erstellung eines Timers um nach 10 sek die Zwischenablage zu leeren
                    java.util.Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // Ersetzen der Zwischenablage durch nichts (Zwischenablage wird geleert)
                            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                                    new StringSelection(""), null);
                        }
                    },10*1000);
                }

                // Nein, Passwort wird vom Nutzer eingegeben
                if (passwort == 1) {
                    keyStr = JOptionPane.showInputDialog(null,"Bitte gebe hier ein Passwort an! (mindestens 16 Zeichen sind empfolen)");
                    // Abbrechen gedrückt oder OK gedrückt ohne Eingabe
                    if (keyStr == null || keyStr.equals("")) return;

                    boolean pruefung = Pruefen.pruefe(keyStr);
                    if (pruefung) {
                        int bit = Bit.bitStraerke();
                        // Zeichen dass PW gesetzt wurde
                        pwgesetzt = true;

                        // Aufruf der setSchluessel Methode
                        try {
                            Schluessel.setSchluessel(keyStr, bit);
                        } catch (NoSuchAlgorithmException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedEncodingException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        // Um den textArea in zu Kopieren
        zwischenablage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                        new StringSelection(textArea.getText()), null);
            }
        });
    }
}