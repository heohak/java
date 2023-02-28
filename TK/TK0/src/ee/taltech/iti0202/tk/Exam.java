package ee.taltech.iti0202.tk;
import java.util.*;
import java.util.stream.Collectors;

public class Exam {



    /**
     * Return the "centered" average of an array of ints, which we'll say is the mean average of the values,
     * except ignoring the largest and smallest values in the array. If there are multiple copies of the
     * smallest value, ignore just one copy, and likewise for the largest value. Use int division to produce
     * the final average. You may assume that the array is length 3 or more.
     * <p>
     * centeredAverage([1, 2, 3, 4, 100]) → 3
     * centeredAverage([1, 1, 5, 5, 10, 8, 7]) → 5
     * centeredAverage([-10, -4, -2, -4, -2, 0]) → -3
     */
    public static int centeredAverage(List<Integer> nums) {
        int sum = nums.stream()
                .mapToInt(Integer::intValue)
                .sum();
        int min = nums.stream()
                .mapToInt(Integer::intValue)
                .min()
                .getAsInt();
        int max = nums.stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
        return (sum - min - max) / (nums.size() - 2);
    }

    /**
     * Given 3 ints, a b c, return the sum of their rounded values.
     * We'll round an int value up to the next multiple of 10
     * if its rightmost digit is 5 or more, so 15 rounds up to 20.
     * Alternately, round down to the previous multiple of 10
     * if its rightmost digit is less than 5, so 12 rounds down to 10
     *
     * roundSum(16, 17, 18) => 60
     * roundSum(12, 13, 14) => 30
     * roundSum(6, 4, 4) => 10
     */
    public static int roundSum(int a, int b, int c) {
        return -1;
    }


    /**
     * A sandwich is two pieces of bread with something in between. Return the string that is between the first and
     * last appearance of "bread" in the given string, or return the empty string ""
     * if there are not two pieces of bread.
     * <p>
     * getSandwich("breadjambread") → "jam"
     * getSandwich("xxbreadjambreadyy") → "jam"
     * getSandwich("xxbreadyy") → ""
     */
    public static String getSandwich(String str) {
        return "";
    }


    /**
     * Given a map of food keys and topping values, modify and return the map as follows: if the key
     * "ice cream" is present, set its value to "cherry". In all cases, set the key "bread"
     * to have the value "butter".
     * <p>
     * <p>
     * topping({"ice cream": "peanuts"}) → {"bread": "butter", "ice cream": "cherry"}
     * topping({}) → {"bread": "butter"}
     * topping({"pancake": "syrup"}) → {"bread": "butter", "pancake": "syrup"}
     */
    public static Map<String, String> topping(Map<String, String> map) {
        return null;
    }


    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(2, 3, 1, 6, 5);
        System.out.println(centeredAverage(list1));
    }
}