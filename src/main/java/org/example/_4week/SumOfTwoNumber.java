package org.example._4week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumOfTwoNumber {

    private static int count = 0;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .sorted()
            .toArray();
        int target = Integer.parseInt(br.readLine());

        int fPointer = 0;
        int sPointer = numbers.length - 1;

        if (sPointer == 0) {
            System.out.println("0");
            return;
        }

        while (fPointer < sPointer) {
            int fValue = numbers[fPointer];
            int sValue = numbers[sPointer];
            int sum = fValue + sValue;

            if (sum > target) {
                sPointer--;
            } else if (sum < target) {
                fPointer++;
            } else if (sum == target) {
                count++;
                fPointer++;
            }
        }

        System.out.println(count);
    }

}
