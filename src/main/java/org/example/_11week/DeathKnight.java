package org.example._11week;

import java.util.*;

public class DeathKnight {

    private static int[] dr = {-2, -2, 0, 0, 2, 2};
    private static int[] dc = {-1, 1, -2, 2, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        Map<Position, Integer> visited = new HashMap<>();
        Position source = new Position(r1, c1);
        Position destination = new Position(r2, c2);
        // bfs
        Queue<Position> queue = new LinkedList<>();
        queue.offer(source);
        visited.put(source, 0);

        while (!queue.isEmpty()) {
            Position poll = queue.poll();
            Integer moveCount = visited.get(poll);

            if (poll.equals(destination)) {
                System.out.println(moveCount);
                return;
            }

            for (int i = 0; i < dr.length; i++) {
                int nextRow = poll.row + dr[i];
                int nextCol = poll.col + dc[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                    continue;
                }

                Position nextPosition = new Position(nextRow, nextCol);
                if (!visited.containsKey(nextPosition)) {
                    queue.offer(nextPosition);
                    visited.put(nextPosition, moveCount + 1);
                }
            }
        }

        System.out.println(-1);
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
