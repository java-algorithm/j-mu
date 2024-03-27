package org.example._6week;

// 해당 문제는 스터디와 관련없이 공부한 내용입니당.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SolvedAC {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        long[] scores = new long[N];

        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }

        int boundaryCount = (int) Math.round(N * 0.15);

        scores = Arrays.stream(scores).sorted().toArray();
        long sum = 0;
        for (int i = boundaryCount; i < N - boundaryCount; i++) {
            sum += scores[i];
        }

        System.out.println(Math.round(sum / (N - boundaryCount * 2)));
    }
}
