package org.example._54week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MakeGreatestNumber2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<String> nums = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String e = st.nextToken();
            nums.add(e);
        }

        nums.sort((e1, e2) -> (e2 + e1).compareTo(e1 + e2));

        for (String num : nums) {
            answer.append(num);
        }

        if (answer.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        System.out.println(answer);
    }
}
