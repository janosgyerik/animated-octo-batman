package com.janosgyerik.stackoverflow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswerSession20150214 {
    public int max;
    public int[] intArray = new int[max];

    @Test
    public void test1() {
        List<List<Integer>> board = new ArrayList<List<Integer>>();

        for (int i = 0; i < 8; i++) {
            List<Integer> row = new ArrayList<Integer>();
            board.add(row);
            for (int x = 0; x < 8; x++) {
                row.add(i * 8 + x + 1);
            }
        }

        for (List<Integer> innerList : board) {
            System.out.println(innerList);
        }
    }

    @Test
    public void test2() {
        String met = "This is the string with comma numberone; string having second wedaweda; last word";
        String[] words = met.split("\\W+");
        if (words.length >= 3) {
            System.out.println(words[words.length - 3]);
        }
    }
}

class Candidate {
    // instance variables
    int numVotes;
    String name;

    /**
     * Constructor for objects of class InventoryItem
     */
    public Candidate(String n, int v) {
        // initialise instance variables
        name = n;
        numVotes = v;
    }

    public int votes() {
        return numVotes;
    }

    public void setVotes(int num) {
        numVotes = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String toString() {
        return name + " received " + numVotes + " votes.";
    }
}

class TestCandidate4 {
    public static void printVotes(Candidate[] election) {
        for (int i = 0; i < election.length; i++)
            System.out.println(election[i]);
    }

    public static int getTotal(Candidate[] election) {
        int total = 0;
        for (Candidate candidate : election) {
            total += candidate.numVotes;
        }
        return total;
    }

    public static void printResults(Candidate[] election) {
        double percent;
        System.out.println("Candidate        Votes Received      % of Total Votes");
        for (int x = 0; x < election.length; x++) {
            percent = (double) (election[x].votes()) / getTotal(election) * 100;
            System.out.printf("%-15s %10d %20.0f", election[x].getName(), election[x].votes(), percent);
            System.out.println();
        }
    }

    public static void replaceName(Candidate[] election, String find, String replace) {
        for (int index = 0; index < election.length; index++)
            if (election[index].getName().equals(find))
                election[index].setName(replace);
    }

    public static void replaceVotes(Candidate[] election, String find, int replace) {
        for (int index = 0; index < election.length; index++)
            if (election[index].getName().equals(find))
                election[index].setVotes(replace);
    }

    public static void replaceCandidate(Candidate[] election, String find, String replace, int replaceV) {
        for (int index = 0; index < election.length; index++)
            if (election[index].getName().equals(find)) {
                election[index].setName(replace);
                election[index].setVotes(replaceV);
            }
    }


    public static void main(String[] args) {
        List<Candidate> electionList = new ArrayList<Candidate>();

        // create election
        electionList.add(new Candidate("John Smith", 5000));
        electionList.add(new Candidate("Mary Miller", 4000));
        electionList.add(new Candidate("Michael Duffy", 6000));
        electionList.add(new Candidate("Tim Robinson", 2500));
        electionList.add(new Candidate("Joe Ashtony", 1800));
        electionList.add(new Candidate("Mickey Jones", 3000));
        electionList.add(new Candidate("Rebecca Morgan", 2000));
        electionList.add(new Candidate("Kathleen Turner", 8000));
        electionList.add(new Candidate("Tory Parker", 500));
        electionList.add(new Candidate("Ashton Davis", 10000));

        Candidate[] election = electionList.toArray(new Candidate[electionList.size()]);

        System.out.println("Original results:");
        System.out.println();
        System.out.println(election);
        System.out.println();
        System.out.println("Total of votes in election: " + getTotal(election));
        System.out.println();

        replaceName(election, "Michael Duffy", "John Elmos");
        System.out.println("Changing Michael Duffy to John Elmos:");
        System.out.println();
        System.out.println(election);
        System.out.println("Total of votes in election: " + getTotal(election));
        System.out.println();

        replaceVotes(election, "Mickey Jones", 2500);
        System.out.println("Changing Mickey Jones to 2500:");
        System.out.println();
        printResults(election);
        System.out.println();
        System.out.println("Total of votes in election: " + getTotal(election));
        System.out.println();

        replaceCandidate(election, "Kathleen Turner", "John Kennedy", 8500);
        System.out.println("Changing Mickey Jones to 2500");
        System.out.println();
        printResults(election);
        System.out.println();
        System.out.println("Total of votes in election: " + getTotal(election));
        System.out.println();

    }
}