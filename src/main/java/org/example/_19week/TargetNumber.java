package org.example._19week;

import java.util.Stack;

public class TargetNumber {

    public static void main(String[] args) {
//        int[] numbers = {1, 1, 1, 1, 1};
        int[] numbers = {4, 1, 2, 1};
        int target = 4;

        final int solution = solution(numbers, target);
        System.out.println(solution);
    }

    private static int solution(final int[] numbers, final int target) {
        return dfs(numbers, target);
    }

    public static int dfs(int[] numbers, int target) {
        int count = 0;
        Stack<Value> stack = new Stack<>();
        stack.push(new Value(0, 0));

        while (!stack.isEmpty()) {
            final Value pop = stack.pop();

            final int step = pop.step;
            if (step == numbers.length) {
                if (pop.value == target) {
                    count++;
                }
                continue;
            }

            final int value1 = pop.value + numbers[step];
            final int value2 = pop.value - numbers[step];

            stack.push(new Value(value1, step + 1));
            stack.push(new Value(value2, step + 1));
        }

        return count;
    }

    private static class Value {
        int value;
        int step;

        public Value(final int value, final int step) {
            this.value = value;
            this.step = step;
        }
    }
}
