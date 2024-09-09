package org.example._30week;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Runway {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int LENGTH;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        LENGTH = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
//        int[][] map = {{2, 2, 3, 3, 2, 2}};

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = 0;

        // 가로
        boolean[][] flag = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            boolean hasSpace = true;
            for (int current = 1; current < N; current++) {
                if (map[row][current] == map[row][current - 1]) {
                    continue;
                }

                hasSpace = hasSpaceInLine(map[row], flag[row], row, current);
                if (!hasSpace) {
                    break;
                }
            }

            if (hasSpace) {
                answer++;
            }
        }

        // 세로
        flag = new boolean[N][N];
        for (int col = 0; col < N; col++) {
            boolean hasSpace = true;
            for (int current = 1; current < N; current++) {
                if (map[current][col] == map[current - 1][col]) {
                    continue;
                }

                int[] array = extractColumnArray(map, col);
                hasSpace = hasSpaceInLine(array, flag[col], col, current);
                if (!hasSpace) {
                    break;
                }
            }

            if (hasSpace) {
                answer++;
            }
        }

        System.out.println(answer);
        // 세로
    }

    private static int[] extractColumnArray(int[][] map, int col) {
        return IntStream.range(0, map.length)
                .map(i -> map[i][col])
                .toArray();
    }

    private static boolean hasSpaceInLine(int[] map, boolean[] flag, int row, int current) {
        // 1. 낮은 칸과 높은 칸의 높이 차가 1이 아닌 경우
        if (Math.abs(map[current] - map[current - 1]) > 1) {
            return false;
        }

        int prev = current - 1;
        // current > prev : 높아질 때
        if (map[current] > map[prev]) {
            int prevValue = map[prev];

            if (prev + 1 < LENGTH) { // 2. 범위를 벗어날 경우
                return false;
            }

            for (int i = prev; i > prev - LENGTH; i--) {
                // 3. 낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우
                if (map[i] != prevValue) {
                    return false;
                }

                // 4. 경사로 위에 또 경사로를 놓는 경우.
                if (flag[i]) {
                    return false;
                }

                flag[i] = true;
            }
        } else { // 낮아질 때
            // 2. 경사로를 놓다가 범위를 벗어나는 경우
            if (current + LENGTH > N) {
                return false;
            }

            int currentValue = map[current];
            for (int i = current; i < current + LENGTH; i++) {
                // 3. 낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우
                if (map[i] != currentValue) {
                    return false;
                }

                // 4. 경사로 위에 또 경사로를 놓는 경우.
                if (flag[i]) {
                    return false;
                }

                flag[i] = true;
            }
        }

        return true;
    }
}
