package com.janosgyerik.codereview.Denis.filemessagereaderwriter;

import org.junit.Test;

import java.io.*;

public class Main {

    public static void hello(String[] args) {
        try {
            MessageWriter writer = null;

            if (args.length < 1) {
                throw new IllegalArgumentException("There is no input file path");
            } else if (args.length == 1) {
//                writer = new ConsoleMessageWriter();
            } else {
//                writer = new FileMessageWriter(args[1]);
            }

            CompressingMessageWrite compressingMessageWrite = new CompressingMessageWrite(writer);

            FileMessageReader fileMessageReader = new FileMessageReader(args[0]);
            int numberOfMessages = fileMessageReader.getNumberOfMessages();
            if (numberOfMessages < 1) {
                return;
            }

//            int loopCounter = (numberOfMessages % 2 == 0) ? numberOfMessages : numberOfMessages - 1;
            int loopCounter = (numberOfMessages - (numberOfMessages % 2));
            for (int i = 0; i < loopCounter / 2; i++) {
                Message message1 = fileMessageReader.readMessage();
                Message message2 = fileMessageReader.readMessage();
                compressingMessageWrite.writeMessages(message1, message2);
            }

            if (loopCounter != numberOfMessages) {
                Message message = fileMessageReader.readMessage();
                compressingMessageWrite.writeMessage(message);
            }
        } catch (IllegalMessageFormatException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}

class IllegalMessageFormatException extends Exception {

    public IllegalMessageFormatException(String s) {

    }
}

/**
 * Reading messages from file
 */
class FileMessageReader {
    private static final int UNDEFINED_NUMBER_OF_LINES = -1;
    private int lastReadStringNumber = 0;
    private final String messageFilePath;

    /**
     * Constructs a new FileMessageReader with specific <code>filePath</code>.
     * @param filePath file with messages
     * @throws FileNotFoundException if given file does not exist
     */
    public FileMessageReader(String filePath) throws FileNotFoundException {
        if (filePath == null)
        {
            throw new IllegalArgumentException("filePath is null");
        }

        File f = new File(filePath);
        if (!f.exists() || f.isDirectory()) {
            throw new FileNotFoundException("file" + filePath + " not found.");
        }

        messageFilePath = filePath;
    }

    /**
     * Reading next message from a file
     * @return Read message
     * @throws IllegalMessageFormatException if message has illegal format
     * @throws java.io.IOException If an input or output exception occurred
     */
    public Message readMessage() throws IllegalMessageFormatException, IOException {
        int currentLine = 0;
        Message message = new Message();
        new FileReader(new File("path"));

        try (FileReader file = new FileReader(messageFilePath);
             BufferedReader reader = new BufferedReader(file)) {
            for(int i = 0; i < lastReadStringNumber; i++) {
                reader.readLine();
                currentLine++;
            }

            String line;
            int numberLinesInMessage = UNDEFINED_NUMBER_OF_LINES;
            while ((line = reader.readLine()) != null) {
                if (numberLinesInMessage == UNDEFINED_NUMBER_OF_LINES) {
                    if (isInteger(line)) {
                        numberLinesInMessage = Integer.parseInt(line);
                    } else {
                        throw new IllegalMessageFormatException("line is not a number");
                    }
                } else if (numberLinesInMessage > 0) {
                    message.append(line);
                    numberLinesInMessage--;
                }

                currentLine++;

                if (numberLinesInMessage == 0) {
                    lastReadStringNumber = currentLine;
                    return message;
                }
            }

            if (numberLinesInMessage > 0) {
                throw new IllegalMessageFormatException("Message has less lines than is specified.");
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("file" + messageFilePath + " not found.");
        } catch (IOException e) {
            throw new IOException("Strange IOException happened. Message: " + e.getMessage());
        }

        lastReadStringNumber = currentLine;
        return message;
    }

    /**
     * @return number of messages in file
     * @throws IllegalMessageFormatException if message has illegal format
     * @throws java.io.IOException If an input or output exception occurred
     */
    public int getNumberOfMessages() throws IllegalMessageFormatException, IOException {
        int numberOfMessages = 0;

        try (FileReader file = new FileReader(messageFilePath);
             BufferedReader reader = new BufferedReader(file)) {

            String line;
            int numberLinesInMessage = UNDEFINED_NUMBER_OF_LINES;

            while ((line = reader.readLine()) != null) {
                if (numberLinesInMessage == UNDEFINED_NUMBER_OF_LINES) {
                    if (isInteger(line)) {
                        numberLinesInMessage = Integer.parseInt(line);
                    } else {
                        throw new IllegalMessageFormatException("line is not a number");
                    }
                } else if (numberLinesInMessage > 0) {
                    numberLinesInMessage--;
                }

                if (numberLinesInMessage == 0) {
                    numberOfMessages++;
                    numberLinesInMessage = UNDEFINED_NUMBER_OF_LINES;
                }
            }

            if (numberLinesInMessage > 0) {
                throw new IllegalMessageFormatException("Message has less lines than is specified.");
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("file" + messageFilePath + " not found.");
        } catch (IOException e) {
            throw new IOException("Strange IOException happened. Message: " + e.getMessage());
        }

        return numberOfMessages;
    }

    private static boolean isInteger(String s) throws IllegalMessageFormatException {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            throw new IllegalMessageFormatException("line is not a number");
        }

        return true;
    }

    private int parseInt(String s) throws IllegalMessageFormatException {
        try {
            return Integer.parseInt(s);
        } catch(NumberFormatException e) {
            throw new IllegalMessageFormatException("line is not a number");
        }
    }
}

/**
 * Compress messages to input
 */
interface MessageWriter {

    void writeMessage(Message message);
}

class Message {

    public void append(Message message) {

    }

    public void append(String line) {

    }
}

class CompressingMessageWrite implements MessageWriter {
    private final MessageWriter writer;

    /**
     * Constructs a new CompressingMessageWrite with specific <code>writer</code>.
     * @param writer which is used for writing to some input
     */
    public CompressingMessageWrite(MessageWriter writer) {
        if (writer == null) {
            throw new IllegalArgumentException("writer is null");
        }

        this.writer = writer;
    }

    /**
     * Write message to input
     * @param message which will be wrote
     */
    @Override
    public void writeMessage(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("message is null");
        }

        writer.writeMessage(message);
    }

    /**
     * Write messages to input
     * @param message1 which will be wrote
     * @param message2 which will be wrote
     */
    public void writeMessages(Message message1, Message message2) {
        if (message1 == null) {
            throw new IllegalArgumentException("message1 is null");
        }

        if (message2 == null) {
            throw new IllegalArgumentException("message2 is null");
        }

        Message resultMessage = new Message();
        resultMessage.append(message1);
        resultMessage.append(message2);

        writer.writeMessage(resultMessage);
    }
}