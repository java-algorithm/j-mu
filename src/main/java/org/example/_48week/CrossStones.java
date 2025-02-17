package org.example._48week;

public class CrossStones {

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;

        System.out.println(solution(stones, k));
    }

    public static int solution(int[] stones, int k) {
        int s = 0;
        int e = 200_000_000;// 2억

        // O(NlogN)
        while (s < e) {
            int mid = s + (e - s) / 2;

            if (isPassable(stones, mid,k)) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }

        return s;
    }

    // O(N)
    private static boolean isPassable(int[] stones, int peopleCnt, int k) {

        int cnt = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] > peopleCnt) {
                cnt=0;
                continue;
            }

            cnt++;
            if (cnt == k) {
                return false;
            }
        }

        return true;
    }
}
