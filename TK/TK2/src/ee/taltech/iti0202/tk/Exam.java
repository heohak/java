package ee.taltech.iti0202.tk;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exam {
    /**
     * Given a list of positive integers, l and a positive integer, n, find from the list l, the integer that is closest to the
     * integer n, but not exactly the same, as n.
     * If there are multiple integers in the list that are closest to n, then return
     * the one with the greatest index (the rightmost of the suitable integers).
     * If no suitable integer is within the list (if the list is empty, or the only integer in the list == n), return -1
     *
     * closestInteger(new int[] {4, 2, 7}, 3) => 2
     * closestInteger(new int[] {4, 2, 7}, 2) => 4
     * closestInteger(new int[] {4}, 4) => -1
     */
    public static int closestInteger(int[] l, int n) {
        int closest = -1;
        int minDiff = Integer.MAX_VALUE;

        for (int i = l.length - 1; i >= 0; i--) {
            int diff = Math.abs(l[i] - n);
            if (diff != 0 && (diff < minDiff || closest == -1)) {
                closest = l[i];
                minDiff = diff;
            }
        }
        return closest;
    }

    /**
     * Given 3 int values, a b c, return their sum. However, if one of the values is the same as another of the values,
     * it does not count towards the sum.
     * <p>
     * loneSum(1, 2, 3) → 6
     * loneSum(3, 2, 3) → 2
     * loneSum(3, 3, 3) → 0
     */
    public static int loneSum(int a, int b, int c) {
        if (a != b && b != c && a != c) {
            return a + b + c;
        } else if (a == b && b == c) {
            return 0;
        } else if (a == b) {
            return c;
        } else if (a == c) {
            return b;
        } else {
            return a;
        }
    }

    /**
     * Given a string, compute a new string by moving the first char to come after the next two chars,
     * so "abc" yields "bca".
     * Repeat this process for each subsequent group of 3 chars, so "abcdef" yields "bcaefd".
     * Ignore any group of fewer than 3 chars at the end.
     *
     * oneTwo("abc") => "bca"
     * oneTwo("tca") => "cat"
     * oneTwo("tcagdo") => "catdog"
     * oneTwo("abcd") => "bca"
     * oneTwo("a") => ""
     */
    public static String oneTwo(String str) {
        String result = "";
        if (str.length() < 3) {
            return str;
        }
        for (int i = 0; i < str.length() - 2;i+=3) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            char c3 = str.charAt(i + 2);
            result = result + c2 + c3 + c1;
        }
        return result;
    }

    /**
     * Modify and return the given map as follows:
     * if exactly one of the keys "a" or "b" exists in the map (but not both),
     * set the other to have that same value in the map.
     *
     * mapAXorB({"a": "aaa", "c": "cake"}) => {"a": "aaa", "b": "aaa", "c": "cake"}
     * mapAXorB({"b": "bbb", "c": "cake"}) => {"a": "bbb", "b": "bbb", "c": "cake"}
     * mapAXorB({"a": "aaa", "b": "bbb", "c": "cake"}) => {"a": "aaa", "b": "bbb", "c": "cake"}
     */
    public static Map<String, String> mapAXorB(Map<String, String> map) {
        if (map.containsKey("a") && !map.containsKey("b")) {
            map.put("b", map.get("a"));
        } else if (!map.containsKey("a") && map.containsKey("b")) {
            map.put("a", map.get("b"));
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(oneTwo("tca"));
        System.out.println(oneTwo("tcagdo"));
        System.out.println(oneTwo("abcd"));
    }

}

