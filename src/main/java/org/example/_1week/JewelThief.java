package org.example._1week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/1202
public class JewelThief {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<Jewel> jewels = new LinkedList<>();
        List<Integer> bags = new LinkedList<>();

        PriorityQueue<Jewel> candidate = new PriorityQueue<>();
        int[] NK = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NK[0];
        int K = NK[1];

        for (int i = 0; i < N; i++) {
            int[] MV = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            jewels.add(new Jewel(MV[0], MV[1]));
        }

        for (int i = 0; i < K; i++) {
            int bagCapacity = Integer.parseInt(br.readLine());
            bags.add(bagCapacity);
        }

        List<Jewel> sortedJewels = jewels.stream().sorted(Comparator.comparing(Jewel::getWeight))
            .collect(Collectors.toList());
        List<Integer> sortedBags = bags.stream().sorted().collect(Collectors.toList());

        long totalValue = getTotalValue(sortedJewels, sortedBags, candidate);

        System.out.println(totalValue);
    }

    private static long getTotalValue(List<Jewel> jewels, List<Integer> bags, PriorityQueue<Jewel> candidate) {
        long totalValue = 0;
        int bagIndex = 0;
        int jewelIndex = 0;

        int bagSize = bags.size();
        while (true) {
            if (bagIndex >= bagSize || jewelIndex >= jewels.size()) {
                break;
            }

            Integer capacity = bags.get(bagIndex);
            Jewel jewel = jewels.get(jewelIndex);
            if (jewel.getWeight() <= capacity) {
                jewelIndex++;
                candidate.offer(jewel);
            } else {
                bagIndex++;
                if(!candidate.isEmpty()){
                    Jewel poll = candidate.poll();
                    totalValue += poll.getValue();
                }
            }
        }

        if (bagIndex < bagSize) {
            for (int i = bagIndex; i < bagSize; i++) {
                Jewel poll = candidate.poll();
                if (poll == null) {
                    break;
                }
                totalValue += poll.getValue();
            }
        }

        return totalValue;
    }

    private static class Jewel implements Comparable {

        private int weight;
        private int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(Object o) {
            Jewel that = (Jewel) o;

            return that.value - this.value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Jewel jewel = (Jewel) o;
            return weight == jewel.weight && value == jewel.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, value);
        }

        @Override
        public String toString() {
            return "Jewel{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
        }
    }
}
