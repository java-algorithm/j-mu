package org.example._44week;

import java.util.Arrays;

public class SuccessfulPairsOfSpellsAndPotions {

    public static void main(String[] args) {
        int[] spells = {5, 1, 3};
        int[] potions = {1, 2, 3, 4, 5};
        int success = 7;

        int[] answers = successfulPairs(spells, potions, success);
        Arrays.stream(answers).forEach(i -> System.out.print(i + " "));
    }

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] answers = new int[spells.length];
        potions = Arrays.stream(potions).sorted().toArray();

        for (int i = 0; i < spells.length; i++) {
            int sp = 0;
            int ep = potions.length - 1;

            while (sp <= ep) {
                int mid = (sp + ep) / 2;
                if ((long) potions[mid] * spells[i] >= success) {
                    ep = mid - 1;
                } else {
                    sp = mid + 1;
                }
            }

            answers[i] = potions.length - sp;
        }

        return answers;
    }

//    private static class Spell implements Comparable<Spell>{
//        int value;
//        int idx;
//
//        public Spell(int value, int idx) {
//            this.value = value;
//            this.idx = idx;
//        }
//
//        @Override
//        public int compareTo(Spell o) {
//            return Integer.compare(this.value, o.value);
//        }
//    }
}
