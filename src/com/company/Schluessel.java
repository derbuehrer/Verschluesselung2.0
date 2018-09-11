package com.company;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Schluessel {

    // Speicherung des Keys in eine private Variable
    private static Key seckey;

    // Generierung eines Keys mithilfe des eingegebenen Passwortes
    public static void setSchluessel(String keyStr, int bit) throws NoSuchAlgorithmException, IOException {

        byte[] key = (keyStr).getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-" + bit);
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        seckey = new SecretKeySpec(key, "AES");

        /* File keyfile = new File(".keyfile.txt");

        OutputStream outputStream = new ObjectOutputStream(new FileOutputStream(keyfile));
        ((ObjectOutputStream) outputStream).writeObject(seckey);
        outputStream.close(); */
    }

    // Entschlüssleung des Textes mithilfe des Keys
    public static void entschluesseln(JTextArea text) throws InvalidKeyException, IOException, NoSuchPaddingException, NoSuchAlgorithmException {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] crypted2 = decoder.decodeBuffer(text.getText());

            Cipher cipher2 = Cipher.getInstance("AES");
            cipher2.init(Cipher.DECRYPT_MODE, seckey);
            byte[] ciperData2 = cipher2.doFinal(crypted2);
            String erg = new String(ciperData2);

            // Ausgabe des entschlüsselten Textes im Textaria
            text.setText(erg);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                    new StringSelection(erg), null);
        }
        // Mögliche Fehler beim entschlüsseln abfangen
        catch (BadPaddingException e) {
            JOptionPane.showMessageDialog(null, "Konnte nicht entschlüsselt werden!(Vielleicht stimmt der Schlüssel oder die Eingabe nicht?)");
        } catch (IllegalBlockSizeException e) {
            JOptionPane.showMessageDialog(null,"Der Text ist bereits entschlüsselt");
        }
    }

    // Verschlüsselung des Textes
    public static void verschluesseln(JTextArea textArea) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, seckey);
            byte[] encrypted = cipher.doFinal(textArea.getText().getBytes());

            BASE64Encoder encoder = new BASE64Encoder();
            String erg = encoder.encode(encrypted);

            // Ausgabe des verschlüsselten Textes im Textaria
            textArea.setText(erg);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                    new StringSelection(erg), null);
        }
        // Möglichen Fehler beim verschlüsseln abfangen
        catch (InvalidKeyException e) {
            JOptionPane.showMessageDialog(null, "Der Key wurde nicht richtig gelesen oder etwas stimmt mit ihm nicht!");
        }
    }
}
