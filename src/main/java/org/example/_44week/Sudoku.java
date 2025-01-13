package org.example._44week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sudoku {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int EMPTY = 0;
    private static final int[][] numberPlaces = new int[9][9];

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 9; i++) {
            numberPlaces[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        backTracking(0, 0);
    }

    private static void backTracking(int currentRow, int currentCol) {
        for (int row = currentRow; row < 9; row++) {
            for (int col = (row == currentRow) ? currentCol : 0; col < 9; col++) {
                if (numberPlaces[row][col] != EMPTY) {
                    continue;
                }

                for (int value = 1; value <= 9; value++) {
                    if (isInvalid(row, col, value)) {
                        continue;
                    }

                    numberPlaces[row][col] = value;
                    backTracking(row, col + 1);
                }

                numberPlaces[row][col] = EMPTY;
                return;
            }
        }

        print();
        System.exit(0);
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(numberPlaces[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static boolean isInvalid(int row, int col, int value) {
        if (isInvalidRow(row, value)) {
            return true;
        }

        if (isInvalidCol(col, value)) {
            return true;
        }

        if (isInvalidCell(row, col, value)) {
            return true;
        }

        return false;
    }

    private static boolean isInvalidCell(int targetRow, int targetCol, int value) {
        // {0, 1, 2}, {3, 4, 5}, {6, 7, 8}
        int pivotRow = targetRow / 3;
        int pivotCol = targetCol / 3;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (numberPlaces[pivotRow * 3 + row][pivotCol * 3 + col] == value) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isInvalidCol(int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (numberPlaces[i][col] == value) {
                return true;
            }
        }

        return false;
    }

    private static boolean isInvalidRow(int row, int value) {
        for (int i = 0; i < 9; i++) {
            if (numberPlaces[row][i] == value) {
                return true;
            }
        }

        return false;
    }

    private static void printNumbers() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(numberPlaces[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
