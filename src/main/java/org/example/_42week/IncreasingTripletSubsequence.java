package org.example._42week;

public class IncreasingTripletSubsequence {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};

        increasingTriplet(nums);
    }
    public static boolean increasingTriplet(int[] nums) {
        if(nums.length<=2){
            return false;
        }

        int[] mins = new int[nums.length];
        int[] maxs = new int[nums.length];
        mins[0] = nums[0];
        maxs[nums.length-1] = nums[nums.length-1];

        for(int i=1; i<nums.length; i++){
            mins[i] = Math.min(mins[i-1],nums[i]);
        }

        for(int i = nums.length-2; i>=0; i--){
            maxs[i] = Math.max(maxs[i+1],nums[i]);
        }

        for(int i=1; i<nums.length; i++){
            if(mins[i]<nums[i] && maxs[i]>nums[i]){
                return true;
            }
        }

        return false;
    }
}
