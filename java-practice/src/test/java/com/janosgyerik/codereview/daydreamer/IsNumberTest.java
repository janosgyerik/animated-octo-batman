package com.janosgyerik.codereview.daydreamer;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsNumberTest {

    private boolean isNumber(String toTest) {
        //-?(\\.?\\d+)|(\\d+\\.\\d+)|(\\d+\\.)
        //-?(\\d*\\.?\\d*)
        //-?(\d*\.?\d+)|(\d+\.?\d*)
        return toTest.matches("[+-]?(\\d*\\.?\\d+)|(\\d+\\.?\\d*)");
    }

    private boolean isNumberOrig(String toTest) {
        boolean isNegativeFoundAlready = false;
        boolean isDecimalPointFoundAlready = false;
        for (int i = 0; i < toTest.length(); i++) {
            if (!"0123456789-.".contains(new String(new char[]{toTest.charAt(i)}))) {
                return false;
            } else {
                if ('-' == toTest.charAt(i) && i != 0) {
                    return false;
                }
                if ('-' == toTest.charAt(i) && (i == toTest.length() - 1)) {
                    return false;
                }

                if ('-' == toTest.charAt(i) && isNegativeFoundAlready) {
                    return false;
                }
                if ('-' == toTest.charAt(i)) {
                    isNegativeFoundAlready = true;
                }
                if ('.' == toTest.charAt(i) && isDecimalPointFoundAlready) {
                    return false;
                }
                if ('.' == toTest.charAt(i)) {
                    isDecimalPointFoundAlready = true;
                }
                if ('.' == toTest.charAt(i) && (i == toTest.length() - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testValidInteger() {
        assertTrue(isNumber("123"));
    }

    @Test
    public void testValidStartsWithDecimal() {
        assertTrue(isNumber(".123"));
    }

    @Test
    public void testValidEndsWithDecimal() {
        assertTrue(isNumber("123."));
    }

    @Test
    public void testValidNegativeStartsWithDecimal() {
        assertTrue(isNumber("-.123"));
    }

    @Test
    public void testValidPlusStartsWithDecimal() {
        assertTrue(isNumber("-.123"));
    }

    @Test
    public void testValidDecimal() {
        assertTrue(isNumber("1.23"));
    }

    @Test
    public void testValidMinusZero() {
        assertTrue(isNumber("-0"));
    }

    @Test
    public void testValidDecimalLooooong() {
        assertTrue(isNumber("1.2311111111111111111111111111111111111111111"));
    }

    @Test
    public void testNotValid() {
        assertFalse(isNumber("-."));
        assertFalse(isNumber(".-"));
        assertFalse(isNumber("12.34.56"));
        assertFalse(isNumber("-"));
        assertFalse(isNumber("12$%^&*#"));
        assertFalse(isNumber("hello"));
        assertFalse(isNumber("   "));
        //assertFalse(isNumber(""));
    }
}

