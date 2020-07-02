package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CombSort {

    public static String [][] arrOut ;

    public static void countVowelLetter(String[][] str) {
        int [] countVowelLetter = new int[str.length];

        Pattern vocalsEng = Pattern.compile("(?iu)[AaEeIiOoUu]");

        for (int i = 0; i < str.length; i++) {
            Matcher m = vocalsEng.matcher(str[i][0]);
            int vocalCounter = 0;
            while (m.find()) {
                vocalCounter++;
            }
            countVowelLetter[i] = vocalCounter;
        }
        combSort(str, countVowelLetter);
    }

    public static void combSort(String[][] str, int[] a) {
        for (int d = a.length - 2; d >= 0; d--) {
            for (int i = 0; i <= (a.length - 2) - d; i++) {
                if (a[i] > a[i + d + 1]) {
                    swap(a, i, i + d + 1);
                    swapStr(str, i, i + d + 1);
                }
                if(Integer.parseInt(String.valueOf(str[i][0].length())) > Integer.parseInt(String.valueOf(str[i + d + 1][0].length()))){
                    swapStr(str, i, i + d + 1);
                }
            }
        }




        for (int i = 0; i < a.length; i++) {
            arrOut = str;
        }
    }

    private static void swap(int[] a, int from, int to) {
        int temp = a[from];
        a[from] = a[to];
        a[to] = temp;
    }

    private static void swapStr(String[][] a, int from, int to) {
        String[] temp = a[from];
        a[from] = a[to];
        a[to] = temp;
    }

}
