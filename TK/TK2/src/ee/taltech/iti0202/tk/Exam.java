package ee.taltech.iti0202.tk;

import java.util.Map;

public class Exam {

    /**
     * @param l
     * @param n
     * @return int
     */
    public static int closestInteger(int[] l, int n) {
        int closest = -1;
        int closestDist = Integer.MAX_VALUE;
        for (int i = 0; i < l.length; i++) {
            int dist = Math.abs(l[i] - n);
            if (dist < closestDist && dist != 0) {
                closest = l[i];
                closestDist = dist;
            } else if (dist == closestDist && closest != -1) {
                closest = l[i];
            }
        }
        return closest;

    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return int
     */
    public static int loneSum(int a, int b, int c) {
        if (a == b && b == c) {
            return 0;
        }
        if (a == b) {
            return c;
        } else if (a == c) {
            return b;
        } else if (b == c) {
            return a;
        } else {
            return a + b + c;
        }
    }

    /**
     * Given a string, compute a new string by moving the first char to come after the next two chars,
     * so "abc" yields "bca".
     * Repeat this process for each subsequent group of 3 chars, so "abcdef" yields "bcaefd".
     * Ignore any group of fewer than 3 chars at the end.
     * oneTwo("abc") => "bca"
     * oneTwo("tca") => "cat"
     * oneTwo("tcagdo") => "catdog"
     * oneTwo("abcd") => "bca"
     * oneTwo("a") => ""
     */
    public static String oneTwo(String str) {
        StringBuilder result = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len - 2; i += 3) {
            result.append(str.charAt(i + 1));
            result.append(str.charAt(i + 2));
            result.append(str.charAt(i));
        }
        return result.toString();
    }

    /**
     * Modify and return the given map as follows:
     * if exactly one of the keys "a" or "b" exists in the map (but not both),
     * set the other to have that same value in the map.
     * mapAXorB({"a": "aaa", "c": "cake"}) => {"a": "aaa", "b": "aaa", "c": "cake"}
     * mapAXorB({"b": "bbb", "c": "cake"}) => {"a": "bbb", "b": "bbb", "c": "cake"}
     * mapAXorB({"a": "aaa", "b": "bbb", "c": "cake"}) => {"a": "aaa", "b": "bbb", "c": "cake"}
     */
    public static Map<String, String> mapAXorB(Map<String, String> map) {
        if (map.containsKey("a") && !map.containsKey("b")) {
            map.put("a", map.get("a"));
            map.put("b", map.get("a"));
        } else if (map.containsKey("b") && !map.containsKey("a")) {
            map.put("b", map.get("b"));
            map.put("a", map.get("b"));
        }
        return map;
    }

}
