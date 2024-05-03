package org.example._12week;

import java.io.*;
import java.util.Arrays;

public class SortingNumbers2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        final int[] counts = new int[10_001];

        for (int i = 0; i < N; i++) {
            final int value = Integer.parseInt(br.readLine());
            counts[value]++;
        }

        for (int i = 0; i < 10_001; i++) {
            final int count = counts[i];
            for (int j = 0; j < count; j++) {
                bw.write(i + "\n");
            }
        }
        bw.flush();
    }
}
