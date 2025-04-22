package org.example._55week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Zelda {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        int round = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                return;
            }

            int[][] map = new int[N][N];
            for (int row = 0; row < N; row++) {
                map[row] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int[][] dp = new int[N][N];
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    dp[row][col] = Integer.MAX_VALUE;
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, 0, map[0][0]});

            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                if (dp[poll[0]][poll[1]] <= poll[2]) {
                    continue;
                }

                dp[poll[0]][poll[1]] = poll[2];
                for (int i = 0; i < 4; i++) {
                    int nextRow = poll[0] + dr[i];
                    int nextCol = poll[1] + dc[i];

                    if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                        continue;
                    }

                    if (dp[nextRow][nextCol] <= poll[2] + map[nextRow][nextCol]) {
                        continue;
                    }

                    queue.add(new int[]{nextRow, nextCol, poll[2] + map[nextRow][nextCol]});
                }
            }

            System.out.printf("Problem %d: %d\n", round++, dp[N - 1][N - 1]);
        }
    }
}
