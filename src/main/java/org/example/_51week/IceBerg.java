package org.example._51week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IceBerg {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int[][] map;
    private static int rowSize;
    private static int colSize;


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new int[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int year = 1; ; year++) {
            int iceBergSize = melt();

            if (iceBergSize == 0) {
                System.out.println(0);
                return;
            }

            boolean isSplitted = inspect();
            if (isSplitted) {
                System.out.println(year);
                return;
            }
        }
    }

    private static int melt() {
        int[][] temp = new int[rowSize][colSize];

        for (int row = 1; row < rowSize; row++) {
            for (int col = 1; col < colSize; col++) {
                for (int i = 0; i < 4; i++) {
                    if (map[row][col] == 0) {
                        continue;
                    }

                    int nextRow = row + dr[i];
                    int nextCol = col + dc[i];

                    if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                        continue;
                    }

                    if (map[nextRow][nextCol] == 0) {
                        temp[row][col]++;
                    }
                }
            }
        }

        int iceBergSize = 0;
        for (int row = 1; row < rowSize; row++) {
            for (int col = 1; col < colSize; col++) {
                map[row][col] -= temp[row][col];
                if(map[row][col] < 0) {
                    map[row][col] =0;
                }

                if (map[row][col] != 0) {
                    iceBergSize++;
                }
            }
        }

        return iceBergSize;
    }

    private static boolean inspect() {
        boolean[][] visited = new boolean[rowSize][colSize];
        int iceBergCnt = 0;

        for (int row = 1; row < rowSize; row++) {
            for (int col = 1; col < colSize; col++) {
                if (map[row][col] == 0) {
                    continue;
                }

                if (visited[row][col]) {
                    continue;
                }

                iceBergCnt++;
                if (iceBergCnt >= 2) {
                    return true;
                }
                dfs(row, col, visited);
            }
        }

        return false;
    }

    private static void dfs(int row, int col, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                continue;
            }

            if (map[nextRow][nextCol] == 0) {
                continue;
            }

            if (visited[nextRow][nextCol]) {
                continue;
            }

            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, visited);
        }
    }
}
