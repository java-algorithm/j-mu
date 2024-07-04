package org.example._22week;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OilDrilling {
    private static boolean[][] visited;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};

    private static final int ROW = 0;
    private static final int COL = 1;


    private static int height;
    private static int width;
    private static int oilsNumber = 2;
    private static int oilsCount = 0;
    private static List<Integer> oilsCounts = new ArrayList<>();
    private static int[][] land;

    public static void main(String[] args) {
//        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        int[][] land = {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};
        final int answer = solution(land);
        System.out.println(answer);
    }

    public static int solution(int[][] aland) {
        land = aland;

        height = aland.length;
        width = aland[0].length;

        visited = new boolean[height][width];
        oilsCounts.add(0); // index 0 제거용
        oilsCounts.add(0); // index 1 제거용

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (land[i][j] != 1) {
                    continue;
                }

                bfs(i, j);

                oilsCounts.add(oilsCount);
                oilsNumber++;
                oilsCount = 0;
            }
        }

        int answer = 0;
        for (int col = 0; col < width; col++) {
            boolean[] visitedOilsBlock = new boolean[oilsCounts.size()];
            int sum = 0;
            for (int row = 0; row < height; row++) {
                if (land[row][col] == 0) {
                    continue;
                }

                final int blockNumber = land[row][col];
                if (visitedOilsBlock[blockNumber]) {
                    continue;
                }

                visitedOilsBlock[blockNumber] = true;
                sum += oilsCounts.get(blockNumber);
            }

            answer = Math.max(answer, sum);
        }

        return answer;
    }

    private static void bfs(final int row, final int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;
        land[row][col] = oilsNumber;
        oilsCount++;

        while (!queue.isEmpty()) {
            final int[] poll = queue.poll();
            final int curRow = poll[ROW];
            final int curCol = poll[COL];

            for (int i = 0; i < 4; i++) {
                final int newRow = curRow + dr[i];
                final int newCol = curCol + dc[i];

                if (newRow < 0 || newRow >= height || newCol < 0 || newCol >= width) {
                    continue;
                }

                if (visited[newRow][newCol]) {
                    continue;
                }

                if (land[newRow][newCol] == 0) {
                    continue;
                }

                queue.offer(new int[]{newRow, newCol});
                visited[newRow][newCol] = true;
                land[newRow][newCol] = oilsNumber;
                oilsCount++;
            }
        }
    }
}
