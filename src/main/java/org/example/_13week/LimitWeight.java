package org.example._13week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LimitWeight {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<List<Island>> graph = new ArrayList<>();
    private static int source;
    private static int destination;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int islandsCount = Integer.parseInt(st.nextToken());
        final int bridgesCount = Integer.parseInt(st.nextToken());

        visited = new boolean[islandsCount + 1];

        for (int i = 0; i < islandsCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < bridgesCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int node1 = Integer.parseInt(st.nextToken());
            final int node2 = Integer.parseInt(st.nextToken());
            final int weightLimit = Integer.parseInt(st.nextToken());

            graph.get(node1).add(new Island(node2, weightLimit));
            graph.get(node2).add(new Island(node1, weightLimit));
        }

        st = new StringTokenizer(br.readLine());
        source = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());

        int left = 1;
        int right = 1_000_000_000;
        int mid = 0;
        while (left < right) {
            mid = (int) Math.ceil((double) (left + right) / 2);

            final boolean canPassBridge = bfs(mid);

            if (canPassBridge) {
                left = mid;
            } else {
                right = mid - 1;
            }

            // 아 이놈때문에 한참 헤맸네..
            visited = new boolean[islandsCount + 1];
        }

        System.out.println(left);
    }

    private static boolean bfs(final int mid) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            final int adjacentsCount = graph.get(node).size();
            for (int i = 0; i < adjacentsCount; i++) {
                Island adjacent = graph.get(node).get(i);
                if (visited[adjacent.to]) {
                    continue;
                }

                if (adjacent.weight < mid) {
                    continue;
                }

                if (adjacent.to == destination) {
                    return true;
                }

                visited[adjacent.to] = true;
                queue.offer(adjacent.to);
            }
        }

        return false;
    }

    private static class Island {
        int to;
        int weight;

        public Island(final int to, final int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
