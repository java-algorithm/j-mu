package org.example._12week;

import java.io.*;
import java.util.Arrays;

public class SortingNumbers {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        final int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            final int value = Integer.parseInt(br.readLine());
            numbers[i] = value;
        }

        Arrays.sort(numbers);
        for (int i = 0; i < N; i++) {
            bw.write(numbers[i] + "\n");
        }
        bw.flush();
    }
}
