package org.example._31week;

class NotDestoryedBuilding {
    public int solution(int[][] board, int[][] skills) {
        int answer = 0;
        int rowSize = board.length;
        int colSize = board[0].length;
        int[][] mark = new int[rowSize + 1][colSize + 1];

        for (int[] skill : skills) {
            int type = skill[0];
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3];
            int c2 = skill[4];
            int degree = (type == 1) ? -1 * skill[5] : skill[5];

            mark[r1][c1] += degree;
            mark[r1][c2 + 1] -= degree;
            mark[r2 + 1][c1] -= degree;
            mark[r2 + 1][c2 + 1] += degree;
        }

        // 가로 한번
        for (int row = 0; row < mark.length; row++) {
            for (int col = 0; col < mark[0].length - 1; col++) {
                mark[row][col + 1] += mark[row][col];
            }
        }

        // 세로 한번
        for (int col = 0; col < mark[0].length; col++) {
            for (int row = 0; row < mark.length - 1; row++) {
                mark[row + 1][col] += mark[row][col];
            }
        }

//        printArray(mark);
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (board[row][col] + mark[row][col] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}