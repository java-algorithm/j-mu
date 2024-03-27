package org.example._5week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CuttingTree {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int treeCount = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] treeHeights = new int[treeCount];
        st = new StringTokenizer(br.readLine());

        long maxBoundary = Long.MIN_VALUE;
        long minBoundary = 1;

        for (int i = 0; i < treeCount; i++) {
            treeHeights[i] = Integer.parseInt(st.nextToken());
            maxBoundary = Math.max(treeHeights[i], maxBoundary);
        }

        while (minBoundary <= maxBoundary) {
            long mid = (minBoundary + maxBoundary) / 2;
            long result = Arrays.stream(treeHeights)
                .mapToLong(height -> height - mid > 0 ? height - mid : 0)
                .sum();

            if (result == target) {
                break;
            } else if (result > target) {
                minBoundary = mid + 1;
            } else if (result < target) {
                maxBoundary = mid - 1;
            }
        }

        System.out.println((minBoundary + maxBoundary) / 2);
    }

}
