package org.example._6week;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DecreasingNumberArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int N = Integer.parseInt(sc.nextLine());
        final int[] numbers = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (N == 1) {
            System.out.println(1);
            return;
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        int maxLength = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[i] < numbers[j] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1;
                } else if (numbers[i] == numbers[j]) {
                    dp[i] = dp[j];
                }
            }

            maxLength = Math.max(maxLength, dp[i]);
        }

        System.out.println(maxLength);
    }
}
