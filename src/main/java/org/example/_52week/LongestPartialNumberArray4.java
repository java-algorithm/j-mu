package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class LongestPartialNumberArray4 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[] nums;
    private static int[] dp;
    private static int[] traces;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[N];
        traces = new int[N];

        for (int i = 0; i < N; i++) {

            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > max) {
                    max = dp[j];
                    traces[i] = j;
                }
            }

            if (max == 0) {
                traces[i] = -1;
            }

            dp[i] = max + 1;
        }

        int max = 0;
        int maxIdx = -1;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) {
                max = dp[i];
                maxIdx = i;
            }
        }

        Stack<Integer> stack = new Stack<>();

        System.out.println(max);
        printTrace(maxIdx, stack);

    }

    private static void printTrace(int idx, Stack<Integer> stack) {
        if (idx == -1) {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }

            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
            return;
        }

        stack.push(nums[idx]);
        printTrace(traces[idx], stack);
    }
}
