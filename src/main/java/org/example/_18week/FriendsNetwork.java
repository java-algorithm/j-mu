package org.example._18week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FriendsNetwork {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String,String> parents;
    public static void main(String[] args) throws IOException {
        final int testCaseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseCount; i++) {
            final int relationsCount = Integer.parseInt(br.readLine());
            parents = new HashMap<>();

            for (int j = 0; j < relationsCount; j++) {
                final StringTokenizer st = new StringTokenizer(br.readLine());
                final String friend1 = st.nextToken();
                final String friend2 = st.nextToken();

                union(friend1, friend2);

                int count = 0;
                final String target = findParent(friend1);
                for (String friend : parents.keySet()) {
                    final String parent = parents.get(friend);
                    if (target.equals(parent)) {
                        count++;
                    }
                }

                System.out.println(count);
            }
        }
    }

    private static void union(final String friend1, final String friend2) {
        final String parent1 = findParent(friend1);
        final String parent2 = findParent(friend2);

        if (parent1.compareTo(parent2) < 0) {
            parents.put(parent2, parent1);
        }else{
            parents.put(parent1, parent2);
        }
    }

    private static String findParent(String friend) {
        final String parent = parents.get(friend);
        if (parent == null) {
            parents.put(friend, friend);
            return friend;
        }

        if (!parent.equals(friend)) {
            parents.put(parent, findParent(parent));
        }

        return parents.get(friend);
    }
}
