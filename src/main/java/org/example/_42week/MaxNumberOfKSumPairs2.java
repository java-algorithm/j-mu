package org.example._42week;

import java.util.Arrays;

public class MaxNumberOfKSumPairs2 {

    public static void main(String[] args) {
        int[] nums = {3,1,3,4,3};
        int i = maxOperations(nums, 6);
        System.out.println(i);
    }

    public static int maxOperations(int[] nums, int k) {
        int num = 0;
        Arrays.sort(nums);
        int leftPointer = 0;
        int rightPointer = nums.length-1;

        while(leftPointer < rightPointer){
            int curr = nums[leftPointer] + nums[rightPointer];
            if(curr == k){
                leftPointer++;
                rightPointer--;
                num++;
            }else if(curr > k){
                rightPointer--;
            }else{
                leftPointer++;
            }
        }

        return num;
    }

    public static int moveSp(int sp, boolean[] visited) {
        while (visited[++sp]) {
            if (visited.length - 1 <= sp) {
                return -1;
            }
        }

        return sp;
    }

    public static int moveEp(int ep, boolean[] visited) {
        while (visited[--ep]) {
            if (ep <= 0) {
                return -1;
            }
        }

        return ep;
    }
}
