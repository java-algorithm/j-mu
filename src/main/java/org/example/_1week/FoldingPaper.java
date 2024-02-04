package org.example._1week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FoldingPaper {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] inputs;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            inputs = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

            boolean isPossible = isPossible();
            System.out.println(isPossible ? "YES" : "NO");
        }
    }

    private static boolean isPossible() {
        int length = inputs.length;
        if (length % 2 == 0) {
            return false;
        }

        return possible(0, length - 1);
    }

    private static boolean possible(int start, int end) {
        if (start == end) {
            return true;
        }

        int middle = (start + end) / 2;
        for (int i = start; i < middle; i++) {
            if (inputs[i] == inputs[end - i]) {
                return false;
            }
        }

        return possible(start, middle - 1) && possible(middle + 1, end);
    }
}
