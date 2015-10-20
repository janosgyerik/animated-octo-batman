package com.janosgyerik.codereview.randomuser;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class StateInfoTest {
    @Test
    public void test_Alabama() {
        assertEquals("Yellowhammer", StateInfo.getInfo("Alabama".toLowerCase()).bird);
    }

    @Test
    public void test_nonexistent() {
        assertEquals(null, StateInfo.getInfo("nonexistent"));
    }

    @Test
    public void test_Colorado() {
        assertEquals("Rocky Mountain Columbine", StateInfo.getInfo("Colorado".toLowerCase()).flower);
    }

    @Test
    public void test_lhs() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("hello");
        set.add("there");
        set.add("jck");

    }
}

class StateInfo {

    final String name;
    final String bird;
    final String flower;

    private static final Map<String, StateInfo> states = new HashMap<>();

    static {
        String[][] stateInfo = new String[][]{{"Alabama", "Yellowhammer", "Camelia"},
                {"Alaska", "Willow Ptarmigan", "Forget-Me-Not"}, {"Arizona", "Cactus Wren", "Saguaro Cactus Blossom"},
                {"Arkansas", "Mockingbird", "Apple Blossom"}, {"California", "California Valley Quail", "Golden Poppy"},
                {"Colorado", "Lark Bunting", "Rocky Mountain Columbine"}, {"Connecticut", "Robin", "Mountain Laurel"},
                {"Delaware", "Blue Hen Chicken", "Peach Blossom"}, {"Florida", "Mockingbird", "Orange Blossom"},
                {"Georgia", "Brown Thrasher", "Cherokee Rose"}, {"Hawaii", "Nene", "Hawaiian Hibiscus"},
                {"Idaho", "Mountain Bluebird", "Syringa, mock orange"}, {"Illinois", "Cardinal", "Violet"},
                {"Indiana", "Cardinal", "Peony"}, {"Iowa", "Eastern Goldfinch", "Wild Praire Rose"},
                {"Kansas", "Western Meadowlark", "Sunflower"}, {"Kentucky", "Cardinal", "Goldenrod"},
                {"Louisiana", "Eastern Brown Pelican", "Magnolia"}, {"Maine", "Chickadee", "Pine Cone and Tassel"},
                {"Maryland", "Baltimore Oriole", "Black-Eyed Susan"}, {"Massachusetts", "Chickadee", "Mayflower"},
                {"Michigan", "Robin", "Apple Blossom"}, {"Minnesota", "Common Loon", "Pink and White Lady's Slippper"},
                {"Mississippi", "Mockingbird", "Magnolia"}, {"Missouri", "Bluebird", "Hawthorn"},
                {"Montana", "Western Meadowlark", "Bitterroot"}, {"Nebraska", "Western Meadowlark", "Goldenrod"},
                {"Nevada", "Mountain Bluebird", "Sagebrush"}, {"New Hampshire", "Purple Finch", "Purple Lilac"},
                {"New Jersey", "Eastern Goldfinch", "Violet"}, {"New Mexico", "Roadrunner", "Yucca Flower"},
                {"New York", "Bluebird", "Rose"}, {"North Carolina", "Cardinal", "Flowering Dogwood"},
                {"North Dakota", "Western Meadowlark", "Wild Praire Rose"}, {"Ohio", "Cardinal", "Scarlet Carnation"},
                {"Oklahoma", "Scissor-tailed Flycatcher", "Oklahoma Rose"},
                {"Oregon", "Western Meadowlark", "Oregon Grape"}, {"Pennsylvania", "Ruffed Grouse", "Mountain Laurel"},
                {"Rhode Island", "Rhode Island Red", "Violet"},
                {"South Carolina", "Great Carolina Wren", "Yellow Jessamine"},
                {"South Dakota", "Ring-necked Pheasant", "Pasque Flower"},
                {"Tennessee", "Mockingbird", "Purple Passionflower"}, {"Texas", "Mockingbird", "Bluebonnet Sp."},
                {"Utah", "Common American Gull", "Sego Lily"}, {"Vermont", "Hermit Thrush", "Red Clover"},
                {"Virginia", "Cardinal", " American Dogwood"}, {"Washington", "Willow Goldfinch", "Coast Rhododendrum"},
                {"West Virginia", "Cardinal", "Rhododendron"}, {"Wisconsin", "Robin", "Wood Violet"},
                {"Wyoming", "Western Meadowlark", "Indian Paintbrush"}
        };

        for (String[] info : stateInfo) {
            states.put(info[0].toLowerCase(), new StateInfo(info[0], info[1], info[2]));
        }
    }

    public StateInfo(String name, String bird, String flower) {
        this.name = name;
        this.bird = bird;
        this.flower = flower;
    }

    public static StateInfo getInfo(String stateName) {
        return states.get(stateName);
    }

    public static void test() {

        Scanner userInput = new Scanner(System.in);

        while(true) {
            System.out.println("Enter a State or None to exit:");
            String stateName = userInput.next();

            if(stateName.equalsIgnoreCase("None")) {
                System.exit(0);
            }
            else {
                StateInfo stateInfo = getInfo(stateName.toLowerCase());
                if (stateInfo != null) {
                    System.out.println("Bird: " + stateInfo.bird);
                    System.out.println("Flower: " + stateInfo.flower);
                } else {
                    System.out.println("Invalid State Entered");
                }
            }
        }
    }
}