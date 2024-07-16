package org.example._24week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class TrainingCenter {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        final int[] heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[] querys = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken()) - 1;
            final int b = Integer.parseInt(st.nextToken());
            final int amount = Integer.parseInt(st.nextToken());

            querys[a] += amount;
            if (b < N) {
                querys[b] -= amount;
            }
        }

        for (int i = 1; i < N; i++) {
            querys[i] += querys[i - 1];
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            heights[i] += querys[i];
            sb.append(heights[i]).append(" ");
        }

        System.out.println(sb);
    }
}
