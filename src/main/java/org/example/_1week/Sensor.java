package org.example._1week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sensor {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int sensorCount = Integer.parseInt(br.readLine());
        int buildingCount = Integer.parseInt(br.readLine());

        int[] sensors = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .distinct()
            .sorted()
            .toArray();

        if (buildingCount >= sensors.length) {
            System.out.println(0);
            return;
        }

        int[] distances = new int[sensors.length - 1];

        for (int i = 0; i < sensors.length - 1; i++) {
            distances[i] = sensors[i + 1] - sensors[i];
        }

        int[] sortedDistance = Arrays.stream(distances).sorted().toArray();
        int sum = 0;
        for (int i = 0; i < distances.length - (buildingCount - 1); i++) {
            sum += sortedDistance[i];
        }

        System.out.println(sum);
    }

}
