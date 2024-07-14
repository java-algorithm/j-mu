package org.example._23week;

import java.io.*;
import java.util.StringTokenizer;

public class CumulativeSum {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        // 누적합 만들기가 쉬움.
        int[] sumArray = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            sumArray[i] = Integer.parseInt(st.nextToken()) + sumArray[i - 1];
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            final int start = Integer.parseInt(st.nextToken());
            final int end = Integer.parseInt(st.nextToken());

            bw.write((sumArray[end] - sumArray[start - 1]) + "\n");
        }

        bw.flush();
    }
}
