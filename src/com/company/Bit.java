package com.company;

import javax.swing.*;

public class Bit {

    public static int bitStraerke() {
        int bit = JOptionPane.showOptionDialog(null,"Wähle bitte aus wie stark die Bittiefe sein soll " +
                        "\n(Da die NSA AES-256Bit knacken will gibts für hardcore Enthustiasten extra 512Bit und sogar 1024Bit)", "Auswahl",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"256Bit", "512Bit", "1024Bit"},"256Bit");
        switch (bit) {
            case 0:
                bit = 256;
                break;
            case 1:
                bit = 512;
                break;
            case 2:
                bit = 1024;
                break;
            default:
                bit = 256;
        }
        return bit;
    }
}
