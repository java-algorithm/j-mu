package org.example._40week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cubing {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int FRONT = 2;
    private static final int BACK = 3;
    private static final int LEFT = 4;
    private static final int RIGHT = 5;

    private static final int WHITE = 0;
    private static final int YELLOW = 1;
    private static final int RED = 2;
    private static final int ORANGE = 3;
    private static final int GREEN = 4;
    private static final int BLUE = 5;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int testCaseCount = Integer.parseInt(br.readLine());
//        System.out.println();
        for (int test = 0; test < testCaseCount; test++) {
            Cube cube = new Cube();

//            System.out.println("초기 상태");
//            cube.debug(cube.cells);

            int rollingCount = Integer.parseInt(br.readLine());
            String[] operations = br.readLine().split(" ");

            for (int roll = 0; roll < rollingCount; roll++) {
                cube.roll(operations[roll]);
            }

//            System.out.println("돌린 후");
//            cube.debug(cube.cells);
            cube.print();
        }

        System.out.println(sb.toString());
    }

    private static class Cube {
        int[][][] cells;

        public Cube() {
            cells = new int[6][3][3];
            fill(TOP, WHITE);
            fill(BOTTOM, YELLOW);
            fill(FRONT, RED);
            fill(BACK, ORANGE);
            fill(LEFT, GREEN);
            fill(RIGHT, BLUE);
        }

        private void fill(int sideNo, int color) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    cells[sideNo][row][col] = color;
                }
            }
        }

        public void roll(String operation) {
            int side = convertToSide(operation.charAt(0));
            char direction = operation.charAt(1);

            int[][][] temp = cloneArray();

            lookSideToTop(temp, side);
            System.out.println("바라보는 방향을 바꿔봐요");
            debug(temp);

            roll(temp, direction);
            System.out.println("실제로 돌린 후");
            debug(temp);

            lookTopToSide(temp, side);

            System.out.println("다시 윗면이 위로오도록 수정후");
            debug(cells);
        }

        private void lookTopToSide(int[][][] cells, int side) {
            switch (side) {
                case TOP:
                    break;
                case BOTTOM:
                    lookSideToTop(cells, BOTTOM);
                    break;
                case FRONT:
                    lookSideToTop(cells, BACK);
                    break;
                case BACK:
                    lookSideToTop(cells, FRONT);
                    break;
                case LEFT:
                    lookSideToTop(cells, RIGHT);
                    break;
                case RIGHT:
                    lookSideToTop(cells, LEFT);
                    break;
            }

            this.cells = cells;
        }

        private void lookSideToTop(int[][][] cells, int side) {
            int[][] top = cells[TOP];
            int temp = 0;

            switch (side) {
                case TOP:
                    break;
                case BOTTOM:
                    int[][] front = cells[FRONT];
                    cells[TOP] = cells[BOTTOM];
                    cells[BOTTOM] = top;
                    cells[FRONT] = cells[BACK];
                    cells[BACK] = front;

                    clockWise2Division(cells, LEFT, 2);
                    counterClockWise2Division(cells, RIGHT, 2);
                    break;
                case FRONT:
                    cells[TOP] = cells[FRONT];
                    cells[FRONT] = cells[BOTTOM];
                    cells[BOTTOM] = cells[BACK];
                    cells[BACK] = top;

                    clockWise2Division(cells, RIGHT, 1);
                    counterClockWise2Division(cells, LEFT, 1);
                    break;
                case BACK:
                    cells[TOP] = cells[BACK];
                    cells[BACK] = cells[BOTTOM];
                    cells[BOTTOM] = cells[FRONT];
                    cells[FRONT] = top;

                    clockWise2Division(cells, LEFT, 1);
                    counterClockWise2Division(cells, RIGHT, 1);
                    break;
                case LEFT:
                    cells[TOP] = cells[LEFT];
                    cells[LEFT] = cells[BOTTOM];
                    cells[BOTTOM] = cells[RIGHT];
                    cells[RIGHT] = top;

                    counterClockWise2Division(cells, BACK, 1);
                    clockWise2Division(cells, FRONT, 1);

                    break;
                case RIGHT:
                    cells[TOP] = cells[RIGHT];
                    cells[RIGHT] = cells[BOTTOM];
                    cells[BOTTOM] = cells[LEFT];
                    cells[LEFT] = top;

                    counterClockWise2Division(cells, FRONT, 1);
                    clockWise2Division(cells, BACK, 1);
                    break;
            }
        }

        private void counterClockWise2Division(int[][][] cells, int side, int count) {
            for (int i = 0; i < count; i++) {
                int[][] temp = new int[3][3];
                temp[0][0] = cells[side][0][2];
                temp[0][1] = cells[side][1][2];
                temp[0][2] = cells[side][2][2];
                temp[1][0] = cells[side][0][1];
                temp[1][1] = cells[side][1][1];
                temp[1][2] = cells[side][2][1];
                temp[2][0] = cells[side][0][0];
                temp[2][1] = cells[side][1][0];
                temp[2][2] = cells[side][2][0];

                cells[side] = temp;
            }
        }

        private static void clockWise2Division(int[][][] cells, int side, int count) {
            for (int i = 0; i < count; i++) {
                int[][] temp = new int[3][3];
                temp[0][0] = cells[side][2][0];
                temp[0][1] = cells[side][1][0];
                temp[0][2] = cells[side][0][0];

                temp[1][0] = cells[side][2][1];
                temp[1][1] = cells[side][1][1];
                temp[1][2] = cells[side][0][1];

                temp[2][0] = cells[side][2][2];
                temp[2][1] = cells[side][1][2];
                temp[2][2] = cells[side][0][2];

                cells[side] = temp;
            }
        }

        private int convertToSide(char ch) {
            switch (ch) {
                case 'U':
                    return TOP;
                case 'D':
                    return BOTTOM;
                case 'F':
                    return FRONT;
                case 'B':
                    return BACK;
                case 'L':
                    return LEFT;
                case 'R':
                    return RIGHT;
            }

            return -1;
        }


        private int[][][] cloneArray() {
            int[][][] temp = new int[6][3][3];

            for (int side = 0; side < 6; side++) {
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        temp[side][row][col] = this.cells[side][row][col];
                    }
                }
            }

            return temp;
        }

        private void roll(int[][][] cells, char direction) {
            if (direction == '+') {
                clockWise(cells);
            } else {
                counterClockWise(cells);
            }
        }

        private void counterClockWise(int[][][] cells) {
            counterClockWise2Division(cells, TOP, 1);

            int[] temp = new int[3];
            temp[0] = cells[FRONT][0][0];
            temp[1] = cells[FRONT][0][1];
            temp[2] = cells[FRONT][0][2];

            cells[FRONT][0][0] = cells[LEFT][0][2];
            cells[FRONT][0][1] = cells[LEFT][1][2];
            cells[FRONT][0][2] = cells[LEFT][2][2];

            cells[LEFT][0][2] = cells[BACK][2][2];
            cells[LEFT][1][2] = cells[BACK][2][1];
            cells[LEFT][2][2] = cells[BACK][2][0];

            cells[BACK][2][0] = cells[RIGHT][0][0];
            cells[BACK][2][1] = cells[RIGHT][1][0];
            cells[BACK][2][2] = cells[RIGHT][2][0];

            cells[RIGHT][0][0] = temp[2];
            cells[RIGHT][1][0] = temp[1];
            cells[RIGHT][2][0] = temp[0];
        }

        private void clockWise(int[][][] cells) {
            int[] temp = new int[3];
            clockWise2Division(cells, TOP, 1);

            temp[0] = cells[FRONT][0][0];
            temp[1] = cells[FRONT][0][1];
            temp[2] = cells[FRONT][0][2];

            cells[FRONT][0][0] = cells[RIGHT][2][0];
            cells[FRONT][0][1] = cells[RIGHT][1][0];
            cells[FRONT][0][2] = cells[RIGHT][0][0];

            cells[RIGHT][0][0] = cells[BACK][2][0];
            cells[RIGHT][1][0] = cells[BACK][2][1];
            cells[RIGHT][2][0] = cells[BACK][2][2];

            cells[BACK][2][0] = cells[LEFT][2][2];
            cells[BACK][2][1] = cells[LEFT][1][2];
            cells[BACK][2][2] = cells[LEFT][0][2];

            cells[LEFT][0][2] = temp[0];
            cells[LEFT][1][2] = temp[1];
            cells[LEFT][2][2] = temp[2];
        }

        public void print() {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    print(cells[TOP][row][col]);
                }
                sb.append("\n");
            }
        }

        private void print(int color) {
            switch (color) {
                case WHITE:
                    sb.append("w");
                    break;
                case YELLOW:
                    sb.append("y");
                    break;
                case RED:
                    sb.append("r");
                    break;
                case ORANGE:
                    sb.append("o");
                    break;
                case GREEN:
                    sb.append("g");
                    break;
                case BLUE:
                    sb.append("b");
                    break;
            }
        }

        public void debug(int[][][] cells) {
            int[][] top = cells[TOP];
            int[][] front = cells[FRONT];
            int[][] bottom = cells[BOTTOM];
            int[][] back = cells[BACK];
            int[][] left = cells[LEFT];
            int[][] right = cells[RIGHT];

            System.out.println(convert(null) + convert(back[0]) + convert(null));
            System.out.println(convert(null) + convert(back[1]) + convert(null));
            System.out.println(convert(null) + convert(back[2]) + convert(null));

            System.out.println(convert(left[0]) + convert(top[0]) + convert(right[0]));
            System.out.println(convert(left[1]) + convert(top[1]) + convert(right[1]));
            System.out.println(convert(left[2]) + convert(top[2]) + convert(right[2]));

            System.out.println(convert(null) + convert(front[0]) + convert(null));
            System.out.println(convert(null) + convert(front[1]) + convert(null));
            System.out.println(convert(null) + convert(front[2]) + convert(null));

            System.out.println(convert(null) + convert(bottom[0]) + convert(null));
            System.out.println(convert(null) + convert(bottom[1]) + convert(null));
            System.out.println(convert(null) + convert(bottom[2]) + convert(null));
        }

        private String convert(int[] line) {
            if (line == null) {
                return "       ";
            }

            return " " + convertChar(line[0]) + " " + convertChar(line[1]) + " " + convertChar(line[2]) + " ";
        }

        private String convertChar(int color) {
            switch (color) {
                case WHITE:
                    return "w";
                case YELLOW:
                    return "y";
                case RED:
                    return "r";
                case ORANGE:
                    return "o";
                case GREEN:
                    return "g";
                case BLUE:
                    return "b";
            }

            return null;
        }
    }
}
