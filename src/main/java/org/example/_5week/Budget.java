package org.example._5week;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class Budget {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer.parseInt(scanner.nextLine());// 필요 x
        int[] regions = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int budget = Integer.parseInt(scanner.nextLine());

        int sum = Arrays.stream(regions).sum();
        OptionalInt optionalMax = Arrays.stream(regions).max();
        int max = optionalMax.getAsInt();
        if (sum <= budget) {
            System.out.println(max);
            return;
        }

        long maxBoundary = max;
        long minBoundary = 1;

        while (minBoundary <= maxBoundary) {
            long mid = (minBoundary + maxBoundary) / 2;

            long total = Arrays.stream(regions)
                .mapToLong(region -> region >= mid ? mid : region)
                .sum();

            if (total > budget) {
                maxBoundary = mid - 1;
            } else if (total < budget) {
                minBoundary = mid + 1;
            } else {
                break;
            }
        }

        System.out.println((minBoundary + maxBoundary) / 2);
    }
}
