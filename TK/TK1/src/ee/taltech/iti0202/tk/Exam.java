package ee.taltech.iti0202.tk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exam {


    /**
     * Filter out the words that can be created with exactly 2 parts.
     * Each part can only be used only once.
     * Making words is case-sensitive.
     * Order of items in the output does not matter.
     * Each item should be in the answer only once.
     * Example:
     * CompileWords(
     *                 List.of("He", "llo", "y", "!", "yo", "i", "H", "yo!"),
     *                 List.of("Hello", "yo!", "Heyo!", "Hi!", "World", "yooo", "Hi", "llo")
     *         ) => "Hello", "Heyo!", "yo!", "Hi"
     *
     */
    public static List<String> compileWords(List<String> parts, List<String> words) {
        Set<String> filteredWords = new HashSet<>();
        for (String word : words) {
            String[] partsOfWord = word.split("(?<=\\G.{2})"); // split the word into two parts
            if (parts.contains(partsOfWord[0]) && parts.contains(partsOfWord[1])) {
                filteredWords.add(word);
            }
        }
        return new ArrayList<>(filteredWords);
    }


    /**
     * Given 2 int values greater than 0, return whichever value is nearest to 21 without going over.
     * Return 0 if they both go over.
     * <p>
     * blackjack(19, 21) → 21
     * blackjack(21, 19) → 21
     * blackjack(19, 22) → 19
     */
    public static int blackjack(int a, int b) {
        final int twenyOne = 21;
        if (a > twenyOne && b > twenyOne) {
            return 0;
        } else if (a > twenyOne) {
            return b;
        } else if (b > twenyOne) {
            return a;
        } else {
            int diffA = twenyOne - a;
            int diffB = twenyOne - b;
            if (diffA < diffB) {
                return a;
            } else {
                return b;
            }
        }
    }


    /**

     Given a string and an int n, return a string made of n repetitions of the last n characters
     of the string. You may assume that n is between 0 and the length of the string, inclusive.

     repeatEnd("Hello", 3) → "llollollo"
     repeatEnd("Hello", 2) → "lolo"
     repeatEnd("Hello", 1) → "o"
     */
    public static String repeatEnd(String str, int n) {
        String substring = str.substring(str.length() - n);
        return substring.repeat(n);
    }

    /**

     Modify and return the given map as follows: if the keys "a" and "b" are both in the map
     and have equal values, remove them both.

     mapAB({"a": "aaa", "b": "aaa", "c": "cake"}) → {"c": "cake"}
     mapAB({"a": "aaa", "b": "bbb"}) → {"a": "aaa", "b": "bbb"}
     mapAB({"a": "aaa", "b": "bbb", "c": "aaa"}) → {"a": "aaa", "b": "bbb", "c": "aaa"}
     */
    public static Map<String, String> mapAB(Map<String, String> map) {
        if (map.containsKey("a") && map.containsKey("b") && map.get("a").equals(map.get("b"))) {
            map.remove("a");
            map.remove("b");
        }
        return map;
    }
}
