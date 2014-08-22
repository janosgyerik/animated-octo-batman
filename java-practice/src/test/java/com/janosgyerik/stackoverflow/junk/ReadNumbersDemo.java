package com.janosgyerik.stackoverflow.junk;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class ReadNumbersDemo {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("/tmp/numbers.txt"));
        List<Double> numbers = new ArrayList<Double>();
        while (scanner.hasNextDouble()) {
            double num = scanner.nextDouble();
            numbers.add(num);
            //System.out.println("num=" + num);
        }
        ListIterator<Double> iterator = numbers.listIterator(numbers.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }
}
