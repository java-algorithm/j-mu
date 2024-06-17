package org.example._20week;

import java.util.*;

public class PathOfTravel {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static int index = 0;

    private static final List<List<Integer>> graph = new ArrayList<>();
    private static final Map<String, Integer> countryIndexMap = new HashMap<>();
    private static String[] countryIndex = new String[10_000];
    private static PriorityQueue<Path> answers = new PriorityQueue<>();
    private static int ticketsSize = 0;

    public static void main(String[] args) throws CloneNotSupportedException {
//        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
//        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
//        String[][] tickets =  {{"ICN", "AAA"}, {"AAA", "ICN"}, {"ICN", "CCC"}, {"CCC", "ICN"}, {"ICN", "DDD"}, {"DDD", "AAA"}};
        String[][] tickets = {{"ICN", "BBB"}, {"BBB", "ICN"}, {"ICN", "AAA"}, {"AAA", "ICN"}, {"ICN", "BBB"}};
        final String[] solution = solution(tickets);
        for (final String s : solution) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private static String[] solution(final String[][] tickets) throws CloneNotSupportedException {
        ticketsSize = tickets.length;

        // 공항의 최대 개수는 10_000
        for (int i = 0; i < 10_000; i++) {
            graph.add(new ArrayList<>());
        }

        // ICN은 무조건 0으로 하자. (편의상)
        countryIndexMap.put("ICN", index++);

        Map<Ticket, Integer> ticketCountMap = new HashMap<>();
        // 그래프 만들기.
        for (final String[] ticket : tickets) {
            final String from = ticket[FROM];
            final String to = ticket[TO];

            if (!countryIndexMap.containsKey(from)) {
                countryIndexMap.put(from, index++);
            }

            if (!countryIndexMap.containsKey(to)) {
                countryIndexMap.put(to, index++);
            }

            final Integer fromIndex = countryIndexMap.get(from);
            final Integer toIndex = countryIndexMap.get(to);

            final Ticket newTicket = new Ticket(fromIndex, toIndex);
            final Integer ticketCount = ticketCountMap.getOrDefault(newTicket, 0);
            ticketCountMap.put(newTicket, ticketCount + 1);

            graph.get(fromIndex).add(toIndex);
            countryIndex[fromIndex] = from;
            countryIndex[toIndex] = to;
        }

        final Path path = new Path();
        path.addPath("ICN");
        dfs(0, path, ticketCountMap);

        final Path answer = answers.poll();
        return answer.toArray();
    }

    private static void dfs(int node, Path path, Map<Ticket, Integer> ticketCountMap) {
        if (path.getSize() == ticketsSize + 1) {
            answers.add(path);
            return;
        }

        final List<Integer> adjacents = graph.get(node);
        for (final Integer adjacent : adjacents) {
            final Ticket ticket = new Ticket(node, adjacent);
            final Integer count = ticketCountMap.get(ticket);

            if (count > 0) {
                final Path newPath = path.clone();
                newPath.addPath(countryIndex[adjacent]);
                Map<Ticket, Integer> newTicketCountMap = new HashMap<>(ticketCountMap);
                newTicketCountMap.put(ticket, count - 1);

                dfs(adjacent, newPath, newTicketCountMap);
            }
        }
    }

    private static class Path implements Comparable<Path> {
        List<String> path;

        public Path() {
            this.path = new ArrayList<>();
        }

        @Override
        public int compareTo(final Path that) {
            for (int i = 0; i < path.size(); i++) {
                final String thisPath = this.getPath(i);
                final String thatPath = that.getPath(i);

                final int compare = thisPath.compareTo(thatPath);
                if (compare == 0) {
                    continue;
                }

                return compare;
            }

            return 0;
        }

        public String getPath(int i) {
            return path.get(i);
        }

        public int getSize() {
            return path.size();
        }

        public void addPath(String country) {
            this.path.add(country);
        }

        public void addAllPath(List<String> path) {
            for (final String country : path) {
                addPath(country);
            }
        }

        protected Path clone() {
            final Path newPath = new Path();
            newPath.addAllPath(this.path);

            return newPath;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (final String s : path) {
                sb.append(s).append(" ");
            }

            return sb.toString();
        }

        public String[] toArray() {
            String[] answer = new String[path.size()];
            return path.toArray(answer);
        }
    }

    private static class Ticket {
        int from;
        int to;

        public Ticket(final int from, final int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return
                "from=" + countryIndex[from] +
                    ", to=" + countryIndex[to];
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Ticket ticket = (Ticket) o;
            return from == ticket.from && to == ticket.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}
