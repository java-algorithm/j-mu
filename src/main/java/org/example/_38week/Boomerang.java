package org.example._38week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boomerang {

    private static int answer = 0;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int rowSize;
    private static int colSize;

    private static int[][] map;
    private static boolean[][] visited;

    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static final int[][] dir = {{UP, LEFT}, {UP, RIGHT}, {DOWN, LEFT}, {DOWN, RIGHT}};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        if (rowSize == 1 || colSize == 1) {
            System.out.println(0);
            return;
        }

        map = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        visited = new boolean[rowSize][colSize];
        dfs(0, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int startRow, int startCol, int sum) {
        answer = Math.max(answer, sum);

        for (int row = startRow; row < rowSize; row++) {
            for (int col = (row == startRow) ? startCol : 0; col < colSize; col++) {
                if (visited[row][col]) {
                    continue;
                }

                for (int dirNo = 0; dirNo < 4; dirNo++) {
                    int dir1 = dir[dirNo][0];
                    int nextRow1 = row + dr[dir1];
                    int nextCol1 = col + dc[dir1];

                    int dir2 = dir[dirNo][1];
                    int nextRow2 = row + dr[dir2];
                    int nextCol2 = col + dc[dir2];

                    if (nextRow1 < 0 || nextRow1 >= rowSize || nextCol1 < 0 || nextCol1 >= colSize) {
                        continue;
                    }

                    if (nextRow2 < 0 || nextRow2 >= rowSize || nextCol2 < 0 || nextCol2 >= colSize) {
                        continue;
                    }

                    if (visited[nextRow1][nextCol1] || visited[nextRow2][nextCol2]) {
                        continue;
                    }

                    visited[row][col] = true;
                    visited[nextRow1][nextCol1] = true;
                    visited[nextRow2][nextCol2] = true;
                    int value = map[row][col] * 2 + map[nextRow1][nextCol1] + map[nextRow2][nextCol2];
                    dfs(row, col + 1, sum + value);
                    visited[row][col] = false;
                    visited[nextRow1][nextCol1] = false;
                    visited[nextRow2][nextCol2] = false;
                }
            }
        }
    }
}
