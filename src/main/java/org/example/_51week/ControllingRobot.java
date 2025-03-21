package org.example._51week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ControllingRobot {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static int[][][] dp;
    private static int[][] map;
    private static int rowSize;
    private static int colSize;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        dp = new int[2][rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            map[row] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp[LEFT][0][0] = map[0][0];
        dp[RIGHT][0][0] = map[0][0];
        for (int col = 1; col < colSize; col++) {
            dp[LEFT][0][col] = map[0][col] + dp[LEFT][0][col - 1];
            dp[RIGHT][0][col] = map[0][col] + dp[RIGHT][0][col - 1];
        }

        for (int row = 1; row < rowSize; row++) {
            // L -> R
            for (int col = 0; col < colSize; col++) {
                int left = col - 1 >= 0 ? dp[LEFT][row][col - 1] : Integer.MIN_VALUE;
                int leftup = dp[LEFT][row - 1][col];
                int rightup = dp[RIGHT][row - 1][col];

                dp[LEFT][row][col] = Math.max(left, Math.max(leftup, rightup)) + map[row][col];
            }

            // R -> L
            for (int col = colSize - 1; col >= 0; col--) {
                int right = col + 1 < colSize ? dp[RIGHT][row][col + 1] : Integer.MIN_VALUE;
                int leftup = dp[LEFT][row - 1][col];
                int rightup = dp[RIGHT][row - 1][col];

                dp[RIGHT][row][col] = Math.max(right, Math.max(leftup, rightup)) + map[row][col];
            }

        }

        System.out.println(Math.max(dp[LEFT][rowSize - 1][colSize - 1], dp[RIGHT][rowSize - 1][colSize - 1]));
    }

    private static void printRow(int row) {
        System.out.println(row+"행");
        System.out.println("left: ");
        for (int col = 0; col < colSize; col++) {
            System.out.print(dp[LEFT][row][col]+" ");
        }
        System.out.println();

        System.out.println("right: ");
        for (int col = 0; col < colSize; col++) {
            System.out.print(dp[RIGHT][row][col]+" ");
        }
        System.out.println();
        System.out.println();
    }
}
