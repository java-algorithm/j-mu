package org.example._4week;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SortingNumber {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        // Counting 정렬을 이용
        int[] counts = new int[10_000_001];

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            counts[number]++;
        }

        for (int i = 0; i < 10_000_001; i++) {

            while (counts[i] > 0) {
                bw.write(i + "\n");
                counts[i]--;
            }
        }

        bw.flush();
    }

}
