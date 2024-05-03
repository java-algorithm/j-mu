package org.example._12week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class KCPC {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        final int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            final int teamsCount = Integer.parseInt(st.nextToken());
            final int problemsCount = Integer.parseInt(st.nextToken());
            final int myTeamId = Integer.parseInt(st.nextToken());
            final int logsCount = Integer.parseInt(st.nextToken());

            test(teamsCount, problemsCount, myTeamId, logsCount);
        }
    }

    private static void test(final int teamsCount, final int problemsCount, final int myTeamId, final int logsCount) throws IOException {
        // 팀 id, 문제 번호, 흭득한 점수
        // 점수 저장.
        int[] scores = new int[teamsCount + 1];
        int[] submitCounts = new int[teamsCount + 1];
        int[] lastSubmits = new int[teamsCount + 1];

        int[][] submits = new int[teamsCount + 1][problemsCount + 1];

        int submitTime = 0;
        for (int i = 0; i < logsCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int teamId = Integer.parseInt(st.nextToken());
            final int problemId = Integer.parseInt(st.nextToken());
            final int score = Integer.parseInt(st.nextToken());

            // 제출한 log저장.
            final int currentProblemScore = submits[teamId][problemId];
            if (currentProblemScore < score) {
                submits[teamId][problemId] = score;
            }

            // 제출 횟수 저장.
            submitCounts[teamId]++;

            // 마지막 제출 시간 저장.
            // 모든 팀은 최소 한번 제출하므로 예외상황 생각 x
            lastSubmits[teamId] = (++submitTime);
        }

        // 점수 배열 저장.
        for (int teamId = 1; teamId < teamsCount + 1; teamId++) {
            final int[] submit = submits[teamId];
            final int sum = Arrays.stream(submit).sum();
            scores[teamId] = sum;
        }

        List<Team> teams = new ArrayList<>(teamsCount);
        for (int teamId = 1; teamId < teamsCount + 1; teamId++) {
            final Team team = new Team(teamId, scores[teamId], submitCounts[teamId], lastSubmits[teamId]);
            teams.add(team);
        }
        final List<Team> sortedTeam = teams.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < teamsCount; i++) {
            final Team team = sortedTeam.get(i);
            if (team.teamId == myTeamId) {
                System.out.println(i + 1);
                return;
            }
        }
    }


    private static class Team implements Comparable<Team> {
        int teamId;
        int score;
        int submitCounts;
        int lastSubmitTime;

        public Team(final int teamId, final int score, final int submitCounts, final int lastSubmitTime) {
            this.teamId = teamId;
            this.score = score;
            this.submitCounts = submitCounts;
            this.lastSubmitTime = lastSubmitTime;
        }

        // 1. 점수 높은순
        // 2. 제출 횟수 적은순
        // 3. 마지막 제출 시간이 빠른순
        @Override
        public int compareTo(final Team o) {
            // 점수는 높을수록 좋은거니 내림차순
            if (this.score != o.score) {
                return Integer.compare(o.score, this.score);
            }

            // 제출횟수는 적을수록 좋으니 오름차순
            if (this.submitCounts != o.submitCounts) {
                return Integer.compare(this.submitCounts, o.submitCounts);
            }

            // 마지막 제출 시간은 빠를수록 좋으니 오름차순.
            return Integer.compare(this.lastSubmitTime,o.lastSubmitTime);
        }
    }
}
