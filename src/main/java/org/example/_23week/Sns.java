package org.example._23week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sns {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] dp;
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static final int EARLY_ADAPTER = 0;
    private static final int NOT_EARLY_ADAPTER = 1;

    public static void main(String[] args) throws IOException {
        final int vertexCount = Integer.parseInt(br.readLine());

        visited = new boolean[vertexCount + 1];
        dp = new int[vertexCount + 1][2];
        graph = new ArrayList[vertexCount + 1];
        for (int i = 1; i < vertexCount + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < vertexCount - 1; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final int vertex1 = Integer.parseInt(st.nextToken());
            final int vertex2 = Integer.parseInt(st.nextToken());

            graph[vertex1].add(vertex2);
            graph[vertex2].add(vertex1);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][EARLY_ADAPTER], dp[1][NOT_EARLY_ADAPTER]));
    }

    public static void dfs(int vertex) {
        visited[vertex] = true;
        dp[vertex][EARLY_ADAPTER] = 1;
        dp[vertex][NOT_EARLY_ADAPTER] = 0;

        for (final Integer child : graph[vertex]) {
            if (visited[child]) {
                continue;
            }

            dfs(child);
            dp[vertex][EARLY_ADAPTER] += Math.min(dp[child][EARLY_ADAPTER], dp[child][NOT_EARLY_ADAPTER]);
            dp[vertex][NOT_EARLY_ADAPTER] += dp[child][EARLY_ADAPTER];
        }
    }
}
