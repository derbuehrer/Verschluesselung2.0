package com.company;

import javax.swing.*;

public class Pruefen {

    // Arrays enthalten elemente die geprüft werden
    private static String[] sonderzeichen = {"?", ",", "@", "\"", "!", "§", "$", "%", "&", "/", "(", ")",
            "=", "+", "#", "*", "\'", "\\", "-", ".", ";", ":", "_", "<", ">", "|", "Ü", "ü", "Ö", "ö", "Ä", "ä"};
    private static String[] buchstaben = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static String[] zahlen = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static boolean pruefe(String keyStr) {
        int sondzeichen = 0;
        int buchstab = 0;
        int zahlenzaeler = 0;

        // Prüfung der Kriterien mit 3 gleichen Algorygmen
        for (int i = 0; i < sonderzeichen.length; i++) {
            if (keyStr.contains(sonderzeichen[i])) {
                sondzeichen++;
            }
        }
        for (int i = 0; i < buchstaben.length; i++) {
            if (keyStr.contains(buchstaben[i])) {
                buchstab++;
            }
        }
        for (int i = 0; i < zahlen.length; i++) {
            if (keyStr.contains(zahlen[i])) {
                zahlenzaeler++;
            }
        }

        // Prüfung des Resultates und weiterer Kriterien
        if (sondzeichen > 1 && buchstab > 5 && zahlenzaeler > 3 && !(keyStr.contains("password"))
                && !(keyStr.contains("passwort")) && !(keyStr.contains("kennwort")) && keyStr.length() >= 16) return true;
        else {
            int unsicher = JOptionPane.showConfirmDialog(null,"Das Passwort scheint unsicher, soll es dennoch verwendet werden?");
            if (unsicher == 0) {
                return true;
            }
            else return false;
        }
    }
}
