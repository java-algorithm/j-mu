package org.example._11week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindingRoute {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[i] = row;
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i]=true;

            dfs(i);
            for (int j = 0; j < N; j++) {
                System.out.print(visited[j] ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    private static void dfs(int vertex) { //3
        int[] adjacents = graph[vertex]; //3
        for (int i = 0; i < adjacents.length; i++) {
            if (adjacents[i] == 1 && !visited[i]) {
                visited[vertex] = !visited[vertex];
                dfs(i);
            }
        }
    }
}
