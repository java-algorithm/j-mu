package org.example._48week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    private static final int s = 0;
    private static final int e = 1;

    public static void main(String[] args) {
//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals = {{1, 4}, {2, 3}};
        int[][] answer = merge(intervals);
        System.out.println("end");
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[s]));
        List<int[]> newIntervals = new ArrayList<>();

        int lastStart = intervals[0][s];
        int lastEnd = intervals[0][e];

        for (int i = 1; i < intervals.length; i++) {
            if (lastEnd >= intervals[i][s]) {
                lastEnd = Math.max(lastEnd, intervals[i][e]);
                continue;
            }

            newIntervals.add(new int[]{lastStart, lastEnd});
            lastStart = intervals[i][s];
            lastEnd = intervals[i][e];
        }

        newIntervals.add(new int[]{lastStart, lastEnd});
        return newIntervals.stream()
                .toArray(int[][]::new);
    }
}
