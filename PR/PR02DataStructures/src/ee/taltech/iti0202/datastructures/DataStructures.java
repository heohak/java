package ee.taltech.iti0202.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DataStructures {

    private final Map<String, Integer> students = new HashMap<>();

    /**
     * The given String is a sentence with some words.
     * There is only a single whitespace between every word and no punctuation marks.
     * There are no capital letters in input string.
     * <p>
     * Return the longest word from the input sentence.
     * <p>
     * If there is more than one word with the same length, return the word which comes first alphabetically.
     * <p>
     * Hints:
     * You can split words into an array using "str.split()"
     * Sorting the list with the longest words can help you find the word which comes first alphabetically.
     *
     * @param sentence input String to find the longest words
     * @return the longest String from input
     */
    public static String findLongestWord(String sentence) {
        String[] splittedString = sentence.split(" ");
        Arrays.sort(splittedString,
                Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.naturalOrder()));

        return splittedString[0];
    }

    /**
     * Classic count the words exercise.
     * <p>
     * From input count all the words and collect results to map.
     *
     * @param sentence array of strings, can't be null.
     * @return map containing all word to count mappings.
     */
    public static Map<String, Integer> wordCount(String[] sentence) {
        Map<String, Integer> result =  new HashMap<>();
        for (String name : sentence) {
            result.put(name, result.getOrDefault(name, 0) + 1);
        }
        return result;
    }

    /**
     * Loop over the given list of strings to build a resulting list of strings like this:
     * when a string appears the 2nd, 4th, 6th, etc. time in the list, append the string to the result.
     * <p>
     * Return the empty list if no string appears a 2nd time.
     * <p>
     * Use map to count times that the string has appeared.
     *
     * @param words input list to filter
     * @return list of strings matching criteria
     */
    public static List<String> onlyEvenWords(List<String> words) {
        String[] arr = words.toArray(new String[0]);
        Map<String, Integer> map1 = wordCount(arr);
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            int value = entry.getValue();
            int calc = value / 2;
            List<String> list = Collections.nCopies(calc, entry.getKey());
            result.addAll(list);


        }
        return result;
    }

    /**
     * Method to save student and student's grade (you should use a Map here).
     * Only add student if his/hers grade is in the range of 0-5.
     *
     * @param studentInfo String with a pattern (name:grade)
     */
    public void addStudent(String studentInfo) {
        String[] splittedStringNew = studentInfo.split(":");
        if (!(Integer.parseInt(splittedStringNew[1]) >= 0 && Integer.parseInt(splittedStringNew[1]) <= 5)) {
            splittedStringNew[1] = "-1";
        }
        String name = splittedStringNew[0];
        int grade = Integer.parseInt(splittedStringNew[1]);
        students.put(name, grade);

    }

    /**
     * Method to get student's grade.
     * Return the student's grade by his/hers name.
     * You can assume that the student is already added (previous function with student's info already called).
     *
     * @param name String students name
     * @return int student's grade.
     */
    public int getStudentGrade(String name) {
        int studentGrade = 0;
        for (Map.Entry<String, Integer> entry : students.entrySet()) {
            if (Objects.equals(entry.getKey(), name)) {
                studentGrade = entry.getValue();

            }
        }
        return studentGrade;
    }

    /**
     * Main.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        System.out.println(findLongestWord("nimi on salastatud"));  // "salastatud"
        System.out.println(findLongestWord("aaa bbbbb"));  // "bbbbb"
        System.out.println(findLongestWord("hello ahllo")); // "ahllo"

        System.out.println(wordCount(new String[]{})); // empty
        System.out.println(wordCount(new String[]{"eggs", "SPAM", "eggs", "bacon", "SPAM", "bacon", "SPAM"}));
        // {bacon=2, eggs=2, SPAM=3}

        System.out.println(onlyEvenWords(Arrays.asList("foo", "bar", "baz", "baz", "bar", "foo")));
        // [baz, bar, foo] any order
        System.out.println(onlyEvenWords(Arrays.asList("a", "b", "b", "a"))); // [b, a] any order
        System.out.println(onlyEvenWords(Arrays.asList("eggs", "bacon", "SPAM", "ham", "SPAM", "SPAM"))); // [SPAM]

        DataStructures dataStructures = new DataStructures();

        dataStructures.addStudent("Ago:5");
        dataStructures.addStudent("Martin:0");
        dataStructures.addStudent("Margo:3");
        dataStructures.addStudent("Cheater:6");

        System.out.println(dataStructures.getStudentGrade("Ago")); // 5
        System.out.println(dataStructures.getStudentGrade("Martin")); // 0
        System.out.println(dataStructures.getStudentGrade("Margo")); // 3
        System.out.println(dataStructures.getStudentGrade("Cheater")); // -1
    }
}
