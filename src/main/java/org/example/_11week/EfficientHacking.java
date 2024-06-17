package org.example._11week;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class EfficientHacking {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<Integer>[] graph;
    private static Set<Integer> visited;
    private static int maxCount = Integer.MIN_VALUE;
    private static List<Integer> vertexs;
    private static Set<Integer> totalVisited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            graph[from].add(to);
        }

        for (int vertex = 1; vertex < N + 1; vertex++) {
            if (totalVisited.contains(vertex)) {
                continue;
            }
            visited = new HashSet<>();
            bfs(vertex);

            if (visited.size() > maxCount) {
                maxCount = visited.size();
                vertexs = new ArrayList<>();
                vertexs.add(vertex);
            } else if (visited.size() == maxCount) {
                vertexs.add(vertex);
            }
        }

        List<Integer> answers = vertexs.stream().sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Integer answer : answers) {
            sb.append(answer + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        System.out.println(sb);
    }

    private static void bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        visited.add(vertex);
        totalVisited.add(vertex);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            List<Integer> adjacents = graph[poll];
            for (Integer adjacent : adjacents) {
                if (!visited.contains(adjacent)) {
                    queue.offer(adjacent);
                    visited.add(adjacent);
                    totalVisited.add(adjacent);
                }
            }
        }
    }
}
