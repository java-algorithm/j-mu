package org.example._51week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SevenPrince {

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAP_SIZE = 5;

    private static final char S = 'S';
    private static final char Y = 'Y';

    private static int answer = 0;
    private static char[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        map = new char[MAP_SIZE][MAP_SIZE];
        visited = new boolean[MAP_SIZE][MAP_SIZE];

        for (int row = 0; row < MAP_SIZE; row++) {
            String input = br.readLine();
            for (int col = 0; col < MAP_SIZE; col++) {
                map[row][col] = input.charAt(col);
            }
        }

        int[] selected = new int[25];
        backtracking(0, 0, 0, selected, 0);

        System.out.println(answer);
    }

    private static void backtracking(int index, int sCnt, int yCnt, int[] selected, int depth) {
        if (yCnt >= 4) {
            return;
        }

        if (sCnt + yCnt == 7) {
            if (isConnected(selected, depth)) {
                answer++;
            }
            return;
        }

        for (int i = index; i < 25; i++) {
            int nextRow = (i / MAP_SIZE);
            int nextCol = (i % MAP_SIZE);

            visited[nextRow][nextCol] = true;
            selected[depth] = i;
            if (map[nextRow][nextCol] == S) {
                backtracking(index + 1, sCnt + 1, yCnt, selected, depth + 1);
            } else if (map[nextRow][nextCol] == Y) {
                backtracking(index + 1, sCnt, yCnt + 1, selected, depth + 1);
            }
            visited[nextRow][nextCol] = false;
        }
    }

    private static boolean isConnected(int[] selected, int depth) {
        boolean[] visited = new boolean[25];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{selected[0] / 5, selected[0] % 5});
        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int j = 0; j < 4; j++) {
                int nextRow = poll[0] + dr[j];
                int nextCol = poll[1] + dc[j];

                if (nextRow < 0 || nextRow >= MAP_SIZE || nextCol < 0 || nextCol >= MAP_SIZE) {
                    continue;
                }

                if (visited[nextRow * 5 + nextCol]) {
                    continue;
                }

                cnt++;
                queue.add(new int[]{nextRow, nextCol});
            }
        }

        return cnt == 7;
    }
}
