package com.janosgyerik.codereview.scratch;

import java.util.*;

class Hanoi {

    public static final String[] diskNames = {"large", "medium", "small"};
    private Map<String, Deque<String>> poles = new LinkedHashMap<>();

    public static void main(String[] args) {
        Hanoi puzzle = new Hanoi();
        puzzle.run();

    }

    public void run() {
        invitation();
        initialisation();
        //      while (!finished()){
        renderPoles();
        String[] move = getInput();
        //          executeMove();
        //      }

        //      congratulation();
    }

    private String[] getInput() {
        String[] result = new String[2];

        result = getSourceTarget();
        return result;

    }

    private String[] getSourceTarget() {
        Scanner in = new Scanner(System.in);
        String selectedDisk = null;
        String targetPole = null;
        String sourcePole = null;


        while(selectedDisk == null){
            System.out.println("Which disk would you like to move?");
            selectedDisk = in.next();

            if(!validateDiskName(selectedDisk)){
                System.out.println("Disk name must be s, m or l");
                selectedDisk = null;
            }

            if(!diskOnTop(selectedDisk)){
                System.out.println("Selected disk is not on top of the pole");
                selectedDisk = null;
            }

            if(selectedDisk != null){

                targetPole = null;
                while(targetPole == null){
                    System.out.println("Which pole would you like the disk to move to?");
                    targetPole = in.next();

                    if(!validatePoleName(targetPole)){
                        System.out.println("Pole letter must be A, B or C");
                        targetPole = null;
                    }
                }

                sourcePole = findTargetPole(selectedDisk);

                if(sourcePole.equalsIgnoreCase(targetPole)){
                    System.out.println("Target pole cannot be the same as source pole");
                    selectedDisk = null;
                }

                if(selectedDisk != null){
                    if(!validatetargetDisk(targetPole, selectedDisk)){
                        System.out.println("Selected disk cannot be placed on top of a smaller disk");
                        targetPole = null;
                    }
                }
            }
        }
        System.out.println("disk = " + selectedDisk + ", source = " +sourcePole + ", taget = " + targetPole);



        in.close();
        return null;
    }

    private boolean validatetargetDisk(String targetPole, String selectedDisk) {
        boolean result = false;
        return result;
    }

    private String findTargetPole(String selectedDisk) {
        for (String poleName: poles.keySet()){

            Deque<String> pole = poles.get(poleName);
            String onTop = pole.peek();
            //          System.out.println(onTop);
            if(onTop != null){
                onTop = onTop.substring(0,1);
            }
            if(selectedDisk.equalsIgnoreCase(onTop)){
                return poleName;
            }
        }
        return null;
    }

    private boolean validatePoleName(String selectedPole) {
        boolean result = false;
        for (String poleName: poles.keySet()){

            if(selectedPole.equalsIgnoreCase(poleName)){
                result = true;
            }
        }
        return result;
    }

    private boolean diskOnTop(String selectedDisk) {
        return findTargetPole(selectedDisk) != null;
    }

    private boolean validateDiskName(String selectedDisk) {
        boolean result = false;

        for (String disk: diskNames){
            String shortName = disk.substring(0, 1);

            if (shortName.equalsIgnoreCase(selectedDisk)){
                result = true;
            }
        }
        return result;
    }

    private void renderPoles() {
        for (String poleName: poles.keySet()){
            Deque<String> pole = poles.get(poleName);

            for (String disk: pole){
                System.out.println("The " + disk + " disk is on pole " + poleName);

            }
        }
    }

    private void initialisation() {
        poles.put("A", new ArrayDeque<String>());
        poles.put("B", new ArrayDeque<String>());
        poles.put("C", new ArrayDeque<String>());

        Deque<String> a = poles.get("A");

        for (String disk: diskNames){
            a.push(disk);
        }
    }

    public void invitation() {
        System.out.println( "Welcome to the puzzleof the mini-towers of Hanoi! \n"
                + "In this version of the puzzle there are threedisks and three poles. \n "
                + "There is a small disk, a mediumdisk and a largedisk. \n"
                + "Each disk has a hole in the middle which allows it to be placedon a pole. \n "
                + "The three poles are arranged left to right and labelled A, B and C. \n"
                + "Initially, all threedisks areon the left-most pole, A. \n "
                + "Yourtask is to move the threedisks to the right-most pole, C. \n "
                + "The problem is that you can only move onedisk at a time and \n"
                + "you cannot put a larger diskon topof a smaller disk. \n "
                + "You can use pole B as atemporarypole for one or moredisks \n "
                + "but to solve the problem all threedisks must ultimately end up \n "
                + "on the right-most pole, C. \n");
    }

    public Set<Integer> getSet() {
        Set<Integer> sample = new HashSet<>();
        return Collections.unmodifiableSet(sample);
    }

}