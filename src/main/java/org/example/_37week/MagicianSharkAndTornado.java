package org.example._37week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MagicianSharkAndTornado {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static int[] dr = {0, 1, 0, -1};
    private static int[] dc = {-1, 0, 1, 0};
    private static final int LEFT = 0;
    private static final int DOWN = 1;
    private static final int RIGHT = 2;
    private static final int TOP = 3;
    private static int N;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int currentRow = N / 2;
        int currentCol = N / 2;

        // 1,1에 도달할 때 까지 토네이도 모양으로 바람이 불어야함.
        // 즉, 토네이도 모양으로 이동하는 함수가 필요.
        // 2. 좌우상하를 기준으로 바람이 부는것을 구현해야함.
        // temp에 모래를 넣어주고 합쳐주는 것이 이상적임.
        // 3. answer에 담긴거 출력.
        int dir = 0;
        int directionLength = 1;
        while (true) {
            for (int j = 0; j < 2; j++, dir = (++dir) % 4) {
                for (int i = 0; i < directionLength; i++) {
                    currentRow += dr[dir];
                    currentCol += dc[dir];
                    blowWind(currentRow, currentCol, dir);
//                    printMap(currentRow,currentCol);

                    if (currentRow == 0 && currentCol == 0) {
                        System.out.println(answer);
                        return;
                    }
                }
            }
            directionLength++;
        }


    }

    private static void printMap(int currentRow, int currentCol) {
        System.out.println("currentRow: " + currentRow + " currentCol: " + currentCol);
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print((row==currentRow&&col==currentCol)? "X " : map[row][col]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void blowWind(int row, int col, int dir) {
        switch (dir) {
            case LEFT:
                leftBlow(row, col);
                break;
            case DOWN:
                downBlow(row, col);
                break;
            case RIGHT:
                rightBlow(row, col);
                break;
            case TOP:
                topBlow(row, col);
                break;
        }
    }

    public static void topBlow(int row, int col) {
        int percent7 = (int) (map[row][col] * 0.07);
        int percent10 = (int) (map[row][col] * 0.1);
        int percent1 = (int) (map[row][col] * 0.01);
        int percent2 = (int) (map[row][col] * 0.02);
        int percent5 = (int) (map[row][col] * 0.05);
        int percentAlpha = map[row][col] - (2 * (percent7 + percent10 + percent1 + percent2) + percent5);

        moveSand(row, col + 1, percent7);
        moveSand(row - 1, col + 1, percent10);
        moveSand(row + 1, col + 1, percent1);
        moveSand(row, col + 2, percent2);

        moveSand(row - 1, col, percentAlpha);
        moveSand(row - 2, col, percent5);

        moveSand(row, col - 1, percent7);
        moveSand(row - 1, col - 1, percent10);
        moveSand(row + 1, col - 1, percent1);
        moveSand(row, col - 2, percent2);
        map[row][col] =0;
    }

    public static void downBlow(int row, int col) {
        int percent7 = (int) (map[row][col] * 0.07);
        int percent10 = (int) (map[row][col] * 0.1);
        int percent1 = (int) (map[row][col] * 0.01);
        int percent2 = (int) (map[row][col] * 0.02);
        int percent5 = (int) (map[row][col] * 0.05);
        int percentAlpha = map[row][col] - (2 * (percent7 + percent10 + percent1 + percent2) + percent5);

        moveSand(row, col + 1, percent7);
        moveSand(row + 1, col + 1, percent10);
        moveSand(row - 1, col + 1, percent1);
        moveSand(row, col + 2, percent2);

        moveSand(row + 1, col, percentAlpha);
        moveSand(row + 2, col, percent5);

        moveSand(row, col - 1, percent7);
        moveSand(row + 1, col - 1, percent10);
        moveSand(row - 1, col - 1, percent1);
        moveSand(row, col - 2, percent2);
        map[row][col] =0;
    }

    public static void leftBlow(int row, int col) {
        int percent7 = (int) (map[row][col] * 0.07);
        int percent10 = (int) (map[row][col] * 0.1);
        int percent1 = (int) (map[row][col] * 0.01);
        int percent2 = (int) (map[row][col] * 0.02);
        int percent5 = (int) (map[row][col] * 0.05);
        int percentAlpha = map[row][col] - (2 * (percent7 + percent10 + percent1 + percent2) + percent5);

        moveSand(row - 1, col, percent7);
        moveSand(row - 1, col - 1, percent10);
        moveSand(row - 1, col + 1, percent1);
        moveSand(row - 2, col, percent2);

        moveSand(row, col - 1, percentAlpha);
        moveSand(row, col - 2, percent5);

        moveSand(row + 1, col, percent7);
        moveSand(row + 1, col - 1, percent10);
        moveSand(row + 1, col + 1, percent1);
        moveSand(row + 2, col, percent2);
        map[row][col] =0;
    }

    public static void rightBlow(int row, int col) {
        int percent7 = (int) (map[row][col] * 0.07);
        int percent10 = (int) (map[row][col] * 0.1);
        int percent1 = (int) (map[row][col] * 0.01);
        int percent2 = (int) (map[row][col] * 0.02);
        int percent5 = (int) (map[row][col] * 0.05);
        int percentAlpha = map[row][col] - (2 * (percent7 + percent10 + percent1 + percent2) + percent5);

        moveSand(row - 1, col, percent7);
        moveSand(row - 1, col + 1, percent10);
        moveSand(row - 1, col - 1, percent1);
        moveSand(row - 2, col, percent2);

        moveSand(row, col + 1, percentAlpha);
        moveSand(row, col + 2, percent5);

        moveSand(row + 1, col, percent7);
        moveSand(row + 1, col + 1, percent10);
        moveSand(row + 1, col - 1, percent1);
        moveSand(row + 2, col, percent2);
        map[row][col] =0;
    }

    private static void moveSand(int row, int col, int amount) {
        if (isIn(row, col)) {
            map[row][col] += amount;
        } else {
            answer += amount;
        }
    }

    public static boolean isIn(int row, int col) {
        return (row >= 0 && row < N && col >= 0 && col < N);
    }
}
