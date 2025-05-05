package org.example._56week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MonkeySaysIWannaBeHorse {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] hdr = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] hdc = {-2, -1, 1, 2, 2, 1, -1, -2};

    private static final int[] mdr = {-1, 0, 1, 0};
    private static final int[] mdc = {0, 1, 0, -1};

    private static int[][][] visited;
    private static int rowSize;
    private static int colSize;
    private static int K;

    private static int[][] map;

    private static final int OBSTACLE = 1;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        bfs();

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void bfs() {
        Queue<Step> queue = new LinkedList<>();

        visited[0][0][0] = 0;
        Step step = new Step(0, 0, 0, 0);
        queue.add(step);

        while (!queue.isEmpty()) {
            Step poll = queue.poll();

            if (poll.row == rowSize - 1 && poll.col == colSize - 1) {
                answer = Math.min(poll.move, answer);
                continue;
            }

            if (poll.k < K) {
                for (int i = 0; i < hdr.length; i++) {
                    int nextRow = poll.row + hdr[i];
                    int nextCol = poll.col + hdc[i];
                    int nextK = poll.k + 1;
                    int nextMove = poll.move + 1;

                    if (isOut(nextRow, nextCol)) {
                        continue;
                    }

                    if (visited[nextRow][nextCol][nextK] <= nextMove) {
                        continue;
                    }

                    if (map[nextRow][nextCol] == OBSTACLE) {
                        continue;
                    }

                    visited[nextRow][nextCol][nextK] = nextMove;
                    Step nextStep = new Step(nextRow, nextCol, nextK, nextMove);
                    queue.add(nextStep);
                }
            }

            for (int i = 0; i < mdr.length; i++) {
                int nextRow = poll.row + mdr[i];
                int nextCol = poll.col + mdc[i];
                int nextK = poll.k;
                int nextMove = poll.move + 1;

                if (isOut(nextRow, nextCol)) {
                    continue;
                }

                if (visited[nextRow][nextCol][nextK] <= nextMove) {
                    continue;
                }

                if (map[nextRow][nextCol] == OBSTACLE) {
                    continue;
                }

                visited[nextRow][nextCol][nextK] = nextMove;
                Step nextStep = new Step(nextRow, nextCol, nextK, nextMove);
                queue.add(nextStep);
            }
        }
    }

    private static boolean isOut(int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize;
    }

    private static void input() throws IOException {
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        visited = new int[rowSize][colSize][K + 1];

        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                for (int k = 0; k < K + 1; k++) {
                    visited[row][col][k] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private static class Step {
        int row;
        int col;
        int k;
        int move;

        public Step(int row, int col, int k, int move) {
            this.row = row;
            this.col = col;
            this.k = k;
            this.move = move;
        }
    }
}
