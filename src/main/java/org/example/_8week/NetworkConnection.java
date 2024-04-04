package org.example._8week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NetworkConnection {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        int nodeCount = Integer.parseInt(br.readLine());
        int edgeCount = Integer.parseInt(br.readLine());

        parent = new int[nodeCount + 1];
        for (int i = 1; i < nodeCount + 1; i++) {
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(from, to, cost);
            edges.add(edge);
        }

        edges.sort(Comparator.comparingInt(e -> e.cost));

        int answer = 0;
        for (int i = 0; i < edgeCount; i++) {
            Edge edge = edges.get(i);
            if (findParent(edge.from) != findParent(edge.to)) {
                union(edge.from, edge.to);
                answer += edge.cost;
            }
        }

        System.out.println(answer);
    }

    private static void union(int node1, int node2) {
        int parent1 = findParent(node1);
        int parent2 = findParent(node2);

        if (parent1 > parent2) {
            parent[parent2] = parent1;
        }else{
            parent[parent1] = parent2;
        }
    }

    private static int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }

    static class Edge {
        private final int from;
        private final int to;
        private final int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
