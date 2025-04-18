package org.example._54week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class MakeGreatestNumber {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<String> nums = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums.add(st.nextToken());
        }

        nums.sort((e1, e2) -> {
            int length = Math.min(e1.length(), e2.length());
            for (int i = 0; i < length; i++) {
                int compare = Integer.compare(e2.charAt(i), e1.charAt(i));
                if (compare == 0) {
                    continue;
                }

                return compare;
            }

            if (e1.length() < e2.length()) {
//                char pivot = e1.charAt(length - 1);
//                for (int i = length; i < e2.length(); i++) {
//                    int compare = Integer.compare(e2.charAt(i), pivot);
//                    if (compare == 0) {
//                        continue;
//                    }
//
//                    return compare;
//                }
                int compare = Integer.compare(e2.charAt(length), e1.charAt(length - 1));
                if (compare == 0) {
                    return Integer.compare(e1.length(), e2.length()); // 10 vs 100;
                } else {
                    return compare;
                }
            } else if (e1.length() > e2.length()) {
//                char pivot = e2.charAt(length - 1);
                int compare = Integer.compare(e2.charAt(length - 1), e1.charAt(length));
                if (compare == 0) {
                    return Integer.compare(e1.length(), e2.length()); // 10 vs 100;
                } else {
                    return compare;
                }
//                for (int i = length; i < e1.length(); i++) {
//                    int compare = Integer.compare(pivot, e1.charAt(i));
//                    if (compare == 0) {
//                        continue;
//                    }
//
//                    return compare;
//                }

//                return Integer.compare(e1.length(),e2.length()); // 10 vs 100;
            }

            return 0;
        });

        for (String num : nums) {
            System.out.print(num+" ");
        }

//        for (String num : nums) {
//            answer.append(num);
//        }

//        if (answer.charAt(0) == '0') {
//            System.out.println(0);
//            return;
//        }

//        System.out.println(answer);
    }
}
