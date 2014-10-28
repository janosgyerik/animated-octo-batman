package com.janosgyerik.stackoverflow.scarl;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class PortScannerTest {

    private static final int TIMEOUT = 1000;
    private static final int RANGE_START = 1;
    private static final int RANGE_END = 65535;

    private static Map<Integer, String> portNames = new HashMap<>();

    static {
        portNames.put(21, "FTP");
        portNames.put(22, "SSH");
        portNames.put(23, "telnet");
        // add more as you like
    }

    public void scan() {
        String host = "localhost";

        for (int port = RANGE_START; port <= RANGE_END; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), TIMEOUT);
                socket.close();
                String name = portNames.get(port);
                System.out.println(String.format("%d\t \t %s\t \t yes", port, name == null ? "-" : name));
            } catch (IOException e) {
                //System.out.println(port + "\t \t -" + "\t \t no");
                //e.printStackTrace();
            }
        }
    }

    @Test
    public void test() {
        scan();
    }
}
