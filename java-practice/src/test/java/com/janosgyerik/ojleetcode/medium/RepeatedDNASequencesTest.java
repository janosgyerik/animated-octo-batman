package com.janosgyerik.ojleetcode.medium;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class RepeatedDNASequencesTest {
    public List<String> findRepeatedDnaSequences(String s) {
        int targetLength = 10;
        Set<Integer> seen = new HashSet<>();
        List<String> repeated = new ArrayList<>();
        int len = s.length();
        for (int start = 0; start <= len - targetLength; ++start) {
            String substring = s.substring(start, start + targetLength);
            int hashCode = substring.hashCode();
            if (seen.contains(hashCode)) {
                if (!repeated.contains(substring)) {
                    repeated.add(substring);
                }
            } else {
                seen.add(hashCode);
            }
        }
        return repeated.stream().filter(string -> s.indexOf(string) < s.lastIndexOf(string)).collect(Collectors.toList());
    }

    @Test
    public void testExample() {
        assertEquals(Arrays.asList("AAAAACCCCC", "CCCCCAAAAA"),
                findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    @Test
    public void testExample_AAAAAAAAAAA() {
        assertEquals(Arrays.asList("AAAAAAAAAA"),
                findRepeatedDnaSequences("AAAAAAAAAAA"));
    }

    @Test
    public void testExample_long() {
        assertEquals(Arrays.<String>asList(),
                findRepeatedDnaSequences("GATGGATACTGCATTCGAACCAGAGCCGGCTTTTGCGGGACTAGCATGAGGGACTTGGCT" +
                        "GTTGAGGCTGTACGAGGTCAGTCCTCCGGCAGTGCTATCGCAGGAATTTTTGCAACTCCACTGCTTATAATCCACCA" +
                        "AGTCCAGACTCAAAGCTCAACTCGGGGATCGCACGGTATGGTCACTGTCGCGCATGCAGTAATAGTCCAGACGAACG" +
                        "CACATTGGTCGTCCCCTGAGCCCGTGCCAGCCTAATACTTCTTATGCCTGCGTAAGTAGACTTTGCCAACGTAATCT" +
                        "CATCCTTATGCCAGATTATTAGTTCATTGAATGTCGGTCGCCGGCGCTCCCGCATTTCTTATCCGCGTATCTTGGGG" +
                        "TCAAGACGTCCCCAGCTTGTTAATACAAGCTACTTTCCCTCGCAATTACTAAGTTCGTGACAAGCGAATCACGCTAAGATGTTATTGGACTCTACAGAAATATTGAATTGACAACATTCGTCTGTTCAGATCGTCGTTCACGCCACTGATAGCGCAGCTCGAGCACTCTGGAGCCACAATGCGGAATGTCCAGAACCTTTGCGCAAGAGTCCGTGGAAAGCATAATCGTGAACAGAATGGCTAGCCGAGGTGCGCAAGGATAGGACCGTCTACACAAAGTATGGGCACCACGCACATCGACACCCCGTAGTGTGTCAGTCGGCTCAGCGGCTAATGGGTTCGGCGTGAGGAATAGAAATAATAGGCAGTGGTGCCAATTGTGGGGTCTTCTTTTGACTTTCTCATCTCTCTATGAATCAGATCGGCCTCTCGCCCCCGCCGGCCCTCTGGCTTTTTTAAATCCTAGATTGTGCACGTGCCCCGGTTTCCTTCAAGGCAAGTGAAGCGCGTCTTTGCTCTAAACCCACGGCCGTTGCACGGCGCCGAACAGGTGTCTCGGTGCGACCGGAGTGAGCAAGTTCTGTCCGCATCGTATGATTATACCCCCTCCTGTCACGGCTCGGGACTTATCGCACCACAGATCAGCTCGCAGCCCCGCGCGAGTACTAGGGACGGGAGGGAAACCAAAGATAATCGTCTTTGCATGGGCCGGCATGTGAATCATTCGTATCATCTTCTGGAGTCTTGTCACGACGATTTTCGATACAGACTGTTGACCCATCTAATCGTGTTGTCAGTCTGGGAACCGTACTTTTTAACCCGTCGTTCGAGCGGCCCGATCAGGGATGCCCGCAGTGTACGGGCACATCGTCGTCTTGGGAACAAAAGCTTGACGGACACCTCTATGCAGACATGAGACGTGAGGCCCCTGCAATAGTGCGGTCACAGGGACCGGCTGTCGATCAGTAGGTATAATCTTGATGTTTGCTGGGAGATTAACAGAGGGCGGAGTTCCGCATCGCCTAACCACTGACAGTCATTGATAGACGCCTAAGTTTGTCCCTGTAGCTACAGTGGTGGCAAAGTGGCCTTGGACGGTTCTGCGCTTGTCAATAAGTCTGTCCCAATCACGAGTGAAAAACTAGCTAGGGTCGGTGATGTGTTTTCAATCATATTTCTCCATCCATCCGGGGCTCCCTAGTACGGAGGAAATCTCCGGGTAACTCTGGATCTCCAGCATTGCGTAAGCAAACCGCCAATCGGGCCGCAGTGAGTTCTTAAACTACGGTTTGGCCCTAATCGCACTATTGGGTGTTGTAGATACGATAGCAAGGTGATTATGAAATCAAGGCACGCACGACCTGTACGTTGATCGTCGAGTGCTCTCGAGTTACTTCATGGGTCAGCCATGCGATTGTCCAAATGGACCGGAAAGTACACTACAAATTGTACCACTGTGCGTTGTACCTCACAAGAACTGTTTGGGTCTACTTACTTTTTACTTGGATCTTTCCTGGTCTCCCTCAGCGTAATTATTCGACACAATGCTGCAGCTGCGTTGTAGTTTTGGCGGTACAGGAAAAATTCTTGTGAGCAACCAGGCCATTCCCTGGAAGGCAGTCCTTGCGAGTATGTTGAGATATATGCTGGGGATGAATTAGAACATTATGCCATCTAAAGTTTGGATTACCGGGGATTCGGCATACCAAATGGATTCGTTGATTATAGCCCCCCCACCTCCTTTTAGGTAAAATGCCCAATCCTGGCGCTGAGCAGGAGGCATGTTGGCCTCTTGTCCGGTAGTACGCTTGACTAGTTCCTAGCGGCGCAAATCACTTGGTCTCTGTCCGTCCTGAATGTTACAAAGCCATATACATGTGTGGAGGTCAAGACATTCCTTATCCGCCCCCCTCGCGATGCAGTTAGATTCGCATTCAAGGTTGAGACCGGAGACCTTCTAACCGGATTTTGGAGTATAGCCCCTTGATAAGAGAAGGAACCATGCTGGGTCTCACGACTATTGAGTTCGGGAAAAGGTGAATGCTCAACGCTAACGCAGTTTGTTACGCCTGGCGGAATAACGTCAGGGACAAACTATATTCTGGCGCCCCAGTGTGGGCTCTTTGACGACATAGGACGGATTAGGCCGGTCTCAACCGCCTCGTAACCCAGGAAGCGGTTCTACTCCCGGCTACTTTTTTGGAGTGTGCAAGGACGTTGCACACAGTGGGTGTCAGATCTAGCCCGTCACATGCAAGTGGTCATATGGATCCCATAATACTCACTGAGTGTCTCGCCAACGGGACTATTAATAGACACGGTGATAGACGGTAGGAATTGTGAGATTCATAATTAGTAACAGTAGGAGCGCCGTAGGCCACGGACCGATACATCGGAACCCTTCGCCGAATACGTTAAGGGTTTGCAACCGGGGGTGCATCTAATCCTTGGGGCTGATCTGTCAAAGGCGTCTCATGCGTGATTATATTAGCGAGAACCTGCATCAATGCTTTAAATAGACAAACCGGTTAATTTGCCAAGAGTTGGGACTACCTGACGGCAAGAGATGGTTAATTGGGCGATTACTTCACGGGTTTGTCTCGATTAAAGAACTAGGATTGCTGTTGTCTTACAAGTTCAGTCATTATCCTTCTGCTATAGCTCTGGATTAGCAATTGGTTTGGGGAAAACCTTTCTCCGTACGAGTAAGGCTGTCGGTAGCCATACTGACTGATCCCGAACACAAAACACTATTCTGTGGAACCAGCAGGTATTAGCATACTGCCCAGTATTCCCGAGTTACGGTTGACTCGGGTCTTAAGCATTTTAAACTGTCCCGATAACCCATTGACTAAGTCCATACATCAGACAAGCTCATGCATCCACTTATACCCGGTGAGAGTAGTTTATGACCAGTAACCGTTACCGAGCCACAGCCACCGCGTGGTGTTGCGGCGCTGTACTATATTATTACTGATGCGGTCCCTAGAGGACTACGCTCCAAGCAAACTTATCTTATTGAAAGTATACACCTAGCAACCTGGGCCGGATTCAATCCGGGATCTGCTCCCCTAGAGCGTTTAATTCGGACCCCTAGTACATAACATTCGGAAATTGGTTCTGGCTTTATGTGGCAATCTGTAATGCAGAAAACATGCTATCGCGCATAAAACCAGTTAAGCTTGATCTCAGATAGTAAAATTCCTGTACCCTCTACAATCCCCCAGAGAAGCTCAGTATACGAACGGAGGAGTAGACTTCGTTACTTGATTCTGGCTACCTCAAAGTCCTCCCTCGAGTTAGAACATGGTGCTCCGTCAGAGGGTTCATTATGGCCGACACGCGAGCTTGCGCTCTGTATGTATGCCGGGGTCCTCTGTCCTGGTGGAGACTGACCATGTACCCCTTATGCCCGGAGAATACCACCTGCTCCACGTGATGCAGCACTGATTGCTCATGGGCGCTCAGCATAGAGATATCTGAGTAGTCCGATACACTTACAGACCGTAAATGCCACTCGGGTTCCTAGCAGAGTGAGGGGTTGCGAATTTTCTGCACATGTAAATTGGGCGTAGACACAACTGTAAAATGCGTCGTAAAAATACAGCTAAGCACCGAACAACGGGATAGGTATTTAGCTAACGTAATTAGTTGTAGAGACACGTAAGCATCCTAGGTCTGACGCTCCCACTATTACGGTGAGTTTGACGCAGAAGTCTTGGTTATGATGCCCTGGATCTGTGAGCTCGGCCTTCCCTGTACAGTGGAAAGATCTCGTTGCTCCGATTGACGGGTTCTGTTTTGTTCGTCCGCGGGGAGTTTCAGATTAACGTCAAGTAGTGCTGGACAGGTCAATAAGATCGCCTCCCTCAACCGTAAGGTCTCGAAGCAAAAGCTCGGTTTGGCATGGAAAACGGAGATTGGCGCTAAGCGACACGGACTGGGGAAGGAAGGAGAGCACAATCTCAGCTTCAAAATGTCCCACCCAGGCTGGGCCCTTCCACGACCTGATTATAATATCTGTATGTGCAAGCGGAAGATTCCCGGTACGTAGAAGAACTCAGCACAGAGACCCCCCGGGACATCGACATCGATTGAACTGCGGGGATGTCAACATAGAACGCGGCGCTCGTAATAAGGGATACCCGGGATAATACACGTCAGGAGGTAACCTGAAGGCGAAGCTCTACAGAGAAAGCGAACCTACGCGTTGATTTAGACCAGCCTACATCACGCTGAGCGCTAATCCACCCGGTAGATAAGGTGAAACACAGATATGGCTGGATTAGATACGGGCGTCCCGTTGCTCATTGGAGTGCGTGTTCGTAACAGACCCCAATAAGGCCGCGTCTGACCAATCATGCGAGAACAAAGACATCCGAACGTCATAAACTATAGATACAAACCACAGGGGAACGTTGCATTGGGCTTGGTCGAAGAATCAATTGAGGTCTGATTGGGAGGCGTCCTGTATAGGTCAATTCTTCTATGGGTACTTCCCCCAGGGCTCCCCATCGACCACTATGCCTCGAGTCTGTCTTGATTTCTGCATGTGCAGACAAGCCTTGACCATACGGGCAATTACTGAGGTCGCCATTCGTGCTACTATATCGTCTACTATTTGAGTGCAAATTTGCCAGACCTGTGGCCCCTTGTGTCAGGGGAGCACATATTAACTAGACCTAGACCCATACGGGTCACACATCTCGGCCGGGAAAATTGGTTATCTGGGTCTATTCTGTACATACTTCGATTTCCATACAAGGTGGATAACTTTTGGCACAGAACGTAGCAGTCGATCTCTACATCCCACGACCTAGATAATCTCCTACCCGTGGTGCGCTATCGGTGGCATCGAGTTGTAATTTTCAGTAGTCCTGTGCACCCGGGGGTGTAGTGACAGCGATTCATCCCCAACGATGGGGGCCACGGATCCACAGCACGGCCACCCGCGGGCCCTAGGCCGACCTATCTACATTGGATCTACCCCGACGAGGCCATGATATAAATAATCCATAGCTAAGCGCATGATTGACTCTATGTCACTCCGTTCTCTAGTGTCTCCATTTCTACTACTTTCGTCGAGCAAGTGTAACAAGAATGCAGTACCTCTTCCAGTTCAGTGGAATCAGGAGCATTGTGCGGCTAAGTCATTAGACCTCATCGCTCCGCGCCGCCATTGTATTAATTAGACGAGGTACTACCCTGTGTCTTAACCGTCGCAGTGGCAGACACCAAGTAAAGTCATGGGGTAGTTCTATCACTAACCAACGGGATCAAGGAGTAGGGACCAGACCACCTATGACGTCCCATCCAACGCGAAGCGCCGGCTCTGACGGGGAGCCGGTAGAATAGGGCCGGCAATCGGGGTCTTGAGGGAGTGTCGAACATCGCGACATGCGAATTCAGTGTTTGGCAGGATTGGGTAGGCAGGCTTCCTGGGGCTGTGCTTTCTTGTTAGTGTACGGACCATACCTTGGGTGAGAGTCTGGGGCCATAGCCTGGATAAAGCATTCCCGGCGCCTCCTTATGCTGTGGCAAGCGCTCCTACACAATGCATGATAACAACCGAGGTCGTCGCACTCACGCTCATGAACAAGAATCTCTACTAGGATCGCTTGCCGGGGGTACTTCGCACCGACTAGGTTCTGTCTTTATCGCTGATAATTTTTACTCAGGGTGCTCCGGCTCTGCACTAGTAATCATCCATGAACCTTTGGCGCGAGAGGCGGTCTTATTACCACGTTGTCATTGAGTACATACTCCGCCGGTCACGTGGTAAGACCAACCACGGACTGTTAGATTTAATGGGGACATATAGGTCCACACAATCAGTCAGACTTAACAGAACGGGCTCTTTTCGCTTTTGGAGCTCCTCTGTATAGGACATCGCTATTGAGCAAAGCATCCGATCGTAGTCGAATACCTCAAGTGAAGGCTTTGCCAAGGATTTACTCCCTGGTTACGTGATAGAGGTTCACCGCGTAGGTGAGGACCTGTGTGTGAAGTTTGTGCGGATTTCGCTGAATGATAGATTCATGGACTATCCGTCCTTTGAGATAGCGATTAGTCATCCTGCGAGACGTTACCATGTGCTACTAAAGAGACTATGAACCCAGCCACGTCATTAATCCGCAGGTAGTCTTGGACTTTTTGCTCGCAACCAACGTTTTTCTAATCCAAAGTTCCGTGCAAGACACGTACCATGCATGCAGCCAGAGTGCTGTCTCCCACGTTCGATGAAGGTAATAAGTGGGTCAAAGAGGGGGGACAGATTACGGGAGCGGAACTTTGTATGACTTATGAAATTAAGACTAGTTACGGGGATGCTCTGGTTATTGAACTGTACAATTTCGATAATGGCCGCGGTCTCGAATTTGCGTTCGCGGGCTCTTGACAATCTCCGTACAAATATACTCGGATACGATTGAGACCAATTAGCAGTCTGCGTTGGGGTTGTAGTCGCCGGTAGACATATCCCGCTAAAGAACGCGGGCGTATGCAGTAGTCAGCAATGCCGGTTGTCTCAAAGTAGTGCGTCCGAATTCATAAGGTATCGCCAGTCTCCCCCACAGTCTGCTTGAGAAGACGTGTAAGGGTCGCGACGGGTAGCTCTACTGGACTGTAATCGGTCGATTAGCACGGGAGGCTATCATACCTACCTCTTGCCTTGAATCGACTTATGGCTAGGCAACGTCACCGGGACTTACTTATCTGGTTTGCAGTGTTCTTTATAGTCAGGGTCCGAGGGTTCCTCTGCGAAGGCCCATCCGCAGAGTTACATAAGTTCCCCTTCGTGCCACAGCTCAGGTAGTTTTTAGAACATAACTAAAAAGGAATCAAGGCGCGGTCCTTCATACGAAATCATTTATAGGCCGGGATGGTGGTAAGGCCGCTACTTCAAGGTGCCTAGGAGAATTAGTACGGTTGTGGCCAATCTCTTCCGAAACAACATAAGATGGCACAGTCACTTTACGTAAGATCACGCCAAGTTGCGGAATGCATTATGGGTCGTCATCCCTGATTGCCTGACACGGATCTTATCTGCGCGTTCGTGTACGCGGTCAGTGTATCGAGTCGGCACGTATTCAGGGAGTTCTTAGTTGCAAAAGGCAGAAACCCTTTCCTCTCGGTCAGATCTCAAAAAATCGTCAGTTGGCTTTCCAGTATCAAAATAAGCGGAGTAAACTAGGGCCCCTTCCAGGAGGGGATGCGATATTTCTCTAAGATACCCGAGGAGCGAGTCCCGGCTAGTTCGGACCCACCGTTATTCAATATAACCGCCGTCTTTTCCTGCGATCAGGGGAAAGAGACTCCAGGTTTGGAGCACACTTGACGATATATAAACTGGCCTTGGTCCTTAAGACGTCCTAGAACGAAGTTTCAACACGGAGGTTCTCTGGAGATAGCCTGATTACCGAGCGGTCGCCCCTCCGGATCCACCAAACGGCTACCGTTCGACTCGTGCTATACTATTGTACGCGAGACAGGCCACAGAACTCGAAGGGGTATAATGTTGTTGCACCTGGCCTTATTACACCACTGACGGATTAACCAGCGCGTATAGTTACGGCGAACCAGCCTGTGCTAATTGATTTCTCGGCTCCACTTTGGTCAGGTCACTAAATGAGGATACACGCTTTATCAGCAAGAATTTAAATACTCAGCGAACGCGGTGAAGATCGTATCCCGGCCGTAGGGGAGGCGTTAGTGCTGCTATAGGGACAATTCATTGCCGAATTTTTTGCGCGCCAATCTAACCATGTGACGCGTCTTTACGCATGCTGCGACGAAGAAAGCCCGTTTAGGAATCCTCACAGCACTTACCATAGGCGCGAATCCTTCCGGTGACGACGCAACATACTGACCTGTGGACGGTTTGTATTTGCGCCGCATGCTTGATTCTTGCGGCGTAAGGGAGCGGCTTCGCAGACTACTGTAACCGGTCATAACGTCACTAAAGAAAGCTTACCCGGGCCAGAAACGGTTCCTCTCAATAGCCCACGAGGAATATTCGACCCTGTCGTCCCCGCCTCCGGCGGTCTTTACGACCCTCATCTTACTCTAATCCTACGTCCCGAATGTCTGTAAGCGTTAAGGCAGAGATACGATGGTGCCTTGCTAGCTTTCTTTATGATAATCGGAATTATGCGCACTCACCTTACGCCGGCAAATCACGGTAAGCCTTCCCCGAGACTATACACCCCATGTGAGCGACAGCAGTGAGTCAATTCGCAAAGAAAAGACCCTCCGTTGCGGATTCGGCGTCACGGGCGAAAGTCGTACAAGTAGGAACTACCCTGCTCCAAGTTACCTGTGTGAAACCTTTAGGGTGCAGCTTCAGATAACTACGGTCACGTTTTTTTCCATCCATGCACTAGGTAGGTTCAATACTCCAAACTGACTCTTGCGGCCAGAGGTGTTCGGAAGTGTACTGAGATTCGCACCCCGCCGAGATTGTCTACGATCAGTTGTTAGGCGGAATGAATGGGACTGTAACGGACCCACGCGCGTGTATACTTTTCCCTTAGGTCCTAAGATGGTTAGCATGTCCTGATAGTGCATCACCGTGCCTTGTATTTAGAGATACACTCAAGTCTGAAGGATCTTTTTCTATAGGATCGCAATAGGACGTGTCTATAGTAAGTACCAGGGCTTCCGTGCGTTCCGATCCGGGACGGCACTCTAATGTCGGAGAATCTGTCGGGGATATGAGGAATTTTACCACTATCACCTTGATCATTGGCCACGGCATGGCTGTGTATGGTCTGGAATTTATACATGAATTTCACATTCGAAACTATGCCGCAGACTCCCTGCGATCCCCTGAACCTGTCGGGGCTCGCGAGATTAAGTGCTTAGTCGTGTACGTAGCATTGGGCCATTCCAGAGCGGGAGCTGAGGGCCGAAGCCAACCCAAAGTTTTAAAAGGCGTCCTTAGGACGAGAGGCTAGTCATTAACCACGCCTTTGCGCGCATTAGTCACACCCGCCCTCTAGTGTTTCAGTGTTCACCGCCGCTATCGTGCTTATTGGCAACGGCGACACCCCATATATGGGACCTGGAACAGAAAGACGCGACAAATTATCCACGAGGCGTTCTCTACAATTTGTTATATCAGTCTAGGTTGTGGCGCCGCATTCTAACTAAGTTGCCACACCGCTCACAGCAGGCTTCTGTGCTAGTGTACGCGTGGCTCCGTGGCCAAAGAGTGCCAAACTTGGTTTGCACGCCATTACGTTTTCAGCTAGTCATAAACCAGGCATACACTTCACAAACTACACTTGCCATTCATTCTCTGCCAGAGATTTGCTTCAAAAGCAGAAGGTAGAACAGTTTCTCTAGGGGTTAAGAACGGGGCCGTCTACTAACCAGCTCCAACGTTATGTCACGTCACAAACGTATAATGTACGAAGTAGCATCGACCAGGCCGGCTATCTCAAACAGACGTGCCATCTTCCTCGCCTTAGGGGGTTCAGCTTCGAATTAGATCCCTTGATCTCGTCCACCGCCTCGAGTGATGTATATAGCTTGTCCTGGAAGCGTGTCCGATTTAGGCATAACAATGATGATATCTTACACCCCCACTCGTCTAATCTCTCTCACGCCCAATAGTTTTTCGTCCGCCCTCTGATGCCAGCACGCGGACAGTGGTCGTTTGCTCGCGATTCGGGTTGTCACTGCAATCACATGCCGGACTTGCAATTGCGCATGACGACAGGTCGACCTCAAACTATAATACTAGCCTCCAGTCTGTCGAAACTTGCGTCTTACCCGCCATGTTGGCGCGGTGATGGGGCAGATGGTGTAGAATCGTGGATTAGTGAGATTCTGGGCGTCAAGGTGATAACCTCATGGGGAAGGTCTTACCTTCCGAGGAAAGATGCTATTGTGAGTATCATCACTTTCTGATAAGTGTAGGGCTATTCTAACCTCAGAGGCGGTGAATCCGCCTGTAGACGCCTCGTGGCGTGCCGCGTTGAACAGGACACTATCCTCCCAGTAATGGAAGTTGGAATATATCACTACAGCCTACCGAGTCAAGCTCTGCAAGCGCCGTTGCTCCAAAGCTCTTTTATCTGCTTTGCCCTTCAGCGGCTGCTAGTATTGGACCCGTGAAAGAATCTCCCAGCACACTAAAACAGTCCGGTAAGTGCCTTACGCAGGCGCCCGTCACCTGCCTATGAGGTTAAGTCAGCCGTGATATAATTCCGCGCCGGAATACTTAACCAGGGAATCGAATGTATCCAGTCGTCTTCACTGGACGAGTCTGCACTGGAGCGCATCTACCGACGCCACTTAGCAAGTGCAACATATGCATTAGAGGATCAATACGTGAGAAGGAAGCATTATGGAGGTTAAGCCTTCTGGGATTGTATGGCTACAGCACCGAAGCAGAACTTCAGTGGAGAGCTGATACTCTACCTCTTTAGACGGTATACTGGCTCTGAGAGACATAGATATTCAAGGGATGAGGTTTGGATGTGAACCCTTCCTGGGAAAGACAGGTGTCATCAAGGTTTCCTGAAACGGCCGCATAGCCAGAACGTCTGGGGGCTTGACCACTAACACTACTTATAGCACATCGCCGAGCCTGCTACACTGGCATGCGATCAAGTCCAGGATTACGCCCTAAGCCCTTAGCCGCTTAGAAGCCGGAGTTAGGAGTATTGACTAGCCATGAGCGACCATCGAGTTACGATTCTCACCTGTGTCTGTGTCTGCAGAAATGTCAGGTTGCGTACGATCGATAACAAGGTGCCAGCATAAGAACGCAAACTGTTTCCGTACCGACTAACTCATAAGATTCTAATGAATGTAGTTAGTGCTTATACATCACACATCGATGCAGTCCGCTCAGACTAGCGAATCCTGCTTTAGGGATGAGTAGCCCCTTAATGCACGTCTCAGCACTCATGTCACTAAACACACGCGGCGCGAACTTTAGCTCTAGCGATCGATTTTGGCCTCTGTGCCTATTCATGAATTCGTCACCGACACACTGCCCGCACGGCATAGAGTACGCCATAGAGTCTTTGTTCCAACTCTTTTAAGTGGCCATGCACTCGTTCTAAGGATAAGCTCGTAACCGTGTCCCATTTTCGAGCACACGGGCCTTCCTTATAATAGTATCCGGGCTGTTGTCCCGTGATGGATTGGTAGGTAATTTTTATCAAATCCGATGCCACTATCTGGAAGTTACCGGAACAACTCTGATCAGCCGTCTATAATGAAAATAGATGAGGACGGCGTACGACCCGGCCGATTGAAGAACTTCATAAGTCCAACTTTACCAAGAGTACATCGTTCACGTCATTCATCTTACACGACCTGCTCTAGGCATCGACCGCTGCCAACGACCTGAGATTCAGCCTTTTCATGTTGTTGTTGTGCGGTCGGTGAGTGGTCAAAACAAGGCCTCGGTCTAAACCCTCTACGAAGCTCGTGGCTTCAACTTCCTAGCGCACTAGTCATCGAGAGCAGATACATCTATTCTTCTAAGGGCATTGCGGGTCAGACCTTGTTTATGTCGAAATAGGGTTTGGTTAGCAAAACCGGAGACGGTTTATGAGAGGTCTTTGTGATTCAAAGAACTCGCAGCCTTGGCCGGTCCCGGGGACGAAGGGGGCTAATGCATTACACAAGCCTCATCCGACCAGTTTCGCATCGGGTTACTGTAAGGAGAACCAATAAAATGGAACTCCCTGGCATGGCTCGCGGAAACCCTCCCAAGCATGGGGCCGGACAACGCTCTTTGGTATAATCCCACACCGAACTTAGGGGTTTAAGCTCGCGGTGACTGTCATGTGTATTCGGAAAGCTAACTCGGCTATAAGTATGCACCTCATCTGGGAGCGTCCCGGTCGAAGACTTAATTCACGCATATGGTTAATCAGCAAGGCACTTGTCACCCAGTTGTGGGCCCCAGACGGAGTAACGGCAATCTGCGGATGCTTCGTAACCCGCCCAATTCGAACCGCTTGAAACTAACGTTCTAGTGTTAACTTGTACCGAAAGATCCACCATTATAGGCGTCGGGACTGTCGCGTGTACATGGCCTTTGGGCTCGTGTATGAGTGTCATACGGCAAGTGCGGGACAATCACTTCGATGGCTACGCGAACAAAGCTCGGCAAGTCCCGAGCGCGCTTTCTCATGCGAGGGTCTCTCATGCTTCACCATTACGTCGTCCACCACGTTGCCGCCCGGGATCGATTACCCAGTTGCGGTCCGAACCTCAGTCATACAATACCCCCGCTTGGCCTATACGCATTTAACTGGATATCAGTGAATGGAAAGGATCAGATTTCCGGGCCGCTCACGGTAGTCTTTCTGATCCCGTCACTTCCTACGATTTCCGCAAGGCATTCATTATATCTGTGATGTCTCACGGAATCTAGTCGTGCATGAGAAGTAAACCGTTTAGCATTCCATAGCAGGATGGCCCCTAGACTCTGTCACTGTCCCGTTTATCTCTGTGCTGAATACGGCCGTGTTGCCCATTAGTTATGACGCCAGACCACGTAGTTGTCATGGACGCCGTCATATGGTTCACTCGCCCCTGGGATGCCCCCCGGGCTACCCACATCGGGCCATCCGATCATCAGTTATTGGTTTCTCAGCCGATAGGCTTTCGACGGCGGTGCCTAGTCGGGTTGCAGCAACGACGCACATGCCGCTCGGGAACCACAGGCACGAGTAAAATTTACATTCCTATTAGGCTCGCGGCGGCTGAGCTCACTCGATCGGTGTCTCTATCTCGCCTTCGCGAGAAACACCAAGAGACATACATCATCCAGCTTGGGGAGACGCGGGTCGGATGTCCCATAATGCAGTCCAAGATGCACCTTCACGATAGAATTACTCCATAAGCGGAATTGGAGCTGTGCAGGTGCACGCAGCATGCTCACAAAGCTCACACACTAGTTCTGATACCGACCCCGAGTGATGGGATGTGATAAAGGTAATTGGGTTAGTCGAATTCTTTTCTTTCGAGGCGTTGCCGGCCTATCTAACTGAAATAATAAAGGTCTCTAGTCGGTCACCGCTAAAGGCGGCGGTATAGGACCAACTGGCGACCGTTTATAACTTGTTTCTGGTCACTTGTTTGAACCGTGCCTGCTGCTATTTGCTGTGAGACAGCAACGGAAAAGAGTGGTCAGTCCAGAGACCTACGAGATCGCTGATCAGTTACCGTGTAAAGTCGCGTCTACCCTTTGCCCGAGTGTGTATCCTGATGAGGTCTGGTCACATACTGAGGACAGCAATGAGACAGACTAGGTCACCGGTATTGACATATAAGAAAACCGATAATTCATTCAGTTGAGGCAGGTAATCTAGTTACAACTCTCTCCTTTCTAGGTGCAAGATATACGATGCCAGGCATTAGTGGAATTGTGTCTTTTGTTACTCACATATGAATCCTCTTTATTGAGTGTTCCAGATGGAGTGAAGATAACCAGTCCCAAAGCGAATTACATGACAGCCGTCATAAAACTGTGCCTCTGGGCGCATTGGGATCACGACGCACGTTAAAGCTCATACACGAACTAACAGTGCTTAAAATGGTCAGTAGCCAAAGTTTCACCTTTGAACCGCTATTTAATAAAATAGCAAGATGAGGTATACTACTCTGCGGTCCTAGATGCATACGGCCGGCATACAACCCGAAACGAGTCAATGTAGTCCAATGACTCACAATATGTAGCAAGAAGCGCCAGTTTGATCCTTACGGAAAGGTCAATCCACGGCACTGGTTTGCTGTAGGTCCGAACTCGATTCGGACCACTGCACCCGCCCCGGTAGGCGCCGGCGCGCCGGCAAGCTCAGCCACTTCTAACATGGTAGGGTAGATTGTGTAAGCGCGTGTTCTGCGACAGAGCGAGGGCATCTAGACATATGATATAGTAGACCATTGGCAGCATACACGGATGGTTCGGCGAGCGGCTCGCGAACGAATTGCTCCACATGCCACTGATTTGTCCTCCATGTCCTATGTCACGCAGATTTCACAGTAGACGCAAACGCGGCTCCGGCTTAGTTGAGGCCGTCACACGCCGTGGCAGATTTGTCTACGTAGGTTAGGATGGGCGCTGACTATGCCATAAAAGGGGGATTGCTAATACGCAGGGCCGCTCCTGGATTTCGGACTAGACGGATGATCACCAATGGACCATAATAGGTCCAGCCCTACAGATGTCGGGGGCCCTAGCGAAGGAACGCACAGTAAAAGTGTCTCGTCTTAAAGCGAATAGTCTCGTACCCCACGCTGTACCAAGTGCACACCCTCTGTTGGCCGGTTCACGCATGAGCTAATGATACCGGTGTCCTGCCTCATGTGTTATCGATGGGAGTACTGGCCGATTCTGTCGCCTAGATCCAGGCATAGGACGTTACTGTTAACAAAACTCGCGGAAGGGTTCTTTCGTCGGGGCGGCTATAGTTCTCACCGCGGGCTAAGGTTATGTAGGCAGCCTGTTCAGGGTCCCCATAATGGTATTTTGTCACGGTCCGCGACTTGACGACACTAAACGACGTGCAGACCATGACTGCAACGTCCGTGACTTACCTTTGTGTACTTCCGCGAAGTCTAACAGGCCTGAGGACTCAGTCGCCTAAGGCATAAGCGCTCTGTGTGCGGCGCTCCTTTCCGACTGACAACAGCCACCTAAATCTATTGTTTGCGATAGGACGGGGTCACCCATAGAGCTTCGGGTATTTGCAGCGCTATCCGTGGTGTAGAGGATCTCTCTTCATGCGCTTGCCATTTCTGCCAACGGGTTTGGTATCGTGCTGTCGTCTGAATTGCTCGGTCAGTATTCTGTCCGGCGGCGAAAGTTACTCTCAAATCCAAGCTTATTGTTGTTAATCCGTGGGTGTTAACTAAGTAGCAGGGTGCTGTCTTGAATATGATTGTACACGTTTATTCTTCTCAGACGGTCGGAGGACCTCTAGTTAACAAACTCGGGTTAGCTGCGGTCACGCGGTGGGTTGTATTCTAACTCTCTATATGGGCTTGCCCATTCGCACACTGTCAGTACTTTCTGCCTATGTTGGCGTTCAAACCAGATTGGGGAATAAGAGCTGAGGTAAAAGATCGAAATAACGAGGTATTCTTGTCCGTTTGTCAAGGGATCGCGCCCTTGCCTAAACTGGATACAGGATTCCTGGAAACGCCGCTCGGTTCGCGCACAGACTGTCTGCGTCCACTCGCCGGTGTGCATGTATCAATTTCCCCAATTCGGAATGCTGGTATTTGTCCAGCCTAAAAACGGATTGGCCCCGATACTCGCTCGTGTCCGCACGCGACGACCAGTTCAGCGAGAACGGGCAAAGGCGTACGCAATAATTTCGACCATCCCGTAACGTGATTGGCGCACATGTACAATACAGTCCCAAGAGATACTATAAGCACTCGAGTAGGCTGCCTAGACGCCGTATTCCTCCAAGCTTGCTCGTGAAAGATTCGCCTTAAAAGGTATGAAGGAAGACTGAGACCTGTCCCGCTCCGCGTCATCACGACGACTTACCAAGGGCTATGATCCTCACCGTTTGAGATCACGTTACTCCGCCCCTGTCGCGAGATGATCAACGTTGGCTTTGGAGTACCAGGCTTACAAGTATAATCGGACTTATTATTTAAGGGACGAGTTTCCATCGCGTACCAAGACTAATGGCATATGATACCAGGGATAGTAAGTGCGTATCCTTTTTAACGTACCGTTGAGCTTCTCACTAACAAGGACTCCATGTATTCTACCACCGAGCGCCTAGCGGACGTAGTTCGCTCAACGATGCCTAGAATAACTACGAGTTACACCTGGAGAACCTCCAGTCCCCGCGATTTCGCGATCTCAGCCGTATACCATGACATTAACTGTCATCGTTCAGCTTATCCTACGTTACCGGGGGTCTATTAACCTGATTTGTGCGAGTCCCACATGGCCGGATAGCCACCAGCTCAACATACCCCGTCCAGCTGTGTAGTCGGTCCGAGATCTGCGTTATGCTTTCTCTTAGTCAAGAGATAAGTAGTGTAACTTACTTTCTGTGCTCGGAAATCAGAAACTGGTCTCTGAGTAGGTCCTGAAGGTGGTTCGAATTAAGTTGATACCATTGGAGGAGCTAATCGTAGGCCATGTATCCCGCTGAGCGTGGGCTTTATGGCACGTAATTACAGAGGAGCAGTAGTACTCAAAGCAGCGACCCCGGTTAGTCTGTCGCCCTCTCGAGGAACAATTACGGCTGTAAGGGCGCGACAGTTCGTAGTACTGCGTCGGACAGTGACCAAGACCAAGACTTAACTGCAGTAGTGACCATTGTACGTGTCAATGGCGTCTAGATGCAGGTTGACCGTGATCCTAAATAGTCTTTACCAGTATCCGCGGATCACCCTGGTAAGAGCTTACGCGATAACCTAGCGATTACTATGTCTGGATGCGATGATTTCAGTCATGCCCGGGACGTCGGGAAAGCATGCGCTTATATGTCGCAGAGTTGATTTGCATTTGTAGTGTCCTCTATTCGTGGTACCAATCGAACCTAGATGTTCTGAGGTCTTAACTGATGCCTCTCAATCAACTTGCCTGATAGGCCGACGCAGTGTGCCCGATTGTGGTTAACGGTAGTTAAGCAGCGCGGCGAGAGGCACGATGGCCCATTACAATAAGCATCTGTAGAGTTCACGGACAACTAGTGTTGGAATGTGCCTCAGGGCTACAATCAGGTGCCTCTGCCACCCGACTAATAGCAACATCGTACCGCCCCCGGTTGGAGCCCAGGGCGGTGTCGGCGTCGTGGCACGTACGAAGACATTCTCTGGGAAGGATGGTCCAGCCACGCATGCGTTTTAGGCTACTGTCATAGGATAGTATATCGGTAATGCTACTAATTTTACCGACATGTTCATCGCTTCCGTTCAATAGTGGTCCATGAGTGCATTTTCCCCTCAAAGATTAGATTCCCATGTAGCCCTCCAATTCACTATCTAGGCGATATGGAGGGCGGGGATTCTTGACCGACACGACCAAGCTTCTCGTCCTCAGGTCCTAGTCCCCGCATATAGAACCCTGGACCGAGAAGTGATCGCGGCCGACTTTGTAGGCCGGGGGTTCATTTGAACTAGCTCGCCGATTATAGATGTGAGTGAACCCGGATATATCGCTCCGACTTAGCCCCGTGAGCCGAATCTACTTTTCTCTCAACGCACGTACGTGAGCTCCGGATTCTTTCTCACTTCCCTCGTCTAAATTGAGAGTCACGCCCATCTTAAACGCGAGCAGGCCGTCAAAGTCCACAGGCCCTAATTTTTCCCGCAGTCTGGAAGTGAGTGTTCTTATTTAGGTTTCAACCCTTCGATACATGTCGTTTCTCCTACCGAATGTGATGCGTGCGCTCCTTCGGGTCAGTATAAATGATGACGGACATTGGCACCTACTCTATTTTGATCTGTTGACAAACGTTATCCACTCGATGGACAAAGCGCACTCAACACTGCTGCCGGCACATTACACACTTCCTTTAGGTCCTCAATGCCGATCTATCTACAGGCCTGTGAGGGTTACGTGGGTACAGTGTGCTAGGAGTATGTCAAATCTGATCTACCTCGAACAGGAACCCAGAAAATAAAATTAGGGGCTCCTACTCCTAATCTATTCTGAGGGAACCCGTTTATGGCCAGTGCGCTAAATTATCCTGTGAGTCGAGTGAGCATGGCAGACGTTGACGCCAGGAACCATCTTGGCTGGAGTAATCCGGTTTCTCCATGCACACCCCCTAGTCATTCAGTCGGAAGCAGAATCTCCGTGGCACAAAGTAATTACAAGGACTACCTTTAGATCGGGAGGCCCGGCCATTCTCCTGTTGGACTGGAAGGTTCCTGTAATAGAAACTAAGATGAGTCGTCAACGCTAGGAGCTGATTGAGGATTAGTGCTGATACGGAAGCGTGGTTTGGGTATCGCAGAGTGGCTAGGATTATCCTACTCGGTGATGGACGGCACGAGCAGTGCCCCCGGATTCAGGTCCTTTGAACTTAAAACGCGCAATCCTAAAAGCTACACTTCAAAATCGTAGCTGGAACTAAACTTTACCCACGAAAATGCAGAATTTAATTCTCGAAGCGGCCTGGGTAGTGGGATTAGATTATCCATTTATGAGGTGTAATGCGCATTATCTTTGTGTCTGCTGTCCTGCATCACGAGCGGTTCGACTATTTTTGAACAGTGAGTGCTTTGCCATTGGGCACGCTTCCGTGTTGGTTTAAGAGATGGGTTCCAGTAGCTGCGCTAAAGGGGAAACATGCGCTACTATGCTCACCGCATAGGTTCGGTCGTTTGAGGATACCATCAGGCAAAACAGCGAGTCGCGACATCCCGACCGGCACGACGAATAGTGAGAACCTGTTCCCGTCAGGTGTCGATCGAGTTTCGCTATAAGTGAGCTTCGACCGCATGTGCACGGTCAGCCTTCCCAAACCATAGACAGCCCATTGTCATACTAACGGAATCTGTGGGGACTGCTGCGTGATTTAAGAGAACTGGGAAGAGCGAGGCATAGCAAAGACACAGATAATTTAAACTTCTGTCCCTTATGTCGGGAACCTGGTTATCAAATAGCGGTGGGCATACATGGAAGAGCAATTGTTTCGATATTGTTATGACGACGATGAATCGCGTGTGCGATGCCCGGGGATACCGTCGGAGCCTAACGCTTTAGATGATGAGATTAGTGACGGATCTTGCAGAGCGGTAATACTCAGTATTACTAGTGCCGCTCGATTCAGCAGTAAATTTTGAGGTCACCCGTGTCATTAAAGAAGCAACTTCTTTCCTCCCAACTTGTACGAAAATTAATACGAGATCCTTTACTTATCAAAACAAAGCGCCATTGTTTTGCAGATCCTCGCGGTTGGCCCAATGCCAGATCTGCTACGATCGGGTCTAGCGATACCATGGCGATCGCTTTGATGATCACTTATACTTAGGAAACGTCTTTATCATTCTAAAGGCATGTCGCATCTACAGTTACTGTATCTCAGGGAACTGCTCAGCACTGCAAACTTTGGGACAAGTCAGAGCCAGTTGTTGCGAGGTAAATTAGTGGTCCGAAGGAGTACGTGTTCCTCTTCGTTGTAAATTCAAAGGCATATCAATTGGAAAAAGCGGCCGCAGTTGTAACTGCGGTCTCAGCGCAACGCCGCCTTCCGAGTACCAGCACCCCTAGCTGTTCATTTGCCGAAATGTCCATGGGTCGTTAAACTGGGCTTAGTGAACCAAGGCATCAGTCGAGTCTCAGGCGAGATCCCTTCCTCATATGGTATGTGTGGCCTGACACATTCCTACACCCCGACGGGCGGGCCAACCTGGTAATTTTCGTCCTGCGGCCACCTGCGAATACGACATCGTGCATGGAGAGGCCTAGGCGCGTGTGGGTCCCCAAGTCACCATACTTTGATCCCCTTCGGATCTATTGCATGTTCAAGACGCAGCCTGCGCTCTCGGGACTCTAGTCGATCCGTTTATCATTTGTTGGGTGCAGCGGGGTTGAATTCCCTCCTAGTGTATCTTAATCAGTCGAATGGACCGCCTCCTTGGCTCTGACTCAGTTGACGGCTTAATCCTCAGTATAAGGGTAACGTCTTCTAGCAACAGACCTGCAGCCAATGAAGCCTCAAACCGGTATCCCTTCAGTATTAGTCTAACCCGTAGTTTATATAAATGCATAGCCGATCATTATATATAGTATGACGAAAGCGAAAAGAACCGGGAGGTATAGGGTTTCGTCATAAAGATGATAAGTTTGAGATACAGTCTAGCGCCGCCCGAAGACCCGATCGACCGGGGCGTCGGGCATAATTGAGTCAGCGTTTGCGGGCCTGATTAATGACCGAATGGATCTTCTCTCGTACTGTAACTTCTCTACAGTGTGTTTCAGCAGTGGATGCGAATAGGCAATTTGACAACTAGGTCAATATCTCGGTTGTTTCTATTAGGTGACCTACTGTGAAGAGCTAACTACCAAGCCGCGCTCCTCTTGGAGTGCTTTTGGTTTCCTAATGATGCTTTGGACAACCTTCTGCATGCGTAAAATCTGACAAAGAAGACATGAATAGGCATAGCCTCACTAAATTTGCGGACCTGGACCATCACTTGCGTTTCCACGCCCTATCGTTGATTGATGAGTGTTGGTCTAGTCCTGGCACCGCTCTTCCAATAGAGGCCCTTCGATGATGTCGTGTAGCCACCGACAGTTCATGGGGATCACTGCAAAGAATGTACACCGAGAAGAGTATATAAGTTAGAGCGAAAACAAGAGGCCCGTGCATGTTTATCTCGAGACCGTTGAAGCGAATTTCAGCTATGTCTTCGTCGTTTACTGTAATGGTCTTTAACCTGTTGAACTGAACACGATGGCTGGTACCACGGAATATGTTATGTCTCGTTCATATGCACTGCCAAGGTTGCATCCTCATGACTCTACTAGCACCAGCTTGGTTTAATTAAGGCGGGGGCTGCGGGCAATCGCGGCCCGCTATCGCGGTAAATTCCAGTACCAATGAGATGGTAACAACTAAAGTGCGAGATAGTATACGCTGGTTACGAAGCTGCAAGCGGTCGACAATGTGGGCAACTAATGACTACGAAAACAATATAGCGGGACCGGCGACGCTCCAATACCACGTACTTGGATGGGTTGAGAGATCGCTTCACAAATTGTGTACTGGCACTGTATTGGAAACTCGACATTGTGGAGGGTTGAAACCCTTCTAGTCAGACGTTTGCCTCGTGGATACGCTTGCACCAAGCATTACCGTTCCGGCCATGTCATAACATGCATGTGCGGACTTCGTTCGTTGTCAATGACTGACACCCCAGGAGGGATCTCACCGTCCGATACTGCGTAATTACTTAGAGTATTCAAACAGGGGTTGAGTACAGGCTACGGCGGGAGCGGTTGTCGTGAGCTCTTTGCCTTCTCCGCTATTTGAGATAACATGCCTAACCCTAGCGGAGACCTCATAGGCTAGTCCCCTCTCAAGAAACGATAATCTCAACCGACCGTACCACCACTTACGCTAGCGCTCCGAGCGCTATCTTAGGCAGACCGCTTCTCCGGCTCGCTAGATAGTTAGGGGGTCTACAAAGCTTTACCTACGTTAATCAACACTTAGTGAGCAGCGGGACATTTTGTATTAGAACAAGGCCATTGGACTAAGATCAGTAGTGAATCCGTATACGTCCGGCCCAGCGCTCCATGTGTTGTTCCAGTCAGAGTAAGTATCACAGGCCAATTCACCGGCGTCCCCAAACTGGCCCCCCCTCTTGAACTCAGGCGGCCAGCTTTGGTTACCGAAGTACACGCACAGGTTTGCTGGAGAGAGCTCAATCCAAGTTGCAACGCTACCGTTGAACACACCTCCTCATGCCTACTATGGTGAACTTTTCTAGCTAGACTAATCAAATAAACAATCTGCAAGCACCAGCCGAGAGGTGGGCGATGGACATGTTCTCTTCTTATCGGTCCGTGCCATTCACATACAGTACCCGGCTGGCGACGGTACATATAGTTGATTGTGGGTACTGCTCGCATCATCCGACACTAGGTTATACTCTTCTTTGTCGGAATGACCCTATATCGATTCGATGGGGTAGGTGGCAGCATGCCCGGCTATTAGCAAATGGGAATCATGAACCTGGGACCCTACCGGATTGAGGTGGCAGATCTGTAGTCTTATGTCTTAATTTTCGAAGCATTCCAGGGTAAGCGACGCACCCCGTAAGATCTGGACCTAGTGACTTATCCCCCACCATGTTCGAACTATGTTGAAATAGCTGTTGGGCTCAATAATTGCGCTTTTCTAATGAGATACACACGCTCTCCTCAGGACCAGCTAGGAATCGCTGTCCTCCGCAGAAGCATTCGGCAGCACTTAAAGACAACGATGATTGCCAAAAGTACTCCAGCGCTGTGGTTTTTTACGCGCTGTTCTGGCCAGTCTTTGACTAAACGCCAACGCAATAGAAAGCCACGGGTGTTTCTGACAAAAGTCATAGACTAGCTTCACATCTGCCTCTGGAGTAAAGCCTCAACCTATCGTTTTTCTCAAATCATCCTCCGCGTTGCGATGCAGCAGCTAAAAAACAAGCACGATAAGACTAGATGGTATAGACACGATCACTATACCTCGATCGTACGTATCACATGCGGGAGCAGTTATATCTGGGGCGCCAACGGTGCTGGACTTCACCAATTCAAAGTTGGTATGTGATCCTCGTCACGATGGATATTTTGGCCGTCGGCAGCTTGGCGGCGGAAAAGACCGCGGCCAGTTGAGTTTACGAACCTCAAGAGGGCCGACCGCATATATACGCAATTATCCTTTCAGCGAAACCTATAAGTGCGCCGTTGACTCATTTGTGTGACAAGAACTCATCGTAGAACCCGCTGTAGCCTCGCTGGCTCGTGGGTGGATTATGGGACAGCTTGCGGTGACACTGACAAGATATTAATCCACCGTCAACAGGCAGAACCCATATATAGGGTAGACCAATGTCATCTTCAACCGGTAACCTGCCACCCATGTTGCTTGGCCTCCAGTTTTCATAAGACGCAGGTCTTTATGCCAAACTGTACGGATCATCATGATTCGCGGATAACGGACCAGGCTGCTGAGCTAGGGGTATGCAGTCCCTGTACTGATTATACGTGCAATGAACTCGAAATACCACTCTGACGGCTGACTGGTCCTCCACAACGAATATAGACGATCGGCATGAGCTCTAGAGAAAGGGTCGAATCTGCCAGGCACGTATCATAAATCTCTTCTTTCTTTAAGCCGGTTGGGTGTATGAGAAGACTTCGGGCGTCACAATACCTGGCGCCGTACCCCATACGTCTTTACTGACGAGCCGAAACGCTATGGCAGGCATCTTGCCACGGAGCCTATAGCAAGCCCCTAAGAGGCGGGATAAATCATGGACTTGAACCCTGTCGCAGTACGTAACCATGGTCTAATCCCTCGGTTTAGCCAAGTAGCTGGGCGACATTGCCATTAAAGCTTCCCTTAAGCGATGAATATCACTCTGTGCCCCTATCGCGGCTCACTACTTTCTGAACTGCGATAGAATGCCTCTCTATCTACCCTACACACACAACTGCGCTGGTGCCCCACGTGTAATTTTGATAAGCACTACTTAACCCGGCCAACCGATATGTTGAAGTTACATCTATCTGGGGGTGGTGCACCAGAAGACCGTATGTGATTTATGGGGGTCACACGTCACGAAAAACATTTCCCCCGGCCAGTGGTGCAGCCACTACTATACCCTATTGAGTCTATAAATGCCAAAGGCAGTAATGCGTCTACCTACACCGTGGCCTGTTCGACCGACCCCACGTAAAGTAATCTGACTCAACGCTGCTCCACTAAATTCGGAAACATATCAGCGTTGATGACAACGGGTTCGAGAGCATACATCTACCAGGCATCTCAGTCCTTAGTACTTGATGCTTGTATGGGCTTATGACTGGACATTTGGGTATGCCCTCCTTATCGATGATGGTTGATGACCCATGGTGAATGGTTAGATCGCGGGGAAGGTTGAGCCATCTCGATAAAGACCGGCTTGGGAGTGCGAATCGATTATACAGAAGGTATAGCAAGGGTGTAGCGCAGACACAAAGCGGTTCACAGCTTTTCTGTTGGAGATTTTAGCATTGTACGAGTCAGAAAGCAACAGCAGTAAGTGTCGTACAACGAGTCCACCATACCGAAGAACGTAACGTCATCGGGCGTTGGGATAGAGGGTCCATCACGGCCCGGAAAACGTCGTCCTGCAAATAAGAGCGTCGTATTGATGTATAAGAGAGCGCGTAGGCTACAGAGCAACAGTCTGACACAAGACTATGAGCTCACATCAAAGTATTACACCTCTTCTAGCCTTGGCAATACTTCGGCATTCGGTAGCCTATGGTCATTGATGCTGCTTCCGCATCCGGGATTTACTGTCTCGATCTCTGTATTTTTCACGTCACGGGTGGGTATAGAAGCGCTGGACCGCCACCCCTGAGGGGATGTGTGCGCGAGGGGTACGCTGGAATCTTGGCCAAAAAGCGCGGGTAAGGGCCGCGGACTCATCTAGTGGTGCCTAGAGCTTCATGATTGAGAGAAGTAGTCCTACATTCACGCCCCGACTTTGGGTCCATCCCAGGAACCCTTAGATTTGGCTAATGGTGGAACGCCCCGAGTAGCCAGGAAGGTGTCCCATCAAAAACGCGAATTCAACGCGAGGTTGCTTCTTAAATGTTCGTCTCGTATGCTCAGACCGACCATCGCCTACCGTGGAAGGCTAGTGGCCGTACTGGGTGACAGAACAAAGGTGGGTTCACCGTGGGCCTGGGTTCGGGTGTACGACGTTGTACTAGTCTATCCGCCCTACTAGTGACATACAGGCAAAATCTACCCAGATTACTGTGAACCGATGACTAGCACCGAGCCTGTTCGTGTTCACAGGAACGGTCTCGATATGCCCCAATCCCGCCATTATCCAATATGATAAGGAGTGACCCTCTATCCTCAGATAAGGTCGCGCGTTGTAACCAGGTCGAACCCTATACTTACTACTTGGCTGCCTTAGGACAGTTGTGTGTATTTGTAGCTCAGCTTTTATTACATCATGAATCACTTTGAAAAACCATATGTTGCCGTTCCTCACGTGTCAACACTGGTTTTTCCTCACCCAGAACGCTGTGCTCTACAGGCGCAGAGGTATGAGGACTGGCGCGACTTAGTTCGCGGTTCATCTGCAATTATACGTAGGGTATACACAGATCTTCTCGGGAAAAAGAGAATTCTCGCTGTCGTCGCATTGGGGACTCAAGTGTGGCCTCCGACCACATTAAAGTGATGGCGGTAAGCTGTTAAGCAACGAGAGCAAGTTAATTTCAGTGGCATCCACCAATTAGTCATAGCATGCGTGTTCAATATATGTTCTCGAACACAACTCTGCAGGGACGACGAATGAGAGTGTGTTAACTCACAAAATGTGCTCAATAAAACCTCTTACTTACCAATTGAACTAATTATAGAATATGGATTTGTTTGTTCCTGTTAAGTATCCCTCTAGCAGAGCCAAGCTGCGTTACCTTTACTAAAGTTGACATGAAACATGCACGGGTGGCGTCCAGCCTTGCGCAACCTGGATGAAGCGTAACTAGGGTAAAACAACGTACATGAGGGATGAGATTTAATAGTCTCTGTGCCATAAGGAGCTTGGTATTTAAGATCGCTCAGGATGTGGTGTCCGGAGCCATCGGCGTGCCGAAGGTCGACGCTCATCATTATTCATTTTTGAATCTGCTTGACTGTTTGCGCACACCAGTGCTTGCCATCGCATAATGGGAATGTGTGACTGAGTTTTGGTCGCATGTGATTGTCAGCCCTACGCTTCGAGGCGCATTGCTAGCGTCGACAACTCGGCGCGGTGTTACGCTTTCAAGCGCTCAAATTGCGAATTCCTAGCTACTCGTTATGCCGTAAAGGCTCACGAATACCAGAGCCAACGAGACTACTATTCTCAAAGTGCATACTCACTAGAAGACATAACGGTCATATGTTGGTACGGTCGCTGAAGGTGTCTAGGAAAGTCATCGTCAGTACGTTTAAATTGCAATATACCCTACGCAATATATAGCAATTGAGTTGAGCGCGATTGTTTGTGAACGGTGGCGGTGCAGATTAGCTCGTAGGCCCGTGGTATAAAATCGGAGCGCCGGTGGAGTCGCCGCGAGCGAGGTACCCATAGGACGATTTAGAAATAGGGGGAGACCTCTTTGATCGCCTGGTAATCGTAATTAGCCTACCAAGTGTGCAAATGTGATCAGTGGACAGCTACATTCTATCGCCATAGTGGAGCGTGATCCTTAAACAACACGCAAGTAACAGATAACACGTCGTCCCTGCGCGATTAAGAGAGATGATAATATCCGAAAATGTGGAGTGGGCTTAACAGTCCGAATGATCCTAATAATGCGACCGCCTCTTAATGGACAAATTAAGTCAAGGTCATCTCCACGCATGGCCCCTTCAACGGATTGATTGACGCTACCGCGTCTTGTCCAGGGAAAGTCGGGTGAACTCGACGAGTGAACCTGTGTTCTGCAGTACAAGCCTGCAGCGTTAACATACGGTGCCCGAGTTAAATCGACGGCCCATGGCGACACTAAGTGTACCTGATTCTTGTTCAGGTGAGGCGGTATGCGGTTAATTACGACAATTACGCGGCACCAGTCGCCGGCTATTCGTGATTCACTCGGGATGCGCACACTTGTGTAGTTTATCTGCACTGCGCGTCGGTACTGCGCTTTGGCGGAGTCTGCGCCAGAGACACTGGACTGCCTTCTGACCCCTACCGATATGCACCACTAACGGATGATGCCGACATCGACCATCACACAAGGTAATCATCACTACTGGAGGGAGGGTTTGGGTTCCGCTTATCGCGAGCAACTGTATGACTGAAGACCTCATGCAGAAATACTCTCTATGGCATCCAACTTTGTCGAGGACACTAAATTAGTCGTTTACAAGCCACACCGTCACGCCGCGCAATCGAGGATTGTACATGTGAATATATTTTAGGCCCCGTCATCTCGGCTTGACGGTTTCCAACGCGGACCCCCGAATCTCTTCGCATCCCGCACTGTGTCCACGTCAGCAAGTGGTAGCCACTGATGTCCTCACCCTTCGAGCTGATCCGTAATCATGTATAGAGTGCGTAGATTACCTAGTTCGCTTTAGTTCGTTGGAAAGCAACTCCGCAGCATTCTATCTCGTGGGTATTGAAAGATTTCGTTTTACGCTAATTGAGTACGCTGTCCTGTTGGCTTCTCAAGGTGGTCTACCTAAGGGCTTTCCCTGATCTTTCAAACCGACTCCCCGAACGAAAACCACAGATTCTGTCAGTATGCCCGTGTGAATTCCCATAAACCTTGCAGAAAGCGCGTCGCAATCATACTGCTATGGTAGTGTATCAGGAACTAGTGTGCTATTGCCGCGGGATAGCCTAGGAAACGACGTACAGTACTGCTCTGGCACCCATGAATTGCAAATTGGAAGTAATTAGACATAGTACTCCGGAGTTCAGCGGTTCTCTTTATTAACTAAATGGAAAAAGGGGGTTAAAAGATACTTTCTGGCCTAGTCACATAATGGCGGAACACTATGGGATCAACGCCCACAAGACAGTGGGGTTTTTGATCCGTGATGTCTTAAGTAGTAAAGGCGACACATCATAGACTGCGGGGGCCTAAAGCTCCCAATCTCACGGCTACTGCCGTCTTTGTCTTCACAGCCTTAGACATGTAATTGCGGTGTGTGGTGACTTAAAGAAGGAGACGATACATCCTGGTAACCTCCGGAAGGTGATATATGTGTAAAACTGGCTACGTTCTTAGCCGGGCGAGTCCCTGACTTCTAACGGCAAACATAACGACGCTGCTTATGGCTCTTGGGCATACTGGACATACCTCAGCCTAGGCAACCTTCCATGGCTCACCGTCGGCCTGAATCACTCAGGCCCCACTGAGGCGCTGGGATATTGTCATGGGGAGGGATTAGCAGTATGTAAGAACGAAATCGGACTAACATTGGGGCAGGACGCTGAGCCTGAGTCATCACTTACGAAGGCGCGTAGCGTTACTCCCTACATAAGTATGGAACCTTAGAAGACAGAGCCGCCTCACTAGTTCGCCCATTTAAGGATGGAGAATTTATGCATGACTGGTGCGAGTATTTGGCCCACTGTTTCTCCTTGGCCTCAAGTCTTTGAGGTCTCACGTCGAACGTAATTCCCGGTGTCCCGATCGAATTACCAGGCGCTGCCATTAAGCACGCGTGATCTCGAATGCTTCTACATAAACGCGTTATGGCTTTCAGCGGGATTTGAGACCACGGTATCTTTACAAACGCCCAAGCCAAATAATAGAGCTTTGCCAGTGTGAGCCTAACAGGGCACTCTCTTTGATGCAACCCCAACGTTCCGCGGGGTGATCTTTCTCTGTAACGCACCGTTTAATATGGAAGTCTTTCTCTCATAATTCGTGGATCGCAAATATGTTAAGTACGTTCGTCCACGGTCGCATTTCGCTCACACAAGCTAAGAGGGGATGATTTTTGTTGTTGCGTACATCGTGTCAAGCGGTGGCCAAACGGGTACGCGTTGTACAATGGCCTAGAATGCTGGATCTGGAAGAATGGTTGGACTAATCCGTCGCTTGGTCTGGTTATCCGGAACTCGCACAGTAGAGTCGGCAGGGCAGCTATTCCACGGAAATAACATTAGGTAGATTCGGAAAATAGGTACATTACGCAAGCGGCCGGGCTTTATTCATGCGAACGGCTCCATATGAGCGAGCTAGCACTTGGGCAATGCCGAGAAAACTCACTTTCAGAAATGCCGGCCGTTAACCTTGAACGAGCTAATGTTAATGCCATGTTCGCCGGCAATAGGGACAGTAGCTCCCACAACACGTTGGCCGGAATCTCCCGTATCTCGAATCTAGAACGAGACAAATTTGAAATTCGTAATGCGGTCCAAACTGTCTGATTTCATAGGGCAGTTGTCTCCGCTTAAAAATCACGATAGTTTCTCCCTGCCTGCAGTATAAGAGGATCACCGTCTGACGCCCTCTCCATTATACAGTTTATGGACGGCGCTTGAACTATCTCCGCCCTTTTCGCTCGTCTACTCGCGCGGCACGATCCAATTCATCCTTGTTGCGGGGAACTTGTCCGCGATTTGACGGAGCCTCGTGGTAGCGAAGTGGAAGATTCGCGATTATCACACTAATGAAAAAAGTTTTCGTGAAGAGTGTGACCGTCTGTGGCAATTGCTCTTTCTATTCGAACAAGTCCGGTATAATAGCGTTCGGCACAAGTACAATTCCGATGCGAATGGTTGACATTGCTGGCCTGCCTCCTCCTATCGCGCGATGTTCCTACAAGTTAAATATAACTATGCCTGACCGGCCGCAGAAGAAAAGGCACATATCCTATTATCAGTTCGCCTTATATCGCAGCCGAAAAGTCCGCCTCTTGATTAGCCTTCTTTTGCTTGGCAACAGGGCTCTAAGGGAAAATTCCATGCCTGGACTTCCGATTAGTGTCCACCCGTTATGTAAAAGCCTAGTAACCTTAACCTACGGTCGGAAAGGGACTCAGTTTGCCCTCAGGCTTACTTTAGTGATGCATTGGGGCGTGACATCAGCGCCAGTGAGTAAGATTTTGGCGTTTAGCAGCGTACGCCACCCGCTCTCGTTTGCCCGCGAACGCATACTTCTTTGTGGCATGAGCCAAGTGGGAAGCTCGACAGCCGTACTCATTCAAGCATGTAACTGATCATAAAAGCCATTTATTGGCTGTCTTGCAGAAGGAGGACCGAGGGAGATCTGACAACGAGAGACGCACAGCGCCCCCTGACCCCATGGGGACCCCAGGGATATACTTCGACGCATTCAGTTTTGCGTGACCCGCGGTGTGCCAGGCGGAGGGCCCTGAGCGTGATGCCTGATGGCCCTGGTATCGACGAGCCACGGCTACGAATGTTGGCTATCATGTTCATACGGTAAGGAAAGAGGAGGACTTAACTTCATGCGTTTCGTAGCGTTGTCCAATCGACTTGCTTGCTCCTTGCGTAAATCCCACAAAACGGCACGGTTGCCGGTGTGTCAAAATGGGAGTAAGTTGTCCCTCAGGAGATCGTAGCTAAATATCAATGGAAATTCCTTCTCTGATCGCCCAACCGTGAGTTTAGGTCCCTTGGAGACGCAGGCTTCGCGAGTCACCCATCTCCCATCCCCACCTACCGCAACGTATGGGCAGCCCTGTGACTTATTGTATCACCATGCTTTGCTTGACAAAGTAGGGTCTATTTTCCATCATACGGCCCTGACTGCATGTACCCTTAATCCCGGTAAGAAGTGAACCCCTCGGTTGGTCATTTGTCGTTGGACTAGAACTTTTCAGCGTCGCAAACTTACACGGACCCGACGCCTGAACTCCCTTCTGATACAGGCCGGAGTAGTAGTACTTATCATCCACTTGTCCTCATTGTTACTGTGTGTCGCGGGTCGCTAACGAACCCTAACAGTCCTCCCACAGACTTTCGGGTTAGACTAGACGTTGGCTCGACTCCATACGGTGGACGGTACCGAGCAATGGTAGACTGTACAGCACCACCGAATTTTCATAAAGCCCTTGGTCGACATCGGTGAATGGACCCAGAAACTAAACTACGATAGTTAATTTGGGTCTTCCGGACACTAAGAGCTGCTCCTATGAACCGAGCTATAAACCCGCCCTACGCAGGACAAAGAAAGAGGTCGATTTTCGGTGAGCGTCGACCGCGTGGAGTCATCCTTGCTGGGGCCGCGACATGAGGCGCTTGGCAGAATCCATCAACTGGCGTCCGGGAGATGAGGCTCACTCCCTGATAGGGGTAGCATCTCCGACATGATCACGTAAATGTGATGTGTCAGCATGTCGAACCCCTACTCCCTGTGCGCAGTCCGTAATTCGGGGGCAAAGTGTACATCCAACTGAGATCGCCTATAGCCGGAACACAAAGGCGCGTGGAACGCTGTCGAAGTCCTTAAAGAACGTTTCTCTACGAGGACGTTATGATAAGAAGGTGCCCCCAACTGAGGGATCGGAGCAAGACACAAGAAAGGTGCTCGGTGTTCAATGGACTGAGCAGTCTTAAGTGCCGACCCGCTTGGTCATGGATGCTTCTGAGCAAATTGCGGGCATCACGAAGGTTCTCCAGTCGCGTAGTAAAGAATATGTGAGTGCTATCACGCTACGTGCTGGTCCGTCATGAGAAGAATTGACTATCCACTGTCGTTAGCTCAATCTATGTGGATATACTCTGGAAACTTGCATAAATATCTTGAATCGTAGGTAGACACCAGTGATTGGAATTGACAGCTTTACTAAGAACATCTGCTTGGTTCTGAGTCAGGGTTTTAAGTAAAGAAGGGAGGGGTTGGCTGGACAGAGTGAATACGTTGCACTCGAGCAAGGCAACACAAATGAGGACTTAGGTGGGTGATATCCCTTGCTCACCTAACGGCGCCTACGTAAATGCGATACCGTGTTACCAGCAGGCTGCCTTCGCTTTCGCGGCTGGTTTAATATAAAATCATGAAAACGACCTCTACTGAGTGTATATACCAATGGGACAATTGTTGTATAGATTTAGATTTGTACTAGCATGGAACGTCACAGCCGAGTCGCAGGGACTATGAATGACGGTCCTCACTTAGCCTTCCAAGCGGGTTGAAGAGCAGCGGCAAGACGAGGTCGCGTTCGGTTGCTATAATTCACAAGGAGTGCCGTTGTCAGAACCTTAGGCTTTACGAGTCCTAAGTAAGATTATAGAAGTTGGTACGACCTTGATAAACCACAGTTCTGTCTCGGAGCTACGTGATTCTATGCAGTACGCAGTCAGCACTTGTCTCTTGCCCAGTTTACGAGTAGCTTAATCTTTATCGTGGTCAACGGCCCCCTGTGCCCAGGATAAGTGACCGAATTTATGTCCAGTTCCTAGAGGCAGATTTCCTAAAGAGAGTACGAGACTTTAAGCCCGTACCAAGGACTCAAGCGACTAAAGCCTCGCTACCGACGAGTGGTATGGGGGCGTTAGTATCGTTACATTCCGGCACGGATAAGCGTATTAGCAACCTTTTTTATATCTAGGGTTTGAATTGGGAGTGGCTAATTTTAACGTGGACTTCTAGACCAAACGGGATCTATCATACATCTTCCTGGACAGTTGCAATGGCAGGCGGCAGGCTTGGGCAGGGGTGAGTCTGTCATACAGCACGAAGAATAGGAGGGGCCTGGCTCTTGTCCCCAGTGTACTCGTATAGGCGAATACGCGGGGCTTTGATGCGTGGCGTAAGAGCTCAGAAATAGGTGGTGATACAATGTGTAGTGAAGGACCCAGGGTTAGCCCCCTACGGTTATTGTGTGGCGGTCGTGGCGCCCGTGCTGCCCTTGGCCAACCGTCACCATTCTTTACGAGGTTCATGAGGCCCACTCAATACCGCGAGGGTTACTAAAAGACTGTCTAACAATACATGGCTAAAGTCCCAAGGGCGATGCGTGATGGGTATACGAGACCACTGAAAGAGCCTTTATTGAAATCGAGCAGCGGAATCATGCTATCCATTCTAAAAGGAGAACACAGGGCCGGATATGTGTATATAAGGACCTACGTGTCATGGTGTGAGAAGCCGGTCCTGCATGCCCAGGTAGGGACCGGGATACACTACGCTATCTTCTGTTAATCGGATTGAATCTGTTTGCCGCGTCCAGACCCGAAGGCTCTGGCAGCACTGTCTGGCTCACACGACTACGCTTCCGATTCCGGAGTTGGCAGTTAATTATGTTTTGGCCAAGAACGTATTCCTGTCTTGCGTAAGAGGAAACGAACCTATGTGTCGCGCTTGACATGCTGCCGCTGAGAAATGGGGAGCGTTAACTTCAGTAGCCTATCTCGGGAACAGTGGACCTCATCATACCTTCGGCTTATTAGACACTACACGAAGTAACCGCGTCGACCCAATCCGTCTCAGACCGTCTATGAGGTCCGCCATGCCTCCATCAAAATGGAAACTTACATGGGGTCGTGGGGATAGCTATCAAAACGTAGGCCCTCGATAACGCTGTTGAAAGATAACGCGAGGGTCCTCTCTTGGAGCAACGATCTCAACGCCGACACCAATCGGGGGCTACACGACCCGTTTCGCTTGGAAGTGGCAACTCGAGTTATTCGCGGCAACTGTGAGTTTCAAAGAATGCGCTCTAGGTCCTTAGCAGTGATTATCCCGACAATCCCAGGAGTCGTGGTTGCTCGCACTAGCCGCCCGGACTGATCACGCGGCCCCATCCACGCCTTAGCTTAATAACCAATCAAGGCTTAAATACAGTCTGTGTAACCAGAACAACGACATGTTAGAGAGGACTCCTCCCTAGACAGAAGAAGTATGGTAGCCCCCTGCGGAAGTCAGGCCGGGCAACTAGTGCCACGCAAGCCGCCAATCTGTGAGTGTGCGGACGATGGTGAATCGGCTCAGGCCACCAATGAGGTAGCGAAACAAACTCTCAAAATCTCGCAATTGGCGACTAACAAACATCGAAGTGCGGCCCGAACGTGTTTGAATGAGGTGAATTACAACCTGCATTGGACAGATTGGACTGCGTCCAAGTCAAAACCTCAAACCCTCCAAGCGCCAATTATCGAGGCCTATCTTTTTCATAACCCTTCACTTAATTGGTGGGCGTTGGATAGACTCAATTTAGCACTCGTGTCTGCGCAGGCTGTCATTTGGCGTGGGGGTATCGTCCAAAGCGCGACAGAAGGCGCAGTTGGCTGAGGAGAAATTGATCGCCGTTTTCTTTCTACCTACTGGGTGCTGGTGAGCTTGTGCAATTGCTTGCTGATTGTTCTAAGGGTCGATTCACTGAGTATGGAGTCATGGGAGACGACAAAGTAACACCATATGGGTCATAGAAGGCGTAACTCATTCACTAGCGGCAGCATCAACCGATCTCCTTGGTGTTAAGTGGGCATCCTTAGGGGCAGGCGGAGAGTCACCAAGCTCTAGTAGTGGCCCACCAATCGCGGGCTTAGAGTAAGCTTTAAGCGAGGGGACAAGACCTTCAAACCTTGCGCCCACGGATCGCTTATTGGTTAAGCCACGGACTTGTGTGACCACAACCTATTAGTCTCTTCGTGCTTAGTTTAGGTTGCTATTGAGAGCAACCTCGGTGTTTAACGTCCAGATTCTTACAAATTTGTGGGGTACCATCCCGACAGGGGAGTGGTTTAAGGTTCACAAATATATACTCTAATATTGTGGTCCTAAGTCTGCAGTGCTTTATCTTTACCTTGAAAGTCAGCCATCTTAGCAAGCAATGCGTTAGGAACCTAGAGCCGGATGGTGTCGGAAGAATCATGTAAAGTCAGACGCGTTATCGATTGTACTACATCTCCTCCCAAGAATAACAGCGATCAAGCACATTTGTGCCGGACTA"));
    }
}
