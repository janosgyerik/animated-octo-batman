package com.janosgyerik.codereview.randomuser;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HeadPhoneTest {
    @Test
    public void test2() {
        assertTrue(consecutive(1, 2, 3));
        assertFalse(consecutive(1, 2, 13));
        assertFalse(consecutive(1, 22, 13));
        assertFalse(consecutive(111, 22, 13));
        assertFalse(consecutive(111, 22, 113));
        assertTrue(consecutive(1, 3, 2));
        assertTrue(consecutive(3, 1, 2));
        assertTrue(consecutive(2, 1, 3));
        assertTrue(consecutive(2, 3, 1));
        assertFalse(consecutive(2, 3, 5));
        assertFalse(consecutive(2, 3, 15));
        assertFalse(consecutive(2, 3, 9));
    }

    public static boolean consecutive(int a, int b, int c) {
        int max = (a + b + c) / 3 + 1;
        return a != b && b != c && a != c && max - a <= 2 && max - b <= 2 && max - c <= 2;
    }

    public static boolean consecutive2(int a, int b, int c) {
        if ( a == b || b == c) {
            return false;
        } else if ((a == b + 1 || a == b - 1) || (a == c + 1 || a == c - 1)
                && ((b == c + 1 || b == c - 1))) {
            return true;
        }
        return false;
    }

    public void test() {
        HeadPhone HeadPhone1 = new HeadPhone();
        HeadPhone HeadPhone2 = new HeadPhone(1, true, "JVC", "Green");
        HeadPhone HeadPhone3 = new HeadPhone(3, true, "Beats", "Red");

        int HeadPhone1Volume = HeadPhone1.getVolume();
        boolean HeadPhone1PluggedIn = HeadPhone1.getPluggedIn();
        String HeadPhone1Manufacturer = HeadPhone1.getManufacturer();
        String HeadPhone1HeadPhoneColor = HeadPhone1.getHeadPhoneColor();
//        String HeadPhone1CurrentVolume = HeadPhone1.currentVolume;
//        String HeadPhone1StatusPluggedIn = HeadPhone1.statusPluggedIn;
//        String HeadPhone1Playlist = HeadPhone1.playlist;

        int HeadPhone2Volume = HeadPhone2.getVolume();
        boolean HeadPhone2PluggedIn = HeadPhone2.getPluggedIn();
        String HeadPhone2Manufacturer = HeadPhone2.getManufacturer();
        String HeadPhone2HeadPhoneColor = HeadPhone2.getHeadPhoneColor();
//        String HeadPhone2CurrentVolume = HeadPhone2.currentVolume;
//        String HeadPhone2StatusPluggedIn = HeadPhone2.statusPluggedIn;

        int HeadPhone3Volume = HeadPhone3.getVolume();
        boolean HeadPhone3PluggedIn = HeadPhone3.getPluggedIn();
        String HeadPhone3Manufacturer = HeadPhone3.getManufacturer();
        String HeadPhone3HeadPhoneColor = HeadPhone3.getHeadPhoneColor();
//        String HeadPhone3CurrentVolume = HeadPhone3.currentVolume;
//        String HeadPhone3StatusPluggedIn = HeadPhone3.statusPluggedIn;

        System.out.println("Head Phone 1 has the following parameters:");
        System.out.println(HeadPhone1.toString());
        System.out.println("Head Phone 2 has the following parameters:");
        System.out.println(HeadPhone2.toString());
        System.out.println("Head Phone 3 has the following parameters:");
        System.out.println(HeadPhone3.toString());
    }
}

// HEADPHONE CLASS
class HeadPhone {
    public static final int LOW = 1;
    public static final int MEDIUM = 2;
    public static final int HIGH = 3;

    private int volume;
    private boolean pluggedIn;
    private String manufacturer;
    private String headPhoneColor;

    // Constructor
    public HeadPhone(int volume, boolean pluggedIn, String manufacturer, String headPhoneColor) {
        this.volume = volume;
        this.pluggedIn = pluggedIn;
        this.manufacturer = manufacturer;
        this.headPhoneColor = headPhoneColor;
    }

    // Default Constructor
    public HeadPhone() {
        volume = MEDIUM;
        pluggedIn = false;
        manufacturer = "DEFAULT";
        headPhoneColor = "DEFAULT";
    }

    // Setter methods
    // setVolume
    public void setVolume(int volume) {
        this.volume = volume;
    }

    // setPluggedIn
    public void setPluggedIn(boolean pluggedIn) {
        this.pluggedIn = pluggedIn;
    }

    // setManufacturer
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    // setHeadPhoneColor
    public void setHeadPhoneColor(String headPhoneColor) {
        this.headPhoneColor = headPhoneColor;
    }

    // getVolume
    public int getVolume() {
        return volume;
    }

    // getPluggedIn
    public boolean getPluggedIn() {
        return pluggedIn;
    }

    // getManufacturer
    public String getManufacturer() {
        return manufacturer;
    }

    // getHeadPhoneColor
    public String getHeadPhoneColor() {
        return headPhoneColor;
    }

    // changeVolume
    public void changeVolume(int volume) {
        setVolume(volume);
    }

    // toString
    public String toString() {
        int volume = this.getVolume();
        boolean pluggedIn = this.getPluggedIn();
        String manufacturer = this.getManufacturer();
        String headphoneColor = this.getHeadPhoneColor();
        String currentVolume = String.valueOf(this.volume);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Manufacturer: %s\n", manufacturer));
        sb.append(String.format("Color: %s\n", headPhoneColor));
        sb.append(String.format("Currently: %s\n", pluggedIn));
        sb.append(String.format("Volume is set to: %s\n", currentVolume));

        return sb.toString();
    }
}
