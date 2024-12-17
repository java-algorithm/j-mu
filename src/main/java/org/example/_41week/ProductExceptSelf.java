package org.example._41week;

public class ProductExceptSelf {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        int[] answer = solution(nums);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    public static int[] solution(int[] nums) {
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                prefix[i] = nums[i];
                continue;
            }

            prefix[i] = nums[i] * prefix[i - 1];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                suffix[i] = nums[i];
                continue;
            }

            suffix[i] = nums[i] * suffix[i + 1];
        }

        int[] answers = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                answers[i] = suffix[i + 1];
            } else if (i == nums.length - 1) {
                answers[i] = prefix[i - 1];
            } else {
                answers[i] = prefix[i - 1] * suffix[i + 1];
            }
        }

        return answers;
    }
}
