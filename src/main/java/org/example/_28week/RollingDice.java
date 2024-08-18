package org.example._28week;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RollingDice {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;

    private static final int[] dr = {0, 0, 0, -1, 1};
    private static final int[] dc = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int colSize = Integer.parseInt(st.nextToken());
        int initialRow = Integer.parseInt(st.nextToken());
        int initailCol = Integer.parseInt(st.nextToken());
        int commandCount = Integer.parseInt(st.nextToken());

        Dice dice = new Dice(initialRow, initailCol);

        int[][] map = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] commands = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

//        printState(map, dice);

        for (int i = 0; i < commands.length; i++) {
            int curRow = dice.row;
            int curCol = dice.col;
            int direction = commands[i];

            int nextRow = curRow + dr[direction];
            int nextCol = curCol + dc[direction];

            if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                continue;
            }

            dice.roll(direction);
            dice.move(nextRow, nextCol);

            if (map[nextRow][nextCol] == 0) {
                int bottomNumber = dice.getBottomNumber();
                map[nextRow][nextCol] = bottomNumber;
            } else {
                dice.setBottom(map[nextRow][nextCol]);
                map[nextRow][nextCol] = 0;
            }

//            printState(map, dice);
            bw.write(dice.getTopNumber() + "\n");
        }

        bw.flush();
    }

    private static void printState(int[][] map, Dice dice) {
        System.out.println("------------ 현재 상태 ------------");
        printMap(map);
        printDice(dice);
    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printDice(Dice dice) {
        System.out.println("현재 위치");
        System.out.println(dice.row + ", " + dice.col);

        System.out.println("전개도");
        System.out.println("  "+dice.numbers[2]+"   ");
        System.out.println(dice.numbers[4]+" "+dice.numbers[1]+" "+dice.numbers[3]);
        System.out.println("  "+dice.numbers[5]+"   ");
        System.out.println("  "+dice.numbers[6]+"   ");
    }

    private static class Dice {
        int row;
        int col;
        int[] numbers;

        public Dice(int row, int col) {
            this.row = row;
            this.col = col;
            this.numbers = new int[7];
        }

        public int getTopNumber() {
            return numbers[1];
        }

        public int getBottomNumber() {
            return numbers[6];
        }

        public void setBottom(int num) {
            numbers[6] = num;
        }

        public void move(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void roll(int direction) {
            switch (direction) {
                case RIGHT:
//                    System.out.println("RIGHT");
                    rightRoll();
                    break;
                case LEFT:
//                    System.out.println("LEFT");
                    leftRoll();
                    break;
                case UP:
//                    System.out.println("UP");
                    upRoll();
                    break;
                case DOWN:
//                    System.out.println("DOWN");
                    downRoll();
                    break;
                default:
                    throw new RuntimeException("망했어요~");
            }


        }

        private void rightRoll() {
            int number1 = numbers[1];
            int number3 = numbers[3];
            int number4 = numbers[4];
            int number6 = numbers[6];
            numbers[1] = number4;
            numbers[3] = number1;
            numbers[4] = number6;
            numbers[6] = number3;
        }

        private void leftRoll() {
            int number1 = numbers[1];
            int number3 = numbers[3];
            int number4 = numbers[4];
            int number6 = numbers[6];

            numbers[1] = number3;
            numbers[3] = number6;
            numbers[4] = number1;
            numbers[6] = number4;
        }

        private void upRoll() {
            int number1 = numbers[1];
            int number2 = numbers[2];
            int number5 = numbers[5];
            int number6 = numbers[6];

            numbers[1] = number5;
            numbers[2] = number1;
            numbers[5] = number6;
            numbers[6] = number2;
        }

        private void downRoll() {
            int number1 = numbers[1];
            int number2 = numbers[2];
            int number5 = numbers[5];
            int number6 = numbers[6];

            numbers[1] = number2;
            numbers[2] = number6;
            numbers[5] = number1;
            numbers[6] = number5;
        }
    }
}
