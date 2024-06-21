package org.example._20week;

import java.util.ArrayList;
import java.util.List;

public class HanoiTower {

    private static List<int[]> tempAnswers = new ArrayList<>();

    public static void main(String[] args) {
        solution(3);
    }

    public static int[][] solution(int n) {
        hanoiMove(1, 3, n);

        final int size = tempAnswers.size();
        int[][] answers = new int[size][];
        for (int i = 0; i < size; i++) {
            answers[i] = tempAnswers.get(i);
        }

        return answers;
    }

    private static void hanoiMove(final int from, final int to, final int n) {
        if (n == 1) {
            tempAnswers.add(new int[]{from, to});
            return;
        }

        final int other = getOther(from, to);
        hanoiMove(from, other, n - 1);
        hanoiMove(from, to, 1);
        hanoiMove(other, to, n - 1);
    }

    private static int getOther(int from, int to) {
        final int totalSum = 6;
        int sum = from + to;

        return totalSum - sum;
    }
}
