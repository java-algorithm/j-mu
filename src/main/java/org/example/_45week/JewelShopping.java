package org.example._45week;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JewelShopping {

    public static void main(String[] args) {
//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        String[] gems = {"AA", "AB", "AC", "AA", "AC"};
        int[] answers = solution(gems);
        for (int answer : answers) {
            System.out.print(answer + ", ");
        }
    }

    public static int[] solution(String[] gems) {
        Set<String> gemCategories = Arrays.stream(gems).collect(Collectors.toSet());
        Map<String, Integer> countMap = new HashMap<>();

        int start = 0;
        int end = 0;

        int answerStart = 0;
        int answerEnd = Integer.MAX_VALUE;

        for (end = 0; end < gems.length; end++) {
            String currentGem = gems[end];
            countMap.put(currentGem, countMap.getOrDefault(currentGem, 0) + 1);

//            if (gems[start].equals(currentGem)) {
//                start++;
//                countMap.put(currentGem, countMap.get(currentGem) - 1);
//            }

            // 종류가 한개씩 들어갈 때 까지
            if (countMap.size() == gemCategories.size()) {
                // 1. start 가능한 만큼 땡기기.
                start = updateStart(start, gems, countMap);
                // 2. 최고 기록과 비교
                // 3. 갱신.
                if ((answerEnd - answerStart) > (end - start)) {
                    answerEnd = end;
                    answerStart = start;
                }
            }
        }


        return new int[]{answerStart + 1, answerEnd + 1};
    }

    private static int updateStart(int start, String[] gems, Map<String, Integer> countMap) {
        int newStart = 0;
        for (newStart = start; ; newStart++) {
            Integer count = countMap.get(gems[newStart]);

            if (count > 1) {
                countMap.put(gems[newStart], count - 1);
            } else {
                break;
            }
        }

        return newStart;
    }
}
