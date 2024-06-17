package org.example._18week;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FriendsNetwork {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Map<String,String> parents;
    private static Map<String,Integer> counts;

    public static void main(String[] args) throws IOException {
        final int testCaseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseCount; i++) {
            final int relationsCount = Integer.parseInt(br.readLine());
            parents = new HashMap<>();
            counts = new HashMap<>();

            for (int j = 0; j < relationsCount; j++) {
                final StringTokenizer st = new StringTokenizer(br.readLine());
                final String friend1 = st.nextToken();
                final String friend2 = st.nextToken();

                union(friend1, friend2);

                final String parent = findParent(friend1);
                Integer count = counts.get(parent);
                bw.write(count);
            }
        }

        bw.flush();
    }

    private static void union(final String friend1, final String friend2) {
        final String parent1 = findParent(friend1);
        final String parent2 = findParent(friend2);
        final Integer count1 = counts.getOrDefault(parent1, 1);
        final Integer count2 = counts.getOrDefault(parent2, 1);

        if (!parent1.equals(parent2)) {
            parents.put(parent2, parent1);
            counts.put(parent1, count1 + count2);
        }

//        if (parent1.compareTo(parent2) < 0) {
//            parents.put(parent2, parent1);
//            counts.put(parent1, count1 + count2);
//        }else{
//            parents.put(parent1, parent2);
//            counts.put(parent2, count1 + count2);
//        }
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
