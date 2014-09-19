package com.janosgyerik.stackoverflow.welsar55;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Managing {

    public static String mime = "wtdb";

    private static boolean isValidFile(File file) {
        return file.getName().endsWith(mime);
    }

    public static boolean addObject(File file, Object object) throws IOException {
        if (isValidFile(file)) {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            pw.println(object.toString());
            pw.close();
            return true;
        } else {
            return false;
        }
    }

    public static boolean containsObject(File file, Object object) throws IOException {
        if (isValidFile(file)) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            boolean foundMatch = false;
            while (br.ready()) {
                String line = br.readLine();
                if (line.trim().equals(object.toString())) {
                    foundMatch = true;
                }
            }
            br.close();
            return foundMatch;
        } else {
            return false;
        }
    }

    public static boolean removeObject(File file, Object object) throws IOException {
        if (isValidFile(file)) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            boolean foundMatch = false;
            List<String> linestoadd = new ArrayList<String>();
            while (br.ready()) {
                String line = br.readLine();
                if (!line.trim().equals(object.toString())) {
                    linestoadd.add(line.trim());
                } else {
                    foundMatch = true;
                }
            }
            br.close();
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            for (String linetoadd : linestoadd) {
                pw.println(linetoadd);
            }
            pw.close();
            return foundMatch;
        } else {
            return false;
        }
    }

    public static ArrayList<String> getContents(File file) throws IOException {
        ArrayList<String> contents = new ArrayList<String>();
        if (file.getName().endsWith(mime)) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                contents.add(br.readLine());
            }
            br.close();
        }
        return contents;
    }
}

public class FileDatabaseTest {
}
