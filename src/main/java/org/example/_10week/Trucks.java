package org.example._10week;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Trucks {

    public static void main(String[] args) {
        // 입출력 받아오기.
        final Scanner sc = new Scanner(System.in);
        final StringTokenizer st = new StringTokenizer(sc.nextLine());

        final int truckCounts = Integer.parseInt(st.nextToken());
        final int bridgeLength = Integer.parseInt(st.nextToken());
        final int maxWeight = Integer.parseInt(st.nextToken());

        final LinkedList<Integer> trucks = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
        final LinkedList<Integer> bridges = new LinkedList<>();
        int bridgeWeight = 0; // 현재 다리위에 있는 트럭들의 총 무게
        for (int i = 0; i < bridgeLength; i++) {
            bridges.add(0);
        }


        int time = 0;
        while (true) {
            // 다리에서 트럭이 내려오는 부분
            final Integer firstTruck = bridges.poll();
            if (firstTruck == null) {
                break;
            }
            time++;

            bridgeWeight -= firstTruck;

            // 더 올라올 트럭이 존재하는지 확인
            final Integer truck = trucks.peek();
            if (truck == null) {
                continue;
            }

            // 다리에 트럭이 올라타는 부분
            if (maxWeight >= bridgeWeight + truck) {
                bridges.add(trucks.poll());
                bridgeWeight+=truck;
            }else{
                bridges.add(0);
            }
        }

        System.out.println(time);
    }
}
