package com.janosgyerik.obscure;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class Dto {
    private final String name;
    private final int number;
    private final Set<Integer> other;

    public Dto(String name, Integer number, Set<Integer> other) {
        this.name = name;
        this.number = number;
        this.other = other;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Set getOther() {
        return other;
    }

    @Override
    public String toString() {
        return "Dto{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", other=" + other +
                '}';
    }
}

class DtoComparator implements Comparator<Dto> {
    @Override
    public int compare(Dto o1, Dto o2) {
        int compare;
        if ((compare = o1.getName().compareTo(o2.getName())) != 0) {
            return compare;
        }
        if ((compare = Integer.compare(o1.getNumber(), o2.getNumber())) != 0) {
            return compare;
        }
        return o1.getOther().toString().compareTo(o2.getOther().toString());
    }
}

public class ComparatorTest {
    @Test(expected = NullPointerException.class)
    public void testCompareIntegerWithNull() {
        new Integer(3).compareTo(null);
    }

    @Test(expected = NullPointerException.class)
    public void testCompareStringWithNull() {
        "hello".compareTo(null);
    }

    @Test
    public void testSorting() {
        List<Dto> list = new ArrayList<>();
        Dto item1 = new Dto("Jack", 5, new HashSet<>(1, 2));
        Dto item2 = new Dto("Jack", 4, new HashSet<>(1, 2));
        Dto item3 = new Dto("Mike", 1, new HashSet<>(1, 2));
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Collections.sort(list, new DtoComparator());
        assertEquals("[Dto{name='Jack', number=4, other=[]}, Dto{name='Jack', number=5, other=[]}, Dto{name='Mike', number=1, other=[]}]",
                list.toString());
    }
}
