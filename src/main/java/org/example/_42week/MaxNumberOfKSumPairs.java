package org.example._42week;

import java.util.Arrays;

public class MaxNumberOfKSumPairs {

    public static void main(String[] args) {
        int[] nums = {3,1,3,4,3};
        int i = maxOperations(nums, 6);
        System.out.println(i);
    }

    public static int maxOperations(int[] nums, int k) {
        int answer = 0;

        if (nums.length == 1) {
            return answer;
        }

        Arrays.sort(nums);
        int sp = 0;
        int ep = nums.length - 1;
        boolean[] visited = new boolean[nums.length];
        while (sp < ep) {
            if (nums[sp] + nums[ep] == k) {
                answer++;
                visited[sp] = true;
                visited[ep] = true;
                sp = moveSp(sp, visited);
                ep = moveEp(ep, visited);
                if (sp == -1 || ep == -1) {
                    break;
                }
            } else if (nums[sp] + nums[ep] < k) {
                sp =moveSp(sp, visited);
            } else {
                ep =moveEp(ep, visited);
            }
        }

        return answer;
    }

    public static int moveSp(int sp, boolean[] visited) {
        while (visited[++sp]) {
            if (visited.length - 1 <= sp) {
                return -1;
            }
        }

        return sp;
    }

    public static int moveEp(int ep, boolean[] visited) {
        while (visited[--ep]) {
            if (ep <= 0) {
                return -1;
            }
        }

        return ep;
    }
}
