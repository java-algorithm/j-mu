package org.example._12week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ThreeKindLiquid {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<Long> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        final List<Long> plus = new ArrayList<>();
        final List<Long> minus = new ArrayList<>();

        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            final long value = Long.parseLong(st.nextToken());
            if (value > 0) {
                plus.add(value);
            } else {
                minus.add(-value);
            }
        }

        final long[] plusLiquids = plus.stream().sorted().mapToLong(e -> e).toArray();
        final long[] minusLiquids = minus.stream().sorted().mapToLong(e -> e).toArray();

        long nearest = Long.MAX_VALUE;

        // 산성 + 알칼리성
        // 3 0
        if (plusLiquids.length >= 3) {
            final long[] array = Arrays.stream(plusLiquids).sorted().toArray();
            final long sum = array[0] + array[1] + array[2];
            if (nearest > sum) {
                nearest = sum;
                answers = List.of(array[0], array[1], array[2]);
            }
        }

        // 알칼리성 + 산성
        // 0 3
        if (minusLiquids.length >= 3) {
            final long[] array = Arrays.stream(minusLiquids).sorted().toArray();
            final long sum = Math.abs(array[0] + array[1] + array[2]);
            if (nearest > sum) {
                nearest = sum;
                answers = List.of(-array[0], -array[1], -array[2]);
            }
        }

        // 알칼리성 + 산성
        // 1 2
        if (plusLiquids.length >= 2) {
            for (int i = 0; i < minusLiquids.length; i++) {
                final long target = minusLiquids[i]; // 양수값으로 저장해놓음.

                int fp = 0;
                int sp = plusLiquids.length - 1;
                while (fp < sp) {
                    final long sum = plusLiquids[fp] + plusLiquids[sp];
                    final long distance = Math.abs(sum - target);
                    if (distance < nearest) {
                        nearest = distance;
                        answers = List.of(-target, plusLiquids[fp], plusLiquids[sp]);
                    }

                    if (sum > target) {
                        sp--;
                    } else if (sum < target) {
                        fp++;
                    } else {
                        break;
                    }
                }
            }
        }

        if (minusLiquids.length >= 2) {
            for (int i = 0; i < plusLiquids.length; i++) {
                final long target = plusLiquids[i]; // 양수값으로 저장해놓음.

                int fp = 0;
                int sp = minusLiquids.length - 1;
                while (fp < sp) {
                    final long sum = minusLiquids[fp] + minusLiquids[sp];
                    final long distance = Math.abs(sum - target);
                    if (distance < nearest) {
                        nearest = distance;
                        answers = List.of(target, -minusLiquids[fp], -minusLiquids[sp]);
                    }

                    if (sum > target) {
                        sp--;
                    } else if (sum < target) {
                        fp++;
                    } else {
                        break;
                    }
                }
            }
        }

        final long[] array = answers.stream().sorted().mapToLong(e -> e).toArray();
        System.out.print(array[0] + " ");
        System.out.print(array[1] + " ");
        System.out.println(array[2]);
    }
}
