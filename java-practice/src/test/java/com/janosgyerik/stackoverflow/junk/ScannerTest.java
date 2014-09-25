package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ScannerTest {
    @Test
    public void testSecondWordSingleLine() {
        Scanner scanner = new Scanner("hello hi there");
        scanner.next();
        assertEquals("hi", scanner.next());
    }

    @Test
    public void testSecondWordMultiLine() {
        Scanner scanner = new Scanner("hello\nhi there");
        scanner.next();
        assertEquals("hi", scanner.next());
    }

    @Test
    public void testSecondWordMultiLineWithNextLineFirst() {
        Scanner scanner = new Scanner("hello\nhi there");
        scanner.nextLine();
        assertEquals("hi", scanner.next());
    }

    static void f(int x, double y)
    {
        System.out.printf("%d, %f",x,y);
    }

    static void f(double x, int y)
    {
        System.out.printf("%f, %d", x, y);
    }


    public static void main(String args[]) {

        f(1d, 2);

    }

    @Test
    public void test2() {
        CollectionClassifier klassifier = new CollectionClassifier();
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>()
        };
        for (Collection<?> c : collections) {
            System.out.println(klassifier.classify(c));
        }
    }

    class CollectionClassifier {
        public String classify(Set<?> s) {
            return "Set";
        }
        public String classify(Collection<?> c) {
            return "Unknown Collection";
        }
    }

    @Test
    public void testCompare() {
        assertEquals(-1, Integer.compare(4, 5));
        assertEquals(0, Integer.compare(5, 5));
        assertEquals(1, Integer.compare(5, 1));
    }
}
