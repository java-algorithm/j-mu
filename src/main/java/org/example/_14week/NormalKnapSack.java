package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NormalKnapSack {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        final int itemsCount = Integer.parseInt(st.nextToken());
        final int maxWeight = Integer.parseInt(st.nextToken());

        Item[] items = new Item[itemsCount + 1];
        for (int i = 1; i < itemsCount + 1; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final int weight = Integer.parseInt(st.nextToken());
            final int value = Integer.parseInt(st.nextToken());

            items[i] = new Item(weight, value);
        }

        int[][] dp = new int[itemsCount + 1][maxWeight + 1];
        for (int itemIndex = 1; itemIndex < itemsCount + 1; itemIndex++) {
            final Item item = items[itemIndex];

            for (int curWeight = 1; curWeight < maxWeight + 1; curWeight++) {
                if (curWeight < item.weight) {
                    dp[itemIndex][curWeight] = dp[itemIndex - 1][curWeight];
                } else {
                    dp[itemIndex][curWeight] = Math.max(dp[itemIndex - 1][curWeight], item.value + dp[itemIndex - 1][curWeight - item.weight]);
                }
            }
        }

        System.out.println(dp[itemsCount][maxWeight]);
    }

    private static class Item {
        int weight;
        int value;

        public Item(final int weight, final int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
