package ee.taltech.iti0202.exam;

import java.util.List;

public class Exam {

    /**
     * Given a list of numbers, count how many 2-s are alone (no 2 before or after it).
     *
     * countSingleTwos([2, 2, 1, 3]) => 0
     * countSingleTwos([7, 6, 1, 3]) => 0
     * countSingleTwos([2, 2, 1, 2]) => 1
     * countSingleTwos([2, 2, 2, 1, 3, 2, 1, 2]) => 2
     */
    public static int countSingleTwos(List<Integer> numbers) {
        if (numbers.size() == 0) {
            return 0;
        }
        if (numbers.size() == 1 && numbers.get(0) == 2) {
            return 1;
        }
        if (numbers.size() == 1 && numbers.get(0) != 2) {
            return 0;
        }
        if (numbers.size() == 2 && numbers.get(0) == 2 && numbers.get(1) == 2) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i < numbers.size() - 1; i++) {
            int i1 = numbers.get(i);
            int i2 = numbers.get(i + 1);
            int i3 = numbers.get(i - 1);
            if (i1 == 2 && i2 != 2 && i3 != 2 ) {
                count++;
            }
        }
        if (numbers.get(numbers.size() - 1) == 2 && numbers.get(numbers.size() - 2) != 2) {
            count++;
        }
        if (numbers.get(0) == 2 && numbers.get(1) != 2) {
            count++;
        }
        return count;
    }

    /**
     * Write a method that takes a string and decodes it.
     * The string may contain some numbers. 
     * All numbers need to be replaced with a corresponding letter from the alphabet.
     * Each number n references to n-th character of the lowercase alphabet (abcdefghijklmnopqrstuvwxyz).
     * If n is out of bounds, then it should start from "a" again. (0, 26 and 52 correspond to "a")
     *
     * Examples:
     * decodeMessage("0") => "a"
     * decodeMessage("0b2d4f6") => "abcdefg"
     * decodeMessage("h8") => "hi"
     * decodeMessage("11o11") => "lol"
     * decodeMessage("h8 th4r30 p17ogramme43") => "hi there programmer"
     * decodeMessage(":14 19h8s 8s 84e45t34n58 54oo37e523423") => ":o this is getting cooler"
     * decodeMessage("This one doesn't need to be changed!") => "This one doesn't need to be changed!"
     *
     * @param message the message that needs to be decoded
     * @return decoded message
     */
    public static String decodeMessage(String message) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        String number = "";
        String result = "";
        for (int i = 0; i < message.length(); i++) {
            if (!Character.isDigit(message.charAt(i)) && number.length() == 0) {
                result += message.charAt(i);
            } else if (Character.isDigit(message.charAt(i))) {
                number += message.charAt(i);
            } else if (!Character.isDigit(message.charAt(i)) && number.length() != 0) {
                result += alpha.charAt(Integer.parseInt(number) % 26);
                result += message.charAt(i);
                number = "";
            }
        }
        if (number.length() != 0) {
            result += alpha.charAt(Integer.parseInt(number) % 26);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(countSingleTwos(List.of(2, 2, 2, 1, 3, 2, 1, 2)));
        System.out.println(countSingleTwos(List.of(2, 6, 1, 3)));
    }
}
