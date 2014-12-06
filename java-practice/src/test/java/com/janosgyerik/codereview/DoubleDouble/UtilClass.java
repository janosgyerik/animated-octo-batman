package com.janosgyerik.codereview.DoubleDouble;

import java.util.Collections;
import java.util.List;

public class UtilClass {

    public static <T> String getNextAvailableNumber(List<T> currentObjects) {
        int nextNumber = UtilClass.getNextAvailableNumberAsInteger(currentObjects, Collections.emptyList());
        return UtilClass.formatNumber(nextNumber);  // formats number as string
    }

    private static String formatNumber(int nextNumber) {
        return null;
    }

    public static <T> int getNextAvailableNumberAsInteger(List<T> currentObjects, List<String> otherUnavailableNumbers) {
        //returns nextNumber as int.
        return 0;
    }

    /*
    public static String getNextAvailableNumber(List<?> objects)
    {
        List<Date> objectList = new ArrayList<Date>();
        List<String> stringList = new ArrayList<String>();

        if (objects.get(0) instanceof Date)
        {
            objectList = (List<Date>) objects;
        }
        else if (objects.get(0) instanceof String)
        {
            stringList = (List<String>) objects;
        }
        int nextNumber = UtilClass.getNextAvailableNumberAsInteger(objectList, stringList);
        return UtilClass.formatNumber(nextNumber);
    }
    */

    public void localClass() {
        class Person {

        }
    }
}
