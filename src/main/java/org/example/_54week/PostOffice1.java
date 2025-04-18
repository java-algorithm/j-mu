package org.example._54week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.StringTokenizer;

public class PostOffice1 {
    private static int[] positions;
    private static int[] people;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        long answer = 0;

        int N = Integer.parseInt(br.readLine());
        positions = new int[N];
        people = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            positions[i] = Integer.parseInt(st.nextToken());
            people[i] = Integer.parseInt(st.nextToken());
        }

        BigInteger totalPeople = BigInteger.ZERO;
        for (int i = 0; i < N; i++) {
            totalPeople = totalPeople.add(BigInteger.valueOf(people[i]));
        }

        BigInteger weightedSum = BigInteger.ZERO;
        for (int i = 0; i < N; i++) {
            BigInteger weight = BigInteger.valueOf(positions[i]).multiply(BigInteger.valueOf(people[i]));
            weightedSum = weightedSum.add(weight);
        }

        // BigDecimal로 변환
        BigDecimal num = new BigDecimal(weightedSum);
        BigDecimal den = new BigDecimal(totalPeople);

        // 나누기
        BigDecimal result = num.divide(den, 10, RoundingMode.HALF_DOWN); // 소수점 10자리까지 계산

        // 반내림: 0.5 이하면 버리고, 초과면 올림
        BigInteger rounded = result.setScale(0, RoundingMode.HALF_DOWN).toBigInteger();

        System.out.println(rounded);
    }
}
