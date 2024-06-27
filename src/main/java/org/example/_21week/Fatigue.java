package org.example._21week;

import java.util.*;

public class Fatigue {

    private static final int LIMIT = 0;
    private static final int CONSUMPTION = 1;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int k = 80;
//        int[][] dungeons = {{78, 18}, {70, 11}, {67, 9}, {60, 8}, {59, 2}, {57, 54}};
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        final int solution = solution(k, dungeons);

        System.out.println("답");
        System.out.println(solution);
    }

    public static int solution(int k, int[][] dungeons) {
        bfs(k, dungeons);
        return answer; //3
    }

    // BFS -> 근처에 있는거?
    // DFS -> 순서가에 따라서 답이 달라짐.
    private static void bfs(final int initialHealth, final int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        Queue<Status> queue = new LinkedList<>();
        queue.offer(new Status(initialHealth, 0, visited));

        while (!queue.isEmpty()) {
            final Status current = queue.poll();

            answer = Math.max(current.count, answer);

            for (int i = 0; i < dungeons.length; i++) {
                if (current.visited[i]) {
                    continue;
                }

                if (current.health < dungeons[i][LIMIT]) {
                    continue;
                }

                final boolean[] newVisited = Arrays.copyOf(current.visited, current.visited.length);
                newVisited[i] = true;

                final Status next = new Status(current.health - dungeons[i][CONSUMPTION], current.count + 1, newVisited);
                queue.offer(next);
            }
        }
    }

    // 초기 생명력: 80
    // 1 2 3 4 5 6 7 8 9 10
    // 80
    // [[80,20],[50,40],[30,10]]
    // 1 -> 4 -> 2
    // 1 -> 2 -> 4 X
    // QUEUE [1번만 간 경우, 2번만 간 경우, 3번 ]
    private static class Status {
        int health;
        int count;
        boolean[] visited;

        public Status(final int health, final int count, final boolean[] visited) {
            this.health = health;
            this.count = count;
            this.visited = visited;
        }
    }
}
