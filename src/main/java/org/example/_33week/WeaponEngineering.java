package org.example._33week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WeaponEngineering {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int rowSize;
    private static int colSize;

    private static int answer = 0;
    private static boolean[][] visited;
    private static int[][] map;

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};
    private static int[][] combis = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        visited = new boolean[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        if (rowSize < 2 || colSize < 2) {
            System.out.println(0);
            return;
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                dfs(row, col, 0, 0);
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int startRow, int startCol, int value, int depth) {
        for (int row = startRow; row < rowSize; row++) {
            for (int col = (row == startRow) ? startCol : 0; col < colSize; col++) {
                if (visited[row][col]) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int v1Row = row + dr[combis[i][0]];
                    int v1Col = col + dc[combis[i][0]];

                    int v2Row = row + dr[combis[i][1]];
                    int v2Col = col + dc[combis[i][1]];

                    if (isOutMap(v1Row, v1Col) || isOutMap(v2Row, v2Col)) {
                        continue;
                    }

                    if (visited[v1Row][v1Col] || visited[v2Row][v2Col]) {
                        continue;
                    }

                    int nextValue = map[v1Row][v1Col] + map[v2Row][v2Col] + map[row][col] * 2 + value;
                    answer = Math.max(nextValue, answer);

                    visited[v1Row][v1Col] = true;
                    visited[v2Row][v2Col] = true;
                    visited[row][col] = true;
                    dfs(row, col + 1, nextValue, depth + 1);
                    visited[v1Row][v1Col] = false;
                    visited[v2Row][v2Col] = false;
                    visited[row][col] = false;
                }
            }
        }
    }

    private static boolean isOutMap(int row, int col) {
        return (row < 0 || row >= rowSize || col < 0 || col >= colSize);
    }
}
