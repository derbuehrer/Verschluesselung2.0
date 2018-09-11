package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class Generieren {

    public static String generiere() {
        // Alle Zeichen die das Passwort beinhalten kann
        String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZöüäÖÜÄ;,:._-'#*+><0123456789!§$%&/()=?~|@€[]{}ß\\ ";
        String passw = "";

        // Generierung des Passwortes
        for (int i = 1; i <= Math.random() * 40 + 25; i++) {
            passw += charset.charAt((int) Math.floor(Math.random() * charset.length()));
        }
        // Ausgabe des Passwortes sowie Speicherung dessen
        JOptionPane.showMessageDialog(null, "Das generierte Passwort wurde für 10 Sekunden in die Zwischenablage kopiert und sieht wie folgt aus: " + passw);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                new StringSelection(passw), null);

        return passw;
    }
}
