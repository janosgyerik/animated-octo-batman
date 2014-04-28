package com.janosgyerik.codingame.easy;

import java.util.*;
import java.io.*;
import java.math.*;

public class Temperatures {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		if (n == 0) {
			System.out.println("0");
			return;
		}
		int closest = in.nextInt();
		for (int i = 1; i < n; i++) {
		    int x = in.nextInt();
		    if (closest > 0) {
    		    if (Math.abs(x) < closest) {
    	            closest = x;
    		    }
		    } else if (Math.abs(x) <= Math.abs(closest)) {
		        closest = x;
		    }
		}
		System.out.println(closest);
	}
}
