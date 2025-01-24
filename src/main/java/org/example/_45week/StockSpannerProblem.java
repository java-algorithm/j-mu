package org.example._45week;

import java.util.Stack;

public class StockSpannerProblem {

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        stockSpanner.next(100); // return 1
        stockSpanner.next(80);  // return 1
        stockSpanner.next(60);  // return 1
        stockSpanner.next(70);  // return 2
        stockSpanner.next(60);  // return 1
        stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        stockSpanner.next(85);
    }

    private static class StockSpanner {
        // 0: cnt, 1: value
        Stack<int[]> stocks;

        public StockSpanner() {
            stocks = new Stack<>();
        }

        public int next(int price) {
            if (stocks.isEmpty()) {
                stocks.push(new int[]{1, price});

                System.out.println(1);
                return 1;
            }

            int idx = 1;
            while (!stocks.isEmpty()) {
                int[] pop = stocks.peek();
                if (pop[1] <= price) {
                    idx += pop[0];
                    stocks.pop();
                } else {
                    break;
                }
            }

            stocks.add(new int[]{idx, price});

            System.out.println(idx);
            return idx;
        }
    }
}
