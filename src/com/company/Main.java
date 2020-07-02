package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                (new Window()).setVisible(true);
            }
        });
    }

    public static String[][] process (String[][] arr) {
        CombSort.countVowelLetter(arr);
        return arr;
    }
}
