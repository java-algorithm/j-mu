package org.example._32week;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {

    }

    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();

        dfs(n, 0, 0, answer, new StringBuilder());

        return answer;
    }

    private void dfs(int n, int open, int close, List<String> answer, StringBuilder sb) {
        if (n == open && n == close) {
            answer.add(sb.toString());
            return;
        }

        if (n > open) {
            sb.append('(');
            dfs(n, open + 1, close, answer, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (open > close) {
            sb.append(')');
            dfs(n, open, close + 1, answer, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
