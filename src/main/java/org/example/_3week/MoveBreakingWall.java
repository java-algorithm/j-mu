package org.example._3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;
import java.util.StringTokenizer;

public class MoveBreakingWall {

    private static int N;
    private static int M;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int WALL = 1;
    private static final int EMPTY = 0;
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int BREAKING_WALL = 2;

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = readTokenWithInteger(st);
        M = readTokenWithInteger(st);

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        int[][] map = new int[N][M];
        int[][] distance = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // row, col , breakingWall
        int[] startPosition = {0, 0, 0};

        // 각 cell에 도착하기 위해 최소한으로 밟아야 하는 1 구하기.
        boolean[][][] visited = new boolean[N][M][2];

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(startPosition);

        while (!deque.isEmpty()) {
            int[] current = deque.pollFirst();

            for (int i = 0; i < 4; i++) {
                int nextRow = current[ROW] + dy[i];
                int nextCol = current[COL] + dx[i];

                if (isOutOfRange(nextRow, nextCol)) {
                    continue;
                }

                if (map[nextRow][nextCol] == WALL) {
                    if (current[BREAKING_WALL] == 0 && !visited[nextRow][nextCol][WALL]) {
                        visited[nextRow][nextCol][current[BREAKING_WALL]] = true;
                        distance[nextRow][nextCol] = distance[current[ROW]][current[COL]] + 1;
                        deque.offerLast(new int[]{nextRow, nextCol, 1});
                    }
                } else {
                    if (!visited[current[ROW]][current[COL]][current[BREAKING_WALL]]) {
                        visited[nextRow][nextCol][current[BREAKING_WALL]] = true;
                        distance[nextRow][nextCol] = distance[current[ROW]][current[COL]] + 1;
                        deque.offerLast(new int[]{nextRow, nextCol, current[BREAKING_WALL]});
                    }
                }


                if (nextRow == N - 1 && nextCol == M - 1) {
                    System.out.println(distance[nextRow][nextCol] + 1);
                    return;
                }
            }

        }

        System.out.println(-1);
    }

    private static int readTokenWithInteger(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

    private static boolean isOutOfRange(int row, int col) {
        if (row < 0 || row >= N) {
            return true;
        }

        if (col < 0 || col >= M) {
            return true;
        }

        return false;
    }

    static class Position {

        int row;
        int col;
        int breakingWall;

        public Position(int row, int col, int breakingWall) {
            this.row = row;
            this.col = col;
            this.breakingWall = breakingWall;
        }

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
