package org.example._11week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Practice2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int M;
    private static int V;
    private static List<List<Integer>> graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>(N + 1));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());

            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
        }

        //DFS
        dfs(V);
        System.out.println();

        visited = new boolean[N + 1];
        bfs();
        System.out.println();
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(V);
        visited[V] = true;

        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            System.out.print(vertex + " ");

            List<Integer> adjacents = graph.get(vertex).stream().sorted().collect(Collectors.toList());
            for (Integer adjacent : adjacents) {
                if (!visited[adjacent]) {
                    queue.offer(adjacent);
                    visited[adjacent] = true;
                }
            }
        }
    }

    private static void dfs(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        List<Integer> adjacents = graph.get(vertex).stream().sorted().collect(Collectors.toList());
        for (Integer adjacent : adjacents) {
            if (!visited[adjacent]) {
                dfs(adjacent);
            }
        }

    }
}
