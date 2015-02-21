package com.janosgyerik.codereview.Legato;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BeautifulStringsTest {

    private int computeMaxBeauty(String text) {
        String sanitized = text.replaceAll("[^a-zA-Z]", "").toLowerCase();

        int[] counts = new int[26];
        for (int i = 0; i < sanitized.length(); ++i) {
            ++counts[sanitized.charAt(i) - 'a'];
        }

        Arrays.sort(counts);

        int sum = 0;
        for (int i = 25; i >= 0 && counts[i] > 0; --i) {
            sum += counts[i] * (i + 1);
        }
        return sum;
    }

    @Test
    public void test_ABbCcc() {
        assertEquals(152, computeMaxBeauty("ABbCcc"));
    }

    @Test
    public void test_This_is_from_Facebook_Hacker_Cup_2013_() {
        assertEquals(551, computeMaxBeauty("This is from Facebook Hacker Cup 2013."));
    }

    @Test
    public void test_Ignore_punctuation__please___() {
        assertEquals(491, computeMaxBeauty("Ignore punctuation, please :)"));
    }

    @Test
    public void test_Sometimes_test_cases_are_hard_to_make_up_() {
        assertEquals(729, computeMaxBeauty("Sometimes, test cases are hard to make up."));
    }

    @Test
    public void test_CodeReview_is_love__CodeReview_is_life_() {
        assertEquals(724, computeMaxBeauty("CodeReview is love. CodeReview is life."));
    }
}
