package org.example._18week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DividingTown {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parents;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int housesCount = Integer.parseInt(st.nextToken());
        int edgesCount = Integer.parseInt(st.nextToken());

        parents = new int[housesCount + 1];
        Arrays.setAll(parents,i->i);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < edgesCount; i++) {
            st = new StringTokenizer(br.readLine());
            int house1 = Integer.parseInt(st.nextToken());
            int house2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(house1, house2, cost);
            pq.add(edge);
        }

        int curEdgesCount = 0;
        long answer = 0;
        while (!pq.isEmpty()) {
            if (curEdgesCount == housesCount - 2) {
                break;
            }

            Edge smallest = pq.poll();

            if (findParent(smallest.x) != findParent(smallest.y)) {
                union(smallest.x, smallest.y);
                answer+=smallest.cost;
                curEdgesCount++;
            }
        }

        System.out.println(answer);
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

    private static class Edge implements Comparable<Edge>{
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
