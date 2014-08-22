package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.util.Arrays;

public class VarargsTest {
    private void method1(String name, String ...cols) {
        System.out.println("Name = " + name);
        System.out.println("Cols = " + Arrays.asList(cols));
    }

    private void method2(String name, int...nums) {
        System.out.println("Name = " + name);
        System.out.println("Nums = " + nums[0] + " " + nums[1]);
    }

    private void method3(String name, boolean flag, int...nums) {
        System.out.println("Name = " + name);
        System.out.println("Nums = " + nums[0] + " " + nums[1]);
    }

    private void method4(String ...cols) {
        if (cols == null || cols.length == 0) {
            throw new IllegalArgumentException("Table without columns? Get out here...");
        }
        System.out.println(Arrays.asList(cols));
    }

    private void method5(String ...cols) {
        if (cols == null || cols.length == 0) {
            throw new IllegalArgumentException("Table without columns? Get out here...");
        }
        System.out.println(Arrays.asList(cols));
    }

    @Test
    public void testMethod1() {
        method1("jack", "field1", "field2");
    }

    @Test
    public void testMethod2() {
        method2("jack", 3, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMethod4() {
        method4();
    }

    @Test
    public void testMethod4_OK() {
        method4("hello");
    }
}
