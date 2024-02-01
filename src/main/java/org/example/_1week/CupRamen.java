package org.example._1week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class CupRamen {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int problemCount = Integer.parseInt(br.readLine());
        Problem[] problems = new Problem[problemCount];

        for (int i = 0; i < problemCount; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            problems[i] = new Problem(inputs[0], inputs[1]);
        }

        Comparator<Problem> deadLineComparator = Comparator.comparing(Problem::getDeadLine);
        Comparator<Problem> ramenComparator = Comparator.comparing(Problem::getRamenCount);
        Arrays.sort(problems, deadLineComparator.reversed());

//        problems.forEach(System.out::println);
        PriorityQueue<Problem> priorityQueue = new PriorityQueue<>(ramenComparator.reversed());

        int totalCount = 0;
        int today = problems[0].getDeadLine();
        int index = 0;
        while (today >= 1 && problemCount > index) {
            Problem problem = problems[index];
            if (problem.getDeadLine() >= today) {
                priorityQueue.add(problem);
                index++;
            } else {
                Problem poll = priorityQueue.poll();
                if (poll == null) {
                    today = problems[index].getDeadLine();
                    continue;
                }
                totalCount += poll.getRamenCount();
                today--;
            }
        }

        for (int i = today; i > 0; i--) {
            Problem poll = priorityQueue.poll();
            if (poll == null) {
                break;
            }
            totalCount += poll.getRamenCount();
        }

        System.out.println(totalCount);
    }

    private static class Problem {

        private int deadLine;
        private int ramenCount;

        public Problem(int deadLine, int ramenCount) {
            this.deadLine = deadLine;
            this.ramenCount = ramenCount;
        }

        public int getDeadLine() {
            return deadLine;
        }

        public int getRamenCount() {
            return ramenCount;
        }

        @Override
        public String toString() {
            return "Problem{" +
                "deadLine=" + deadLine +
                ", ramenCount=" + ramenCount +
                '}';
        }
    }
}

