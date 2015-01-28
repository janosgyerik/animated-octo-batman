package com.janosgyerik.codereview.BenignReaver;

import java.io.*;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class execute {
    public static void main(String[] args) {

        final String file_name = "C:/Users/wigginsm/Desktop/Log.txt";

        WriteToFile writes = new WriteToFile(file_name);
        PrintReader reads = new PrintReader(file_name);

        ExecutorService thread = Executors.newCachedThreadPool();

        boolean loop = true;
        while (loop == true) {
            thread.execute(reads);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            thread.execute(writes);
        }
    }
}

class PrintReader implements Runnable {
    @SuppressWarnings("unused")
    private final String taskName;

    final String file_name = "C:/Users/wigginsm/Desktop/Log.txt";

    public PrintReader(String name) {
        taskName = name;
    }

    public void run() {
        try {

            FileReader fr = new FileReader(file_name);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            int count = 0;
            while (line != null) {
                count++;
                line = br.readLine();
            }
            FileInputStream fileIn = new FileInputStream(file_name);
            BufferedReader fileR = new BufferedReader(new InputStreamReader(fileIn));

            String strLine = null, tmp;

            while ((tmp = fileR.readLine()) != null) {
                strLine = tmp;
            }
            String lastLine = strLine;
            System.out.println("Last entered line: " + lastLine + "\n" + "Total number of Lines: " + count);
            br.close();
            fileR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//end of reader class

class WriteToFile implements Runnable {
    @SuppressWarnings("unused")
    private final String taskName;

    class WriteFile {
        private String path;
        private boolean append_to_file = false;

        public WriteFile(String file_path) {
            //path = file_path;
        }

        public WriteFile(String file_path, boolean append_value) {
            path = file_path;
            append_to_file = append_value;
        }

        public void writeToFile(String timestamp) throws IOException {
            int i = 0;
            while (i < 1) {
                FileWriter write = new FileWriter(path, append_to_file);
                Calendar current = Calendar.getInstance();
                int ms = current.get(Calendar.MILLISECOND);
                int minute = current.get(Calendar.MINUTE);
                int second = current.get(Calendar.SECOND);
                int hour = current.get(Calendar.HOUR_OF_DAY);
                int day = current.get(Calendar.DAY_OF_YEAR);
                int month = current.get(Calendar.MONTH) + 1;
                int year = current.get(Calendar.YEAR);
                timestamp = day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second + ":" + ms;

                PrintWriter print_line = new PrintWriter(write);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                print_line.printf("%s" + "%n", timestamp);
                print_line.close();
            }
        }
    }

    //constructor
    public WriteToFile(String name) {
        taskName = name;
    }

    public void run() {
        try {
            String file_name = "C:/Users/wigginsm/Desktop/Log.txt";
            Calendar current = Calendar.getInstance();
            int ms = current.get(Calendar.MILLISECOND);
            int minute = current.get(Calendar.MINUTE);
            int second = current.get(Calendar.SECOND);
            int hour = current.get(Calendar.HOUR_OF_DAY);
            int day = current.get(Calendar.DAY_OF_YEAR);
            int month = current.get(Calendar.MONTH) + 1;
            int year = current.get(Calendar.YEAR);
            String timestamp = day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second + ":" + ms;
            WriteFile data = new WriteFile(file_name, true);
            data.writeToFile(timestamp);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


public class ExecuteTest {
}
