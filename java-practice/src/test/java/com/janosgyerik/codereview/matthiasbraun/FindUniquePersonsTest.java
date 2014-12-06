package com.janosgyerik.codereview.matthiasbraun;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Person {
    private String name;
    private String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", id=" + id + "]";
    }
}

public class FindUniquePersonsTest {
    @Test
    public void test() {
        List<Person> people = Arrays.asList(
                new Person("Michael", "1"),
                new Person("Theresa", "2"),
                new Person("Jack", "2"),
                new Person("Nicole", "3"),
                new Person("Sandra", "3"));

        // Group persons by their ID
        Map<String, List<Person>> peopleById = people.stream().collect(Collectors.groupingBy(Person::getId));

        // Print out groups of people that share one ID
        peopleById
                .values()
                .parallelStream()
                .filter(peopleWithSameId -> peopleWithSameId.size() > 1)
                .forEach(peopleWithSameId -> System.out.println("People with identical IDs: " + peopleWithSameId));

        people.stream()
                .collect(Collectors.groupingBy(Person::getId))
                .forEach((id, peopleWithSameId) -> {
                    if (peopleWithSameId.size() > 1) {
                        System.out.printf("People with identical ID %s are : %s%n", id, peopleWithSameId);
                    }
                });
        
        people.stream()
                .collect(Collectors.groupingBy(Person::getId))
                .values().stream()
                .filter(peopleWithSameId -> peopleWithSameId.size() > 1)
                .forEach(peopleWithSameId -> System.out.println("People with identical IDs: " + peopleWithSameId));
    }
}
