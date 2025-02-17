package org.example._48week;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

public class ShuttleBus {

    public static void main(String[] args) {
        String[] arr = {"09:10", "09:09", "08:00"};
        String answer = solution(2,10,2, arr);

        System.out.println(answer);
    }

    // n: 셔틀 운행 횟수
    // t: 셔틀 운행 간격
    // m: 한 셔틀에 탈 수 있는 최대 크루원 수
    public static String solution(int n, int t, int m, String[] timetable) {
        Bus[] busses = new Bus[n];
        boolean[] full = new boolean[n];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime currentTime = LocalDateTime.now()
                .withHour(9)
                .withMinute(0)
                .withSecond(0)
                .minusMinutes(t);

        Arrays.sort(timetable);

        // busses 초기화
        for (int busIdx = 0; busIdx < n; busIdx++) {
            currentTime = currentTime.plusMinutes(t);
            busses[busIdx] = new Bus(currentTime.format(formatter));
        }

        for (int timeIdx = 0, busIdx = 0; timeIdx < timetable.length && busIdx < n; timeIdx++) {
            if (timetable[timeIdx].compareTo(busses[busIdx].time) > 0) {
                busIdx++;
                timeIdx--;
                continue;
            }

            busses[busIdx].cnt++;
            if (busses[busIdx].cnt >= m) {
                full[busIdx] = true;
                busIdx++;
            }
        }

        for (int busIdx = busses.length - 1; busIdx >= 0; busIdx--) {
            if (!full[busIdx]) {
                return busses[busIdx].time;
            }
        }

        LocalDateTime dateTime = LocalDateTime.of(
                LocalDateTime.now().toLocalDate(), // 오늘 날짜 가져오기
                LocalTime.parse(timetable[m-2], formatter) // 문자열을 LocalTime으로 변환
        );

        return dateTime.minusMinutes(1).format(formatter);
    }

    private static class Bus {
        String time;
        int cnt;

        public Bus(String time) {
            this.time = time;
            this.cnt = 0;
        }
    }
}
