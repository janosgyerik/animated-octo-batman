package com.janosgyerik.practice.codility.so;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TransformStringTest {

    private int ladderLength_orig(String start, String end, Set<String> dict) {
        dict.add(end);
        Stack<String> wordSearch = new Stack<String>();
        Stack<Integer> lengthCount = new Stack<Integer>();
        String analyzing = start;
        int curCount = 1;
        while (true) {
            if (analyzing.equals(end)) {
                return curCount;
            }
            for (String item : dict.toArray(new String[dict.size()])) {
                if (isSingleCharDiff(analyzing, item)) {
                    dict.remove(item);
                    lengthCount.add(curCount + 1);
                    wordSearch.add(item);
                }
            }
            if (wordSearch.isEmpty()) {
                break;
            }
            analyzing = wordSearch.pop();
            curCount = lengthCount.pop();
        }
        return 0;
    }

    private int ladderLength_orig_improved(String start, String end, HashSet<String> dict) {
        Deque<String> wordSearch = new ArrayDeque<String>();
        Deque<Integer> lengthCount = new ArrayDeque<Integer>();
        wordSearch.add(start);
        lengthCount.add(1);
        while (!wordSearch.isEmpty()) {
            String analyzing = wordSearch.poll();
            int curCount = lengthCount.poll();
            if (analyzing.equals(end)) {
                return curCount;
            }
            for (int j = 0; j < analyzing.length(); j++) {
                for (String item : dict.toArray(new String[dict.size()])) {
                    if (isSingleCharDiff(analyzing, item)) {
                        dict.remove(item);
                        lengthCount.add(curCount + 1);
                        wordSearch.add(item);
                    }
                }
            }
        }
        return 0;
    }

    private static int ladderLength_orig_real(String start, String end, HashSet<String> dict) {
        Deque<String> wordSearch = new ArrayDeque<String>();
        Deque<Integer> lengthCount = new ArrayDeque<Integer>();
        wordSearch.add(start);
        lengthCount.add(1);
        while (!wordSearch.isEmpty()) {
            String analyzing = wordSearch.poll();
            int curCount = lengthCount.poll();
            if (analyzing.equals(end)) {
                return curCount;
            }
            for (int j = 0; j < analyzing.length(); j++) {
                for (char i = 'a'; i <= 'z'; i++) {
                    char[] possibleMatch = analyzing.toCharArray();
                    possibleMatch[j] = i;
                    String checkMatch = new String(possibleMatch);
                    if (dict.contains(checkMatch)) {
                        dict.remove(checkMatch);
                        lengthCount.add(curCount + 1);
                        wordSearch.add(checkMatch);
                    }
                }
            }
        }
        return 0;
    }

    private int shortestTransformLength(String start, String end, Set<String> dict, Set<String> seen) {
        if (start.equals(end)) {
            return 1;
        }
        int shortest = dict.size() + 1;
        for (String item : dict) {
            if (isSingleCharDiff(start, item)) {
                Set<String> dict2 = new HashSet<String>(dict);
                dict2.remove(item);
                Set<String> seen2 = new HashSet<String>(seen);
                seen2.add(item);
                int length = 1 + shortestTransformLength(item, end, dict2, seen2);
                if (length > 0) {
                    shortest = Math.min(shortest, length);
                }
            }
        }
        return shortest < dict.size() ? shortest : -1;
    }

    private int shortestTransformLength2(String start, String end, Set<String> dict) {
        Set<String> dict2 = new HashSet<String>(dict);
        dict2.add(end);
        return shortestTransformLength(start, end, dict2, new HashSet<String>());
    }

    private boolean isSingleCharDiff(String start, String end) {
        int count = 0;
        for (int i = 0; i < start.length(); ++i) {
            if (start.charAt(i) != end.charAt(i)) {
                if (++count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    @Test
    public void testSingleCharDiff() {
        Assert.assertTrue(isSingleCharDiff("hot", "dot"));
        Assert.assertTrue(isSingleCharDiff("hot", "hxt"));
        Assert.assertTrue(isSingleCharDiff("hot", "hog"));
    }

    @Test
    public void testNotSingleCharDiff() {
        Assert.assertFalse(isSingleCharDiff("hot", "dog"));
        Assert.assertFalse(isSingleCharDiff("hot", "hot"));
        Assert.assertFalse(isSingleCharDiff("hot", "abc"));
    }

    @Test
    public void testExampleLength1() {
        HashSet<String> dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        Assert.assertEquals(1, shortestTransformLength("hit", "hit", dict));
    }

    @Test
    public void testExampleLength2() {
        HashSet<String> dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        Assert.assertEquals(2, shortestTransformLength("hit", "hot", dict));
    }

    @Test
    public void testExampleLength3() {
        HashSet<String> dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        Assert.assertEquals(3, shortestTransformLength("hit", "dot", dict));
    }

    @Test
    public void testExampleLength4() {
        HashSet<String> dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        Assert.assertEquals(4, shortestTransformLength("hit", "dog", dict));
    }

    @Test
    public void testExampleLength5() {
        HashSet<String> dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        Assert.assertEquals(5, shortestTransformLength("hit", "cog", dict));
    }

    //@Test
    public void testOtherExamples() {
        HashSet<String> dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        Assert.assertEquals(3, shortestTransformLength("hit", "lot", dict));
        dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log", "hat", "tat", "cat", "cag", "hut", "cut", "cud", "cod"));
        // "hit" -> "hot" -> "dot" -> "dog" -> "cog"
        System.out.println(dict);
//        System.out.println(Arrays.asList(dict.toArray(new String[dict.size()])));
        Assert.assertEquals(5, shortestTransformLength("hit", "cog", dict));
    }

    @Test
    public void testExampleLengthImpossible() {
        HashSet<String> dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        Assert.assertEquals(0, shortestTransformLength("hit", "abc", dict));
    }

    private int shortestTransformLength(String start, String end, HashSet<String> dict) {
        return ladderLength_orig(start, end, dict);
//        HashSet<String> dict2 = new HashSet<String>(dict);
//        dict2.add(end);
//        return ladderLength_orig(start, end, dict2);
//        return shortestTransformLength2(start, end, dict);
    }
}
