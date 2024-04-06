package org.example._8week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Virus {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        int nodeCount = Integer.parseInt(br.readLine())+1;
        int edgeCount = Integer.parseInt(br.readLine());
        parent = new int[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edgeCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            union(from, to);
        }
        int root = findRoot(1);
        long count = Arrays.stream(parent).filter(node -> findRoot(node) == root).count();

        // 1번 컴퓨터는 포함 안하므로.
        System.out.println(count-1);
    }

    // todo 이거 뭔가 이상한데..? 확인필요.
    public static int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }

    public static int findRoot(int node) {
        if (node == parent[node]) {
            return node;
        }

        return findRoot(parent[node]);
    }
    public static void union(int node1, int node2) {
        if (node1 == node2) {
            return;
        }

        int parent1 = findParent(node1);
        int parent2 = findParent(node2);

        if (parent1 != parent2) {
            parent[parent2]=parent1;
        }
    }
}
