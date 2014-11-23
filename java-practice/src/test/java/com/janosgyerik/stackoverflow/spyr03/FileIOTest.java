package com.janosgyerik.stackoverflow.spyr03;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileIO {

    public List<String> load(String path) {
        File file = new File(path);
        List<String> lines = new ArrayList<>();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(file));
            String line;
            while ((line = input.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find the file - are you sure the file is in this location: " + path);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        return lines;
    }

    public void register() {

    }

    public void save(String file, String[] array) throws FileNotFoundException,
            IOException {

        File aFile = new File(file);
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(aFile));
            for (int i = 0; i < array.length; i++) {
                output.write(array[i]);
                output.write(System.getProperty("line.separator"));
            }
        } finally {
            if (output != null)
                output.close();
        }
    }

    public void save(String file, String[][] array)
            throws FileNotFoundException, IOException {

        File aFile = new File(file);
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(aFile));
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length - 1; j++) {
                    output.write(array[i][j] + ",");
                }
                output.write(array[i][array[0].length - 1]);
                output.write(System.getProperty("line.separator"));
            }
        } finally {
            if (output != null)
                output.close();
        }
    }
}

public class FileIOTest {
    @Test
    public void example() {
        System.out.println(new FileIO().load("/tmp/ls.out"));
    }
}
