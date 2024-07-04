package org.example._22week;

import java.util.HashMap;
import java.util.Map;

public class ProgrammersTest2 {

    private static final int DISAGREE = 0;
    private static final int AGREE = 1;

    private static final int RT = 0;
    private static final int CF = 1;
    private static final int JM = 2;
    private static final int AN = 3;

    private static final int[] scores = {0, 3, 2, 1, 0, 1, 2, 3};
    private static final int[] characters = new int[4];

    private static final Map<String, Integer> characterSign = new HashMap<>();
    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};

//        String[] survey = {"TR", "RT", "TR"};
//        int[] choices = {7, 1, 3};
        final String answer = solution(survey, choices);
        System.out.println(answer);
    }

    public static String solution(String[] survey, int[] choices) {
        characterSign.put("R", -1);
        characterSign.put("T", 1);
        characterSign.put("C", -1);
        characterSign.put("F", 1);
        characterSign.put("J", -1);
        characterSign.put("M", 1);
        characterSign.put("A", -1);
        characterSign.put("N", 1);

        final int length = survey.length;
        for (int i = 0; i < length; i++) {
            final String[] split = survey[i].split("");

            final int index = convertToIndex(split[DISAGREE]);
            final int choice = choices[i];
            final Integer sign = choice >= 4 ? characterSign.get(split[AGREE]) : characterSign.get(split[DISAGREE]);

            characters[index] += scores[choice] * sign;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String character = getCharacter(i,characters[i]);
            sb.append(character);
        }

        return sb.toString();
    }
    // A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
    private static String getCharacter(final int i, final int score) {
        switch (i) {
            case RT:
                if (score == 0) {
                    return "R";
                } else if (score > 0) {
                    return "T";
                }else{
                    return "R";
                }
            case CF:
                if (score == 0) {
                    return "C";
                } else if (score > 0) {
                    return "F";
                }else{
                    return "C";
                }
            case JM:
                if (score == 0) {
                    return "J";
                } else if (score > 0) {
                    return "M";
                }else{
                    return "J";
                }
            case AN:
                if (score == 0) {
                    return "A";
                } else if (score > 0) {
                    return "N";
                }else{
                    return "A";
                }
        }

        return "";
    }

    public static int convertToIndex(String ch) {
        switch (ch) {
            case "R":
            case "T":
                return RT;
            case "C":
            case "F":
                return CF;
            case "J":
            case "M":
                return JM;
            case "A":
            case "N":
                return AN;
        }

        return -1;
    }
}
