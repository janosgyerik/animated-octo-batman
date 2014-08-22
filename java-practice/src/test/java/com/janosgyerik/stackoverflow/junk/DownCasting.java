package com.janosgyerik.stackoverflow.junk;

public class DownCasting {
    static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Person person = new Person("Jack");
        Object obj = person;
        System.out.println(person);
        System.out.println(obj);
        System.out.println(person == obj);
        System.out.println("image.png".split("\\.").length);
    }
}
