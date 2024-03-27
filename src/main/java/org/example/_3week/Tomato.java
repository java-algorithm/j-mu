package org.example._3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Tomato {

    private static final int NO_RIPEN = 0;
    private static final int RIPEN = 1;
    private static final int EMPTY = -1;

    private static int H;
    private static int N;
    private static int M;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int[][][] box = new int[H][N][M];
        boolean[][][] visited = new boolean[H][N][M];
        List<Deque<Position>> deques = new ArrayList<>();

        int count = 0;
        int depth = 0;

        // 1번은 여기서 함.
        deques.add(new ArrayDeque<>());
        deques.add(new ArrayDeque<>());
        Deque<Position> firstDeque = deques.get(depth);
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    int value = Integer.parseInt(st.nextToken());
                    box[i][j][k] = value;
                    if (value == 1) {
                        firstDeque.offerLast(new Position(i, j, k));
//                        visited[i][j][k] = true;
                    }

                    if (value == 0) {
                        count++;
                    }

                    if (value == -1) {
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        if (count == 0) {
            System.out.println(0);
            return;
        }

        if (firstDeque.isEmpty()) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < count; i++) {
            deques.add(new ArrayDeque<>());
        }

        while (!deques.get(depth).isEmpty()) {
            Deque<Position> curDeque = deques.get(depth);
            if (curDeque.isEmpty()) {
                break;
            }

            while (!curDeque.isEmpty()) {
                Position target = curDeque.pollFirst();
                if (visited[target.h][target.n][target.m]) {
                    continue;
                }

                if (depth > 0) {
                    count--;
                }
                box[target.h][target.n][target.m] = 1;
                visited[target.h][target.n][target.m] = true;

                Position next = new Position(target.h + 1, target.n, target.m);
                if (!isOutOfBounds(next)) {
                    if (!visited[next.h][next.n][next.m]) {
                        deques.get(depth + 1).add(next);
                    }
                }

                next = new Position(target.h - 1, target.n, target.m);
                if (!isOutOfBounds(next)) {
                    if (!visited[next.h][next.n][next.m]) {
                        deques.get(depth + 1).add(next);
                    }
                }

                next = new Position(target.h, target.n + 1, target.m);
                if (!isOutOfBounds(next)) {
                    if (!visited[next.h][next.n][next.m]) {

                        deques.get(depth + 1).add(next);
                    }
                }

                next = new Position(target.h, target.n - 1, target.m);
                if (!isOutOfBounds(next)) {
                    if (!visited[next.h][next.n][next.m]) {

                        deques.get(depth + 1).add(next);
                    }
                }

                next = new Position(target.h, target.n, target.m + 1);
                if (!isOutOfBounds(next)) {
                    if (!visited[next.h][next.n][next.m]) {

                        deques.get(depth + 1).add(next);
                    }
                }

                next = new Position(target.h, target.n, target.m - 1);
                if (!isOutOfBounds(next)) {
                    if (!visited[next.h][next.n][next.m]) {

                        deques.get(depth + 1).add(next);
                    }
                }
            }

            if (count != 0) {
                depth++;
            }
        }

        System.out.println(count == 0 ? depth : -1);
    }

    private static boolean isOutOfBounds(Position next) {
        int h = next.h;
        int n = next.n;
        int m = next.m;

        if (h < 0 || h >= H) {
            return true;
        }

        if (n < 0 || n >= N) {
            return true;
        }

        if (m < 0 || m >= M) {
            return true;
        }

        return false;
    }

    static class Position {

        int h;
        int n;
        int m;

        public Position(int h, int n, int m) {
            this.h = h;
            this.n = n;
            this.m = m;
        }
    }
}
