package org.example._45week;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FindPeakElement {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private final static int SLASH = 0;
    private final static int BACK_SLASH = 1;
    private final static int V = 2;
    private final static int ㅅ = 3;

    public static void main(String[] args) {
//        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        int[] nums = {1, 2, 3, 1};

        int peakElement = findPeakElement(nums);
        System.out.println(peakElement);
    }

    public static int findPeakElement(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }

        int sp = -1;
        int ep = nums.length;

        int mid = 0;
        while (sp < ep) {
            mid = (sp + ep) / 2;

            int shape = getSahpe(mid, nums);
            if (shape == SLASH) {
                sp = mid + 1;
            } else if (shape == BACK_SLASH) {
                ep = mid - 1;
            } else if (shape == V) {
                sp = mid + 1;
            } else if (shape == ㅅ) {
                return mid;
            }
        }

        return (sp+ep)/2;
    }

    private static int getSahpe(int mid, int[] nums) {
        if (mid == 0) {
            if (nums[mid] < nums[mid + 1]) {
                return SLASH;
            } else if (nums[mid] > nums[mid + 1]) {
                return ㅅ;
            }
        } else if (mid == nums.length - 1) {
            if (nums[mid - 1] < nums[mid]) {
                return ㅅ;
            } else if (nums[mid - 1] > nums[mid]) {
                return BACK_SLASH;
            }
        } else {
            int left = nums[mid - 1] > nums[mid] ? 1 : -1;
            int right = nums[mid + 1] > nums[mid] ? 1 : -1;
            if (left > 0 && right > 0) {
                return V;
            } else if (left > 0 && right < 0) {
                return BACK_SLASH;
            } else if (left < 0 && right > 0) {
                return SLASH;
            } else {
                return ㅅ;
            }
        }

        return -1;
    }
}
