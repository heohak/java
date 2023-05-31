package ee.taltech.iti0202.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exam {

    /**
     * Return a list that contains the exact same numbers as the given list,
     * but rearranged so that all the zeros are grouped at the start of the list.
     *
     * The order of the non-zero numbers does not matter.
     * So [1, 0, 0, 1] becomes [0 ,0, 1, 1].
     * You may modify and return the given list or make a new list.
     *
     * zeroFront([1, 0, 0, 1]) => [0, 0, 1, 1]
     * zeroFront([0, 1, 1, 0, 1]) => [0, 0, 1, 1, 1]
     * zeroFront([1, 0]) => [0, 1]
     *
     * @param numbers list of integers
     * @return "ordered" list
     */
    public static List<Integer> zeroFront(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0;i <numbers.size();i++) {
            int i1 = numbers.get(i);
            if (i1 == 0) {
                result.add(i1);
            }
        } for (int i = 0; i < numbers.size();i++) {
            int i2 = numbers.get(i);
            if (i2 != 0) {
                result.add(i2);
            }
        }
        return result;
    }

    /**
     * Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray
     * in non-decreasing order, then the whole array will be sorted in non-decreasing order.
     * Find the shortest such subarray and return its length.
     *
     * findUnsortedSubarray({2, 6, 4, 8, 10, 9, 15}) => 5
     * findUnsortedSubarray({1, 2, 3, 4}) => 0
     * findUnsortedSubarray({1, 2, 4, 6, 11, 7, 12, 13}) => 2
     */
    public static int findUnsortedSubarray(int[] nums) {
      return -1;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(0);
        list1.add(0);
        list1.add(1);
        System.out.println(zeroFront(list1));
    }

}
