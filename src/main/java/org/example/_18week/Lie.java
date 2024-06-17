package org.example._18week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lie {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final Set<Integer> truthPeople = new HashSet();
    private static final Set<Integer> truthParents = new HashSet();
    private static int[] parents;
    private static List<List<Integer>> parties = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int peopleCount = Integer.parseInt(st.nextToken());
        final int partiesCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < partiesCount; i++) {
            parties.add(new ArrayList<>());
        }
        parents = new int[peopleCount + 1];
        Arrays.setAll(parents, i -> i);

        st = new StringTokenizer(br.readLine());
        final int truthPeopleCount = Integer.parseInt(st.nextToken());
        for (int i = 0; i < truthPeopleCount; i++) {
            final int truthPerson = Integer.parseInt(st.nextToken());
            truthPeople.add(truthPerson);
        }

        for (int i = 0; i < partiesCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int participantCount = Integer.parseInt(st.nextToken());
            final int firstOne = Integer.parseInt(st.nextToken());
            parties.get(i).add(firstOne);

            for (int j = 1; j < participantCount; j++) {
                final int participant = Integer.parseInt(st.nextToken());
                parties.get(i).add(participant);
                union(firstOne, participant);
            }
        }

        for (final Integer truthPerson : truthPeople) {
            final int parent = findParent(truthPerson);
            truthParents.add(parent);
        }

        int answer = 0;
        for (int i = 0; i < partiesCount; i++) {
            final List<Integer> participants = parties.get(i);
            boolean hasTruthPerson = false;

            for (final Integer participant : participants) {
                final int parent = findParent(participant);
                if (truthParents.contains(parent)) {
                    hasTruthPerson = true;
                    break;
                }
            }

            if (!hasTruthPerson) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static void union(final int participant1, final int participant2) {
        final int parent1 = findParent(participant1);
        final int parent2 = findParent(participant2);

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
