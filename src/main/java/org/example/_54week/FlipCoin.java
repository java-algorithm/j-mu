package org.example._54week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FlipCoin {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static boolean[][] map;

    private static final boolean FRONT = true;
    private static final boolean BACK = false;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();

            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j) == 'H' ? FRONT : BACK;
            }
        }

        permutation(0, new boolean[N]);
        System.out.println(answer);
    }

    private static void permutation(int depth, boolean[] mask) {
        if (depth == N) {
            int[] backCnt = new int[N];
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if ((mask[row] && map[row][col]==FRONT) || (!mask[row] && map[row][col] == BACK)) {
                        backCnt[col]++;
                    }
                }
            }

            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += Math.min(backCnt[i], N - backCnt[i]);
            }

            answer = Math.min(answer, sum);
            return;
        }

        mask[depth] = true;
        permutation(depth + 1, mask);
        mask[depth] = false;
        permutation(depth + 1, mask);
    }

    private static void printArray(boolean[][] newMap) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print(newMap[row][col] == FRONT ? "H" : "T");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printMask(boolean[] mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(mask[i] ? "1" : "0");
        }

        System.out.println(sb);
    }

    private static boolean[][] copyArray(boolean[][] map) {
        boolean[][] newMap = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                newMap[row][col] = map[row][col];
            }
        }

        return newMap;
    }
}
