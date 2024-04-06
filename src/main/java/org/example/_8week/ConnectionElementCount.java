package org.example._8week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ConnectionElementCount {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        parent = new int[nodeCount + 1];

        //init parent
        for (int i = 1; i < nodeCount + 1; i++) {
            parent[i] = i;
        }


        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            union(from, to);
        }

        Set<Integer> graph = new HashSet<>();
        for (int i = 1; i < nodeCount + 1; i++) {
            int parentNode = findParent(i);
            graph.add(parentNode);
        }

        System.out.println(graph.size());
    }

    private static void union(int node1, int node2) {
        int parent1 = findParent(node1);
        int parent2 = findParent(node2);

        if (parent1 != parent2) {
            parent[parent2] = parent1;
        }
    }

    private static int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }
}
