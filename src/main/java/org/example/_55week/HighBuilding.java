package org.example._55week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HighBuilding {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] nums;

    private static int answer = 0;
    private static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int pivot = 0; pivot < N; pivot++) {
            int count = 0;

            for (int target = 0; target < N; target++) {
                if (isVisible(pivot, target)) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
            map.put(count, pivot);
        }

        System.out.println(answer);
//        System.out.println(map.get(answer));
    }

    private static boolean isVisible(int pivot, int target) {
        if (pivot == target) {
            return false;
        }

        int left = Math.min(pivot,target);
        int right = Math.max(pivot, target);

        for (int i = left + 1; i < right; i++) {
            double height = ((double) (nums[right] - nums[left]) / (right - left)) * (i - right) + nums[right];
            if (nums[i] >= height) {
                return false;
            }
        }

        return true;
    }
}
