package org.example._22week;

import java.io.*;
import java.util.*;

public class NandM6 {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] values;
    private static Set<Status> answers = new HashSet<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        values = new int[N];
        boolean[] visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            final int value = Integer.parseInt(st.nextToken());
            values[i] = value;
        }

        values = Arrays.stream(values).sorted().toArray();
        List<Integer> arr = new ArrayList<>();

        dfs(N, M, 0, visited, new Status());
        bw.flush();
    }

    public static void dfs(int N, int M, int depth, boolean[] visited, Status arr) throws IOException {
        if (depth == M) {
            if (answers.contains(arr)) {
                return;
            }

            for (int i = 0; i < M; i++) {
                if (i == M - 1) {
                    bw.write(arr.get(i) + "\n");
                    break;
                }
                bw.write(arr.get(i) + " ");
            }
            answers.add(arr);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            final int value = values[i];
            final boolean[] newVisited = visited.clone();
            final List<Integer> newArray = new ArrayList<>(arr.values);
            newArray.add(value);
            dfs(N, M, depth + 1, newVisited, new Status(newArray));
            newArray.remove(newArray.size() - 1);
        }
    }

    private static class Status{
        List<Integer> values;

        public Status(final List<Integer> values) {
            this.values = values;
        }

        public Status() {
            this.values = new ArrayList<>();
        }

        public Integer get(int i) {
            return values.get(i);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Status status = (Status) o;
            return Objects.equals(values, status.values);
        }

        @Override
        public int hashCode() {
            return Objects.hash(values);
        }
    }
}
