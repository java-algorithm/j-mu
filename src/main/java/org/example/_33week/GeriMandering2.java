package org.example._33week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GeriMandering2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static boolean[][] boarder;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        boarder = new boolean[N + 1][N + 1];
        int total = 0;

        for (int row = 1; row <= N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                total += map[row][col];
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {

                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 > N) {
                            continue;
                        }

                        if (!(1 <= y - d1 && y + d2 <= N)) {
                            continue;
                        }

                        boarder = new boolean[N + 1][N + 1];
                        int[] regions = new int[5];

                        for (int i = 0; i <= d1; i++) {
                            boarder[x + i][y - i] = true;
                            boarder[x + d2 + i][y + d2 - i] = true;
                        }

                        for (int i = 0; i <= d2; i++) {
                            boarder[x + i][y + i] = true;
                            boarder[x + d1 + i][y - d1 + i] = true;
                        }


                        // 1구역
                        for (int row = 1; row < x + d1; row++) {
                            for (int col = 1; col <= N; col++) {
                                if (boarder[row][col]) {
                                    break;
                                }

                                regions[0] += map[row][col];
                            }
                        }

                        // 2구역
                        for (int row = 1; row < x + d2; row++) {
                            for (int col = N; col > y; col--) {
                                if (boarder[row][col]) {
                                    break;
                                }

                                regions[1] += map[row][col];
                            }
                        }

                        // 3구역
                        for (int row = x + d1; row < N; row++) {
                            for (int col = 1; col < y - d1 + d2; col++) {
                                if (boarder[row][col]) {
                                    break;
                                }

                                regions[2] += map[row][col];
                            }
                        }

                        // 4구역
                        for (int row = x + d2 + 1; row <= N; row++) {
                            for (int col = N; col >= y - d1 + d2; col--) {
                                if (boarder[row][col]) {
                                    break;
                                }

                                regions[3] += map[row][col];
                            }
                        }

                        regions[4] = total;
                        for (int i = 0; i < 4; i++) {
                            regions[4] -= regions[i];
                        }

                        int max = Integer.MIN_VALUE;
                        int min = Integer.MAX_VALUE;
                        for (int i = 0; i < regions.length; i++) {
                            max = Math.max(regions[i], max);
                            min = Math.min(regions[i], min);
                        }

                        answer = Math.min(max - min, answer);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
