package org.example._54week;

import java.util.ArrayList;
import java.util.List;

public class Concurrent {

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();

        arr.add("A");
        arr.add("B");
        arr.add("C");
        arr.add("D");
        arr.add("E");
        arr.add("F");

        for (String value : arr) {
            if (value.equals("C")) {
                arr.remove("C");
            }
        }
    }
}
