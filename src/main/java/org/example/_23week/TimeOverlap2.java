package org.example._23week;

import java.io.*;
import java.util.StringTokenizer;

public class TimeOverlap2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final int TIME_LIMIT = 1_000_002;

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        final int[] sums = new int[TIME_LIMIT];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            final int start = Integer.parseInt(st.nextToken());
            final int end = Integer.parseInt(st.nextToken());

            sums[start]++;
            sums[end + 1]--;
        }

        for (int i = 1; i < TIME_LIMIT; i++) {
            sums[i] += sums[i - 1];
        }

        final int Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            final int questionTime = Integer.parseInt(st.nextToken());
            bw.write(sums[questionTime] + "\n");
        }
        bw.flush();
    }
}
