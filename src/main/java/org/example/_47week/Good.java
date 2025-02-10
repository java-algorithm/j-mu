package org.example._47week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Good {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[] nums;
    private static int N;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        input();

        Arrays.sort(nums);

        for (int i = 0; i < N; i++) {
            boolean isPossible = isPossible(nums[i], i);
            if (isPossible) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static boolean isPossible(int targetSum, int exceptIdx) {
        int s = 0, e = nums.length - 1;

        while (s < e) {
            if (s == exceptIdx) {
                s++;
                continue;
            }
            if (e == exceptIdx) {
                e--;
                continue;
            }

            int sum = nums[s] + nums[e];

            if (targetSum > sum) {
                s++;

            } else if (targetSum < sum) {
                e--;
            } else {// (targetSum == sum)
                return true;
            }
        }

        return false;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
