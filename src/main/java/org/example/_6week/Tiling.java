package org.example._6week;

import java.util.Scanner;

public class Tiling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());

        if (N == 1) {
            System.out.println(1);
            return;
        }

        if (N == 2) {
            System.out.println(2);
            return;
        }

        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 10007;
        }

        System.out.println(dp[N]);
    }
}
