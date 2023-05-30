package ee.taltech.iti0202.personstatistics;

import java.util.*;

public class PersonBuilder {




    /**
     *
     * @param numbers
     * @return
     */

    public static int sum67(List<Integer> numbers) {
        int sum = 0;
        boolean inIgnoreSection = false;

        for (int number : numbers) {
            if (number == 6) {
                inIgnoreSection = true;
            } else if (number == 7 && inIgnoreSection) {
                inIgnoreSection = false;
            } else if (!inIgnoreSection) {
                sum += number;
            }
        }
        return sum;
    }


    /**
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static int roundSum(int a, int b, int c) {
        return round10(a) + round10(b) + round10(c);
    }

    private static int round10(int num) {
        int remainder = num % 10;
        if (remainder >= 5) {
            return num - remainder + 10; // Round up
        } else {
            return num - remainder; // Round down
        }
    }


    /**
     * Return a list that contains the exact same numbers as the given list,
     * @param numbers list of integers
     * @return "ordered" list
     */
    public static List<Integer> zeroFront(List<Integer> numbers) {
        Collections.sort(numbers);
        return numbers;

    }

    /**
     * You are given a string as an input where which represents a sequence of numbers in the format `num, num, num`
     * @param input the sequence of numbers as a string, separate the numbers by coma and leave no empty spaces
     * @return a list that is sorted by to number popularity
     */


    /**
     * Given an array of integers,
     * every element appears twice except for one. Find that single one.
     * If there are not such (and in any other case) return 0.
     * singleNumber([4, 1, 2, 1, 2]) => 4
     */
    public static int singleNumber(int[] nums) {
        Set<Integer> numberSet = new HashSet<>();
        for (int number : nums) {
            if (!numberSet.contains(number)) {
                numberSet.add(number);
                int count = 0;
                for (int num : nums) {
                    if (num == number) {
                        count++;
                    }
                }
                if (count == 1) {
                    return number;
                }
            }
        }
        return 0;
    }
    public static List<Integer> frequencyBasedSort(String input) {
        String[] arr = input.split(",");
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (String s : arr) {
            int num = Integer.parseInt(s);
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort((a, b) -> {
            if (a.getValue().equals(b.getValue())) {
                return b.getKey() - a.getKey();
            }
            else {
                return b.getValue() - a.getValue();
            }
        });
        List<Integer> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++) {
                resultList.add(entry.getKey());
            }
        }

        return resultList;

    }

    /**
     * Calculate the result of the expression.
     * The input contains of only digits, plus and minus sign.
     * "1+2" => 3
     * "3" => 3
     */
    public static int calculate(String expression) {
        int result = 0;
        String operator = "+";
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-') {
                if (number.length() != 0) {
                    result += operator.equals("+") ? Integer.parseInt(number.toString()) : -Integer.parseInt(number.toString());
                    number = new StringBuilder();
                }
                operator = String.valueOf(c);
            } else {
                number.append(c);
            }
        }
        if (number.length() != 0) {
            result += operator.equals("+") ? Integer.parseInt(number.toString()) : -Integer.parseInt(number.toString());
        }
        return result;
    }



    public static void main(String[] args) {
        System.out.println(frequencyBasedSort("3,3,2,4,5,1,5"));
        System.out.println(calculate("1+4+6-1"));
    }

}
