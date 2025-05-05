package org.example._56week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MoveBreakingWall {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int EMPTY = 0;
    private static final int WALL = 1;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int rowSize;
    private static int colSize;

    private static int[][] map;
    private static int[][][] dp;

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        bfs();

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void bfs() {
        Queue<Step> queue = new LinkedList<>();
        queue.add(new Step(0, 0, 1, 0));
        dp[0][0][0] = 0;

        while (!queue.isEmpty()) {
            Step poll = queue.poll();

            if (poll.row == rowSize - 1 && poll.col == colSize - 1) {
                answer = Math.min(answer, poll.distance);
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = poll.row + dr[i];
                int nextCol = poll.col + dc[i];

                if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                    continue;
                }

                if (map[nextRow][nextCol] == WALL && poll.breaking == 0) {
                    if (dp[1][nextRow][nextCol] <= poll.distance + 1) {
                        continue;
                    }

                    dp[1][nextRow][nextCol] = poll.distance + 1;
                    queue.add(new Step(nextRow, nextCol, poll.distance + 1, 1));
                }

                if (map[nextRow][nextCol] == EMPTY) {
                    if (dp[poll.breaking][nextRow][nextCol] <= poll.distance + 1) {
                        continue;
                    }

                    dp[poll.breaking][nextRow][nextCol] = poll.distance + 1;
                    queue.add(new Step(nextRow, nextCol, poll.distance + 1, poll.breaking));
                }

            }
        }
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        dp = new int[2][rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            map[row] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                for (int dimension = 0; dimension < 2; dimension++) {
                    dp[dimension][row][col] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private static class Step {
        int row;
        int col;
        int distance;
        int breaking;

        public Step(int row, int col, int distance, int breaking) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.breaking = breaking;
        }
    }
}
