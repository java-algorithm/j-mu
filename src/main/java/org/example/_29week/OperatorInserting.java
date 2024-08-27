package org.example._29week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OperatorInserting {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        int numberCount = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int plusCount = Integer.parseInt(st.nextToken());
        int minusCount = Integer.parseInt(st.nextToken());
        int multiplyCount = Integer.parseInt(st.nextToken());
        int divisionCount = Integer.parseInt(st.nextToken());

        dfs(numbers[0], 1, plusCount, minusCount, multiplyCount, divisionCount);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int number, int index, int plusCount, int minusCount, int multiplyCount, int divisionCount) {
        if (plusCount == 0 && minusCount == 0 && multiplyCount == 0 && divisionCount == 0) {
            max = Math.max(max, number);
            min = Math.min(min, number);
        }

        if (plusCount > 0) {
            dfs(number + numbers[index], index + 1, plusCount - 1, minusCount, multiplyCount, divisionCount);
        }

        if (minusCount > 0) {
            dfs(number - numbers[index], index + 1, plusCount, minusCount - 1, multiplyCount, divisionCount);
        }

        if (multiplyCount > 0) {
            dfs(number * numbers[index], index + 1, plusCount, minusCount, multiplyCount - 1, divisionCount);
        }

        if (divisionCount > 0) {
            dfs(number / numbers[index], index + 1, plusCount, minusCount, multiplyCount, divisionCount - 1);
        }
    }
}

