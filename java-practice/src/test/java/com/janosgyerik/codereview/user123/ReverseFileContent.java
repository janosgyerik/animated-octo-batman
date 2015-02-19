package com.janosgyerik.codereview.user123;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReverseFileContent {
    public void orig() throws FileNotFoundException {
        File file = new File("/tmp/numbers.txt");
        Scanner scanner = new Scanner(file);

        LinkedList<String> lines = new LinkedList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();

        Iterator iterator = lines.descendingIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test() throws FileNotFoundException {
        orig();
        System.out.println("hello");
    }
}
