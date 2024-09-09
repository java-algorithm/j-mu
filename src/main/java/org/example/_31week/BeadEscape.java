package org.example._31week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeadEscape {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int SUCCESS_CODE = 1;
    private static final int FAIL_CODE = 0;
    private static final int EXIT_CODE = 2;

    private static int rowSize;
    private static int colSize;
    private static final char EMPTY = '.';
    private static final char WALL = '#';
    private static final char EXIT = 'O';
    private static final char RED_BREAD = 'R';
    private static final char BLUE_BREAD = 'B';

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        char[][] board = new char[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            board[i] = br.readLine().toCharArray();
        }

//        rowSize = 5;
//        colSize = 2;
//        char[][] board = new char[][]{{'.', EXIT}, {'R', 'R'}, {'#', '.'}, {'.', '.'}, {'B', '.'}};

//        printArray(board);
        dfs(board, 1);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void dfs(char[][] board, int depth) {
        if (depth == 11) {
            return;
        }

        upSwipe(board, depth);
        downSwipe(board, depth);
        rightSwipe(board, depth);
        leftSwipe(board, depth);
    }

    private static char[][] copyBoard(char[][] board) {
        char[][] newBoard = new char[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        return newBoard;
    }

    private static void leftSwipe(char[][] board, int depth) {
        char[][] newBoard = copyBoard(board);
        boolean exits = false;

        for (int row = 0; row < rowSize; row++) {
            int colPivot = 0;

            for (int col = 0; col < colSize; col++) {
                // 비어있는 경우
                if (newBoard[row][col] == EMPTY) {
                    continue;
                }

                if (newBoard[row][col] == WALL) {
                    colPivot = col + 1;
                    continue;
                }

                if (newBoard[row][col] == EXIT) {
                    colPivot = col;
                    continue;
                }

                if (newBoard[row][col] == RED_BREAD || newBoard[row][col] == BLUE_BREAD) {
                    //구멍에 빠지는 경우를 고려할 수 있도록 수정해야함.
                    if (newBoard[row][colPivot] == EXIT) {
                        if (newBoard[row][col] == RED_BREAD) {
                            exits = true;
                        }

                        if (newBoard[row][col] == BLUE_BREAD) {
                            return;
                        }

                    } else {
                        newBoard[row][colPivot] = newBoard[row][col];
                        if (colPivot != col) {
                            newBoard[row][col] = EMPTY;
                        }

                        colPivot++;
                    }
                }
            }

            if (exits) {
                answer = Math.min(answer, depth);
                return;
            }
        }

        dfs(newBoard, depth + 1);
    }

    private static void rightSwipe(char[][] board, int depth) {
        char[][] newBoard = copyBoard(board);
        boolean exits = false;

        for (int row = 0; row < rowSize; row++) {
            int colPivot = colSize - 1;

            for (int col = colSize - 1; col >= 0; col--) {
                // 비어있는 경우
                if (newBoard[row][col] == EMPTY) {
                    continue;
                }

                if (newBoard[row][col] == WALL) {
                    colPivot = col - 1;
                    continue;
                }

                if (newBoard[row][col] == EXIT) {
                    colPivot = col;
                    continue;
                }

                if (newBoard[row][col] == RED_BREAD || newBoard[row][col] == BLUE_BREAD) {
                    //구멍에 빠지는 경우를 고려할 수 있도록 수정해야함.
                    if (newBoard[row][colPivot] == EXIT) {
                        if (newBoard[row][col] == RED_BREAD) {
                            exits = true;
                        }

                        if (newBoard[row][col] == BLUE_BREAD) {
                            return;
                        }

                    } else {
                        newBoard[row][colPivot] = newBoard[row][col];
                        if (colPivot != col) {
                            newBoard[row][col] = EMPTY;
                        }
                        colPivot--;
                    }
                }
            }

            if (exits) {
                answer = Math.min(answer, depth);
                return;
            }
        }

        dfs(newBoard, depth + 1);
    }

    private static void upSwipe(char[][] board, int depth) {
        char[][] newBoard = copyBoard(board);
        boolean exits = false;

        for (int col = 0; col < colSize; col++) {
            int rowPivot = 0;

            for (int row = 0; row < rowSize; row++) {
                // 비어있는 경우
                if (newBoard[row][col] == EMPTY) {
                    continue;
                }

                if (newBoard[row][col] == WALL) {
                    rowPivot = row + 1;
                    continue;
                }

                if (newBoard[row][col] == EXIT) {
                    rowPivot = row;
                    continue;
                }

                if (newBoard[row][col] == RED_BREAD || newBoard[row][col] == BLUE_BREAD) {
                    //구멍에 빠지는 경우를 고려할 수 있도록 수정해야함.
                    if (newBoard[rowPivot][col] == EXIT) {
                        if (newBoard[row][col] == RED_BREAD) {
                            exits = true;
                        }

                        if (newBoard[row][col] == BLUE_BREAD) {
                            return;
                        }

                    } else {
                        newBoard[rowPivot][col] = newBoard[row][col];
                        if (rowPivot != row) {
                            newBoard[row][col] = EMPTY;
                        }
                        rowPivot++;
                    }
                }
            }

            if (exits) {
                answer = Math.min(answer, depth);
                return;
            }
        }

        dfs(newBoard, depth + 1);
    }

    private static void downSwipe(char[][] board, int depth) {
        char[][] newBoard = copyBoard(board);
        boolean exits = false;

        for (int col = 0; col < colSize; col++) {
            int rowPivot = rowSize - 1;

            for (int row = rowSize - 1; row >= 0; row--) {
                // 비어있는 경우
                if (newBoard[row][col] == EMPTY) {
                    continue;
                }

                if (newBoard[row][col] == WALL) {
                    rowPivot = row - 1;
                    continue;
                }

                if (newBoard[row][col] == EXIT) {
                    rowPivot = row;
                    continue;
                }

                if (newBoard[row][col] == RED_BREAD || newBoard[row][col] == BLUE_BREAD) {
                    //구멍에 빠지는 경우를 고려할 수 있도록 수정해야함.
                    if (newBoard[rowPivot][col] == EXIT) {
                        if (newBoard[row][col] == RED_BREAD) {
                            exits = true;
                        }

                        if (newBoard[row][col] == BLUE_BREAD) {
                            return;
                        }

                    } else {
                        newBoard[rowPivot][col] = newBoard[row][col];
                        if (rowPivot != row) {
                            newBoard[row][col] = EMPTY;
                        }

                        rowPivot--;
                    }
                }
            }

            if (exits) {
                answer = Math.min(answer, depth);
                return;
            }
        }

        dfs(newBoard, depth + 1);
    }

    private static void printArray(char[][] board) {
        System.out.println("------print board!!------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
