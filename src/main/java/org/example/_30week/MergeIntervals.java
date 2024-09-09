package org.example._30week;

import java.util.*;

class MergeIntervals {
    private static Set<Integer> zeroIntervals;

    public int[][] merge(int[][] intervals) {
        List<int[]> answers = new ArrayList<>();
        zeroIntervals = new HashSet<>();
        int[] checkPoints = new int[10_001];

        for (int[] interval : intervals) {
            if (interval[0] == interval[1]) {
                zeroIntervals.add(interval[0]);
                continue;
            }

            checkPoints[interval[0]]++;
            checkPoints[interval[1]]--;
        }

//        for (int i = 0; i < 19; i++) {
//            System.out.print(checkPoints[i]+" ");
//        }
//        System.out.println();

        int startCount = 0;
        int startIndex = 0;
        for (int i = 0; i < 10_001; i++) {
            int value = checkPoints[i];

            if (value > 0) {
                if (startCount == 0) {
                    startIndex = i;
                }
                startCount += value;
            } else if (value < 0) {
                startCount += value;
                if (startCount == 0) {
                    answers.add(new int[]{startIndex, i});
                }
            } else {
                if (startCount == 0) {
                    boolean contains = zeroIntervals.contains(i);

                    if (contains) {
                        answers.add(new int[]{i, i});
                    }
                }
            }
        }

        return answers.stream().toArray(int[][]::new);
    }
}
