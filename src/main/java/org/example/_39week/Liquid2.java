package org.example._39week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Liquid2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] liquids;

    private static int answer = Integer.MAX_VALUE;
    private static int ansStart = 0;
    private static int ansEnd = 0;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        liquids = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(liquids);

        int start = 0;
        int end = liquids.length - 1;

        ansStart = start;
        ansEnd = end;

        // 1  2 3
        // -3 -1 1 4
        while (start < end) {
            int sum = liquids[start] + liquids[end];

            renewAnswer(start, end, sum);

            if (sum > 0) {
                end--;
            } else if (sum < 0) {
                start++;
            } else {
                break;
            }
        }

        System.out.println(liquids[ansStart] + " " + liquids[ansEnd]);
    }

    private static void renewAnswer(int start, int end, int sum) {
        if (Math.abs(answer) > Math.abs(sum)) {
            answer = sum;
            ansStart = start;
            ansEnd = end;
        }
    }
}
