package com.company;

import javax.swing.*;

public class Bit {

    public static int bitStraerke() {
        int bit = JOptionPane.showOptionDialog(null,"Wähle bitte aus wie stark die Bittiefe sein soll " +
                        "\n(Ein sicheres Passwort ist ab 256Bit sinnvoller als die Bittiefe)", "Auswahl",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"128Bit", "256Bit", "512Bit"},"256Bit");
        switch (bit) {
            case 0:
                bit = 128;
                break;
            case 1:
                bit = 256;
                break;
            case 2:
                bit = 512;
                break;
            default:
                bit = 256;
        }
        return bit;
    }
}
