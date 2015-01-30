package com.janosgyerik.codereview.BenignReaver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

class TCPClient {
    private static final int TIMEOUT_MILLIS = 3000;
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 22003;

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("You are connected to the TCPCLient;" + "\n" + "Please enter a message:");
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);
            client.setSoTimeout(TIMEOUT_MILLIS);

            DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String input = inFromUser.readLine();
                outToServer.writeBytes(input + '\n');
                String modedInput = inFromServer.readLine();
                System.out.println("You Sent: " + modedInput);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Timed Out Waiting for a Response from the Server");
        }

    }
}

class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket input = new ServerSocket(22003))
        {
            while (true) {
                try (
                    Socket connectionSocket = input.accept();
                    BufferedReader iptFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    DataOutputStream optToClient = new DataOutputStream(connectionSocket.getOutputStream())
                ) {
                    String TCPclientMess = iptFromClient.readLine();
                    System.out.println("You Sent: " + TCPclientMess);
                    String returnMess = TCPclientMess;
                    optToClient.writeBytes(returnMess);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

public class TcpClientTest {
}
