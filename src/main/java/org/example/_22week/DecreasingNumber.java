package org.example._22week;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DecreasingNumber {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<Long> decreasingNumbers = new ArrayList<>();

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        N = sc.nextInt();


        // 10자리가 넘어가면 감소하는 수가 될 수 없음. 9876543210 => 값이 long이여야함.
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < 10; j++) {
                dfs(j, i - 1, i, (long) ((long) j * Math.pow(10, i - 1)));
            }
        }

        if (decreasingNumbers.size() - 1 < N) {
            System.out.println(-1);
        }else{
            System.out.println(decreasingNumbers.get(N));
        }
    }

    private static void dfs(final int curNumber, final int curDigit, final int digit, final long value) {
        if (curDigit == 0) {
            decreasingNumbers.add((long) value);
            return;
        }

        for (long k = 0; k <= curNumber - 1; k++) {
            long newValue = (long) (value + k * Math.pow(10, curDigit - 1));
            dfs((int) k, curDigit - 1, digit, newValue);
        }
    }
}
