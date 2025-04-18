package org.example._54week;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        LinkedList<String> str = new LinkedList<>();

        str.add("A");
        str.add("B");
        str.add("C");
        str.add("D");
        str.add("E");

//        for (int i = 0; i < str.size(); i++) {
//            System.out.println(str.get(i));
//        }
//
//        for (String value : str) {
//            System.out.println(value);
//        }
//
//        Iterator<String> iterator = str.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//
//        ListIterator<String> stringListIterator = str.listIterator();
//        stringListIterator.previous();
//
//        Iterator<String> stringIterator = str.descendingIterator();
//        stringIterator.hasNext(); // 역순으로 가는거에요.

//        for (String value : str) {
//            if (value.equals("C")) {
//                str.remove(value);
//            }
//        }
//
//        Iterator<String> iterator = str.iterator();
//        while (iterator.hasNext()) {
//            if (iterator.next().equals("C")) {
//                iterator.remove();
//            }
//        }

        for (int i = 0; i < str.size(); i++) {
            String value = str.get(i);
            if (value.equals("C")) {
                str.remove(value);
                continue;
            }

            System.out.println(value);
        }
    }
}
