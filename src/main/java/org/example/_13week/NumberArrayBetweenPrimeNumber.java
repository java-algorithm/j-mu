package org.example._13week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberArrayBetweenPrimeNumber {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final int T = Integer.parseInt(br.readLine());
        final int MAX_VALUE = 1_299_710;
        boolean[] compositeNumbers = new boolean[MAX_VALUE];

        // find prime numbers
        for (int i = 2; i < MAX_VALUE - 1; i++) {
            for (int j = 2; i * j < MAX_VALUE; j++) {
                compositeNumbers[i*j]=true;
            }
        }

        List<Integer> primes = new ArrayList<>(100_000);
        for (int i = 2; i < compositeNumbers.length; i++) {
            if (!compositeNumbers[i]) {
                primes.add(i);
            }
        }

        for (int i = 0; i < T; i++) {
            final int K = Integer.parseInt(br.readLine());
            // binary search
            int left = 0;
            int right = primes.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (primes.get(mid) == K) {
                    System.out.println(0);
                    break;
                }


                if (primes.get(mid) < K && K < primes.get(mid + 1)) {
                    System.out.println(primes.get(mid + 1) - primes.get(mid));
                    break;
                }

                if (primes.get(mid) > K && K > primes.get(mid - 1)) {
                    System.out.println(primes.get(mid) - primes.get(mid - 1));
                    break;
                }

                if (primes.get(mid) < K) {
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }
    }
}
