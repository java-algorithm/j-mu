package org.example._23week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GreatTown {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[][] dp;
    private static int[] towns;
    private static final int GREAT = 0;
    private static final int NOT_GREAT = 1;

    public static void main(String[] args) throws IOException {
        final int townCount = Integer.parseInt(br.readLine()) + 1;

        dp = new int[townCount][2];
        visited = new boolean[townCount];
        graph = new ArrayList[townCount];
        for (int i = 0; i < townCount; i++) {
            graph[i] = new ArrayList<>();
        }

        towns = new int[townCount];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < townCount; i++) {
            towns[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < townCount - 2; i++) {
            st = new StringTokenizer(br.readLine());
            final int town1 = Integer.parseInt(st.nextToken());
            final int town2 = Integer.parseInt(st.nextToken());

            graph[town1].add(town2);
            graph[town2].add(town1);
        }

        dfs(1);
        System.out.println(Math.max(dp[1][GREAT], dp[1][NOT_GREAT]));
    }

    private static void dfs(int vertex) {
        dp[vertex][GREAT] = towns[vertex];
        dp[vertex][NOT_GREAT] = 0;
        visited[vertex] = true;

        for (final Integer adjacent : graph[vertex]) {
            if (visited[adjacent]) {
                continue;
            }

            dfs(adjacent);
            dp[vertex][GREAT] += dp[adjacent][NOT_GREAT];
            dp[vertex][NOT_GREAT] += Math.max(dp[adjacent][GREAT], dp[adjacent][NOT_GREAT]);
        }
    }
}
