package org.example._5week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CuttingLanCable {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] cables = new long[K];
        int[] count = new int[K];

        long maxBoundary = Long.MIN_VALUE;
        long minBoundary = 1;

        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            maxBoundary = Math.max(cables[i], maxBoundary);
        }

        // answer은 작아질 수록 만들어지는 랜선의 크기가 증가.
        // 만들어지는 랜선의 크기>=N인 최대 answer값을 알아야함.
        // 즉, answer값을 -1씩 내려가면서 찾으면 답을 알 수 있음.
        // 하지만 이건 시간이 너무 오래걸리기 때문에...
        // 이진탐색으로 그 값을 찾겠다는 겨.
        while (minBoundary <= maxBoundary) {
            long mid = (maxBoundary + minBoundary) / 2;
            long cableCounts = Arrays.stream(cables).map(cable -> cable / mid).sum();

            if (cableCounts >= N) {
                minBoundary = mid + 1;
            } else {
                maxBoundary = mid - 1;
            }
        }

        System.out.println((maxBoundary + minBoundary) / 2);
    }

}
