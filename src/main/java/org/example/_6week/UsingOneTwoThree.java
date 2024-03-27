package org.example._6week;

import java.util.Scanner;

public class UsingOneTwoThree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int T = Integer.parseInt(scanner.nextLine());

        // 테스트 케이스 만큼 반복
        for (int i = 0; i < T; i++) {
            final int N = Integer.parseInt(scanner.nextLine());

            if (N <= 3) {
                final int[] dp = new int[4];
                dp[1] = 1;
                dp[2] = 2;
                dp[3] = 4;
                System.out.println(dp[N]);
                continue;
            }

            final int[] dp = new int[N + 1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;



            // ex) 4일 경우 1의 경우의수에 +3하면됨.
            // 2의 경우의수에 +2하면됨
            // 3의 경우의 수에 +1하면됨.
            // 즉, 1,2,3이 만들어지는 경우의 수의 합이 4가 만들어지는 경우의 수임.
            for (int j = 4; j <= N; j++) {
                dp[j] = dp[j - 3] + dp[j - 2] + dp[j - 1];
            }
            System.out.println(dp[N]);
        }
    }
}
