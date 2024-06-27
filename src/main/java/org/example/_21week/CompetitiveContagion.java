package org.example._21week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CompetitiveContagion {

    private static final int EMPTY = 0;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Position> pq = new PriorityQueue<>();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                final int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;

                if (value != EMPTY) {
                    final Position position = new Position(i, j, value, 0);
                    pq.add(position);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        final int S = Integer.parseInt(st.nextToken());
        final int R = Integer.parseInt(st.nextToken()) - 1;
        final int C = Integer.parseInt(st.nextToken()) - 1;

        while (!pq.isEmpty()) {
            final Position poll = pq.poll();

            for (int i = 0; i < 4; i++) {
                final int nextRow = poll.row + dr[i];
                final int nextCol = poll.col + dc[i];
                final int value = poll.value;
                final int curStep = poll.step;

                if (curStep >= S) {
                    break;
                }

                if (nextRow >= N || nextRow < 0 || nextCol >= N || nextCol < 0) {
                    continue;
                }

                if (map[nextRow][nextCol] != EMPTY) {
                    continue;
                }

                map[nextRow][nextCol] = value;
                final Position nextPosition = new Position(nextRow, nextCol, value, curStep + 1);
                pq.offer(nextPosition);
            }
        }

        System.out.println(map[R][C]);
    }

    private static class Position implements Comparable<Position> {
        int row;
        int col;
        int value;
        int step;

        public Position(final int row, final int col, final int value, final int step) {
            this.row = row;
            this.col = col;
            this.value = value;
            this.step = step;
        }

        @Override
        public int compareTo(final Position o) {
            final int compareStep = Integer.compare(this.step, o.step);

            if (compareStep == 0) {
                return Integer.compare(this.value, o.value);
            } else {
                return compareStep;
            }
        }
    }
}
