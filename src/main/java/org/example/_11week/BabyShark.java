package org.example._11week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BabyShark {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, 1, -1, -1, 0, 1};
    private static int[][] graph;
    private static int N;
    private static int M;

    private static int maxSafeDistance = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int cell = Integer.parseInt(st.nextToken());
                graph[row][col] = cell;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int safeDistance = bfs(new Position(i, j));
                if (maxSafeDistance < safeDistance) {
                    maxSafeDistance = safeDistance;
                }
            }
        }

        System.out.println(maxSafeDistance);
    }

    private static int bfs(Position startPosition) {
        Queue<Position> queue = new LinkedList();
        Map<Position, Integer> visited = new HashMap<>();

        queue.offer(startPosition);
        visited.put(startPosition, 0);

        Integer curPositionDistance = 0;
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            curPositionDistance = visited.get(position);
            if (graph[position.row][position.col] == 1) {
                break;
            }

            for (int i = 0; i < dx.length; i++) {
                int nextRow = position.row + dy[i];
                int nextCol = position.col + dx[i];
                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                    continue;
                }

                Position nextPosition = new Position(nextRow, nextCol);

                if (!visited.containsKey(nextPosition)) {
                    queue.offer(nextPosition);
                    visited.put(nextPosition, curPositionDistance + 1);
                }
            }
        }

        return curPositionDistance;
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
