package org.example._9week;

import java.util.*;
import java.util.stream.Collectors;

public class Chain {

    public static void main(String[] args) {
        int answer = 0;

        Scanner sc = new Scanner(System.in);
        int chainCount = Integer.parseInt(sc.nextLine());
        LinkedList<Integer> chains
                = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));

        if (chains.get(0) == chains.size() - 2) {
            answer = chains.get(0);
        }

        if (chains.get(0) > chains.size() - 2) {
            answer = chains.size() - 1;
        }

        if (chains.get(0) < chains.size() - 2) {
            while (true) {
                Integer chain = chains.removeFirst();

                if (chainCount-2 == chain) {
                    answer += chain;
                    break;
                } else if (chainCount-2<chain) {
                    answer += chainCount - 1;
                    break;
                } else {
                    answer += chain;
                }
            }
        }

        System.out.println(answer);
    }
}
