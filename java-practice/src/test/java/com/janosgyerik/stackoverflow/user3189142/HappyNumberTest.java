package com.janosgyerik.stackoverflow.user3189142;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HappyNumberTest {
    private boolean isHappy(int number) {
        Set<Integer> unique = new HashSet<>();

        while (unique.add(number)) {
            int value = 0;
            for (char c : String.valueOf(number).toCharArray()) {
                int digit = c - '0';
                value += digit * digit;
            }
            number = value;
        }

        return number == 1;
    }

    @Test
    public void test_19_Is_Happy() {
        assertTrue(isHappy(19));
    }

    @Test
    public void test_22_Is_NotHappy() {
        assertFalse(isHappy(22));
    }

    @Test
    public void testKnownHappy() {
        Set<Integer> happy = new HashSet<>();
        happy.addAll(Arrays.asList(1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100, 103, 109, 129, 130, 133, 139, 167, 176, 188, 190, 192, 193, 203, 208, 219, 226, 230, 236, 239, 262, 263, 280, 291, 293, 301, 302, 310, 313, 319, 320, 326, 329, 331, 338, 356, 362, 365, 367, 368, 376, 379, 383, 386, 391, 392, 397, 404, 409, 440, 446, 464, 469, 478, 487, 490, 496, 536, 556, 563, 565, 566, 608, 617, 622, 623, 632, 635, 637, 638, 644, 649, 653, 655, 656, 665, 671, 673, 680, 683, 694, 700, 709, 716, 736, 739, 748, 761, 763, 784, 790, 793, 802, 806, 818, 820, 833, 836, 847, 860, 863, 874, 881, 888, 899, 901, 904, 907, 910, 912, 913, 921, 923, 931, 932, 937, 940, 946, 964, 970, 973, 989, 998, 1000));
        for (int i = 0; i < 1000; ++i) {
            assertEquals(happy.contains(i), isHappy(i));
        }
    }
}
