package org.example._35week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DiceRolling2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int answer = 0;
    private static int rowSize;
    private static int colSize;
    // 북 동 남 서
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        int moveCount = Integer.parseInt(st.nextToken());

        int[][] map = new int[rowSize + 1][colSize + 1];
        for (int i = 1; i <= rowSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= colSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice(1, 1);
        for (int i = 0; i < moveCount; i++) {
            int nextRow = dice.row + dr[dice.direction];
            int nextCol = dice.col + dc[dice.direction];

            if (nextRow <= 0 || nextRow > rowSize || nextCol <= 0 || nextCol > colSize) {
                dice.reverseDirection();
                nextRow = dice.row + dr[dice.direction];
                nextCol = dice.col + dc[dice.direction];
            }

            // 도착한 칸에 대한 점수를 흭득한다.
            answer += bfs(nextRow, nextCol, map);
            dice.roll();
            dice.row = nextRow;
            dice.col = nextCol;
            // 주사위의 아랫면에 있는 정수 A와 map에 있는 정수 B를 비교해 이동방향을 결정한다.
            if (dice.bottom() > map[nextRow][nextCol]) {
                dice.clockTurn();
            } else if (dice.bottom() < map[nextRow][nextCol]) {
                dice.reverseClockTurn();
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int row, int col, int[][] map) {
        boolean[][] visited = new boolean[map.length][map[0].length];

        int cellCount = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = poll[0] + dr[i];
                int nextCol = poll[1] + dc[i];

                if (nextRow <= 0 || nextRow > rowSize || nextCol <= 0 || nextCol > colSize) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                if (map[nextRow][nextCol] != map[row][col]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol});
                cellCount++;
            }
        }

        return cellCount * map[row][col];
    }

    private static class Dice {
        int row;
        int col;
        int direction;
        int[] numbers;

        public Dice(int row, int col) {
            this.row = row;
            this.col = col;
            this.direction = 1;
            this.numbers = new int[]{0, 1, 2, 3, 4, 5, 6};
        }

        private void roll() {
            switch (this.direction) {
                case 0: // 북
                    upRolling();
                    break;
                case 1: // 동
                    rightRolling();
                    break;
                case 2: // 남
                    downRolling();
                    break;
                case 3: // 서
                    leftRolling();
                    break;
            }
        }

        private void leftRolling() {
            int number1 = numbers[1];
            int number2 = numbers[2];
            int number3 = numbers[3];
            int number4 = numbers[4];
            int number5 = numbers[5];
            int number6 = numbers[6];

            numbers[1] = number3;
            numbers[2] = number2;
            numbers[3] = number6;
            numbers[4] = number1;
            numbers[5] = number5;
            numbers[6] = number4;
        }

        private void rightRolling() {
            int number1 = numbers[1];
            int number2 = numbers[2];
            int number3 = numbers[3];
            int number4 = numbers[4];
            int number5 = numbers[5];
            int number6 = numbers[6];

            numbers[1] = number4;
            numbers[2] = number2;
            numbers[3] = number1;
            numbers[4] = number6;
            numbers[5] = number5;
            numbers[6] = number3;
        }

        private void downRolling() {
            int number1 = numbers[1];
            int number2 = numbers[2];
            int number3 = numbers[3];
            int number4 = numbers[4];
            int number5 = numbers[5];
            int number6 = numbers[6];

            numbers[1] = number2;
            numbers[2] = number6;
            numbers[3] = number3;
            numbers[4] = number4;
            numbers[5] = number1;
            numbers[6] = number5;
        }

        private void upRolling() {
            int number1 = numbers[1];
            int number2 = numbers[2];
            int number3 = numbers[3];
            int number4 = numbers[4];
            int number5 = numbers[5];
            int number6 = numbers[6];

            numbers[1] = number5;
            numbers[2] = number1;
            numbers[3] = number3;
            numbers[4] = number4;
            numbers[5] = number6;
            numbers[6] = number2;
        }

        public void reverseDirection() {
            this.direction = (this.direction + 2) % 4;
        }

        public int bottom() {
            return numbers[6];
        }

        public void clockTurn() {
            this.direction = (direction + 1) % 4;
        }

        public void reverseClockTurn() {
            this.direction = (this.direction + 3) % 4;
        }

        public void printDice() {
            System.out.println("  " + numbers[2] + "  ");
            System.out.println(numbers[4] + " " + numbers[1] + " " + numbers[3]);
            System.out.println("  " + numbers[5] + "  ");
            System.out.println("  " + numbers[6] + "  ");
        }
    }
}
