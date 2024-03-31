package org.example._8week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Travel {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        final int M = Integer.parseInt(br.readLine());

        if (N == 0) {
            System.out.println("YES");
            return;
        }

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        // 그래프 그리기.
        for (int i = 1; i < N + 1; i++) {
            final int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (row[j] == 1) {
                    union(i, j + 1);
                }
            }
        }

        final int[] travel = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int startingPoint = travel[0];
        if (travel.length == 1) {
            System.out.println("YES");
            return;
        }

        for (int i = 1; i < M; i++) {
            boolean hasRoad = isSameSet(startingPoint, travel[i]);
            if (!hasRoad) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
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

    private static int findParent(final int node1) {
        if (parent[node1] == node1) {
            return node1;
        }

        return parent[node1] = findParent(parent[node1]);
    }
}
