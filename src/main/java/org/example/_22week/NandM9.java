package org.example._22week;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class NandM9 {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Map<Integer, Integer> countMap = new HashMap<>();
    private static int N;
    private static int M;

    private static Set<Answers> answersSet = new HashSet<>();

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            values[i] = sc.nextInt();
            final Integer count = countMap.getOrDefault(values[i], 0);
            countMap.put(values[i], count + 1);
        }

        final ArrayList<Integer> answers = new ArrayList<>();
        dfs(values, countMap, 0, answers);
        final PriorityQueue<Answers> pq = new PriorityQueue<>(answersSet);
        while (!pq.isEmpty()) {
            final Answers poll = pq.poll();
            poll.print();
        }
    }

    private static void dfs(final int[] values, final Map<Integer, Integer> countMap, final int depth, final ArrayList<Integer> answers) {
        if (M == depth) {
            final Answers answer = new Answers(answers);
            answersSet.add(answer);
        }

        for (int i = 0; i < N; i++) {
            final Integer count = countMap.get(values[i]);
            if (count > 0) {
                final ArrayList<Integer> newAnswers = new ArrayList<>(answers);
                newAnswers.add(values[i]);

                final HashMap<Integer, Integer> newCountMap = new HashMap<>(countMap);
                newCountMap.put(values[i], count - 1);

                dfs(values, newCountMap, depth + 1, newAnswers);
            }
        }
    }

    private static class Answers implements Comparable<Answers>{
        List<Integer> answers;

        public Answers(final List<Integer> answers) {
            this.answers = answers;
        }

        public void print() {
            for (int i = 0; i < answers.size(); i++) {
                System.out.print(answers.get(i) + " ");
            }
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Answers answers1 = (Answers) o;
            return Objects.equals(answers, answers1.answers);
        }

        @Override
        public int hashCode() {
            return Objects.hash(answers);
        }

        @Override
        public int compareTo(final Answers that) {
            final int size = answers.size();
            for (int i = 0; i < size; i++) {
                final Integer thisValue = this.answers.get(i);
                final Integer thatValue = that.answers.get(i);

                final int compare = Integer.compare(thisValue, thatValue);
                if (compare == 0) {
                    continue;
                }

                return compare;
            }

            return 0;
        }
    }
}
