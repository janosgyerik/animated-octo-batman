package com.janosgyerik.codereview.chava;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TravellerTest {

    @Test
    public void test() {
        String[][] trips = new String[12][2];
        trips[0][0] = "Person A";
        trips[0][1] = "City 1";
        trips[1][0] = "Person A";
        trips[1][1] = "City 2";
        trips[2][0] = "Person B";
        trips[2][1] = "City 1";
        trips[3][0] = "Person B";
        trips[3][1] = "City 3";
        trips[4][0] = "Person B";
        trips[4][1] = "City 3";
        trips[5][0] = "Person B";
        trips[5][1] = "City 4";
        trips[6][0] = "Person B";
        trips[6][1] = "City 4";
        trips[7][0] = "Person C";
        trips[7][1] = "City 1";
        trips[8][0] = "Person C";
        trips[8][1] = "City 2";
        trips[9][0] = "Person C";
        trips[9][1] = "City 2";
        trips[10][0] = "Person C";
        trips[10][1] = "City 2";
        trips[11][0] = "Person C";
        trips[11][1] = "City 4";
        assertEquals("Person B", BiggestTraveler.pickBiggestTraveler(trips));
    }
}

class BiggestTraveler {

    public static class TravelerInfo {
        //map that contains the cities a person has visited and how times they have visited it
        String name; //not including setters just because they make no sense in this context (and data isn't changing)
        int totalTrips = 0;
        int maxVisitedCity = 0;
        Map<String, Integer> cityVisits = new HashMap<String, Integer>();

        public TravelerInfo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Map<String, Integer> getCityVisits() {
            return cityVisits;
        }

        public void setCityVisits(final Map<String, Integer> cityVisits) {
            this.cityVisits = cityVisits;
        }

        public void addVisit(String city) {
            int numVisits = 1;
            if (cityVisits.containsKey(city)) {
                numVisits += cityVisits.get(city);

                if (numVisits > maxVisitedCity) {
                    maxVisitedCity = numVisits;
                }
            }
            cityVisits.put(city, numVisits);
            totalTrips++;
        }

        public int getTotalTrips() {
            return totalTrips;
        }

        public int getDistinctCityCount() {
            return cityVisits.keySet().size();
        }

        public int getMaxVisitedCity() {
            return maxVisitedCity;
        }
    }

    public static class TravelsComparator implements Comparator<TravelerInfo> {
        @Override
        public int compare(final TravelerInfo o1, final TravelerInfo o2) {
            return -Integer.compare(o1.getTotalTrips(), o2.getTotalTrips());
        }
    }

    public static class DistinctCitiesComparator implements Comparator<TravelerInfo> {
        @Override
        public int compare(final TravelerInfo o1, final TravelerInfo o2) {
            return -Integer.compare(o1.getDistinctCityCount(), o2.getDistinctCityCount());
        }
    }

    public static class DistributedTravelsComparator implements Comparator<TravelerInfo> {
        @Override
        public int compare(final TravelerInfo o1, final TravelerInfo o2) {
            return Integer.compare(o1.getMaxVisitedCity(), o2.getMaxVisitedCity());
        }
    }

    static String pickBiggestTraveler(String[][] trips) {
        Map<String, TravelerInfo> travelRecords = new HashMap<String, TravelerInfo>();

        for (String[] trip : trips) {
            if (travelRecords.containsKey(trip[0])) {
                //if we've dealt with this traveler just add a new visited city
                travelRecords.get(trip[0]).addVisit(trip[1]);
            } else {
                //we've found a new traveler so let's add him
                TravelerInfo info = new TravelerInfo(trip[0]);
                info.addVisit(trip[1]);
                travelRecords.put(info.getName(), info);
            }
        }

        List<Comparator<TravelerInfo>> comparators = Arrays.asList(
                new TravelsComparator(),
                new DistinctCitiesComparator(),
                new DistributedTravelsComparator());

        List<TravelerInfo> travelInfo = new ArrayList<TravelerInfo>(travelRecords.values());

        Collections.sort(travelInfo, new Comparator<TravelerInfo>() {
            @Override
            public int compare(TravelerInfo o1, TravelerInfo o2) {
                for (Comparator<TravelerInfo> comparator : comparators) {
                    int cmp = comparator.compare(o1, o2);
                    if (cmp != 0) {
                        return cmp;
                    }
                }
                return 0;
            }
        });

        return travelInfo.get(0).getName();

        /*
        TravelerInfo currentMax;
        for (Comparator comparator : comparators) {
            //if we're not down to a single winner then continue
            if (travelInfo.size() > 1) {
                Collections.sort(travelInfo, comparator);

                currentMax = travelInfo.get(0);

                int lastIndex = travelInfo.size();

                //find the last traveler that is tied with the current max
                for (int i = 0; i < travelInfo.size(); i++) {
                    if (comparator.compare(currentMax, travelInfo.get(i)) < 0) {
                        lastIndex = i;
                    }
                }
                //remove values lower than the highest
                travelInfo = travelInfo.subList(0, lastIndex);
            } else {
                //if we've found a winner no need to continue iterating over comparators
                break;
            }
        }

        if (travelInfo.size() > 1) {
            return "Tie";
        } else {
            return travelInfo.get(0).getName();
        }
        */
    }
}