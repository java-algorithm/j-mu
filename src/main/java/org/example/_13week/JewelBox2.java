package org.example._13week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JewelBox2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int studentCount = Integer.parseInt(st.nextToken());
        final int jewelCount = Integer.parseInt(st.nextToken());

        int[] jewels = new int[jewelCount];

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < jewelCount; i++) {
            final int value = Integer.parseInt(br.readLine());
            if (maxValue < value) {
                maxValue = value;
            }

            jewels[i] = value;
        }

        int left = 1;
        int right = maxValue;

        while (left <= right) {
            if (left == right) {
                System.out.println(left);
                return;
            }

            int mid = (left + right) / 2;
            double needs = 0;
            for (int i = 0; i < jewelCount; i++) {
                if (needs > studentCount) {
                    break;
                }
                needs += Math.ceil(jewels[i] / (double) mid);
            }

            if (needs > studentCount) {
                left = mid + 1;
            } else if (needs <= studentCount) {
                // 보석이 부족함.

                right = mid;
            }
        }
    }
}
