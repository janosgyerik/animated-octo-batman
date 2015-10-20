package com.janosgyerik.stackoverflow.pairwisereverse;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedListTest {

    private int[] single = new int[] {
            12
    };

    private int[] twoElems = new int[] {
            12, 34
    };
    private int[] twoElemsReversedPairs = new int[] {
            34, 12
    };

    private int[] threeElems = new int[] {
            12, 34, 78
    };
    private int[] threeElemsReversedPairs = new int[] {
            34, 12, 78
    };

    private int[] evenElems = new int[] {
            12, 34, 78, 124
    };
    private int[] evenElemsReversedPairs = new int[] {
            34, 12, 124, 78
    };

    private int[] oddElems = new int[] {
            12, 34, 78, 45, 90
    };
    private int[] oddElemsReversedPairs = new int[] {
            34, 12, 45, 78, 90
    };

    @Test
    public void test() {
        assertEquals(true, doReverse(new SinglyLinkedList(single), single, single));

        assertEquals(true, doReverse(new SinglyLinkedList(twoElems), twoElems, twoElemsReversedPairs));

        assertEquals(true, doReverse(new SinglyLinkedList(threeElems), threeElems, threeElemsReversedPairs));

        assertEquals(true, doReverse(new SinglyLinkedList(oddElems), oddElems, oddElemsReversedPairs));

        assertEquals(true, doReverse(new SinglyLinkedList(evenElems), evenElems, evenElemsReversedPairs));

    }

    private boolean isFirstCorrect(SinglyLinkedList linkedList, int value) {
        return linkedList.getFirst().getData() == value;
    }

    private boolean isLastCorrect(SinglyLinkedList linkedList, int value) {
        return linkedList.getLast().getData() == value;
    }

    private boolean doReverse(SinglyLinkedList list, int[] origArray, int[] reversedArray) {
        boolean checkOrig = isListCorect(list, origArray);
        if (!checkOrig) {
            return false;
        }
        list.reversePairs();

        return isFirstCorrect(list, reversedArray[0]) && isLastCorrect(list,
                reversedArray[reversedArray.length - 1]) && isListCorect(list, reversedArray);
    }

    private boolean isListCorect(SinglyLinkedList list, int[] array) {
        Node curr = list.getFirst();
        int arrayIndex = 0;
        while (curr != null) {
            if (array[arrayIndex] != curr.getData()) {
                return false;
            }
            curr = curr.getNext();
            arrayIndex++;
        }
        return true;
    }

    @Test
    public void test_12_reversed_is_12() {
        assertArrayEquals(new int[]{12}, reversed(12));
    }

    @Test
    public void test_12_34_reversed_is_34_12() {
        assertArrayEquals(new int[]{34, 12}, reversed(12, 34));
    }

    @Test
    public void test_12_34_78_reversed_is_34_12() {
        assertArrayEquals(new int[]{34, 12, 78}, reversed(12, 34, 78));
    }

    @Test
    public void test_12_34_78_124_reversed_is_34_12_124_78() {
        assertArrayEquals(new int[]{34, 12, 124, 78}, reversed(12, 34, 78, 124));
    }

    @Test
    public void test_1_2_3_4_reversed_is_2_1_4_3() {
        assertArrayEquals(new int[]{2, 1, 4, 3}, reversed(1, 2, 3, 4));
    }

    @Test
    public void test_empty_reversed_is_empty() {
//        assertArrayEquals(new int[]{}, reversed());
    }

    private int[] reversed(int... nums) {
        SinglyLinkedList list = new SinglyLinkedList(nums);
        list.reversePairs();
        return toArray(list);
    }

    private int[] toArray(SinglyLinkedList list) {
        List<Integer> values = new ArrayList<>();

        Node node = list.getFirst();
        while (node != null) {
            values.add(node.getData());
            node = node.getNext();
        }

        int[] arr = new int[values.size()];
        for (int index = 0; index < arr.length; ++index) {
            arr[index] = values.get(index);
        }
        return arr;
    }

}