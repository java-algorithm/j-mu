package org.example._26week;

public class AnalogClock {

    public static void main(String[] args) {
        final int h1 = 0;
        final int m1 = 5;
        final int s1 = 30;

        final int h2 = 0;
        final int m2 = 7;
        final int s2 = 0;

        int solution = solution(h1, m1, s1, h2, m2, s2);

        System.out.println(solution);
    }


    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int count = 0;

        int targetTimeToSecond = h2 * 3600 + m2 * 60 + s2;
        int curTimeToSecond = h1 * 3600 + m1 * 60 + s1;
        int time = (targetTimeToSecond - curTimeToSecond);

        int curHourPosition = (h1 * 3600 + m1 * 60 + s1);
        int curMinutePosition = (m1 * 720 + s1 * 12);
        int curSecondPosition = s1 * 12 * 60;

        if (curHourPosition == curSecondPosition) {
            count++;
        } else if (curMinutePosition == curSecondPosition) {
            count++;
        }

        if (curTimeToSecond < 43200 && targetTimeToSecond >= 43200) {
            count--;
        }

        for (int second = 1; second <= time; second++) {
            int nextHourPosition = (curHourPosition) % 43200;
            int nextMinutePosition = (curMinutePosition + 12) % 43200;
            int nextSecondPosition = (curSecondPosition + 720) % 43200;

            if (curHourPosition >= curSecondPosition && curHourPosition < nextSecondPosition) {
                count++;
            }

            if (curMinutePosition >= curSecondPosition && curMinutePosition < nextSecondPosition) {
                count++;
            }

            curHourPosition = nextHourPosition;
            curMinutePosition = nextMinutePosition;
            curSecondPosition = nextSecondPosition;
        }


        return count;
    }
}
