package org.example._46week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ComplexAssemblyLine {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[][] positions;
    private static int[][] moves;
    private static int[][] dp;

    private static int K;
    private static int N;


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        positions = new int[K][N];
        moves = new int[K][N];
        dp = new int[K][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                positions[i][j] = Integer.parseInt(st.nextToken());
            }

            if (i != K - 1) {
                for (int j = 0; j < N; j++) {
                    moves[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
//        print(positions);
        for (int row = 0; row < K; row++) {
            for (int col = 0; col < N; col++) {
                if (col == 0) {
                    dp[row][col] = positions[row][col];
                } else {
                    dp[row][col] = dp[row][col - 1] + positions[row][col];
                    if (row == K - 1) {
                        for (int t = 0; t < K - 1; t++) {
                            dp[row][col] = Math.min(dp[row][col], dp[t][col - 1] + moves[t][col - 1] + positions[row][col]);
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, dp[i][K - 1]);
        }

        System.out.println(min);

    }

    private static void print(int[][] positions) {
        for (int row = 0; row < K; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print(positions[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
