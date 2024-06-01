package org.example._16week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NStepLogic {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int ALPHABET_SIZE = 26;
    private static final int ARRAY_LENGTH = ALPHABET_SIZE + 1;

    public static void main(String[] args) throws IOException {
        final int logicsCount = Integer.parseInt(br.readLine());
        boolean[][] relations = new boolean[ALPHABET_SIZE][ALPHABET_SIZE];
        for (int i = 0; i < logicsCount; i++) {
            final String input = br.readLine();
            final char from = input.charAt(0);
            final char to = input.charAt(input.length() - 1);

            relations[from - 97][to - 97] = true;
        }

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            for (int j = 0; j < ALPHABET_SIZE; j++) {
                for (int k = 0; k < ALPHABET_SIZE; k++) {
                    if (relations[j][k]) {
                        continue;
                    }

                    if (j == k) {
                        relations[j][k] = true;
                        continue;
                    }

                    if (!relations[j][i] || !relations[i][k]) {
                        continue;
                    }

                    relations[j][k] = true;
                }
            }
        }

        final int questionsCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < questionsCount; i++) {
            final String input = br.readLine();
            final char from = input.charAt(0);
            final char to = input.charAt(input.length() - 1);

            System.out.println(relations[from-97][to-97] ? "T" : "F");
        }
    }
}
