package com.janosgyerik.codereview.randomuser;

import java.util.*;

public class MovieSorterTest {
    class ISort {

        public int getYear() {
            return 0;
        }

        public boolean hasImage() {
            return false;
        }

        public String getName() {
            return null;
        }

        public Object getOriginalName() {
            return null;
        }
    }

    static class StringUtils {
        public static String normaliseClean(String str) {
            return str;
        }
    }

    static class ObjectUtils {

        public static boolean compare(String name, Object originalName) {
            return false;
        }
    }

    static class MovieInfo {

    }

    static class MovieComparator implements Comparator<MovieInfo> {
        private final int targetYear;
        private final int threshold;

        MovieComparator(int targetYear, int threshold) {
            this.targetYear = targetYear;
            this.threshold = threshold;
        }

        @Override
        public int compare(MovieInfo o1, MovieInfo o2) {
            return -Integer.compare(getScore(o1), getScore(o2));
        }

        private int getScore(MovieInfo movieInfo) {
            return 0;
        }
    }

    public static <T extends ISort> void sortAccurate(List<T> list, String str, int year, int threshold) {

        final String tocompare = StringUtils.normaliseClean(str);
        Map<Integer, List<T>> values = new TreeMap<Integer, List<T>>(Collections.reverseOrder());
        for (T object : list) {

            // If year is (almost) the same, we add a "bonus"
            int bonus = 0;
            if (year >= 1900 && year <= Calendar.getInstance().get(Calendar.YEAR)) {
                final int oYear = object.getYear();
                if (year == oYear) {
                    bonus = 50;
                } else if (oYear == (year - 1) || oYear == (year + 1)) {
                    bonus = 25;
                }
            }

            // if there is an image we add a "bonus"
            if (object.hasImage()) {
                bonus += 10;
            }

            // Get best similarity between title and orig title
            int sim = getSimilarity(tocompare, object.getName(), bonus);
            if (object.getOriginalName() != null && !ObjectUtils.compare(object.getName(), object.getOriginalName())) {
                sim = Math.max(sim, getSimilarity(tocompare, object.getName(), bonus));
            }

            // We use a list cause 2 (or more) can have the same "sim" number
            List<T> listObj = values.get(sim);
            if (listObj == null) {
                listObj = new ArrayList<T>();
                values.put(sim, listObj);
            }

            listObj.add(object);
        }
        List<String> coll = Arrays.asList("hello");
        Collections.sort(coll, Collections.reverseOrder());

        // Get the higher "sim number"
        int maxSim = 0;
        for (Integer sim : values.keySet()) {
            maxSim = sim;
            break;
        }

        // If "sim number" is greater than threshold we sort the list
        if (maxSim >= threshold) {
            list.clear();
            for (List<T> olist : values.values()) {
                list.addAll(olist);
            }
        }
    }

    private static int getSimilarity(String search, String str, int bonus) {
        String toCompare = StringUtils.normaliseClean(str);// Clean the string to get best result (search is already cleaned)
        AbstractStringMetric algorithm;

        Float res = 0.0F;
        algorithm = new JaroWinkler();
        res += algorithm.getSimilarity(search, toCompare);// Return a float ([0 - 1] , 1 => exact match)

        algorithm = new Levenshtein();
        res += algorithm.getSimilarity(search, toCompare);// Return a float ([0 - 1] , 1 => exact match)

        return Math.round((res) * 100) + bonus;
    }

    static abstract class AbstractStringMetric {
        float getSimilarity(String search, String toCompare) {
            return 1;
        }
    }

    static class JaroWinkler extends AbstractStringMetric {

    }

    static class Levenshtein extends AbstractStringMetric {

    }
}
