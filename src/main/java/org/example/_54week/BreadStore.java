package org.example._54week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BreadStore {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static boolean found = false;
    private static int answer = 0;
    private static int R;
    private static int C;
    private static boolean[][] map;

    private static final boolean EMPTY = false;
    private static final boolean BUILDING = true;

    private static final int[][] dir = {{-1, 1}, {0, 1}, {1, 1}};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new boolean[R][C];

        for (int row = 0; row < R; row++) {
            String input = br.readLine();

            for (int col = 0; col < C; col++) {
                map[row][col] = input.charAt(col) == '.' ? EMPTY : BUILDING;
            }
        }

        for (int row = 0; row < R; row++) {
            if (map[row][0] == BUILDING) {
                continue;
            }

            dfs(row, 0);
            found = false;
        }

        System.out.println(answer);
    }

    private static void dfs(int row, int col) {
        if (col == C - 1) {
            answer++;

            found = true;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nextRow = row + dir[i][0];
            int nextCol = col + dir[i][1];

            if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) {
                continue;
            }

            if (map[nextRow][nextCol] == BUILDING) {
                continue;
            }

            map[nextRow][nextCol] = BUILDING;
            dfs(nextRow, nextCol);
            if (found) {
                return;
            }
        }
    }
}
