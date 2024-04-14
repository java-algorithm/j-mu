package org.example._10week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Switch {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static LinkedList<Integer> switches;
    private static String MALE = "1";

    public static void main(String[] args) throws IOException {
        final int switchCount = Integer.parseInt(br.readLine());
        switches = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
        switches.addFirst(0); // index맞추기 위해 앞에 하나 추가.

        final int studentCount = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < studentCount; i++) {
            st = new StringTokenizer(br.readLine());
            final String gender = st.nextToken();
            final int cardNum = Integer.parseInt(st.nextToken());

            if (gender.equals(MALE)) {
                maleSwap(cardNum);
            } else {
                femaleSwap(cardNum);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < switches.size(); i++) {
            sb.append(switches.get(i));
            if (i % 20 == 0) {
                sb.append("\n");
            }else{
                sb.append(" ");
            }
        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb);
    }

    private static void femaleSwap(final int cardNum) {
        switches.set(cardNum, swap(switches.get(cardNum)));
        for (int i = 1; ; i++) {
            final int left = cardNum - i;
            final int right = cardNum + i;
            if (left <= 0 || right >= switches.size()) {
                break;
            }

            if (switches.get(left) != switches.get(right)) {
                break;
            }

            switches.set(left, swap(switches.get(left)));
            switches.set(right, swap(switches.get(right)));
        }
    }

    private static void maleSwap(int cardNum) {
        final int length = switches.size();
        for (int i = 1; cardNum * i < length; i++) {
            switches.set(i * cardNum, swap(switches.get(i * cardNum)));
        }
    }

    private static int swap(int value) {
        if (value == 0) {
            return 1;
        }

        return 0;
    }
}
