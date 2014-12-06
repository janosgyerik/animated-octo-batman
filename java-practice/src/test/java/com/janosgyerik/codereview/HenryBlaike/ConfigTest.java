package com.janosgyerik.codereview.HenryBlaike;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Config {

    private static final String COMMENT_PREFIX = "# ";

    private final String path;

    Config(String path) {
        this.path = path;
    }

    public void set(String key, Object value) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.path, true));
        BufferedReader br = new BufferedReader(new FileReader(this.path));
        List<String> file = new ArrayList<>();
        if (this.getValue(key) != null) {
            for (String line; (line = br.readLine()) != null; ) {
                if (line.startsWith(COMMENT_PREFIX)) {
                    file.add(line);
                } else if (line.isEmpty()) {
                    file.add("");
                } else {
                    if (!line.contains(":")) {
                        bw.close();
                        br.close();
                        return;
                    }
                    String lineKey = line.substring(0, line.indexOf(":"));
                    String lineValue = line.substring(line.indexOf(":") + 2);
                    if (lineKey.equals(key)) {
                        if (value instanceof ArrayList) {
                            StringBuilder newValue = new StringBuilder(value.toString());
                            newValue.replace(value.toString().lastIndexOf("]"), value.toString().lastIndexOf("]") + 1, "");
                            newValue.replace(value.toString().indexOf("["), value.toString().indexOf("[") + 1, "");
                            bw.append(key).append(": ").append(newValue.toString());
                            bw.newLine();
                        } else {
                            file.add(lineKey + ": " + value);
                        }
                    } else {
                        file.add(lineKey + ": " + lineValue);
                    }
                }
            }
            this.clear();
            for (String line : file) {
                bw.append(line);
                bw.newLine();
            }
        } else {
            if (value instanceof ArrayList) {
                StringBuilder newValue = new StringBuilder(value.toString());
                newValue.replace(value.toString().lastIndexOf("]"), value.toString().lastIndexOf("]") + 1, "");
                newValue.replace(value.toString().indexOf("["), value.toString().indexOf("[") + 1, "");
                bw.append(key).append(": ").append(newValue.toString());
                bw.newLine();
            } else {
                bw.append(key).append(": ").append(value.toString());
                bw.newLine();
            }
        }
        br.close();
        bw.close();
    }

    private void clear() {

    }

    private Object getValue(String key) {
        return null;
    }
}

public class ConfigTest {
    @Test
    public void testExample() throws IOException {
        Config config = new Config(File.createTempFile("config", ".cfg").getAbsolutePath());
        config.set("hello", "world");
    }
}