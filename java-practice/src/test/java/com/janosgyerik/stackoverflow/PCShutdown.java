package com.janosgyerik.stackoverflow;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PCShutdown {
    private int minutes;
    private int hours;

    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length < 2) {
            System.out.println("usage: java PCShutdown HOURS MINUTES");
        }
        int hours = Integer.parseInt(args[0]);
        int minutes = Integer.parseInt(args[1]);
        Thread.sleep(hours * 60 * 60 * 1000 + minutes * 60 * 1000);
        Runtime.getRuntime().exec("shutdown -s -t 30");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Runtime runtime = Runtime.getRuntime();
                Process process = null;
                try {
                    process = runtime.exec("shutdown -s -t 30");
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (process != null) {
                        process.destroy();
                    }
                }
            }
        }, (hours * 60 * 60 * 1000) + (minutes * 60 * 1000));

    }

    public static void doIt() {

    }

    private void setHours(int hoursArg) {
        this.hours = hoursArg;
    }

    private void setMinutes(int minutesArg) {
        this.minutes = minutesArg;
    }

    private void shutDown() {
        Thread shutDownThread = new Thread(new ShutDownThread());
        shutDownThread.start();
    }

    private class ShutDownThread implements Runnable {

        @Override
        public void run() {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    Runtime runtime = Runtime.getRuntime();
                    Process process = null;
                    try {
                        process = runtime.exec("shutdown -s -t 30");
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        process.destroy();
                    }
                }
            }, (hours * 60 * 60 * 1000) + (minutes * 60 * 1000));
        }
    }
}