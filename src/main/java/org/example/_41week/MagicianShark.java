package org.example._41week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MagicianShark {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    private static final int[] drr = {-1, -1, 1, 1};
    private static final int[] dcc = {-1, 1, -1, 1};

    private static int N;
    private static int M;
    private static int[][] map;

    private static int[] d;
    private static int[] s;


    private static class Cloud {
        int row;
        int col;

        public Cloud(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return
                    "row=" + row +
                    ", col=" + col;
        }
    }

    private static List<Cloud> clouds = new ArrayList<>();
    private static boolean[][] visited = new boolean[N + 1][N + 1];

    public static void main(String[] args) throws IOException {
        init();

        // 모든 구름이 di방향으로 si칸 이동한다.
        for (int i = 0; i < M; i++) {
            visited = new boolean[N + 1][N + 1];
            int size = clouds.size();
            for (int j = 0; j < size; j++) {
                Cloud cloud = clouds.get(j);
                int[] move = move(cloud.row, cloud.col, s[i], d[i]);

                cloud.row = move[0];
                cloud.col = move[1];
            }

            for (Cloud cloud : clouds) {
                map[cloud.row][cloud.col]++;
                visited[cloud.row][cloud.col] = true;
            }

            clouds = new ArrayList<>();

            for (int row = 1; row <= N; row++) {
                for (int col = 1; col <= N; col++) {
                    if (visited[row][col]) {
                        for (int j = 0; j < 4; j++) {
                            int nextRow = row + drr[j];
                            int nextCol = col + dcc[j];

                            if (nextRow <= 0 || nextRow > N || nextCol <= 0 || nextCol > N) {
                                continue;
                            }

                            if (map[nextRow][nextCol] == 0) {
                                continue;
                            }

                            map[row][col] +=1;
                        }
                    }
                }
            }

            for (int row = 1; row <= N; row++) {
                for (int col = 1; col <= N; col++) {
                    if (map[row][col] >= 2 && !visited[row][col]) {
                        clouds.add(new Cloud(row, col));
                        map[row][col] -= 2;
                    }
                }
            }
        }

        int sum = 0;
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                sum += map[row][col];
            }
        }

        System.out.println(sum);
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds.add(new Cloud(N, 1));
        clouds.add(new Cloud(N, 2));
        clouds.add(new Cloud(N - 1, 1));
        clouds.add(new Cloud(N - 1, 2));

        d = new int[M];
        s = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            d[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int[] move(int row, int col, int moveCount, int dir) {
        int nextRow = row;
        int nextCol = col;

        for (int i = 0; i < moveCount; i++) {
            nextRow = nextRow + dr[dir];
            nextCol = nextCol + dc[dir];

            nextRow = move(nextRow);
            nextCol = move(nextCol);
        }

        return new int[]{nextRow, nextCol};
    }

    private static int move(int num) {
        if (num > N) {
            return 1;
        }

        if (num == 0) {
            return N;
        }

        return num;
    }
}
