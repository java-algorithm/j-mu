package org.example._32week;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ByeFineDust {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int pivot = 0;
    private static int[][] airMachine = new int[2][2];

    private static int rowSize;
    private static int colSize;
    private static int[][] map;
    private static List<Dust> dusts;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        int targetSecond = Integer.parseInt(st.nextToken());

        map = new int[rowSize + 1][colSize + 1];

        // 1. 초기화.
        for (int i = 1; i <= rowSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= colSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    continue;
                }

                if (map[i][j] != -1) {
                    continue;
                }

                // airMachine[0] = 공기청전기 위쪽
                // [1] = 아래쪽
                if (map[i][j] == -1) {
                    airMachine[pivot++] = new int[]{i, j};
                }
            }
        }

        for (int second = 0; second < targetSecond; second++) {
            //2. 미세먼지 확산.
            spread();

            // 공기청정기
            airTop();
            airBottom();
        }

        int answer = 2;
        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                answer += map[row][col];
            }
        }

        System.out.println(answer);
    }

    private static void airTop() {
        int[] machine = airMachine[0];
        int machineRow = machine[0];
        int machineCol = machine[1];

        // 왼쪽
        for (int row = machineRow - 2; row > 0; row--) {
            map[row + 1][machineCol] = map[row][machineCol];
        }

        // 위쪽
        for (int col = 2; col <= colSize; col++) {
            map[1][col - 1] = map[1][col];
        }

        // 오른쪽
        for (int row = 2; row <= machineRow; row++) {
            map[row - 1][colSize] = map[row][colSize];
        }

        // 아래쪽
        for (int i = colSize - 1; i >= 2; i--) {
            map[machineRow][i + 1] = map[machineRow][i];
        }
        map[machineRow][2] = 0;
    }

    private static void airBottom() {
        int[] machine = airMachine[1];
        int machineRow = machine[0];
        int machineCol = machine[1];

        // 왼쪽
        for (int row = machineRow + 2; row <= rowSize; row++) {
            map[row - 1][machineCol] = map[row][machineCol];
        }

        // 아래쪽
        for (int i = 2; i <= colSize; i++) {
            map[rowSize][i - 1] = map[rowSize][i];
        }

        // 오른쪽
        for (int row = rowSize - 1; row >= machineRow; row--) {
            map[row + 1][colSize] = map[row][colSize];
        }

        // 위쪽
        for (int col = colSize - 1; col >= 2; col--) {
            map[machineRow][col + 1] = map[machineRow][col];
        }

        map[machineRow][2] = 0;
    }


    private static void print(int[][] map) {
        System.out.println();
        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void spread() {
        int[][] tempDust = new int[rowSize + 1][colSize + 1];

        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                if (map[row][col] == 0 || map[row][col] == -1) {
                    continue;
                }

                int spreadCount = 0;
                int amount = map[row][col] / 5;
                for (int j = 0; j < 4; j++) {
                    int nextRow = row + dr[j];
                    int nextCol = col + dc[j];

                    if (nextRow <= 0 || nextRow > rowSize || nextCol <= 0 || nextCol > colSize) {
                        continue;
                    }

                    if ((airMachine[0][0] == nextRow && airMachine[0][1] == nextCol) || (airMachine[1][0] == nextRow && airMachine[1][1] == nextCol)) {
                        continue;
                    }

                    spreadCount++;
                    tempDust[nextRow][nextCol] += amount;
                }

                map[row][col] -= amount * spreadCount;
            }
        }

        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                map[row][col] += tempDust[row][col];
            }
        }
    }

    private static class Dust {
        int row;
        int col;
        int size;

        public Dust(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
        }
    }
}
