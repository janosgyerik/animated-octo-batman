package com.janosgyerik.codereview.scratch;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Scratch2 {
    public String everyNth(String str, int n) {
        String result = "";

        for (int i = 0; i <= str.length(); i++) {
            if (i % n == 0) {
                result = result + str.charAt(i);
            } else {
                result = result;
            }
        }
        return result;
    }

    public int getMaxN(int N, char digit) {
        for (int i = N - 1; i > 0; --i) {
            if (Integer.toString(i).indexOf(digit) == -1) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    @Test
    public void testGetMaxN() {
        assertEquals(119, getMaxN(123, '2'));
        assertEquals(122, getMaxN(123, '3'));
        assertEquals(99, getMaxN(123, '1'));
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoSuchElement() {
        getMaxN(0, '0');
    }

    @Test
    public void scratch4() {
//        Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((18|19|20|21)\\d\\d)");
        Pattern pattern = Pattern.compile("(((0[1-9]|[12][0-9]|3[01])/(0[13578]|1[02]))|((0[1-9]|[12][0-9]|30)/(0[469]|11)))/((1[89]|2[01])\\d\\d)");
//        assertTrue(pattern.matcher("28/02/2014").matches());
//        assertTrue(pattern.matcher("30/02/2014").matches()); // should be false
        assertTrue(pattern.matcher("31/03/2014").matches());
        assertTrue(pattern.matcher("30/04/2014").matches());
        assertFalse(pattern.matcher("31/04/2014").matches());
        assertTrue(pattern.matcher("31/05/2014").matches());
        assertTrue(pattern.matcher("30/06/2014").matches());
        assertFalse(pattern.matcher("31/06/2014").matches());
        assertTrue(pattern.matcher("31/07/2014").matches());
        assertTrue(pattern.matcher("31/08/2014").matches());
        assertTrue(pattern.matcher("30/09/2014").matches());
        assertFalse(pattern.matcher("31/09/2014").matches());
        assertTrue(pattern.matcher("31/10/2014").matches());
        assertTrue(pattern.matcher("30/11/2014").matches());
        assertFalse(pattern.matcher("31/11/2014").matches());
        assertTrue(pattern.matcher("31/12/2014").matches());

        assertFalse(pattern.matcher("32/12/2014").matches());
        assertFalse(pattern.matcher("31/13/2014").matches());
    }

    @Test
    public void scratch3() {
        new MyList<Integer>().addNode(5);
        new MyList<String>().addNode("hello");
        MyList<Object> list3 = new MyList<Object>();
        list3.addNode(3);
        list3.addNode("hello");
    }

    class MyList<T> {
        Node<T> head = null;

        public void addNode(T element) {
            head = new Node<T>(element);
        }
        //Other methods removed for simplicity

        public void myMethod() {
            MyList<T> newList = new MyList<T>();

            //Below code generate error: required: T, found: int
//            newList.addNode(5);  //How to add an int 5 here when it is expecting Type:T?
        }
    }

    class Node<T> {
        T element;

        public Node(T item) {
            element = item;
        }
        //Other methods removed for simplicity
    }
}
