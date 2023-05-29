package ee.taltech.iti0202.tk;

import com.sun.tools.jconsole.JConsoleContext;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

import static java.lang.Math.abs;

public class Exam {

    /**
     * Carry out battles between consecutive digits.
     *
     * When two consecutive digits battle, the larger number wins.
     * So, 5 wins against 3, 2 wins against 1.
     * The digit which loses is removed from the number.
     * When digits are equal, both are removed.
     * For example: 6672 => 7.
     * Both 6 are removed (because they are equal) and 7 wins against 2.
     * If a digit is not followed by another digit, then this digit wins.
     * For example: 345 => 45. 4 wins against 3. And 5 is alone and wins.
     *
     * The function returns an integer with only the winners.
     *
     * integerBattles(345) => 45
     * integerBattles(70) => 7
     * integerBattles(5033) => 5
     * integerBattles(123456) => 246
     */
    public static int integerBattles(int contestants) {
        String result = "";
        String string1 = Integer.toString(contestants);
        char cEnd = string1.charAt(string1.length() - 1);
        if (string1.length() % 2 != 0) {;
            string1 = string1.substring(0, string1.length() - 1);
        }
        for (int i = 0; i < string1.length() - 1; i+=2) {
            char c = string1.charAt(i);
            char c2 = string1.charAt(i + 1);
            int num = Character.getNumericValue(c);
            int num2 = Character.getNumericValue(c2);
            if (num > num2) {
                result = result + c;
            } else if (num < num2){
                result = result + c2;
            } else {
                continue;
            }

        }
        String string77 = Integer.toString(contestants);
        if (string77.length() % 2 != 0) {
            result = result + cEnd;
        }
        int resultInt = Integer.parseInt(result);

        return resultInt;
    }

    /**
     * Given three ints, a b c, one of them is small, one is medium and one is large.
     *
     * Return true if the three values are evennly spaced,
     * so the difference between small and medium is the same as the difference between medium and large.
     *
     * evenlySpaced(2, 4, 6) => true
     * evenlySpaced(4, 6, 2) => true
     * evenlySpaced(4, 6, 3) => false
     */
    public static boolean evenlySpaced(int a, int b, int c) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(a);
        numbers.add(b);
        numbers.add(c);
        System.out.println(numbers);
        Collections.sort(numbers);
        System.out.println(numbers);
        if (Math.abs(numbers.get(2) - numbers.get(1)) == Math.abs(numbers.get(0) - numbers.get(1))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Look for patterns like "zip" and "zap" in the string --
     * length-3, starting with 'z' and ending with 'p'.
     *
     * Return a string where for all such words, the middle letter is gone, so "zipXzap" yields "zpXzp".
     *
     * zipZap("zipXzap") => "zpXzp"
     * zipZap("zopzop") => "zpzp"
     * zipZap("zzzopzop") => "zzzpzp"
     */
    public static String zipZap(String str) {
        String result = "";
        for (int i = 0; i < str.length() - 2; i++) {
            char c = str.charAt(i);
            char c2 = str.charAt(i + 1);
            char c3 = str.charAt(i + 2);
            if (c == 'z' && c3 == 'p') {
                 result = result + "zp";
                 i+=2;

            } else {
                result = result + c;
            }

        }
        return result;
    }

    /**
     * Create a new map and switch keys and values in the input map.
     *
     * If the key and value of an entry are the same, then this entry is skipped.
     *
     * mapSwitchKeysAndValues({"a": "b", "c": "d"}) => {"b": "a", "d": "c"}
     * mapSwitchKeysAndValues({"a": "a", "e": "e"}) => {}
     */
    public static Map<String, String> mapSwitchKeysAndValues(Map<String, String> map) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key1 = entry.getKey();
            String value1 = entry.getValue();
            if (!key1.equals(value1)) {
                result.put(value1, key1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> map6 = new HashMap<>();
        map6.put("a", "b");
        map6.put("c", "d");
        map6.put("e", "e");
        System.out.println(mapSwitchKeysAndValues(map6));

    }
}
