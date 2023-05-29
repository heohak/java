package ee.taltech.iti0202.exam;

import java.util.*;

public class Exam {

    /**
     * Find the prime factors of a number and return the sum of all the factors.
     * 0 and 1 are not prime.
     *
     * Examples:
     * primeFactorsSum(10) => 2 + 5 => 7
     * Prime factors of 10 are 2 * 5 => 2 + 5 => answer is 7
     *
     * primeFactorsSum(102) => 22
     * Prime factors of 102 are 2 * 3 * 17 => 2 + 3 + 17 => answer is 22
     *
     * primeFactorsSum(1) => 0
     * primeFactorsSum(40) => 11 (2 + 2 + 2 + 5)
     * primeFactorsSum(8881) => 190
     * primeFactorsSum(999961) => 999961
     *
     * @param num input number. 1 <= num <= 1000000
     * @return sum of all prime factors
     */
    public static int primeFactorsSum(int num) {
        int max = 1000000; // You can adjust this as needed
        boolean[] primes = new boolean[max];
        for (int i = 2; i < max; i++) {
            primes[i] = true;
        }
        for (int p = 2; p * p < max; p++) {
            if (primes[p]) {
                for (int i = p * p; i < max; i += p) {
                    primes[i] = false;
                }
            }
        }
        int sum = 0;
        for (int p = 2; p < max && num > 1; p++) {
            if (primes[p]) {
                while (num % p == 0) {
                    sum += p;
                    num /= p;
                }
            }
        }
        if (num > 1) {
            // num is a prime larger than max
            sum += num;
        }
        return sum;
    }

    /**
     * Find the longest distance between two equal symbols.
     *
     * The same symbol can contain in this distance.
     *
     * If there are no equals symbols, return -1.
     *
     * longestDistanceBetweenEqualSymbols("abcda") => 3
     * longestDistanceBetweenEqualSymbols("aaaa") => 2
     * longestDistanceBetweenEqualSymbols("aia") => 1
     * longestDistanceBetweenEqualSymbols("aiu") => -1
     * longestDistanceBetweenEqualSymbols("") => -1
     * longestDistanceBetweenEqualSymbols("abcdabbg") => 4
     *
     * @param s input string
     * @return longest distance
     */
    public static int longestDistanceBetweenEqualSymbols(String s) {
        if (s.equals("")) {
            return -1;
        }
        Map<Character,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (!map.containsKey(currentChar)) {
                map.put(currentChar, new ArrayList<>());
            }
            map.get(currentChar).add(i);
        }
        System.out.println(map);
        List<Integer> finalist = new ArrayList<>();
        for (Map.Entry<Character,List<Integer>> entry : map.entrySet()) {
            char key = entry.getKey();
            List<Integer> values = entry.getValue();
            if (values.size() > 1) {
                int answer = values.get(values.size() - 1) - values.get(0) - 1;
                finalist.add(answer);
            }

        }

        if (finalist.size() == 0) {
            return -1;
        }
        int finalfinal = Collections.max(finalist);
        return finalfinal;
    }

    public static void main(String[] args) {
        System.out.println(longestDistanceBetweenEqualSymbols("abcdefa"));
        System.out.println(longestDistanceBetweenEqualSymbols("aiu"));
        System.out.println(longestDistanceBetweenEqualSymbols("aaaa"));
    }
}
