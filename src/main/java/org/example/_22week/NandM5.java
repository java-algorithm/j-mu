package org.example._22week;

import java.io.*;
import java.util.*;

public class NandM5 {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] values;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        values = new int[N];
        final Map<Integer, Boolean> visited = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            final int value = Integer.parseInt(st.nextToken());
            visited.put(value, false);
            values[i] = value;
        }

        values = Arrays.stream(values).sorted().toArray();
        int[] arr = new int[M];

        dfs(N, M, 0, visited, arr);
        bw.flush();
    }

    public static void dfs(int N, int M, int depth, Map<Integer, Boolean> visited, int[] arr) throws IOException {
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

        for (int i = 0; i < N; i++) {
            final int value = values[i];
            if (visited.get(value)) {
                continue;
            }

            final HashMap<Integer, Boolean> newVisited = new HashMap<>(visited);
            newVisited.put(value, true);
            final int[] newArray = arr.clone();
            newArray[depth] = value;
            dfs(N, M, depth + 1, newVisited, newArray);
        }
    }
}
