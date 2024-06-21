package org.example._18week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Manito {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int EXIT_CODE = 0;

    public static Map<String, String> parents;

    public static void main(String[] args) throws IOException {

        for (int step = 1; ; step++) {
            final int studentsCount = Integer.parseInt(br.readLine());
            if (studentsCount == EXIT_CODE) {
                return;
            }

            parents = new HashMap<>();

            for (int i = 0; i < studentsCount; i++) {
                final StringTokenizer st = new StringTokenizer(br.readLine());
                final String student1 = st.nextToken();
                final String student2 = st.nextToken();

                union(student1, student2);
            }

            Set<String> set = new HashSet<>();

            for (String key : parents.keySet()) {
                final String parent = findParent(key);
                set.add(parent);
            }

            System.out.println(step+" "+set.size());
        }
    }

    public static void union(String student1, String student2) {
        String parent1 = findParent(student1);
        String parent2 = findParent(student2);

        if (parent1.compareTo(parent2) < 0) {
            parents.put(parent2, parent1);
        } else {
            parents.put(parent1, parent2);
        }
    }

    public static String findParent(String student) {
        String parent = parents.get(student);
        if (parent == null) {
            parents.put(student, student);
            return student;
        }

        if (!parent.equals(student)) {
            parents.put(parent, findParent(parent));
        }
        return parents.get(student);
    }
}
