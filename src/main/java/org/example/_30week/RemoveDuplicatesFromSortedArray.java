package org.example._30week;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
//        int[] nums = {1, 1, 2};
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int answer = removeDuplicates(nums);
        System.out.println("size = " + answer);
        Arrays.stream(nums).forEach(e -> System.out.print(e + ", "));
    }

    public static int removeDuplicates(int[] nums) {
        int index = 0;
        int prev = 1000;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != prev) {
                nums[index] = nums[i];
                index++;
                prev = nums[i];
            }
        }

        return index;
    }
}
