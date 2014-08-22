package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

class JensorLogProcessor {

    private static final Deque<JensorObject> stack = new ArrayDeque<>();
    private static final List<JensorObject> listOfProcesses = new ArrayList<>();
    private static final Map<String, Integer> numberOfFunctionCalled = new HashMap<>();

    public static String process(Scanner sc) throws IOException {
        String logFileName = sc.nextLine();
        int functionNumber = sc.nextInt();

        return new JensorLogProcessor().getDetailsOfFunctionFromFile(logFileName, functionNumber);
    }

    public static void main(String[] args) throws IOException {
        process(new Scanner(System.in));
    }

    public String getDetailsOfFunctionFromFile(String fileName, int functionNumber) throws IOException {
        parseLogFile(fileName);
        JensorObject jensorObject = listOfProcesses.get(functionNumber - 1); // 0 based indexing
        String functionName = jensorObject.getFunctionName();
        long responseTime = jensorObject.getExecutionTime();
        int numberOfInvocation = numberOfFunctionCalled.get(functionName);

        return String.format("%s\n%s\n%s", functionName, responseTime, numberOfInvocation);
    }

    private void parseLogFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            parseFunctionAndProcess(line);
        }
    }

    private void parseFunctionAndProcess(String line) {
        String[] functionDesc = line.split(",");
        String enterOrExit = functionDesc[0];
        String functionName = functionDesc[1];
        long ticks = Long.parseLong(functionDesc[2]);

        switch (enterOrExit) {
            case "Enter":
                JensorObject jensorObject = new JensorObject(functionName,
                        ticks,  // start ticks
                        0L      // at starting execution ticks is 0
                );
                stack.push(jensorObject);
                listOfProcesses.add(jensorObject);
                if (numberOfFunctionCalled.containsKey(jensorObject.getFunctionName())) {
                    numberOfFunctionCalled.put(jensorObject.getFunctionName(),
                            numberOfFunctionCalled
                                    .get(jensorObject.getFunctionName()) + 1);
                } else {
                    numberOfFunctionCalled.put(jensorObject.getFunctionName(), 1);
                }
                break;
            case "Exit":
                JensorObject currentJensorObject = stack.pop();
                currentJensorObject.calculateExecutionTime(ticks);
                break;
        }
    }

    private class JensorObject {
        private final String functionName;
        private final long startTime;
        private long executionTime;

        public JensorObject(String functionName, long startTime, long executionTime) {
            this.functionName = functionName;
            this.startTime = startTime;
            this.executionTime = executionTime;
        }

        public void calculateExecutionTime(long endTime) {
            this.executionTime = endTime - startTime;
        }

        public long getExecutionTime() {
            return executionTime;
        }

        public String getFunctionName() {
            return functionName;
        }
    }
}

public class JensorLogProcessorTest {
    @Test
    public void testSample1() throws IOException {
        assertEquals("D()\n20\n4", JensorLogProcessor.process(new Scanner("/tmp/test1/JensorProfile.txt\n10")));
    }

    @Test
    public void testSample2() throws IOException {
        assertEquals("C()\n47\n2", JensorLogProcessor.process(new Scanner("/tmp/test1/JensorProfile.txt\n5")));
    }

    @Test(expected = InputMismatchException.class)
    public void testSample3() throws IOException {
        JensorLogProcessor.process(new Scanner("/tmp/test1/JensorProfile.txt\na"));
    }
}
