package org.example._31week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MultiTapScheduling {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int multiTapSize = Integer.parseInt(st.nextToken());
        int schedulingSize = Integer.parseInt(st.nextToken());

        int[] tasks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] dp = new int[schedulingSize + 1][schedulingSize];
        List<Integer> multiTaps = new ArrayList<>();

        if (schedulingSize == 1) {
            System.out.println(0);
            return;
        }

        if (schedulingSize == 2) {
            System.out.println(multiTapSize == 1 ? 2 : 0);
            return;
        }

        // scheduleSize > 2
        for (int scheduleNo = schedulingSize - 1; scheduleNo > 0; scheduleNo--) {
            if (scheduleNo != schedulingSize - 1) {
                for (int taskNo = 0; taskNo <= schedulingSize; taskNo++) {
                    dp[taskNo][scheduleNo] = dp[taskNo][scheduleNo + 1];
                }
            }


            dp[tasks[scheduleNo]][scheduleNo] = scheduleNo;
        }

        multiTaps.add(tasks[0]);

        for (int i = 1; i < schedulingSize; i++) {
            boolean contains = multiTaps.contains(tasks[i]);
            if (contains) {
                continue;
            }

            if (multiTaps.size() < multiTapSize) {
                multiTaps.add(tasks[i]);
                continue;
            }

            // get min value index
            int maxValue = Integer.MIN_VALUE;
            int maxIndex = -1;
            for (int j = 0; j < multiTapSize; j++) {
                Integer taskNo = multiTaps.get(j);
                if (dp[taskNo][i] == 0) {
                    maxValue = dp[taskNo][i];
                    maxIndex = j;
                    break;
                }

                if (dp[taskNo][i] > maxValue) {
                    maxValue = dp[taskNo][i];
                    maxIndex = j;
                }
            }

            multiTaps.remove(maxIndex);
            multiTaps.add(tasks[i]);
            answer++;
        }

        System.out.println(answer);
    }

    private static void printArray(int[][] dp) {
        System.out.println("-------- print DP ---------");
        for (int i = 0; i < dp.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
