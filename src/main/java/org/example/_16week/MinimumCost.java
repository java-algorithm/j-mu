package org.example._16week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinimumCost {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int INF = 2_000_000_000;

    public static void main(String[] args) throws IOException {
        final int vertexesCount = Integer.parseInt(br.readLine());
        final int edgesCount = Integer.parseInt(br.readLine());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < vertexesCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgesCount; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final int from = Integer.parseInt(st.nextToken());
            final int to = Integer.parseInt(st.nextToken());
            final int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, cost));
        }

        final StringTokenizer st = new StringTokenizer(br.readLine());
        final int startingPoint = Integer.parseInt(st.nextToken());
        final int endPoint = Integer.parseInt(st.nextToken());

        int[] distances = new int[vertexesCount + 1];
        Arrays.fill(distances, INF);
        distances[startingPoint] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startingPoint, 0));

        while (!pq.isEmpty()) {
            final Node poll = pq.poll();
            final int now = poll.vertex;

            if (distances[now] < poll.cost) {
                continue;
            }

            final List<Node> adjacents = graph.get(now);
            for (final Node adjacent : adjacents) {
                final int cost = adjacent.cost + distances[now];

                if (cost < distances[adjacent.vertex]) {
                    distances[adjacent.vertex] = cost;
                    pq.offer(new Node(adjacent.vertex, cost));
                }
            }
        }

        System.out.println(distances[endPoint]);
    }

    private static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        public Node(final int vertex, final int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(final Node o) {
            return Integer.compare(o.cost, this.cost);
        }
    }
}
