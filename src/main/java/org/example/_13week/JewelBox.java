package org.example._13week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JewelBox {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 이거 구현처럼 풀어본 풀이인데 시간초과납니다. 이거 정답 아니에용.
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int studentCount = Integer.parseInt(st.nextToken());
        final int jewelCount = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewel> jewels = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < jewelCount; i++) {
            final int jewel = Integer.parseInt(br.readLine());
            jewels.add(new Jewel(jewel));
        }

        while (studentCount < jewels.size()) {
            jewels.poll();
        }

        // 보석을 모두 나눠줘도 못 받는 학생이 있을 수 있음에 주의!
        int currentStudent = jewels.size();
        while (currentStudent < studentCount) {
            final Jewel biggestJewel = jewels.poll();

            if (biggestJewel.getNumPerPerson() == 1) {
                jewels.add(biggestJewel);
                break;
            }

            biggestJewel.person += 1;
            jewels.add(biggestJewel);
            currentStudent += 1;
        }

        System.out.println(jewels.poll().getNumPerPerson());
    }

    private static class Jewel implements Comparable<Jewel> {
        int num;
        int person;

        public int getNumPerPerson() {
            return num % person == 0 ? (num / person) : (num / person) + 1;
        }

        @Override
        public int compareTo(final Jewel o) {
            return Integer.compare(this.getNumPerPerson(), o.getNumPerPerson());
        }

        public Jewel(final int num) {
            this.num = num;
            this.person = 1;
        }
    }
}
