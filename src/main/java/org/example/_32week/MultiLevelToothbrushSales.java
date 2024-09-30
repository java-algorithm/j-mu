package org.example._32week;

import java.util.HashMap;
import java.util.Map;

public class MultiLevelToothbrushSales {

    private static final Map<String, Integer> enrollIndexMap = new HashMap<>();
    private static final Map<String, String> parentMap = new HashMap<>();
    private static int[] answer;


    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] ans = solution(enroll, referral, seller, amount);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }

    private static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amounts) {
        answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            enrollIndexMap.put(enroll[i], i);
            parentMap.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            String curSeller = seller[i];
            int amount = amounts[i] * 100;

            while (!curSeller.equals("-")) {
                answer[enrollIndexMap.get(curSeller)] += (amount / 10) * 9 + amount % 10;

                curSeller = parentMap.get(curSeller);
                amount /= 10;

                if (amount == 0) {
                    break;
                }
            }
        }

        return answer;
    }

}
