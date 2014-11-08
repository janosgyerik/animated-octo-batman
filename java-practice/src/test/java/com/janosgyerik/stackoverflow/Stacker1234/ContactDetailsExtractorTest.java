package com.janosgyerik.stackoverflow.Stacker1234;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

class Location {

    private String streetAddress1;
    private String streetAddress2;
    private String streetAddress3;
    private String streetAddress4;
    private String city;
    private String state;
    private String zip;
    private String addressTypeCdValue;

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress3(String streetAddress3) {
        this.streetAddress3 = streetAddress3;
    }

    public String getStreetAddress3() {
        return streetAddress3;
    }

    public void setStreetAddress4(String streetAddress4) {
        this.streetAddress4 = streetAddress4;
    }

    public String getStreetAddress4() {
        return streetAddress4;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    public void setAddressTypeCdValue(String addressTypeCdValue) {
        this.addressTypeCdValue = addressTypeCdValue;
    }

    public String getAddressTypeCdValue() {
        return addressTypeCdValue;
    }

    @Override
    public String toString() {
        return "Location{" +
                "streetAddress1='" + streetAddress1 + '\'' +
                ", streetAddress2='" + streetAddress2 + '\'' +
                ", streetAddress3='" + streetAddress3 + '\'' +
                ", streetAddress4='" + streetAddress4 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", addressTypeCdValue='" + addressTypeCdValue + '\'' +
                '}';
    }
}

public class ContactDetailsExtractorTest {
    private List<Location> extractLocations(String text) {
        //Pattern pattern = Pattern.compile("(([^\\^]*)\\^([^\\^]*)\\^([^\\^]*)\\^([^\\^]*)\\^([^;]*);([^;]*);)");
        Pattern pattern = Pattern.compile("(?x)(" +
                "   ([^^]*)            # (1), Address type\n" +
                "   \\^" +
                "   ([^^]*)            # (2), street1;street2;street3;street4;\n" +
                "   \\^" +
                "   ([^^]*)            # (3), City\n" +
                "   \\^" +
                "   ([^^]*)            # (4), State\n" +
                "   \\^" +
                "   ([^;]*)             # (5), Zip\n" +
                "   ;" +
                "   ([^;]*)             # (6), Phone\n" +
                "   ;)");
        Matcher matcher = pattern.matcher(text);
        List<Location> locationList = new ArrayList<>();
        while (matcher.find()) {
            MatchResult result = matcher.toMatchResult();
            Location location = new Location();
            location.setAddressTypeCdValue(result.group(2));
            String[] streetAddressParts = result.group(3).split(";");
            location.setStreetAddress1(streetAddressParts[0]);
            if (streetAddressParts.length > 1) {
                location.setStreetAddress2(streetAddressParts[1]);
                if (streetAddressParts.length > 2) {
                    location.setStreetAddress3(streetAddressParts[2]);
                    if (streetAddressParts.length > 3) {
                        location.setStreetAddress4(streetAddressParts[3]);
                    }
                }
            }
            location.setCity(result.group(4));
            location.setState(result.group(5));
            location.setZip(result.group(6));
            locationList.add(location);
        }
        return locationList;
    }

    @Test
    public void testMatching() {
        String string = "Billing^Tata;3001 Garden Parkway^^NJ^;100-00-0009;Home^Goggle;3341 Main Parkway^^NY^;;";
        List<Location> locations = extractLocations(string);
        assertEquals("[" +
                "Location{streetAddress1='Tata', streetAddress2='3001 Garden Parkway', " +
                "streetAddress3='null', streetAddress4='null', city='', state='NJ', zip='', addressTypeCdValue='Billing'}, " +
                "Location{streetAddress1='Goggle', streetAddress2='3341 Main Parkway', " +
                "streetAddress3='null', streetAddress4='null', city='', state='NY', zip='', addressTypeCdValue='Home'}" +
                "]",
                locations.toString());
    }

    @Test
    public void testNonMatching() {
        String string = "Billing^Tata";
        assertEquals(0, extractLocations(string).size());
    }
}
