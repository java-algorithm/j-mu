package org.example._50week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Airport {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] gates;

    public static void main(String[] args) throws IOException {
        int gateCnt = Integer.parseInt(br.readLine());
        int airplaneCnt = Integer.parseInt(br.readLine());
        int answer = 0;

        gates = new int[gateCnt + 1];

        // 1. gate 초기화
        for (int i = 0; i < gates.length; i++) {
            gates[i] = i;
        }
        gates[0] = -1;

        for (int i = 0; i < airplaneCnt; i++) {
            int gi = Integer.parseInt(br.readLine());

            int parent = find(gi);
            if (parent == -1) {
                break;
            }

            gates[parent] = find(parent - 1);
            answer++;
        }

        System.out.println(answer);
    }

    private static int find(int gi) {
        if (gi == -1) {
            return -1;
        }

        if (gi == gates[gi]) {
            return gi;
        }

        return gates[gi] = find(gates[gi]);
    }
}
