package org.example._23week;

import java.io.*;
import java.util.StringTokenizer;

public class TimeOverlap {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final int TIME_LIMIT = 1_000_001;

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        final int[] starts = new int[TIME_LIMIT];
        final int[] ends = new int[TIME_LIMIT];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            final int start = Integer.parseInt(st.nextToken());
            final int end = Integer.parseInt(st.nextToken());

            starts[start]++;
            ends[end]++;
        }

        // init sum_starts, sum_ends
        for (int i = 1; i < TIME_LIMIT; i++) {
            starts[i] += starts[i - 1];
            ends[i] += ends[i - 1];
        }

        final int Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            final int questionTime = Integer.parseInt(st.nextToken());
            final int enterPeopleCount = starts[questionTime];
            final int exitPeopleCount = questionTime <= 1 ? 0 : ends[questionTime - 1];
            bw.write((enterPeopleCount - exitPeopleCount) + "\n");
        }
        bw.flush();
    }
}
