package org.example._2week;

import java.util.Arrays;
import java.util.Scanner;

public class _2048 {

    // 0: initial
    private static int[][] board;
    private static int N;
    private static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = Integer.parseInt(scanner.nextLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < row.length; j++) {
                board[i][j] = row[j];
                if (maxValue < row[j]) {
                    maxValue = row[j];
                }
            }
        }

        if (N == 1) {
            System.out.println(board[0][0]);
            return;
        }
        DIRECTION[] directions = new DIRECTION[5];

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }

        DIRECTION[] directionValues = DIRECTION.values();
        for (DIRECTION directionValue : directionValues) {
            directions[0]=directionValue;
            combination(directions, directionValue, 1);
        }

        System.out.println(maxValue);
    }

    // 4^4 = 2^8 = 256
    private static void combination(DIRECTION[] directions, DIRECTION dir, int count) {
        directions[count]=dir;

        if (count == 4) {
            for (int i = 0; i < 5; i++) {
                swipe(directions[i], board);
            }
            return;
        }

        DIRECTION[] directionValues = DIRECTION.values();
        for (DIRECTION directionValue : directionValues) {
            combination(directions, directionValue, count + 1);
        }
    }

    private static void swipe(DIRECTION direction, int[][] gameBoard) {
        switch (direction) {
//            case UP:
//                int[][] copyBoard1 = copyWith(gameBoard);
//                upSwipe(copyBoard1);
//                break;
            case DOWN:
                int[][] copyBoard2 = copyWith(gameBoard);
                downSwipe(copyBoard2);
                break;
//            case LEFT:
//                int[][] copyBoard3 = copyWith(gameBoard);
//                leftSwipe(copyBoard3);
//                break;
//            case RIGHT:
//                int[][] copyBoard4 = copyWith(gameBoard);
//                rightSwipe(copyBoard4);
//                break;
        }
    }

    private static void rightSwipe(int[][] board) {
        for (int row = 0; row < N; row++) {
            int fPointer = 0;
            int sPointer = 1;
            while (true) {
                if (sPointer == N) {
                    break;
                }

                if (fPointer == sPointer) {
                    sPointer++;
                    continue;
                }

                int fValue = board[row][fPointer];
                int sValue = board[row][sPointer];

                if (sValue == 0) {
                    sPointer++;
                    continue;
                }

                if (isPossibleSwipe(fValue, sValue)) {
                    if (fValue == 0) {
                        board[row][fPointer] = board[row][sPointer];
                        board[row][sPointer] = 0;

                    } else {
                        board[row][fPointer] *= 2;
                        board[row][sPointer] = 0;
                        if (maxValue < fValue * 2) {
                            maxValue = fValue * 2;
                        }
                    }

                    sPointer++;
                    continue;
                }

                if (sValue != 0) {
                    fPointer++;
                    continue;
                }

                sPointer++;
            }
        }
    }

    private static void leftSwipe(int[][] board) {
        for (int row = 0; row < N; row++) {
            int fPointer = N - 1;
            int sPointer = N - 2;
            while (true) {
                if (sPointer == -1) {
                    break;
                }

                if (fPointer == sPointer) {
                    sPointer--;
                    continue;
                }

                int fValue = board[row][fPointer];
                int sValue = board[row][sPointer];

                if (sValue == 0) {
                    sPointer--;
                    continue;
                }

                if (isPossibleSwipe(fValue, sValue)) {
                    if (fValue == 0) {
                        board[row][fPointer] = board[row][sPointer];
                        board[row][sPointer] = 0;

                    } else {
                        board[row][fPointer] *= 2;
                        board[row][sPointer] = 0;
                        if (maxValue < fValue * 2) {
                            maxValue = fValue * 2;
                        }
                    }

                    sPointer--;
                    continue;
                }

                if (sValue != 0) {
                    fPointer--;
                    continue;
                }

                sPointer--;
            }
        }
    }

    private static void downSwipe(int[][] board) {
        for (int col = 0; col < N; col++) {
            int fPointer = N - 1;
            int sPointer = N - 2;
            while (true) {
                if (sPointer == -1) {
                    break;
                }

                if (fPointer == sPointer) {
                    sPointer--;
                    continue;
                }

                int fValue = board[fPointer][col];
                int sValue = board[sPointer][col];

                if (sValue == 0) {
                    sPointer--;
                    continue;
                }

                if (isPossibleSwipe(fValue, sValue)) {
                    if (fValue == 0) {
                        board[fPointer][col] = board[sPointer][col];
                        board[sPointer][col] = 0;

                    } else {
                        board[fPointer][col] *= 2;
                        board[sPointer][col] = 0;
                        if (maxValue < fValue * 2) {
                            maxValue = fValue * 2;
                        }
                    }

                    sPointer--;
                    continue;
                }

                if (sValue != 0) {
                    fPointer--;
                    continue;
                }

                sPointer--;
            }
        }
    }


    private static void upSwipe(int[][] board) {
        for (int col = 0; col < N; col++) {
            int fPointer = 0;
            int sPointer = 1;
            while (true) {
                if (sPointer == N) {
                    break;
                }

                if (fPointer == sPointer) {
                    sPointer++;
                    continue;
                }

                int fValue = board[fPointer][col];
                int sValue = board[sPointer][col];

                if (sValue == 0) {
                    sPointer++;
                    continue;
                }

                if (isPossibleSwipe(fValue, sValue)) {
                    if (fValue == 0) {
                        board[fPointer][col] = board[sPointer][col];
                        board[sPointer][col] = 0;

                    } else {
                        board[fPointer][col] *= 2;
                        board[sPointer][col] = 0;
                        if (maxValue < fValue * 2) {
                            maxValue = fValue * 2;
                        }
                    }

                    sPointer++;
                    continue;
                }

                if (sValue != 0) {
                    fPointer++;
                    continue;
                }

                sPointer++;
            }
        }
    }

    private static boolean isPossibleSwipe(int fValue, int sValue) {
        if (fValue == 0 && sValue == 0) {
            return false;
        }

        if (fValue == 0 && sValue != 0) {
            return true;
        }

        return fValue == sValue;
    }

    public static int[][] copyWith(int[][] original) {
        int[][] copy = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = original[i][j];
            }
        }

        return copy;
    }

    enum DIRECTION {
        UP,
        DOWN,
        RIGHT,
        LEFT;
    }

}



