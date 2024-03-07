package org.example._5week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ManagementMoney {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int days = Integer.parseInt(st.nextToken());
        int withdrawsCount = Integer.parseInt(st.nextToken());

        int max = 0;
        int[] needMoneys = new int[days];
        for (int i = 0; i < days; i++) {
            needMoneys[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, needMoneys[i]);
        }

        int minBoundary = max;
        int maxBoundary = Arrays.stream(needMoneys).sum();
        int result = 0;

        while (minBoundary <= maxBoundary) {
            int mid = (minBoundary + maxBoundary) / 2;

            if (withdrawsCount >= calculateWithdraw(mid, days, needMoneys)) {
                result = mid;
                maxBoundary = mid - 1;
            } else {
                minBoundary = mid + 1;
            }
        }

        System.out.println(result);
    }

    private static int calculateWithdraw(int withdrawMoney, int days, int[] needMoneys) {
        int cash = withdrawMoney;
        int withdrawCount = 1;

        for (int i = 0; i < days; i++) {
            if (withdrawMoney < needMoneys[i]) {
                cash = withdrawMoney;
                withdrawCount++;
            }

            cash -= needMoneys[i];
        }

        return withdrawCount;
    }


}
