package org.example._38week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ClassRoom {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final PriorityQueue<Integer> rooms = new PriorityQueue<>();
    private static final List<Map.Entry<Integer,Integer>> lectures = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lectures.add(Map.entry(start, end));
        }

        // NlogN
        lectures.sort(getLectureComparator());

        // 첫 강의를 위한 룸 개설
        Map.Entry<Integer, Integer> firstLecture = lectures.get(0);
        rooms.add(firstLecture.getValue());

        for (int i = 1; i < lectures.size(); i++) {
            Map.Entry<Integer, Integer> lecture = lectures.get(i);
            if (rooms.peek() <= lecture.getKey()) {
                rooms.poll();
            }

            rooms.add(lecture.getValue());
        }

        System.out.println(rooms.size());
    }

    private static Comparator<Map.Entry<Integer,Integer>> getLectureComparator() {
        return (l1, l2) -> {
            int startTimeCompare = Integer.compare(l1.getKey(), l2.getKey());
            if (startTimeCompare != 0) {
                return startTimeCompare;
            }

            return Integer.compare(l1.getValue(), l2.getValue());
        };
    }
}
