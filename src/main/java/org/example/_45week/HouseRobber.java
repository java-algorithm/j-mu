package org.example._45week;

class HouseRobber {
    private final int O = 0;
    private final int X = 1;
    public int rob(int[] nums) {
        int[][] money = new int[2][nums.length];

        money[O][0] = nums[0];
        money[X][0] = 0;

        for(int i=1; i<nums.length; i++){
            money[O][i] = money[X][i-1] + nums[i];
            money[X][i] = Math.max(money[X][i-1],money[O][i-1]);
        }

        return Math.max(money[O][nums.length-1],money[X][nums.length-1]);
    }
}
