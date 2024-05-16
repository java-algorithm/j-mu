package org.example._14week;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Hi {

    private static final int MAX_HEALTH = 100;

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int peopleCount = Integer.parseInt(sc.nextLine());

        final int[] healths = new int[peopleCount + 1];
        final int[] pleasures = new int[peopleCount + 1];

        StringTokenizer st = new StringTokenizer(sc.nextLine());
        for (int i = 1; i < peopleCount + 1; i++) {
            healths[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(sc.nextLine());
        for (int i = 1; i < peopleCount + 1; i++) {
            pleasures[i] = Integer.parseInt(st.nextToken());
        }

        final int[][] dp = new int[peopleCount + 1][MAX_HEALTH + 1];
        for (int peopleIndex = 1; peopleIndex < peopleCount + 1; peopleIndex++) {
            for (int currentHealth = 1; currentHealth < MAX_HEALTH + 1; currentHealth++) {
                if (currentHealth <= healths[peopleIndex]) {
                    dp[peopleIndex][currentHealth] = dp[peopleIndex - 1][currentHealth];
                } else {
                    dp[peopleIndex][currentHealth] = Math.max(dp[peopleIndex - 1][currentHealth], pleasures[peopleIndex] + dp[peopleIndex - 1][currentHealth - healths[peopleIndex]]);
                }
            }
        }

        System.out.println(dp[peopleCount][MAX_HEALTH]);
    }
}
