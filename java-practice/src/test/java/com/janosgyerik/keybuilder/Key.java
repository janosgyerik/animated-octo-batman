package com.janosgyerik.keybuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Key {
    private final int hashCode;

    private Key(int hashCode) {
        this.hashCode = hashCode;
    }

    public static class Builder {
        List<Object> components = new ArrayList<>();

        public Builder addComponent(Object obj) {
            components.add(obj);
            return this;
        }

        public Key build() {
            int hashCode = 0;
            return new Key(hashCode);
        }
    }

    private static class Example {
        int number;
        Double precision;
        String name;
        Set<Integer> set;
        List<Double> list;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Example example = (Example) o;

            if (number != example.number) {
                return false;
            }
            if (list != null ? !list.equals(example.list) : example.list != null) {
                return false;
            }
            if (name != null ? !name.equals(example.name) : example.name != null) {
                return false;
            }
            if (precision != null ? !precision.equals(example.precision) : example.precision != null) {
                return false;
            }
            if (set != null ? !set.equals(example.set) : example.set != null) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = number;
            result = 31 * result + (precision != null ? precision.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (set != null ? set.hashCode() : 0);
            result = 31 * result + (list != null ? list.hashCode() : 0);
            return result;
        }
    }
}
