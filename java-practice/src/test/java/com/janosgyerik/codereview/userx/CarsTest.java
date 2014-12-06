package com.janosgyerik.codereview.userx;

import org.junit.Test;

import java.io.IOException;
import java.util.*;

class Cars {
    public static final String ADD_COMMAND = "a";
    public static final String ITEM_COMMAND = "i";
    public static final String SEARCH_COMMAND = "s";
    public static final String END_MARKER = "<END>";

    public static void processCars(Scanner scanner) throws IOException {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();

        String line;
        line = scanner.nextLine();

        while (!line.equals(END_MARKER)) {

            String stringa = "";
            String elemento = "";


            if (line.startsWith(ITEM_COMMAND)) {
                map = new HashMap<String, Set<String>>();
            } else if (line.startsWith(ADD_COMMAND)) {
                String[] parts = line.split("\\s+");

                for (int i = 2; i < parts.length - 1; ++i) {
                    String car = parts[1];
                    String tag = parts[i];
                    for (int j = 1; j <= parts[i].length(); ++j) {
                        String tagPrefix = tag.substring(0, j);
                        Set<String> cars = map.get(tagPrefix);
                        if (cars == null) {
                            cars = new HashSet<String>();
                            map.put(tagPrefix, cars);
                        }
                        cars.add(car);
                    }
                }
            } else if (line.startsWith(SEARCH_COMMAND)) {
                String subtag = line.substring(2, line.length() - 3);
                System.out.println(map);
                if (map.containsKey(subtag)) {
                    System.out.println(map.get(subtag).size());
                } else {
                    System.out.println("missing");
                }
            }

            line = scanner.nextLine();
        }
    }
}

public class CarsTest {
    @Test
    public void testExample() throws IOException {
        Cars.processCars(new Scanner("i3\n" +
                "a Duna automobile Deserto -1 \n" +
                "a Nissan auto automobile -1 \n" +
                "s aut -1\n" +
                "\n" +
                "i2\n" +
                "a Pesca Sport Frutta -1\n" +
                "s sport -1\n" +
                "\n" +
                "<END>\n\nx\n"));
    }
    /*
{a=[Nissa, Dun], aut=[Nissa, Dun], De=[Dun], auto=[Nissa, Dun], D=[Dun], automob=[Nissa, Dun], -=[Nissa, Dun], automobile=[Nissa, Dun], Deser=[Dun], autom=[Nissa, Dun], Des=[Dun], Dese=[Dun], au=[Nissa, Dun], automobi=[Nissa, Dun], Desert=[Dun], automo=[Nissa, Dun], automobil=[Nissa, Dun], Deserto=[Dun]}
2
{Sport=[Pesc], S=[Pesc], Spo=[Pesc], F=[Pesc], Spor=[Pesc], Fru=[Pesc], Frutta=[Pesc], Frutt=[Pesc], Frut=[Pesc], Fr=[Pesc], Sp=[Pesc]}
missing
{a=[Duna, Nissan], aut=[Duna, Nissan], De=[Duna], auto=[Duna, Nissan], D=[Duna], automob=[Duna, Nissan], Deser=[Duna], autom=[Duna, Nissan], Des=[Duna], Dese=[Duna], au=[Duna, Nissan], automobi=[Duna, Nissan], Desert=[Duna], automo=[Duna, Nissan], automobil=[Duna, Nissan]}
2
{S=[Pesca], Spo=[Pesca], F=[Pesca], Spor=[Pesca], Fru=[Pesca], Frutt=[Pesca], Frut=[Pesca], Fr=[Pesca], Sp=[Pesca]}
missing

     */

}
