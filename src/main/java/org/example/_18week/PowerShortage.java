package org.example._18week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PowerShortage {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int housesCount = Integer.parseInt(st.nextToken());
            int edgesCount = Integer.parseInt(st.nextToken());

            if (housesCount == 0 && edgesCount == 0) {
                return;
            }

            parents = new int[housesCount + 1];
            Arrays.setAll(parents, i -> i);

            int beforeCost = 0;
            int afterCost = 0;

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for (int i = 0; i < edgesCount; i++) {
                st = new StringTokenizer(br.readLine());
                int house1 = Integer.parseInt(st.nextToken());
                int house2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                Edge edge = new Edge(house1, house2, cost);
                pq.offer(edge);
                beforeCost += cost;
            }

            while (!pq.isEmpty()) {
                Edge smallest = pq.poll();
                if (findParent(smallest.from) != findParent(smallest.to)) {
                    union(smallest.from, smallest.to);
                    afterCost+= smallest.cost;
                }
            }

            System.out.println(beforeCost - afterCost);
        }
    }

    private static void union(int house1, int house2) {
        int parent1 = findParent(house1);
        int parent2 = findParent(house2);

        if (parent1 < parent2) {
            parents[parent2] = parent1;
        }else{
            parents[parent1] = parent2;
        }
    }

    private static int findParent(int house) {
        if (parents[house] != house) {
            parents[house] = findParent(parents[house]);
        }

        return parents[house];
    }

    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
