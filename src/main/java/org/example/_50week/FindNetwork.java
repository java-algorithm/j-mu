package org.example._50week;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FindNetwork {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Map<String, Integer> groupCntMap;
    private static Map<String, String> group;

    public static void main(String[] args) throws IOException {
        int testCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCnt; i++) {
            calculate();
        }
        bw.flush();
    }

    private static void calculate() throws IOException {
        groupCntMap = new HashMap<>();
        group = new HashMap<>();

        int relationCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < relationCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String friend1 = st.nextToken();
            String friend2 = st.nextToken();

            group.putIfAbsent(friend1, friend1);
            group.putIfAbsent(friend2, friend2);

            String parent = union(friend1, friend2);

            bw.write(groupCntMap.get(parent) + "\n");
        }
    }

    private static String union(String friend1, String friend2) {
        String parent1 = find(friend1, group);
        String parent2 = find(friend2, group);

        if(parent1.equals(parent2)){
            return parent1;
        }

        if (parent1.compareTo(parent2) <= 0 ) {
            group.put(parent1, parent2);
            groupCntMap.put(parent2, groupCntMap.getOrDefault(parent1, 1) + groupCntMap.getOrDefault(parent2, 1));
            return parent2;
        } else {
            group.put(parent2, parent1);
            groupCntMap.put(parent1, groupCntMap.getOrDefault(parent1, 1) + groupCntMap.getOrDefault(parent2, 1));
            return parent1;
        }
    }

    private static String find(String person, Map<String, String> group) {
        if (group.get(person).equals(person)) {
            return person;
        }

        String parent = find(group.get(person), group);
        group.put(person, parent);

        return parent;
    }
}
