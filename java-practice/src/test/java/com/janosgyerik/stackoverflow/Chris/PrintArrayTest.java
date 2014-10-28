package com.janosgyerik.stackoverflow.Chris;

import java.util.Scanner;

class p1 {
    public static void main(String[] args) {
        int[] arr1 = new int[10]; //all these arrays will be passed to the same printArray1 function.
        int[] arr4 = new int[10]; //I'll initialize all of these later.
        int[] arr5 = new int[50];
        int[] arr7 = new int[265];
        int size;

        initialize(arr1);
        printArray1(arr1);

    }

    public static int[] initialize(int[] arr1) {
        for (int i = 0; i < arr1.length; i++) {
            Scanner console = new Scanner(System.in);
            System.out.print("Input Integer " + (i + 1) + " : ");
            arr1[i] = console.nextInt();
        }
        return arr1;
    }

    public static void printArray1(int[] arr1) {
        System.out.println("\nContents of array are:");

        for (int i = 0; i < arr1.length; i++)
            System.out.println("Array[" + (i + 1) + "] = " + arr1[i]);    // I put in specific paramaters to print
        //how can I make it an unspecific method?
    }

}

public class PrintArrayTest {
}
