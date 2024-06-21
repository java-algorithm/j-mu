package org.example._11week;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StartLink {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        int total = Integer.parseInt(st.nextToken());
        int current = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int up = Integer.parseInt(st.nextToken());
        int down = Integer.parseInt(st.nextToken());

        int[] movable = {up, -down};
        int[] visited = new int[total + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(current);
        visited[current] = 0;

        while (!queue.isEmpty()) {
            Integer floor = queue.poll();
            int step = visited[floor];

            if (floor == target) {
                System.out.println(step);
                return;
            }

            for (int i = 0; i < movable.length; i++) {
                int nextFloor = floor + movable[i];
                if (nextFloor <= 0 || nextFloor > total) {
                    continue;
                }

                if (visited[nextFloor] == 0) {
                    visited[nextFloor] = step + 1;
                    queue.offer(nextFloor);
                }
            }
        }

        System.out.println("use the stairs");
    }
}
