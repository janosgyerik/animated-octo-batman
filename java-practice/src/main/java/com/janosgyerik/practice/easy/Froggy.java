package com.janosgyerik.practice.easy;

public class Froggy {
    public int submit(int x, int y, int d) {
        return (y - x) / d + ((y - x) % d > 0 ? 1 : 0);
    }
}
