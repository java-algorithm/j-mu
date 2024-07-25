package org.example._25week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Telephone {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final int testCaseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseCount; i++) {
            final int teleCount = Integer.parseInt(br.readLine());

            solution(teleCount);

        }
    }

    private static void solution(int teleCount) throws IOException {
        boolean yes = true;
        String[] telephones = new String[teleCount];
        for (int j = 0; j < teleCount; j++) {
            telephones[j] = br.readLine();
        }

        Arrays.sort(telephones);
        for (int i = 0; i < teleCount - 1; i++) {
            if (telephones[i + 1].startsWith(telephones[i])) {
                yes = false;
                break;
            }
        }

        System.out.println(yes ? "YES" : "NO");
    }
}
