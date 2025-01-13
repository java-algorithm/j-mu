package org.example._44week;

import java.util.Arrays;

public class SuccessfulPairsOfSpellsAndPotions {

    public static void main(String[] args) {
        int[] spells = {5,1,3};
        int[] potions = {1,2,3,4,5};
        int success = 7;

        int[] answers = successfulPairs(spells, potions, success);
        Arrays.stream(answers).forEach(i -> System.out.print(i+" "));
    }

    // 안부전해줘야되고 준우형이 보고싶어한다고 해줘야하고.
    // 안보고싶다하면 그렇게 말할줄 알았다고 말했다고 해야하고
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] sortedPotions = Arrays.stream(potions).sorted().toArray();


    }

    private static class Spell implements Comparable<Spell>{
        int value;
        int idx;

        @Override
        public int compareTo(Spell o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
