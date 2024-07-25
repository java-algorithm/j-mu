package org.example._25week;

import java.util.Arrays;

public class EntryScreening {

    private static final int MAX_TIME = 1_000_000_000;

    public static void main(String[] args) {
        final int n = 6;
        final int[] times = {7, 10};

        long solution = solution(n, times);
        System.out.println(solution);
    }

    public static long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long left = 0;
        long right = (long) times[times.length - 1] * n;

        while (left <= right) {
            final long mid = (left + right) / 2;

            long count = 0;
            for (int time : times) {
                count += (mid / time);
            }

            if (count >= n) {
                right = mid - 1;
                answer = mid;
            } else { // count < n
                left = mid + 1;
            }
        }

        return answer;
    }
}
