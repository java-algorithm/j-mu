package org.example._15week;

import java.util.*;

public class Oil {

    private static int[][] oilsMap;
    private static boolean[][] visited;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};

    private static int height;
    private static int width;

    public static void main(String[] args) {
        int[][] land = new int[][]{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        height = land.length;
        width = land[0].length;

        visited = new boolean[height][width];
        oilsMap = new int[height][width];
        System.out.println(solution(land));
    }

    public static int solution(int[][] land) {
        int answer = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (land[i][j] == 0) {
                    continue;
                }

                if (visited[i][j]) {
                    continue;
                }

                bfs(new Position(i, j), land);
            }
        }

        int[] answers = new int[width];
        for (int col = 0; col < width; col++) {
            boolean continuous = false;
            for (int row = 0; row < height; row++) {
                if (oilsMap[row][col] == 0) {
                    continuous=false;
                    continue;
                }

                if (continuous) {
                    continue;
                }

                answers[col] += oilsMap[row][col];
                continuous=true;
            }
        }

        answer = Arrays.stream(answers).max().getAsInt();
        return answer;
    }

    private static void bfs(Position position, int[][] land) {
        Set<Position> oils = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();
        queue.offer(position);
        oils.add(position);
        visited[position.row][position.col] = true;

        while (!queue.isEmpty()) {
            final Position poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                final int nextRow = poll.row + dr[i];
                final int nextCol = poll.col + dc[i];

                if (nextRow < 0 || nextRow >= height || nextCol < 0 || nextCol >= width) {
                    continue;
                }

                if (!visited[nextRow][nextCol] && land[nextRow][nextCol] == 1) {
                    visited[nextRow][nextCol] = true;
                    final Position nextPosition = new Position(nextRow, nextCol);
                    oils.add(nextPosition);
                    queue.offer(nextPosition);
                }
            }
        }

        final int oilSize = oils.size();
        for (final Position oilPosition : oils) {
            oilsMap[oilPosition.row][oilPosition.col] = oilSize;
        }
    }

    static private class Position {
        int row;
        int col;

        public Position(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
    }
}
