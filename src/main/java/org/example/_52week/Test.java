package org.example._52week;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<>();

        arrList.add(1);
        arrList.add(2);
        arrList.add(3);
        arrList.add(4);
        arrList.add(5);
        for (int i = 0; i < arrList.size(); i++) {
            if (arrList.get(i) == 3) {
                arrList.remove(i);
                continue;
            }

            System.out.println(arrList.get(i));
        }

        for (Integer num : arrList) {
            if (num == 3) {
                arrList.remove(num);
                continue;
            }

            System.out.println(num);
        }
    }
}
