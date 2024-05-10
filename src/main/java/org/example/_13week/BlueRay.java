package org.example._13week;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BlueRay {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        final int lessonCount = Integer.parseInt(st.nextToken());
        final int blueRayCount = Integer.parseInt(st.nextToken());

        final int[] lessons = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long left = 0;
        long right = 10_000*100_000;

        while (left <= right) {
            if (left == right) {
                System.out.println(left);
                break;
            }
            long mid = (left + right) / 2;

            int sum = 0;
            int boxCount = 0;
            for (int i = 0; i < lessonCount; i++) {
                if (boxCount > blueRayCount) {
                    break;
                }

                if (sum + lessons[i] <= mid) {
                    sum += lessons[i];
                    if (i == lessonCount - 1) {
                        boxCount += 1;
                    }
                } else {
                    i--;
                    sum = 0;
                    boxCount += 1;
                }
            }

            if (boxCount > blueRayCount) {
                left = mid + 1;
            } else if (boxCount <= blueRayCount) {
                right = mid;
            }
        }
    }
}
