package org.example._2week;

import java.util.Arrays;
import java.util.Scanner;

public class Lion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[][] dp = new int[N][3];

        Arrays.fill(dp[0], 1);

        for (int i = 1; i < N; i++) {
            // 이전칸 배치
            int nowherePlace = dp[i - 1][0];
            int firstPlace = dp[i - 1][1];
            int secondPlace = dp[i - 1][2];

            dp[i][0] = (nowherePlace + firstPlace + secondPlace) % 9901;
            dp[i][1] = (nowherePlace + secondPlace) % 9901;
            dp[i][2] = (nowherePlace + firstPlace) % 9901;
        }

        int sum = Arrays.stream(dp[N - 1]).sum();
        System.out.println(sum % 9901); // 이거 조심..
    }
}
