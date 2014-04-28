package com.janosgyerik.codingame.easy;

import java.util.Scanner;

public class AsciiArt {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        in.nextLine();
        String text = in.nextLine();
        String[] ascii = new String[height];
        for (int i = 0; i < ascii.length; ++i) {
            ascii[i] = in.nextLine();
        }
        for (String line : ascii) {
            for (char c : text.toUpperCase().toCharArray()) {
                int start = (c - 'A') * width;
                if (start > line.length() || start < 0) {
                    start = line.length() - width;
                }
                String segment = line.substring(start, start + width);
                System.out.print(segment);
            }
            System.out.println();
        }
    }
}
