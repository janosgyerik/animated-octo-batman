package com.janosgyerik.codereview.arunprakash;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BalancedBracketsTest {
    private static boolean isOpeningBracket(char c) {
        return "({[".indexOf(c) > -1;
    }

    private static boolean isClosingBracket(char c) {
        return ")}]".indexOf(c) > -1;
    }

    private static boolean isMatchingBrackets(char opening, char closing) {
        switch (opening) {
            case '(': return closing == ')';
            case '{': return closing == '}';
            case '[': return closing == ']';
            default: return false;
        }
    }

    static boolean isBalanced(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (isOpeningBracket(c)) {
                stack.push(c);
            } else if (isClosingBracket(c)) {
                if (stack.isEmpty() || !isMatchingBrackets(stack.pop(), c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void testEmpty() {
        assertTrue(isBalanced(""));
    }

    @Test
    public void testBalancedWithoutBrackets() {
        assertTrue(isBalanced("hello"));
        assertTrue(isBalanced("blah"));
    }

    @Test
    public void testUnbalancedSingleChar() {
        assertFalse(isBalanced("("));
        assertFalse(isBalanced(")"));
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced("}"));
        assertFalse(isBalanced("["));
        assertFalse(isBalanced("]"));
    }

    @Test
    public void testNonBracketIgnored() {
        assertTrue(isBalanced("he(ll)o"));
        assertTrue(isBalanced("he(llo)"));
        assertTrue(isBalanced("(he)(()llo)"));
        assertFalse(isBalanced("(he)(()llo"));
        assertFalse(isBalanced("(he)()llo)"));
    }

    @Test
    public void testComplicated() {
        assertTrue(isBalanced("()()(){}{}[]"));
        assertTrue(isBalanced("((()))"));
        assertTrue(isBalanced("(({()}{}[{hello}]))"));
        String balanced = "(({()}{}[{}]))";
        for (int i = 0; i < balanced.length(); ++i) {
            assertFalse(isBalanced(replaceCharAt(balanced, i, 'x')));
        }
    }

    private String replaceCharAt(String string, int i, char c) {
        return string.substring(0, i) + c + string.substring(i + 1);
    }

    @Test
    public void testReplaceCharAt() {
        String balanced = "(({()}{}[{}]))";
        assertEquals("x({()}{}[{}]))", replaceCharAt(balanced, 0, 'x'));
        assertEquals("(({x)}{}[{}]))", replaceCharAt(balanced, 3, 'x'));
        assertEquals("(({(x}{}[{}]))", replaceCharAt(balanced, 4, 'x'));
        assertEquals("(({()x{}[{}]))", replaceCharAt(balanced, 5, 'x'));
        assertEquals("(({()}{}[{}])x", replaceCharAt(balanced, balanced.length() - 1, 'x'));
    }
}
