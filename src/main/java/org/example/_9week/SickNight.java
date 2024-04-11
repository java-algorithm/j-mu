package org.example._9week;

import java.util.Scanner;
import java.util.StringTokenizer;

public class SickNight {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        if (height == 1) {
            System.out.println(1);
            return;
        }

        // P를 width -1 이라할 때,
        // 2칸 늘어날 때 마다 +1되므로
        // 1(처음 위치) + (width-1)/2 = (width+1)/2
        if (height == 2) {
            System.out.println(Math.min(4, (width + 1) / 2));
            return;
        }

        // 여기부터는 세로로 움직일 수 있음.
        if (width <= 6) {
            System.out.println(Math.min(4, width));
            return;
        }

        // 처음 4칸에서 칸수 : 2
        // 이후 => 1칸당 1개 (width-4)
        // 따라서 width-2
        System.out.println(width - 2);
    }
}
