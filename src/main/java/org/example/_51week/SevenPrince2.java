package org.example._51week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SevenPrince2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final boolean S = true;
    private static final boolean Y = false;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static boolean[] students;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        students = new boolean[25];

        for (int row = 0; row < 5; row++) {
            String input = br.readLine();
            for (int col = 0; col < 5; col++) {
                students[row * 5 + col] = input.charAt(col) == 'S';
            }
        }

        int[] selected = new int[7];
        backtracking(students, 0, 0, 0, 0, selected);

        System.out.println(answer);
    }

    private static void backtracking(boolean[] students, int idx, int s, int y, int depth, int[] selected) {
        if (y >= 4) {
            return;
        }

        if (s + y == 7) {
            if (isConnected(selected)) {
                answer++;
            }

            return;
        }

        for (int i = idx; i < 25; i++) {
            selected[depth] = i;

            if (students[i] == S) {
                backtracking(students, i + 1, s + 1, y, depth + 1, selected);
            } else {
                backtracking(students, i + 1, s, y + 1, depth + 1, selected);
            }
        }
    }

    private static boolean isConnected(int[] selected) {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < selected.length; i++) {
            visited.add(selected[i]);
        }

        int cnt = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(selected[0]);
        visited.remove(selected[0]);
        cnt++;


        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            int row = poll / 5;
            int col = poll % 5;

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];

                if (nextRow < 0 || nextRow >= 5 || nextCol < 0 || nextCol >= 5) {
                    continue;
                }

                int convertedIdx = nextRow * 5 + nextCol;
                if (visited.contains(convertedIdx)) {
                    cnt++;
                    queue.add(convertedIdx);
                    visited.remove(convertedIdx);
                }
            }
        }


        return cnt == 7;
    }
}
