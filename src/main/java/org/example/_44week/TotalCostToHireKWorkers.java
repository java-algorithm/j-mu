package org.example._44week;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TotalCostToHireKWorkers {

    private static final boolean FRONT = false;
    private static final boolean BACK = true;

    public static void main(String[] args) {
//        int[] costs = {17, 12, 10, 2, 7, 2, 11, 20, 8};
        int[] costs = {2, 2, 2, 2, 2, 2, 1, 4, 5, 5, 5, 5, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        int k = 3;
        int candidates = 4;

        long ans = totalCost(costs, 7, 3);
        System.out.println(ans);
    }

    public static long totalCost(int[] costs, int k, int candidates) {
        // costs의 front에서 candidates만큼, backend에서 cnadidates만큼 가져온다.
        long answer = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int currentSize = costs.length;
        int front = candidates;
        int backend = costs.length - candidates - 1;

        if (currentSize <= candidates * 2) {
            for (int i = 0; i < costs.length; i++) {
                pq.add(new Node(costs[i], i, FRONT));
            }
        } else {
            for (int i = 0; i < front; i++) {
                pq.add(new Node(costs[i], i, FRONT));
                pq.add(new Node(costs[costs.length - i - 1], costs.length - i - 1, BACK));
            }
        }

        for (int round = 0; round < k; round++) {
            Node poll = pq.poll();
            answer += poll.cost;

            if (front <= backend) {
                if (poll.side == FRONT) {
                    pq.add(new Node(costs[front], front, FRONT));
                    front++;
                } else { //BACK
                    pq.add(new Node(costs[backend], backend, BACK));
                    backend--;
                }
            }
        }

        return answer;
    }

    private static class Node implements Comparable<Node> {
        int cost;
        int index;
        boolean side;

        public Node(int cost, int index, boolean side) {
            this.cost = cost;
            this.index = index;
            this.side = side;
        }


        @Override
        public int compareTo(Node o) {
            int costCompare = Integer.compare(this.cost, o.cost);
            if (costCompare != 0) {
                return costCompare;
            }

            return Integer.compare(this.index, o.index);
        }
    }
}
