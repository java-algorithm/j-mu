package org.example._11week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SafeArea {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Position, Position> visited;
    private static int maxSafeArea = Integer.MIN_VALUE;

    private static int[][] graph;
    private static int[] dr = {-1, 0, 0, 1};
    private static int[] dc = {0, -1, 1, 0};
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cell = Integer.parseInt(st.nextToken());
                if (cell > maxValue) {
                    maxValue = cell;
                }

                if (cell < minValue) {
                    minValue = cell;
                }

                graph[i][j] = cell;
            }
        }

        for (int height = minValue - 1; height <= maxValue; height++) {
            visited = new HashMap<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    Position position = new Position(i, j);

                    if (!visited.containsKey(position) && graph[i][j] > height) {
                        dfs(position, position, height);
                    }
                }
            }

            Collection<Position> values = visited.values();
            Set<Position> area = new HashSet<>(values);

            if (maxSafeArea < area.size()) {
                maxSafeArea = area.size();
            }
        }

        System.out.println(maxSafeArea);
    }

    private static void dfs(Position position, Position root, int height) {
        visited.put(position, root);

        for (int i = 0; i < dr.length; i++) {
            int nextRow = position.row + dr[i];
            int nextCol = position.col + dc[i];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                continue;
            }

            if (graph[nextRow][nextCol] <= height) {
                continue;
            }

            Position nextPosition = new Position(nextRow, nextCol);
            if (visited.containsKey(nextPosition)) {
                continue;
            }

            dfs(nextPosition, root, height);
        }
    }

    private static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
