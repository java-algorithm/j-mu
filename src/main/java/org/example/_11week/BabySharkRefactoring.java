package org.example._11week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BabySharkRefactoring {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int M;

    private static int maxSafeDistance = Integer.MIN_VALUE;
    private static Map<Position, Boolean> sharks;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharks = new HashMap<>(N * M * 2);

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int cell = Integer.parseInt(st.nextToken());
                if (cell == 1) {
                    sharks.put(new Position(row, col), true);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Position position = new Position(i, j);
                if (sharks.containsKey(position)) {
                    continue;
                }

                int safeDistance = calculateSafeDistance(position);
                if (maxSafeDistance < safeDistance) {
                    maxSafeDistance = safeDistance;
                }
            }
        }

        System.out.println(maxSafeDistance);
    }

    private static int calculateSafeDistance(Position position) {
        int minDistance = Integer.MAX_VALUE;

        Set<Position> sharkSet = sharks.keySet();
        for (Position shark : sharkSet) {
            int distance = calculateDistance(position, shark);

            if (distance < minDistance) {
                minDistance = distance;
            }
        }

        return minDistance;
    }

    private static int calculateDistance(Position position, Position shark) {
        int rowDistance = Math.abs(position.row - shark.row);
        int colDistance = Math.abs(position.col - shark.col);

        return Math.max(rowDistance, colDistance);
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
