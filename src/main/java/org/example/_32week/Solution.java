package org.example._32week;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final Map<String, Integer> enrollIdx = new HashMap<>();
    private static int[] answer;

    private static String[] enroll;
    private static String[] referral;
    private static String[] seller;
    private static int[] amount;
    
    public int[] solution(String[] _enroll, String[] _referral, String[] _seller, int[] _amount) {
        enroll = _enroll;
        referral = _referral;
        seller = _seller;
        amount = _amount;

        answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            enrollIdx.put(enroll[i], i);
        }

        for (int i = 0; i < seller.length; i++) {
            recursion(seller[i], amount[i] * 100);
        }

        return answer;
    }
    
    private void recursion(String seller, int amount) {
        if (seller.equals("-")) {
            return;
        }

        Integer enrollIndex = enrollIdx.get(seller);
        String referralPerson = referral[enrollIndex];

        answer[enrollIndex] += (amount / 10) * 9 + amount % 10;
        recursion(referralPerson, amount / 10);
    }
}