package org.example._36week;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingGems {
    private static int answer = Integer.MAX_VALUE;

    private static final Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        String[] gems = new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        solution(gems);
    }

    public static int[] solution(String[] gems) {
        int s = 0;
        int e = 0;

        Set<String> set = Arrays.stream(gems).collect(Collectors.toSet());
        int targetGemSize = set.size();

        int start = 0;
        int end = -1;

        while (true) {
            end++;
            if (end == gems.length) {
                break;
            }

            Integer count = map.getOrDefault(gems[end], 0);
            map.put(gems[end], count + 1);

            while (map.get(gems[start]) > 1) {
                map.put(gems[start], map.get(gems[start]) - 1);
                start++;
            }

            if (map.size() == targetGemSize && answer > end - start) {
                answer = end - start;
                s = start + 1;
                e = end + 1;
            }
        }

        return new int[]{s, e};
    }
}
