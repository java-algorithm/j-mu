package org.example._42week;

public class MaximumNumberOfVowelsInASubStringOfGivenLength {

    public static void main(String[] args) {
        String s = "abciiidef";
        System.out.println(maxVowels(s, 3));
    }

    public static int maxVowels(String s, int k) {
        int count = 0;
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                count++;
            }
        }

        int answer = count;


        for (int i = 0, j = k; j < s.length(); i++, j++) {
            if (isVowel(s.charAt(i))) {
                count--;
            }
            if (isVowel(s.charAt(j))) {
                count++;
            }

            answer = Math.max(count, answer);
        }

        return answer;
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
