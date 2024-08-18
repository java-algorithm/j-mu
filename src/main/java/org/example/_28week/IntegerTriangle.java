package org.example._28week;

import java.util.Arrays;

public class IntegerTriangle {

    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        int result = solution(triangle);
        System.out.println(result);
    }

    private static int solution(int[][] triangle) {
        int rowSize = triangle.length;

        for (int row = 1; row < rowSize; row++) {

            for (int col = 0; col <= row; col++) {
                int left = col > 0 ? triangle[row - 1][col - 1] : 0;
                int right = row == col ? 0 : triangle[row - 1][col];

                triangle[row][col] += Math.max(left, right);
            }
        }

        return Arrays.stream(triangle[rowSize - 1]).max().getAsInt();
    }


}
