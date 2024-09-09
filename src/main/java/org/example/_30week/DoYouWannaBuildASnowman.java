package org.example._30week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DoYouWannaBuildASnowman {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] numbers;

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Snowman> snowmen = new ArrayList<>(180_000);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                snowmen.add(new Snowman(i, j));
            }
        }

        Collections.sort(snowmen);

        int snowmanSize = snowmen.size();
        for (int target = 0; target < snowmanSize; target++) {
            Snowman targetSnowman = snowmen.get(target);

            for (int i = target + 1; i < snowmanSize; i++) {
                Snowman snowman = snowmen.get(i);

                boolean hasDuplication = targetSnowman.hasDuplication(snowman);
                if (!hasDuplication) {
                    answer = Math.min(answer, Math.abs(targetSnowman.getSize() - snowman.getSize()));
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    private static class Snowman implements Comparable<Snowman> {
        int x;
        int y;
        int sum;

        public Snowman(int x, int y) {
            this.x = x;
            this.y = y;
            this.sum = numbers[x] + numbers[y];
        }

        public boolean hasDuplication(Snowman other) {
            return other.x == this.x || other.x == this.y || other.y == this.x || other.y == this.y;
        }

        public int getSize() {
            return numbers[x] + numbers[y];
        }

        @Override
        public int compareTo(Snowman o) {
            return Integer.compare(this.sum, o.sum);
        }
    }

}
