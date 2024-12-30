package org.example._43week;

public class Dota2 {
    private static final char RADIENT = 'R';
    private static final char DIRE = 'D';

    public static void main(String[] args) {
        String senate = "RD";

        String answer = predictPartyVictory(senate);
        System.out.println(answer);
    }

    public static String predictPartyVictory(String senate) {
        boolean[] banned = new boolean[senate.length()];

        while(true){
            for (int i = 0; i < senate.length(); i++) {
                if (banned[i]) {
                    continue;
                }

                char sen = senate.charAt(i);
                char reverse = reverse(sen);
                boolean found = false;
                for (int j = 0; j < senate.length(); j++) {
                    int idx = (i+j+1)%senate.length();

                    if (banned[idx]) {
                        continue;
                    }

                    if(senate.charAt(idx) == reverse){
                        banned[idx] = true;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    return fullName(sen);
                }
            }
        }
    }

    private static String fullName(char sen) {
        if (sen == RADIENT) {
            return "Radiant";
        }else{
            return "Dire";
        }
    }

    private static char reverse(char sen) {
        if (sen == RADIENT) {
            return DIRE;
        }else{
            return RADIENT;
        }
    }
}
