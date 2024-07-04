package org.example._22week;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class NandM4 {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        final Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();

        final int[] arr = new int[M];
        dfs(N, M, 1, 0, arr);
        bw.flush();
    }

    public static void dfs(int N, int M, int start, int depth, int[] arr) throws IOException {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                if (i == M - 1) {
                    bw.write(arr[i] + "\n");
                    break;
                }
                bw.write(arr[i] + " ");
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            final int[] newArray = arr.clone();
            newArray[depth] = i;
            dfs(N, M, i, depth + 1, newArray);
        }
    }
}
