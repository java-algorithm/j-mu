package org.example._35week;

import java.util.HashSet;
import java.util.Set;

public class BannedId {

    private static String[] userIds;
    private static String[] bannedIds;
    private static boolean[] visited;

    private static final Set<Set<String>> answers = new HashSet<>();

    public static void main(String[] args) {

//        String[] user_id = new String[]{"aa", "ab", "ac", "ad", "ae", "be"};
//        String[] banned_id = new String[]{"*b", "*c", "*d", "*e", "a*", "**"};
//        String[] user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banned_id = new String[]{"fr*d*", "abc1**"};

        String[] user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banned_id = new String[]{"fr*d*", "*rodo", "******", "******"};
        String[] banned_id = new String[]{"*rodo", "*rodo", "******"};

        solution(user_id, banned_id);
    }

    private static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
         userIds = user_id;
         bannedIds = banned_id;
        visited = new boolean[user_id.length];

        printArray(userIds);
        printArray(bannedIds);
        printArray(visited);

        dfs(0);
        printAnswers();
        return answers.size();
    }

    private static void dfs(int bannedIndex){
        if(bannedIndex==bannedIds.length){
            Set<String> answer = new HashSet<>();

            for(int i=0; i<userIds.length; i++){
                if(visited[i]){
                    answer.add(userIds[i]);
                }
            }

            answers.add(answer);
            return;
        }

        for(int i=0; i<userIds.length; i++){
            if(visited[i]){
                continue;
            }

            if(userIds[i].length() != bannedIds[bannedIndex].length()){
                continue;
            }
            System.out.println(bannedIndex);

            boolean isSame = true;
            for(int j=0; j<userIds[i].length(); j++){
                if(bannedIds[bannedIndex].charAt(j)=='*'){
                    continue;
                }

                if(userIds[i].charAt(j) == bannedIds[bannedIndex].charAt(j)){
                    continue;
                }

                isSame = false;
                break;
            }

            if(isSame){
                visited[i] = true;
                dfs(bannedIndex+1);
                visited[i] = false;
            }
        }
    }

    private static void printAnswers(){
        for(Set<String> answer : answers){
            for(String ans : answer){
                System.out.print(ans+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printArray(String[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    private static void printArray(boolean[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
