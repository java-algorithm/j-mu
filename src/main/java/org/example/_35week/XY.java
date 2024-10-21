package org.example._35week;
import java.util.*;

class XY {

    static class Student implements Comparable<Student> {
        int mathScore;
        int englishScore;

        public Student(int mathScore, int englishScore) {
            this.mathScore = mathScore;
            this.englishScore = englishScore;
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(o.mathScore, this.mathScore);
        }
    }

    public int solution(
            int N,
            int[][] scores
    ) {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            students.add(new Student(scores[i][0], scores[i][1]));
        }

        Collections.sort(students);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int maxSum = 0;

        for (int i = 0; i < N; i++) {
            minHeap.add(students.get(i).englishScore);
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
            if (minHeap.size() == 3) {
                int minMath = students.get(i).mathScore;
                int minEnglish = minHeap.peek();
                maxSum = Math.max(maxSum, minMath + minEnglish);
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        XY solution = new XY();
        int[][] scores = {
                {84, 70},
                {60, 55},
                {56, 86},
                {93, 90},
                {94, 58}
        };
    }
}
