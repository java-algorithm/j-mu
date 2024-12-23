package org.example._42week;

import java.util.Arrays;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        maxArea(height);
    }

//    public static int maxArea(int[] height) {
//        int[] maxs = new int[height.length];
//        Arrays.fill(maxs, 0);
//
//        for (int i = 0; i < height.length - 1; i++) {
//            for (int j = i + 1; j < height.length; j++) {
//                int minHeight = Math.min(height[i], height[j]);
//                int width = j - i;
//
//                maxs[i] = Math.max(maxs[i], minHeight * width);
//            }
//        }
//
//        return Arrays.stream(maxs).max().getAsInt();
//    }

    public static int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int maxSp = 0;
        int minSp = 0;

        int sp = 0;
        int ep = height.length - 1;

        while (sp < ep) {
            int value = (ep - sp) * (Math.min(height[ep], height[sp]));

            max = Math.max(value, max);

            if (height[ep] < height[sp]) {
                ep--;
            } else {
                sp++;
            }
        }

        System.out.println(minSp);
        System.out.println(maxSp);
        return max;
    }
}
