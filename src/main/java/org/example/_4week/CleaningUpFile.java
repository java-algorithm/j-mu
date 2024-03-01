package org.example._4week;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CleaningUpFile {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        // key: extension, value : count
        Map<String, Integer> extensionCountMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String file = scanner.nextLine();
            String extension = file.split("\\.")[1];

            extensionCountMap.compute(extension, (key, value) -> value == null ? 1 : value + 1);
        }

        List<String> extensions = new ArrayList<>(extensionCountMap.keySet())
            .stream()
            .sorted()
            .collect(Collectors.toList());

        for (String extension : extensions) {
            System.out.println(extension + " " + extensionCountMap.get(extension));
        }
    }
}
