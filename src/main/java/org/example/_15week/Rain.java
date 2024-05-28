package org.example._15week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Rain {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final boolean BLOCK = true;
    private static final boolean EMPTY = false;

    public static void main(String[] args) throws IOException {
        final StringTokenizer st = new StringTokenizer(br.readLine());
        final int row = Integer.parseInt(st.nextToken());
        final int col = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[row][col];
        int[] heights = new int[col];
        heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int maxHeight = Arrays.stream(heights).max().getAsInt();

        for (int i = 0; i < col; i++) {
            final int height = heights[i];
            for (int j = row - 1; j >= row - height; j--) {
                map[j][i] = BLOCK;
            }
        }

//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        int answer = 0;
        for (int i = row - maxHeight; i < row; i++) {
            boolean hasBlock = false;
            int result = 0;
            int count = 0;
            for (int j = 0; j < col; j++) {
                if (map[i][j] == BLOCK && hasBlock) {
                    result += count;
                    count = 0;
                } else if (map[i][j] == BLOCK) {
                    hasBlock = true;
                } else if (map[i][j] == EMPTY && hasBlock) {
                    count++;
                }
            }
//            System.out.println(result);
            answer += result;
        }

//        System.out.println();
        System.out.println(answer);
    }
}
