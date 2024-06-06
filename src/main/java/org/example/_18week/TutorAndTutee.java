package org.example._18week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TutorAndTutee {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int MOD = 1_000_000_007;
    private static int[] parents;
    private static long answer = 1;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int studentsCount = Integer.parseInt(st.nextToken());
        final int relationsCount = Integer.parseInt(st.nextToken());

        // parent 배열 초기화.
        parents = new int[studentsCount + 1];
        Arrays.setAll(parents, i -> i);

        // parent배열 만들기.
        for (int i = 0; i < relationsCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int student1 = Integer.parseInt(st.nextToken());
            final int student2 = Integer.parseInt(st.nextToken());

            union(student1, student2);
        }

        Map<Integer, Integer> graphSizeMap = new HashMap<>();
        for (int i = 1; i <= studentsCount; i++) {
            final int parent = findParent(i);
            graphSizeMap.compute(parent, (key, value) -> value == null ? 1 : value + 1);
        }

        final Set<Integer> roots = graphSizeMap.keySet();
        for (int root : roots) {
            final Integer size = graphSizeMap.get(root);
            answer = (answer * size) % MOD;
        }

        System.out.println(answer);
    }

    public static void union(int student1, int student2) {
        final int parent1 = findParent(student1);
        final int parent2 = findParent(student2);

        if (parent1 < parent2) {
            parents[parent2] = parent1;
        } else {
            parents[parent1] = parent2;
        }
    }

    public static int findParent(int student) {
        if (parents[student] != student) {
            parents[student] = findParent(parents[student]);
        }

        return parents[student];
    }
}
