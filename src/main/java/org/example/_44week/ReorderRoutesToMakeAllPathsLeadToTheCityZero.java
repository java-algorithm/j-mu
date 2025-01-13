package org.example._44week;

import java.util.ArrayList;
import java.util.Stack;

public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    private static boolean[][] forward;
    private static boolean[][] reverse;
    private static boolean[] visited;
    private static int N;
    private static int answer;

    public static void main(String[] args) {
        int n =6;
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};

        int ans = minReorder(n, connections);
        System.out.println(ans);
    }

    public static int minReorder(int n, int[][] connections) {
        forward = new boolean[n][n];
        reverse = new boolean[n][n];
        visited = new boolean[n];
        N=n;
        answer = 0;

        for(int i=0; i<connections.length; i++){
            int[] connection = connections[i];
            forward[connection[0]][connection[1]] = true;
            reverse[connection[1]][connection[0]] = true;
        }

        Stack<Integer> stack = new Stack<>();
        visited[0] = true;
        push(stack,0);

        while(!stack.isEmpty()){
            Integer current = stack.pop();

            push(stack, current);
        }

        return answer;
    }

    private static void push(Stack<Integer> stack, int currentNode){
        for(int i =0; i<N; i++){
            if(!forward[currentNode][i] && !reverse[currentNode][i]){
                continue;
            }

            if (visited[i]) {
                continue;
            }

            if(forward[currentNode][i]){
                answer++;
            }

            visited[i] = true;
            stack.push(i);
        }
    }
}
