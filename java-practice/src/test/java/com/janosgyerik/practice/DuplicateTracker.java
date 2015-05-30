package com.janosgyerik.practice;

import java.util.*;

public class DuplicateTracker<T> {

    private Map<T, Set<T>> dups = new HashMap<>();

    public void add(T item1, T item2) {
        Set<T> dups1 = dups.get(item1);
        Set<T> dups2 = dups.get(item2);

        if (dups1 != null && dups2 != null) {
            dups1.addAll(dups2);
            for (T item : dups2) {
                dups.put(item, dups1);
            }
        } else if (dups1 != null) {
            dups1.add(item2);
            dups.put(item2, dups1);
        } else if (dups2 != null) {
            dups2.add(item1);
            dups.put(item1, dups2);
        } else {
            dups1 = new HashSet<>();
            dups1.add(item1);
            dups1.add(item2);
            dups.put(item1, dups1);
            dups.put(item2, dups1);
        }
    }

    public Collection<Set<T>> getDuplicates() {
        return new HashSet<>(dups.values());
    }
}
