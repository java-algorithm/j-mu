package org.example._30week;

import java.util.Arrays;

public class LongestCommonPrefix {

    public static void main(String[] args) {
//        String[] strs = {"flower", "flow", "flight"};
        String[] strs = {"dog", "racecar", "car"};

        String prefix = longestCommonPrefix(strs);
        System.out.println(prefix);
    }

    public static String longestCommonPrefix(String[] strs) {
        int minLength = Arrays.stream(strs).mapToInt(String::length).min().getAsInt();

        StringBuilder prefix = new StringBuilder();
        for (int index = 0; index < minLength; index++) {
            char ch = strs[0].charAt(index);

            for (int i = 0; i < strs.length; i++) {
                if (strs[i].charAt(index) != ch) {
                    return prefix.toString();
                }
            }

            prefix.append(ch);
        }

        return prefix.toString();
    }
}
