package org.example._49week;

public class PedestrianParadise {
    private static final int MOD = 20170805;
    private static final int LEFT = 0;
    private static final int UP = 1;

    public static void main(String[] args) {
        int m = 3;
        int n = 6;
//        int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};

        System.out.println(solution(m, n, cityMap));
    }

    private static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m + 1][n + 1][2];

        if (m == 1 && n == 1) {
            if (cityMap[0][0] == 1) {
                return 0;
            } else {
                return 1;
            }
        }

        dp[1][1][LEFT] = 1;
        dp[1][1][UP] = 1;

        // LEFT든 UP이든 상관 X
        for (int col = 2; col <= n; col++) {
            if (cityMap[0][col - 1] == 1) {
                continue;
            }
            dp[1][col][LEFT] = dp[1][col - 1][LEFT];
        }

        for (int row = 2; row <= m; row++) {
            if (cityMap[row - 1][0] == 1) {
                continue;
            }

            dp[row][1][UP] = dp[row - 1][1][UP];
        }

        for (int row = 2; row <= m; row++) {
            for (int col = 2; col <= n; col++) {
                if (row == 1 && col == 1) {
                    continue;
                }

                if (cityMap[row - 1][col - 1] == 1) {
                    continue;
                }

                // get Up
                int up;
                if (row - 2 >= 0 && cityMap[row - 2][col - 1] == 2) {
                    up = dp[row - 1][col][UP];
                } else {
                    up = (dp[row - 1][col][LEFT] + dp[row - 1][col][UP]) % 20170805;
                }

                // get Left
                int left;
                if (col - 2 >= 0 && cityMap[row - 1][col - 2] == 2) {
                    left = dp[row][col - 1][LEFT];
                } else {
                    left = (dp[row][col - 1][LEFT] + dp[row][col - 1][UP]) % 20170805;
                }

                dp[row][col][LEFT] = left;
                dp[row][col][UP] = up;
            }
        }

        return (dp[m][n][LEFT] + dp[m][n][UP]) % 20170805;
    }
}
