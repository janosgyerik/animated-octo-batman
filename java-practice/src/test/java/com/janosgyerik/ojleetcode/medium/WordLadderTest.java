package com.janosgyerik.ojleetcode.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class WordLadderTest {
    public int ladderLength(String start, String end, Set<String> dict) {
        return ladderLength(start, end, dict, dict.size());
    }

    public int ladderLength(String start, String end, Set<String> dict, int maxMoves) {
        if (distance(start, end) == 1) {
            return 2;
        } else if (maxMoves == 0) {
            return dict.size();
        }
        int shortest = dict.size() + 2;
        for (String word : dict) {
            int dist = distance(start, word);
            if (dist == 1) {
                int length = 1 + ladderLength(word, end, copyWithoutWord(dict, word), Math.min(maxMoves, shortest) - 1);
                if (length < shortest) {
                    shortest = length;
                }
            }
        }
        return shortest;
    }

    private int distance(String start, String word) {
        int dist = 0;
        for (int i = 0; i < start.length() && dist < 2; ++i) {
            if (start.charAt(i) != word.charAt(i)) {
                ++dist;
            }
        }
        return dist;
    }

    private Set<String> copyWithoutWord(Set<String> dict, String word) {
        Set<String> copy = new HashSet<>(dict);
        copy.remove(word);
        return copy;
    }

    @Test
    public void test_hit_cog() {
        assertEquals(5, ladderLength("hit", "cog", new HashSet<>(Arrays.asList("hot","dot","dog","lot","log"))));
    }

    @Test
    public void test_long() {
        assertEquals(5, ladderLength("qa", "sq", new HashSet<>(Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"))));
    }
}
