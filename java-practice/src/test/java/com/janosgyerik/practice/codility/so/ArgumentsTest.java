package com.janosgyerik.practice.codility.so;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

abstract class Arguments {
    public static int requirePositive(final int value) throws IllegalArgumentException {
        return requirePositive(value, "value");
    }

    public static int requirePositive(final int value, final String name) throws IllegalArgumentException {
        //Objects.requireNonNull(name);
        if (value <= 0) {
            throw new IllegalArgumentException("the " + name + " must be positive: " + value);
        }
        return value;
    }

    public static int requireNegative(final int value) throws IllegalArgumentException {
        return requireNegative(value, "value");
    }

    public static int requireNegative(final int value, final String name) throws IllegalArgumentException {
        //Objects.requireNonNull(name);
        if (value >= 0) {
            throw new IllegalArgumentException("the " + name + " must be negative: " + value);
        }
        return value;
    }

    public static int requirePositiveOrZero(final int value) throws IllegalArgumentException {
        return requirePositiveOrZero(value, "value");
    }

    public static int requirePositiveOrZero(final int value, final String name) throws IllegalArgumentException {
        //Objects.requireNonNull(name);
        if (value < 0) {
            throw new IllegalArgumentException("the " + name + " must be positive or zero: " + value);
        }
        return value;
    }

    public static int requireNegativeOrZero(final int value) throws IllegalArgumentException {
        return requireNegativeOrZero(value, "value");
    }

    public static int requireNegativeOrZero(final int value, final String name) throws IllegalArgumentException {
        //Objects.requireNonNull(name);
        if (value > 0) {
            throw new IllegalArgumentException("the " + name + " must be negative or zero: " + value);
        }
        return value;
    }

    public static int requireInRange(final int value, final int lowInclusive, final int highExclusive) throws IllegalArgumentException {
        return requireInRange(value, lowInclusive, highExclusive, "value");
    }

    public static int requireInRange(final int value, final int lowInclusive, final int highExclusive, final String name) throws IllegalArgumentException {
        //Objects.requireNonNull(name);
        if (lowInclusive >= highExclusive) {
            throw new IllegalArgumentException("the lower inclusive bound is greater or equal to the higher exclusive bound: " + lowInclusive + " >= " + highExclusive);
        }
        if (value < lowInclusive || value >= highExclusive) {
            throw new IllegalArgumentException("the " + name + " was not in range: " + value + ", expected: [" + lowInclusive + ", " + highExclusive + ")");
        }
        return value;
    }

    public static int requireInRangeClosed(final int value, final int lowInclusive, final int highInclusive) throws IllegalArgumentException {
        return requireInRangeClosed(value, lowInclusive, highInclusive, "value");
    }

    public static int requireInRangeClosed(final int value, final int lowInclusive, final int highInclusive, final String name) throws IllegalArgumentException {
        //Objects.requireNonNull(name);
        if (lowInclusive > highInclusive) {
            throw new IllegalArgumentException("the lower inclusive bound is greater or equal to the higher inclusive bound: " + lowInclusive + " >= " + highInclusive);
        }
        if (value < lowInclusive || value > highInclusive) {
            throw new IllegalArgumentException("the " + name + " was not in range: " + value + ", expected: [" + lowInclusive + ", " + highInclusive + ")]");
        }
        return value;
    }

    public static int requireIndexInRange(final int index, final int lowInclusive, final int highExclusive) throws IndexOutOfBoundsException {
        if (lowInclusive >= highExclusive) {
            throw new IllegalArgumentException("the lower inclusive bound is greater or equal to the higher exclusive bound: " + lowInclusive + " >= " + highExclusive);
        }
        if (index < lowInclusive || index >= highExclusive) {
            throw new IndexOutOfBoundsException("the index was not in range: " + index + ", expected: [" + lowInclusive + ", " + highExclusive + ")");
        }
        return index;
    }

    public static int requireIndexInRangeClosed(final int index, final int lowInclusive, final int highInclusive) throws IndexOutOfBoundsException {
        if (lowInclusive > highInclusive) {
            throw new IllegalArgumentException("the lower inclusive bound is greater or equal to the higher inclusive bound: " + lowInclusive + " >= " + highInclusive);
        }
        if (index < lowInclusive || index > highInclusive) {
            throw new IndexOutOfBoundsException("the index was not in range: " + index + ", expected: [" + lowInclusive + ", " + highInclusive + "]");
        }
        return index;
    }
}

@Ignore
public class ArgumentsTest {
    static {
        assertTrue(true);
    }

    /** Arguments.requirePositive **/

    @Test
    public void testRequirePositive_int() {
        int result = Arguments.requirePositive(1);
        assertEquals(1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositive_intIAE1() {
        Arguments.requirePositive(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositive_intIAE2() {
        Arguments.requirePositive(-1);
    }

    @Test
    public void testRequirePositive_int_String() {
        int result = Arguments.requirePositive(1, "test");
        assertEquals(1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositive_int_StringIAE1() {
        Arguments.requirePositive(0, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositive_int_StringIAE2() {
        Arguments.requirePositive(-1, "test");
    }

    //@Test(expected = NullPointerException.class)
    public void testRequirePositive_int_StringNPE() {
        Arguments.requirePositive(1, null);
    }

    /** Arguments.requireNegative **/

    @Test
    public void testRequireNegative_int() {
        int result = Arguments.requireNegative(-1);
        assertEquals(-1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegative_intIAE1() {
        Arguments.requireNegative(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegative_intIAE2() {
        Arguments.requireNegative(1);
    }

    @Test
    public void testRequireNegative_int_String() {
        int result = Arguments.requireNegative(-1, "test");
        assertEquals(-1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegative_int_StringIAE1() {
        Arguments.requireNegative(0, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegative_int_StringIAE2() {
        Arguments.requireNegative(1, "test");
    }

    //@Test(expected = NullPointerException.class)
    public void testRequireNegative_int_StringNPE() {
        Arguments.requireNegative(-1, null);
    }

    /** Arguments.requirePositiveOrZero **/

    @Test
    public void testRequirePositiveOrZero_int1() {
        int result = Arguments.requirePositiveOrZero(0);
        assertEquals(0, result);
    }

    @Test
    public void testRequirePositiveOrZero_int2() {
        int result = Arguments.requirePositiveOrZero(1);
        assertEquals(1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositiveOrZero_intIAE() {
        Arguments.requirePositiveOrZero(-1);
    }

    @Test
    public void testRequirePositiveOrZero_int_String1() {
        int result = Arguments.requirePositiveOrZero(0, "test");
        assertEquals(0, result);
    }

    @Test
    public void testRequirePositiveOrZero_int_String2() {
        int result = Arguments.requirePositiveOrZero(1, "test");
        assertEquals(1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositiveOrZero_int_StringIAE() {
        Arguments.requirePositiveOrZero(-1, "test");
    }

    //@Test(expected = NullPointerException.class)
    public void testRequirePositiveOrZero_int_StringNPE() {
        Arguments.requirePositiveOrZero(0, null);
    }

    /** Arguments.requireNegativeOrZero **/

    @Test
    public void testRequireNegativeOrZero_int1() {
        int result = Arguments.requireNegativeOrZero(0);
        assertEquals(0, result);
    }

    @Test
    public void testRequireNegativeOrZero_int2() {
        int result = Arguments.requireNegativeOrZero(-1);
        assertEquals(-1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegativeOrZero_intIAE() {
        Arguments.requireNegativeOrZero(1);
    }

    @Test
    public void testRequireNegativeOrZero_int_String1() {
        int result = Arguments.requireNegativeOrZero(0, "test");
        assertEquals(0, result);
    }

    @Test
    public void testRequireNegativeOrZero_int_String2() {
        int result = Arguments.requireNegativeOrZero(-1, "test");
        assertEquals(-1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegativeOrZero_int_StringIAE() {
        Arguments.requireNegativeOrZero(1, "test");
    }

    //@Test(expected = NullPointerException.class)
    public void testRequireNegativeOrZero_int_StringNPE() {
        Arguments.requireNegativeOrZero(0, null);
    }

    /** Arguments.requireInRange **/

    @Test
    public void testRequireInRange_3args() {
        int result = Arguments.requireInRange(1, 1, 2);
        assertEquals(1, result);
        int result2 = Arguments.requireInRange(1, 0, 3);
        assertEquals(1, result2);
        int min = 10;
        int max = min * 2;
        assertEquals(min, Arguments.requireInRange(min, min, max));
        assertEquals(min, Arguments.requireInRange(min + 1, min, max));
        assertEquals(max - 1, Arguments.requireInRange(max - 1, min, max));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE1() {
        Arguments.requireInRange(1, 5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE2() {
        Arguments.requireInRange(1, 6, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE3() {
        Arguments.requireInRange(1, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE4() {
        Arguments.requireInRange(1, 2, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE5() {
        Arguments.requireInRange(1, 10, 20);
    }

    @Test
    public void testRequireInRange_4args() {
        int result = Arguments.requireInRange(1, 1, 2, "test");
        assertEquals(1, result);
        int result2 = Arguments.requireInRange(1, 0, 3, "test");
        assertEquals(1, result2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE1() {
        Arguments.requireInRange(1, 5, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE2() {
        Arguments.requireInRange(1, 6, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE3() {
        Arguments.requireInRange(1, 0, 1, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE4() {
        Arguments.requireInRange(1, 2, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE5() {
        Arguments.requireInRange(1, 10, 20, "test");
    }

    //@Test(expected = NullPointerException.class)
    public void testRequireInRange_4argsNPE() {
        Arguments.requireInRange(1, 0, 2, null);
    }

    /** Arguments.requireInRangeClosed **/

    @Test
    public void testRequireInRangeClosed_3args() {
        int result = Arguments.requireInRangeClosed(1, 1, 2);
        assertEquals(1, result);
        int result2 = Arguments.requireInRangeClosed(1, 0, 1);
        assertEquals(1, result2);
        int result3 = Arguments.requireInRangeClosed(1, 1, 1);
        assertEquals(1, result3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_3argsIAE1() {
        Arguments.requireInRangeClosed(1, 6, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_3argsIAE2() {
        Arguments.requireInRangeClosed(1, -4, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_3argsIAE3() {
        Arguments.requireInRangeClosed(1, 2, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_3argsIAE4() {
        Arguments.requireInRangeClosed(1, 20, 40);
    }

    @Test
    public void testRequireInRangeClosed_4args() {
        assertEquals(1, Arguments.requireInRangeClosed(1, 1, 2, "test"));
        int result2 = Arguments.requireInRangeClosed(1, 0, 1, "test");
        assertEquals(1, result2);
        int result3 = Arguments.requireInRangeClosed(1, 1, 1, "test");
        assertEquals(1, result3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_4argsIAE1() {
        Arguments.requireInRangeClosed(1, 6, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_4argsIAE2() {
        Arguments.requireInRangeClosed(1, -4, 0, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_4argsIAE3() {
        Arguments.requireInRangeClosed(1, 2, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_4argsIAE4() {
        Arguments.requireInRangeClosed(1, 20, 40, "test");
    }

    //@Test(expected = NullPointerException.class)
    public void testRequireInRangeClosed_4argsNPE() {
        Arguments.requireInRangeClosed(5, 2, 20, null);
    }

    /** Arguments.requireIndexInRange **/

    @Test
    public void testRequireIndexInRange() {
        int result = Arguments.requireIndexInRange(1, 1, 2);
        assertEquals(1, result);
        int result2 = Arguments.requireIndexInRange(1, 0, 3);
        assertEquals(1, result2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireIndexInRangeIAE1() {
        Arguments.requireIndexInRange(1, 5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireIndexInRangeIAE2() {
        Arguments.requireIndexInRange(1, 6, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeIAE3() {
        Arguments.requireIndexInRange(1, 0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeIAE4() {
        Arguments.requireIndexInRange(1, 2, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeIAE5() {
        Arguments.requireIndexInRange(1, 10, 20);
    }

    /** Arguments.requireIndexInRangeClosed **/

    @Test
    public void testRequireIndexInRangeClosed() {
        int result = Arguments.requireIndexInRangeClosed(1, 1, 2);
        assertEquals(1, result);
        int result2 = Arguments.requireIndexInRangeClosed(1, 0, 1);
        assertEquals(1, result2);
        int result3 = Arguments.requireIndexInRangeClosed(1, 1, 1);
        assertEquals(1, result3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireIndexInRangeClosedIAE1() {
        Arguments.requireIndexInRangeClosed(1, 6, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeClosedIAE2() {
        Arguments.requireIndexInRangeClosed(1, -4, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeClosedIAE3() {
        Arguments.requireIndexInRangeClosed(1, 2, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeClosedIAE4() {
        Arguments.requireIndexInRangeClosed(1, 20, 40);
    }
}

