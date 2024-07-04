package org.example._22week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FriendPalindrome {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static boolean[][] graph;
    private static boolean[] visited;

    private static int N;
    private static int M;
    private static int answer;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        graph = new boolean[N + 1][N + 1];
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            final int friend1 = Integer.parseInt(st.nextToken());
            final int friend2 = Integer.parseInt(st.nextToken());

            graph[friend1][friend2] = true;
            graph[friend2][friend1] = true;

            final Pair pair = new Pair(friend1, friend2);
            pairs.add(pair);
        }

        for (int i = 0; i < M; i++) {
            dfs(pairs, i, 2);
        }

        System.out.println(answer < N ? answer + 1 : answer);
    }

    private static void dfs(final List<Pair> pairs, final int index, final int count) {
        final Pair pair = pairs.get(index);
        if (pair.isVisited()) {
            return;
        }
        if (count > answer) {
            answer = count;
        }

        for (int i = index; i < M; i++) {
            pair.visit();
            dfs(pairs, i, count + 2);
            pair.visit();
        }
    }

    private static class Pair {
        int friend1;
        int friend2;

        public Pair(final int friend1, final int friend2) {
            this.friend1 = friend1;
            this.friend2 = friend2;
        }

        public void visit() {
            visited[friend1] = !visited[friend1];
            visited[friend2] = !visited[friend2];
        }

        public boolean isVisited() {
            return visited[friend1] || visited[friend2];
        }
    }
}
