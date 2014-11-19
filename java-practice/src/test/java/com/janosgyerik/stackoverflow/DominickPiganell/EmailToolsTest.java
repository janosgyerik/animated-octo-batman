package com.janosgyerik.stackoverflow.DominickPiganell;

import java.util.ArrayList;
import java.util.List;

class EmailTools {
    public static void send(String smtpServer, String[] to, String[] cc, String from, String subject, String body, boolean HTML, String fullFilePath) {
        //
    }

    public static void send(EmailOptions options) {

    }

}

public class EmailToolsTest {
}

class EmailOptions {
    public static class Builder {
        private final String smtpServer;
        private final String from;
        private final String subject;
        private final String body;
        private final List<String> to = new ArrayList<>();
        private final List<String> cc = new ArrayList<>();

        public Builder(String smtpServer, String from, String subject, String body) {
            this.smtpServer = smtpServer;
            this.from = from;
            this.subject = subject;
            this.body = body;
        }
    }
}