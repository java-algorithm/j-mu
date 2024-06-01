package org.example._16week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DeliveryParcel2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int INF = 100_000_000; // 1억 실제론 5000만 이하만 유효 범위

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int nodesCount = Integer.parseInt(st.nextToken());
        final int edgesCount = Integer.parseInt(st.nextToken());
        final int targetNode = nodesCount;

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < nodesCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgesCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int vertex1 = Integer.parseInt(st.nextToken());
            final int vertex2 = Integer.parseInt(st.nextToken());
            final int cost = Integer.parseInt(st.nextToken());

            graph.get(vertex1).add(new Node(vertex2, cost));
            graph.get(vertex2).add(new Node(vertex1, cost));
        }

        // 거리 배열
        int[] distances = new int[nodesCount + 1];
        Arrays.fill(distances, INF);

        // queue 만들기
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0));

        // 현재 노드 거리 초기화
        distances[1] = 0;

        while (!queue.isEmpty()) {
            final Node poll = queue.poll();
            final int nowVertex = poll.vertex;
            if (distances[nowVertex] < poll.cost) { // 이미 방문 되었던 노드라면
                continue;
            }

            final List<Node> adjacents = graph.get(nowVertex);
            for (final Node adjacent : adjacents) {
                final int cost = adjacent.cost + distances[nowVertex];

                if (cost < distances[adjacent.vertex]) {
                    distances[adjacent.vertex] = cost;
                    queue.offer(new Node(adjacent.vertex, cost));
                }
            }
        }

        System.out.println(distances[targetNode]);
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
            return Integer.compare(o.vertex, this.vertex);
        }
    }

}
