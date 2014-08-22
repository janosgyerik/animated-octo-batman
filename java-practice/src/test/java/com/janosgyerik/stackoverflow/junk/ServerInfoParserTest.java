package com.janosgyerik.stackoverflow.junk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

class ServerInfoParser {
    static List<String> findServersDown(Document document, Set<String> names) {
        List<String> downServers = new ArrayList<>();
        Element table = document.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");

        Iterator<Element> rowIterator = rows.iterator();
        rowIterator.next();
        boolean wasMatch = false;

        while (rowIterator.hasNext()) {
            ServerInfo serverInfo = new ServerInfo(rowIterator.next().select("td"));
            String clusterName = serverInfo.getClusterName();

            if (wasMatch && clusterName.isEmpty() || names.contains(clusterName)) {
                wasMatch = true;
                if (serverInfo.isDown()) {
                    downServers.add(serverInfo.getServerName());
                }
            } else {
                wasMatch = false;
            }
        }
        return downServers;
    }

    public static void mainx(String[] args) throws IOException {
        Document doc = Jsoup.parse(new File("/tmp/test1/table.html"), "utf-8");
        Set<String> names = new HashSet<>(Arrays.asList("Titan"));
        List<String> downServers = ServerInfoParser.findServersDown(doc, names);
        System.out.println(downServers);
    }
}

class ServerInfo {
    final Elements cols;

    ServerInfo(Elements cols) {
        this.cols = cols;
    }

    String getClusterName() {
        return cols.get(3).text();
    }

    String getServerName() {
        return cols.get(5).text();
    }

    boolean isDown() {
        return cols.get(7).text().equals("down");
    }
}

public class ServerInfoParserTest {
    @Test
    public void testSecondServerDown() throws IOException {
        Document doc = Jsoup.parse(new File("src/test/resources/junk/server-info-parser/table1.html"), "utf-8");
        Set<String> names = new HashSet<>(Arrays.asList("Titan", "Platinum"));
        List<String> downServers = ServerInfoParser.findServersDown(doc, names);
        assertEquals(Arrays.asList("machineB.abc.com"), downServers);
    }

    @Test
    public void testTwoServersDown() throws IOException {
        Document doc = Jsoup.parse(new File("src/test/resources/junk/server-info-parser/table2.html"), "utf-8");
        Set<String> names = new HashSet<>(Arrays.asList("Titan", "Platinum"));
        List<String> downServers = ServerInfoParser.findServersDown(doc, names);
        assertEquals(Arrays.asList("machineB.abc.com", "machineC.abc.com", "machineD.abc.com"), downServers);
    }
}