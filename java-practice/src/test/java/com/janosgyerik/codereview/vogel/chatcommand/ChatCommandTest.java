package com.janosgyerik.codereview.vogel.chatcommand;

import java.util.function.Consumer;
import java.util.function.Predicate;

class ChatMessage {

}

class CommandHandle {

    private final Predicate<String> matchesSyntax;
    private final Consumer<ChatMessage> executor;
    private final String helpText;
    private final String infoText;

    public static class Builder {
        private final Predicate<String> matchesSyntax;
        private final Consumer<ChatMessage> executor;
        private String helpText = "";
        private String infoText = "";

        public Builder(Predicate<String> matchesSyntax, Consumer<ChatMessage> executor) {
            this.matchesSyntax = matchesSyntax;
            this.executor = executor;
        }

        public Builder setHelpText(String helpText) {
            this.helpText = helpText;
            return this;
        }

        public Builder setInfoText(String infoText) {
            this.infoText = infoText;
            return this;
        }
    }

    private CommandHandle(Builder builder) {
        this.matchesSyntax = builder.matchesSyntax;
        this.executor = builder.executor;
        this.helpText = builder.helpText;
        this.infoText = builder.infoText;
    }

    public static Builder builder(Predicate<String> matchesSyntax, Consumer<ChatMessage> executor) {
        return new Builder(matchesSyntax, executor);
    }

    public void execute(ChatMessage message) {
        executor.accept(message);
    }

    public boolean matchesSyntax(String commandCall) {
        return matchesSyntax.test(commandCall);
    }

    public String getHelpText() {
        return helpText;
    }

    public String getInfoText() {
        return infoText;
    }
}

//class CommandHandleExample {
//    public void example() {
//        String TRIGGER = "x";
//        CommandHandle shutdown = CommandHandle.builder(s -> {
//            return s.trim().equals(TRIGGER + "shutdown");
//        }, message -> {
//            chatInterface.broadcast("*~going down*");
//            executor.shutdownNow();
//            System.exit(0);
//        }).setHelpText("Command shutdown: call using:'" + TRIGGER + "shutdown'. Terminates the bot")
//        .setInfoText("Shuts down the executing JVM. The bot will no more respond")
//        .build();
//    }
//}

public class ChatCommandTest {
    public void m1() throws IllegalStateException {

    }
    public void m2() {
        m1();
    }
}
