package org.example._18week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Flow {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(br.readLine());

        parents = new int[count];
        Arrays.setAll(parents, i -> i);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < count; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = i + 1; j < count; j++) {
                Edge edge = new Edge(i, j, row[j]);
                pq.offer(edge);
            }
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            Edge smallest = pq.poll();
            if (findParent(smallest.x) != findParent(smallest.y)) {
                union(smallest.x, smallest.y);
                answer += smallest.cost;
            }
        }

        System.out.println(answer);
    }

    private static void union(int house1, int house2) {
        int parent1 = findParent(house1);
        int parent2 = findParent(house2);

        if (parent1 < parent2) {
            parents[parent2] = parent1;
        } else {
            parents[parent1] = parent2;
        }
    }

    private static int findParent(int house) {
        if (parents[house] != house) {
            parents[house] = findParent(parents[house]);
        }

        return parents[house];
    }

    private static class Edge  implements Comparable<Edge>{
        int x;
        int y;
        int cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
