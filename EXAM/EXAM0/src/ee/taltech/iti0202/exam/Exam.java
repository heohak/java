package ee.taltech.iti0202.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Exam {


    /**
     * 01
     *
     * For each multiple of 10 in the given array,
     * change all the values following it to be that multiple of 10,
     * until encountering another multiple of 10.
     * So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.
     *
     * tenRun([2, 10, 3, 4, 20, 5]) => [2, 10, 10, 10, 20, 20]
     * tenRun([10, 1, 20, 2]) => [10, 10, 20, 20]
     * tenRun([10, 1, 9, 20]) => [10, 10, 10, 20]
     */
    public static List<Integer> tenRun(List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        boolean multipleOf10 = false;
        int multipleValue = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) % 10 == 0) {
                multipleOf10 = true;
                multipleValue = nums.get(i);
            }
            if (multipleOf10) {
                result.add(multipleValue);
            } else {
                result.add(nums.get(i));
            }
        }
        return result;
    }


    /**
     * 02
     *
     * Write a method that analyzes input String and returns all pairs of same letter that is present as lower-case and upper-case in input String.
     * Returned letter pairs have to be in alphabetic order. If there are multiple same letter pairs, then return only one. If there are no suitable pairs, return "".
     * Take latin alphabet 'a' - 'z' as base.
     * mixedPairs("abcABD") => "AaBb" (a and b are represented by both lowe and upper cased letter)
     * mixedPairs("aaaAAAaaaa") => "Aa"
     * mixedPairs("tere") => ""
     * mixedPairs("bBaacA") => "AaBb" (result is in alphabetic order, although in input String, b is earlier than a).
     * @param input
     * @return
     */
    public static String mixedPairs(String input) {
        String result = "";
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        String sortedInput = new String(charArray);
        for (int i = 0; i < sortedInput.length(); i++) {
            char c = sortedInput.charAt(i);
            String charStr = String.valueOf(c);
            if (sortedInput.contains(charStr.toLowerCase()) && sortedInput.contains(charStr.toUpperCase())) {
                if (!result.contains(charStr.toUpperCase() + charStr.toLowerCase())) {
                    result = result + charStr.toUpperCase() + charStr.toLowerCase();
                }
            }
        }
        return result;

    }


    /**
     * 03
     *
     * Write a recursive method (no loops, no global variables, calls itself)
     * which separates different letters by single space.
     *
     * recSeparate("abc") => "a b c"
     * recSeparate("aabbc") => aa bb c"
     * recSeparate("aaaabbbccd") => "aaaa bbb cc d"
     * recSeparate("") => ""
     * recSeparate("aaa") => "aaa"
     *
     * @param text
     * @return
     */
    public static String recSeparate(String text) {
        // Base case: if the text is empty or only has one character, just return the text
        if (text.length() <= 1) {
            return text;
        }

        // If the first character is the same as the next one, do not add a space
        if (text.charAt(0) == text.charAt(1)) {
            return text.charAt(0) + recSeparate(text.substring(1));
        }

        // If the first character is different from the next one, add a space
        else {
            return text.charAt(0) + " " + recSeparate(text.substring(1));
        }
    }
}
