package com.janosgyerik.codility.so;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AsciiArtDemo {
    public static void main(String args[]) throws FileNotFoundException {
        int width = 4;
        int height = 5;
        String text = "MANHATTAN";
        String z = " #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ###  # # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   #  ### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ##  # # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #        # # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #   ";
        String[] ascii = new String[height];
//        for (int i = 0; i < ascii.length; ++i) {
//            ascii[i] = in.nextLine();
//        }
        int piece = z.length() / height;
        for (int i = 0; i < height; ++i) {
            int start = i * piece;
            ascii[i] = z.substring(start, start + piece);
            System.out.println(ascii[i]);
        }
        for (String line : ascii) {
            for (char c : text.toCharArray()) {
                int start = (c - 'A') * width;
                String segment = line.substring(start, start + width);
                System.out.print(segment);
            }
            System.out.println();
        }
        Scanner in = new Scanner(new File("/tmp/input"));
        System.out.println(in.nextInt());
        System.out.println(in.nextInt());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
    }
}
