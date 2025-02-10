package org.example._47week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MoneyManagement {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int M;
    private static int[] moneys;

    public static void main(String[] args) throws IOException {
        input();

        int s = 0;
        int e = Integer.MAX_VALUE;
        // 실제로는 1만(금액) * 10만(일수) = 10억이지만 0 개수 헷갈리니 걍 MAX_VALUE

        while (s < e) {
            int mid = s + (e - s) / 2;

            if (isEnough(mid)) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }

        System.out.println(s);
    }

    private static boolean isEnough(int money) {
        int cnt = 0;

        int currentMoney = 0;
        for (int i = 0; i < N; i++) {
            if (cnt > M) {
                break;
            }

            if (currentMoney < moneys[i]) {
                currentMoney = money;
                cnt++;
                i--;
                continue;
            }

            currentMoney -= moneys[i];
        }

        return cnt <= M;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        moneys = new int[N];
        for (int i = 0; i < N; i++) {
            moneys[i] = Integer.parseInt(br.readLine());
        }
    }
}
