package com.janosgyerik.codereview.KorayTugay;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TelnetServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8888);

        while (true) {
            new Thread(new ServerSocketThread(serverSocket.accept())).start();
        }
    }
}

class ServerSocketThread implements Runnable {

    private final Socket socket;

    public ServerSocketThread(Socket accept) {
        this.socket = accept;
    }

    @Override
    public void run() {
        try {
            Scanner s = new Scanner(socket.getInputStream());
            String readLine;

            while (!(readLine = s.nextLine()).equals("bye")) {
                System.out.println(readLine);
            }

            new PrintWriter(socket.getOutputStream()).write("Bye then..");
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
