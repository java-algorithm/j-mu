package org.example._47week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Good2 {
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
        for (int pivot = 0; pivot < N; pivot++) {
            if (pivot == exceptIdx) {
                continue;
            }

            int foundIdx = Arrays.binarySearch(nums, targetSum - nums[pivot]);
            if (foundIdx > 0 && foundIdx != pivot && foundIdx != exceptIdx) {
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
