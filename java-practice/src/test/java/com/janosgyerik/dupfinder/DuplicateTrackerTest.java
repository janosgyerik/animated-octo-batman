package com.janosgyerik.dupfinder;

import org.junit.Test;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DuplicateTrackerTest {

    private String toString(Collection<Set<String>> duplicates) {
        Collection<String> sorted = duplicates.stream().map(set -> new TreeSet<>(set).toString())
                .collect(Collectors.toCollection(TreeSet::new));
        return sorted.toString();
    }

    @Test
    public void test_a_b_plus_c_d() {
        DuplicateTracker<String> tracker = new DuplicateTracker<>();
        tracker.add("a", "b");
        tracker.add("c", "d");
        assertEquals("[[a, b], [c, d]]", toString(tracker.getDuplicates()));
    }

    @Test
    public void test_a_b_plus_c_d_plus_a_x() {
        DuplicateTracker<String> tracker = new DuplicateTracker<>();
        tracker.add("a", "b");
        tracker.add("c", "d");
        tracker.add("a", "x");
        assertEquals("[[c, d], [a, b, x]]", tracker.getDuplicates().toString());
    }

    @Test
    public void test_a_b_plus_c_d_plus_b_d() {
        DuplicateTracker<String> tracker = new DuplicateTracker<>();
        tracker.add("a", "b");
        tracker.add("c", "d");
        tracker.add("b", "d");
        assertEquals("[[a, b, c, d]]", tracker.getDuplicates().toString());
    }

    @Test
    public void test_a_b_plus_c_d_plus_a_c() {
        DuplicateTracker<String> tracker = new DuplicateTracker<>();
        tracker.add("a", "b");
        tracker.add("c", "d");
        tracker.add("a", "c");
        assertEquals("[[a, b, c, d]]", tracker.getDuplicates().toString());
    }

    @Test
    public void test_a_b_plus_c_d_plus_e_f_plus_a_f_plus_d_e() {
        DuplicateTracker<String> tracker = new DuplicateTracker<>();
        tracker.add("a", "b");
        tracker.add("c", "d");
        tracker.add("e", "f");
        tracker.add("a", "f");
        tracker.add("d", "e");
        assertEquals("[[a, b, c, d, e, f]]", tracker.getDuplicates().toString());
    }
}
