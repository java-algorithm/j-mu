package org.example._8week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SetExpression {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int UNION = 0;

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int nodeCount = Integer.parseInt(st.nextToken()) + 1;
        final int operandCount = Integer.parseInt(st.nextToken());

        // 집합 초기화
        parent = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < operandCount; i++) {
            st = new StringTokenizer(br.readLine());

            final int command = Integer.parseInt(st.nextToken());
            final int node1 = Integer.parseInt(st.nextToken());
            final int node2 = Integer.parseInt(st.nextToken());

            if (command == UNION) {
                union(node1, node2);
            } else {
                System.out.println(isSameSet(node1, node2) ? "YES" : "NO");
            }
        }
    }

    private static void union(final int node1, final int node2) {
        if (node1 == node2) {
            return;
        }

        final int parent1 = findParent(node1);
        final int parent2 = findParent(node2);

        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }

    private static boolean isSameSet(final int node1, final int node2) {
        return findParent(node1) == findParent(node2);
    }

    private static int findParent(final int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }
}
