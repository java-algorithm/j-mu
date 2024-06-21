package org.example._11week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Practice {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int M;
    private static int V;
    private static boolean[] visited;
    private static List<List<Integer>> graph;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
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

        visited = new boolean[N + 1];
        sb = new StringBuilder();
        dfs();

        visited = new boolean[N + 1];
        sb = new StringBuilder();
        dfsRecursion(V);
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);

        visited = new boolean[N + 1];
        sb.setLength(0);
        bfs();
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(V);
        visited[V] = true;

        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            sb.append(vertex).append(" ");

            List<Integer> adjacents = graph.get(vertex).stream().sorted().collect(Collectors.toList());
            for (Integer adjacent : adjacents) {
                if (!visited[adjacent]) {
                    visited[adjacent] = true;
                    queue.offer(adjacent);
                }
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    private static void dfsRecursion(Integer vertex) {
        visited[vertex] = true;
        sb.append(vertex).append(" ");

        List<Integer> adjacents = graph.get(vertex).stream()
                .sorted()
                .collect(Collectors.toList());
        for (Integer adjacent : adjacents) {
            if (!visited[adjacent]) {
                dfsRecursion(adjacent);
            }
        }
    }

    private static void dfs() {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(V);

        while (!stack.isEmpty()) {
            Integer vertex = stack.pollLast();
            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            sb.append(vertex).append(" ");

            List<Integer> adjacents = graph.get(vertex).stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            for (Integer adjacent : adjacents) {
                stack.addLast(adjacent);
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

}
