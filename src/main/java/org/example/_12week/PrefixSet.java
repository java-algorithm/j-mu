package org.example._12week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PrefixSet {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        List<String> wordsSet = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            wordsSet.add(br.readLine());
        }

        final ArrayList<String> words = new ArrayList<>(wordsSet);
        words.sort(Comparator.comparingInt(String::length));
        final Set<String> answers = new HashSet<>();
        for (int i = 0; i < N; i++) {
            final String targetWord = words.get(i);
            if (answers.contains(targetWord)) {
                continue;
            }

            boolean hasPrefix = false;

            for (int j = i + 1; j < N; j++) {
                final String word = words.get(j);
                final boolean isPrefix = word.startsWith(targetWord);
                if (isPrefix) {
                    hasPrefix = true;
                    break;
                }
            }

            if (!hasPrefix) {
                answers.add(targetWord);
            }
        }

        System.out.println(answers.size());
    }
}
