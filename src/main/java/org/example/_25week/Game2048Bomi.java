package org.example._25week;

import java.io.*;
import java.util.*;

public class Game2048Bomi {

    private static int N;
    private static int[][] board;
    private static boolean[][] check;
    private static int maxBlock = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        check = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
/*
        mergeLeft(board);
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
 */
        dfs(new int[5], 0);
        System.out.println(maxBlock);
    }

    public static void mergeTop(int[][] board, boolean target) {
        check = new boolean[N][N];

        for (int col = 0; col < N; col++) {
            for (int row = 1; row < N; row++) {
                int num = board[row - 1][col];
                maxBlock = Math.max(maxBlock, num);

                for (int k = row; k < N; k++) {
                    if (board[k][col] > 0) {
                        if (target) {
                            System.out.println("??ㅋㅋㅋㅋㅋ - 2");
                            System.out.println("num: " + num);
                            System.out.println("board[k][col]: " + board[k][col]);
                            System.out.println("check[row - 1][col]: " + check[row - 1][col]);
                            System.out.println();
                            System.out.println();

                            System.out.println("board: ");
                            printArray(board);

                            System.out.println("k : " + k);
                            System.out.println("col : " + col);
                            System.out.println("row : " + row);
                        }

                        if (num == board[k][col] && !check[row - 1][col]) {
                            if (target) {
                                System.out.println("??ㅋㅋㅋㅋ");
                            }
                            board[row - 1][col] = num * 2;
                            board[k][col] = 0;
                            check[row - 1][col] = true;
                            maxBlock = Math.max(maxBlock, num * 2);
                        }
                        break;
                    }
                }
            }

            // compact
            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (board[j][col] == 0 && board[k][col] > 0) {
                        board[j][col] = board[k][col];
                        board[k][col] = 0;
                        break;
                    }
                }
            }
        }
    }

    public static void mergeLeft(int[][] board) {
        check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                int num = board[i][j - 1];
                maxBlock = Math.max(maxBlock, num);
                for (int k = j; k < N; k++) {
                    if (board[i][k] > 0) {
                        if (num == board[i][k] && !check[i][j - 1]) {
                            board[i][j - 1] = num * 2;
                            board[i][k] = 0;
                            check[i][j - 1] = true;
                            maxBlock = Math.max(maxBlock, num * 2);
                        }
                        break;
                    }
                }
            }

            // compact
            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (board[i][j] == 0 && board[i][k] > 0) {
                        board[i][j] = board[i][k];
                        board[i][k] = 0;
                        break;
                    }
                }
            }
        }
    }

    public static void mergeRight(int[][] board, boolean target) {
        check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 1; j--) {
                int num = board[i][j];
                maxBlock = Math.max(maxBlock, num);
                for (int k = j - 1; k >= 0; k--) {
                    if (board[i][k] > 0) {
                        if (num == board[i][k] && !check[i][j]) {
                            board[i][j] = num * 2;
                            board[i][k] = 0;
                            check[i][j] = true;
                            maxBlock = Math.max(maxBlock, num * 2);
                        }
                        break;
                    }
                }
            }

            // compact
            for (int j = N - 1; j >= 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (board[i][j] == 0 && board[i][k] > 0) {
                        board[i][j] = board[i][k];
                        board[i][k] = 0;
                        break;
                    }
                }
            }
        }
    }

    public static void mergeDown(int[][] board) {
        check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 1; j--) {
                int num = board[j][i];
                maxBlock = Math.max(maxBlock, num);
                for (int k = j - 1; k >= 0; k--) {
                    if (board[k][i] > 0) {
                        if (num == board[k][i] && !check[j][i]) {
                            board[j][i] = num * 2;
                            board[k][i] = 0;
                            check[j][i] = true;
                            maxBlock = Math.max(maxBlock, num * 2);
                        }
                        break;
                    }
                }
            }

            // compact
            for (int j = N - 1; j >= 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (board[j][i] == 0 && board[k][i] > 0) {
                        board[j][i] = board[k][i];
                        board[k][i] = 0;
                        break;
                    }
                }
            }
        }
    }

    public static void dfs(int[] arr, int index) {
        if (index == 5) {
//            for (int i = 0; i < 5; i++) {
//                System.out.print(arr[i] + " ");
//            }
//            System.out.println();

            int[][] copyBoard = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }

            boolean target = false;
            printArrayStep(arr, copyBoard);
            if (arr[0] == 2 && arr[1] == 0 && arr[2] == 2 && arr[3] == 1 && arr[4] == 0) {
                target = true;
            }

            for (int i = 0; i < 5; i++) {
                if (arr[i] == 0) {
                    mergeRight(copyBoard, target);
                    printArrayStep(arr, copyBoard);

                } else if (arr[i] == 1) {
                    mergeLeft(copyBoard);
                    printArrayStep(arr, copyBoard);

                } else if (arr[i] == 2) {
                    mergeTop(copyBoard, target);
                    printArrayStep(arr, copyBoard);

                } else {
                    mergeDown(copyBoard);
                    printArrayStep(arr, copyBoard);

                }
            }

            //if(arr[0] == 2 && arr[1] == 0 && arr[2] == 2 && arr[3] == 1) {
            /*
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        System.out.print(copyBoard[i][j] + " ");
                    }
                    System.out.println();
                }

             */
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    maxBlock = Math.max(maxBlock, copyBoard[i][j]);
                }
            }
//            System.out.println("maxBlock : " + maxBlock);
            //}
            return;
        }

        for (int i = 0; i < 4; i++) {
            arr[index] = i;
            dfs(arr, index + 1);
        }
    }

    private static void printArrayStep(int[] arr, int[][] copyBoard) {
        if (arr[0] == 2 && arr[1] == 0 && arr[2] == 2 && arr[3] == 1 && arr[4] == 0) {
            printArray(copyBoard);
        }
    }

    private static void printArray(int[][] copyBoard) {
        System.out.println("hoho");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(copyBoard[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n");
    }
}