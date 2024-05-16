package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Guitar {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] visited;
    private static int[] volumes;

    private static int maxLimit;
    private static int songsCount;
    private static boolean hasCompleted = false;
    private static int maxVolume = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        final StringTokenizer st = new StringTokenizer(br.readLine());
        songsCount = Integer.parseInt(st.nextToken());
        final int startVolume = Integer.parseInt(st.nextToken());
        maxLimit = Integer.parseInt(st.nextToken());

        final String line = br.readLine();
        volumes = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

        visited = new int[maxLimit + 1];
        Arrays.fill(visited, -1);
        bfs(startVolume);

        System.out.println(hasCompleted ? maxVolume : -1);
    }

    public static void bfs(int startVolume) {
        Queue<Step> queue = new LinkedList<>();
        queue.add(new Step(startVolume, 0));
        visited[startVolume] = 0;

        while (!queue.isEmpty()) {
            final Step curStep = queue.poll();

            final int volume = curStep.volume;
            final int step = curStep.step;

            if (step == songsCount) {
                hasCompleted = true;
                if (maxVolume < volume) {
                    maxVolume = volume;
                }
                continue;
            }

            int[] multiply = {1, -1};
            for (int i = 0; i < 2; i++) {
                int nextVolume = volume + multiply[i] * volumes[step];
                if (nextVolume < 0 || nextVolume > maxLimit) {
                    continue;
                }

                if (visited[nextVolume] == step) {
                    continue;
                }

                queue.add(new Step(nextVolume, step + 1));
                visited[nextVolume] = step;
            }
        }


    }

    private static class Step {
        int volume;
        int step;

        public Step(final int volume, final int step) {
            this.volume = volume;
            this.step = step;
        }
    }
}
