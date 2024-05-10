package org.example._13week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Router {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final StringTokenizer st = new StringTokenizer(br.readLine());
        final int houseCount = Integer.parseInt(st.nextToken());
        final int routerCount = Integer.parseInt(st.nextToken());

        int[] houses = new int[houseCount];

        for (int i = 0; i < houseCount; i++) {
            final int house = Integer.parseInt(br.readLine());
            houses[i] = house;
        }

        houses = Arrays.stream(houses).sorted().toArray();

        int left = 0;
        int right = 1_000_000_000; //10억 최대 좌표값

        while (left <= right) {
            if (left == right) {
                System.out.println(left);
                return;
            }

            int mid = (int) Math.ceil((double) (left + right) / 2);

            int count = 1;
            int prev = houses[0];
            for (int i = 1; i < houses.length; i++) {
                if (count > routerCount) {
                    break;
                }

                if (houses[i] >= prev + mid) {
                    count++;
                    prev = houses[i];
                }
            }

            if (count < routerCount) {
                right = mid - 1;
            } else if (count >= routerCount) {
                left = mid;
            }
        }
    }
}
