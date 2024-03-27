package org.example._4week;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SortingNumber2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        // Counting 정렬을 이용
        boolean[] plusCounts = new boolean[1_000_001];
        boolean[] minusCounts = new boolean[1_000_001];

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            if (number >= 0) {
                plusCounts[number]=true;
            }else{
                minusCounts[-number]=true;
            }
        }

        for (int i = 1_000_000; i >= 0; i--) {
            if (minusCounts[i]) {
                bw.write(-i+"\n");
            }
        }
        for (int i = 0; i < 1_000_001; i++) {
            if (plusCounts[i]) {
                bw.write(i + "\n");
            }
        }

        bw.flush();
    }

}
