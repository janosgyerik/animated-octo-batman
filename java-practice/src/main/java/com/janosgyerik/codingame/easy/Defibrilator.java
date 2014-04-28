package com.janosgyerik.codingame.easy;

import java.util.*;
import java.io.*;
import java.math.*;

public class Defibrilator {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		double lon = Double.parseDouble(in.nextLine().replace(",", "."));
		double lat = Double.parseDouble(in.nextLine().replace(",", "."));
		int n = in.nextInt();
		in.nextLine();
		
		String closest = null;
		double mdist = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
		    String line = in.nextLine();
		    String[] parts = line.split(";");
		    double zlon = Double.parseDouble(parts[4].replace(",", "."));
		    double zlat = Double.parseDouble(parts[5].replace(",", "."));
		    double x = (lon - zlon) * Math.cos((lon + zlon) / 2);
		    double y = lat - zlat;
		    double d = Math.sqrt(x*x + y*y) * 6371;
		    if (d < mdist) {
		        closest = parts[1];
		        mdist = d;
		    }
		}
		System.out.println(closest != null ? closest : "");
	}
}
