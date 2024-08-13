package org.example._27week;

import java.util.Arrays;

public class FindLatestGroupOfSizeM {

    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 2, 4};
//        int[] arr = {1};
//        int[] arr = {10, 6, 9, 4, 7, 8, 5, 2, 1, 3};
        int m = 1;

        int answer = findLatestStep(arr, m);
        System.out.println(answer);
    }

    public static int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        int max = -1;

        // 이진 문자열 생성
        boolean[] bits = new boolean[n];
        Arrays.fill(bits, true);

        if (hasMLength(m, n, bits)) {
            return n;
        }

        for (int i = n - 1; i >= 0; i--) {
            bits[arr[i] - 1] = false;

            if (i < m - 1) {
                break;
            }

            if (hasMLength(m, n, bits)) {
                return i;
            }
        }

        return max;
    }

    private static boolean hasMLength(int m, int n, boolean[] bits) {
        int curLength = 0;

        for (int j = 0; j < n; j++) {
            if (!bits[j]) {
                curLength = 0;
                continue;
            }

            curLength++;
            if (curLength != m) {
                continue;
            }

            // 1이 연속해서 m개 있는 경우

            //
            if (j + 1 == n) {
                return true;
            }

            if (!bits[j + 1]) {
                return true;
            }
        }

        return false;
    }
}
