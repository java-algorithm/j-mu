package org.example._46week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FilialPietyTogether {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int M;
    private static int[][] map;
    private static int[][] initialPositions;

    private static int max = 0;

    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();
        Status status = new Status();

        dfs(status, 0);

        System.out.println(max);
    }

    private static void dfs(Status status, int personNo) {
        if (personNo == M) {
            max = Math.max(max, status.getPoint());
            return;
        }

        status.addPerson(personNo);
        dfs(status, 0, personNo);
        status.removePerson(personNo);
    }

    private static void dfs(Status status, int depth, int person) {
        if (depth == 3) {
            dfs(status, person + 1);
            return;
        }

        Person currentPerson = status.getCurrentPerson(person);
        int[] position = currentPerson.getPosition(depth);

        for (int j = 0; j < 4; j++) {
            int nextRow = position[0] + dr[j];
            int nextCol = position[1] + dc[j];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                continue;
            }

            if (status.isNotMovable(depth, nextRow, nextCol)) {
                continue;
            }

            currentPerson.go(depth, nextRow, nextCol);
            dfs(status, depth + 1, person);
        }
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        initialPositions = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            initialPositions[i][0] = Integer.parseInt(st.nextToken()) - 1;
            initialPositions[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
    }

    private static class Status {
        List<Person> people;

        public Status() {
            this.people = new ArrayList<>();
        }

        public boolean isNotMovable(int depth, int row, int col) {
            for (Person person : people) {
                if (person.isConflict(depth, row, col)) {
                    return true;
                }
            }

            return false;
        }

        public int getPoint() {
            int sum = 0;
            Set<Position> positions = new HashSet<>();
            for (Person person : people) {
                for (int i = 0; i < 4; i++) {
                    positions.add(new Position(person.getPosition(i)));
                }
            }

            for (Position position : positions) {
                sum += map[position.getRow()][position.getCol()];
            }

            return sum;
        }

        public Person getCurrentPerson(int depth) {
            return people.get(depth);
        }

        public void addPerson(int depth) {
            people.add(new Person(initialPositions[depth][0], initialPositions[depth][1]));
        }

        public void removePerson(int depth) {
            people.remove(depth);
        }

        public void print(int person) {
            Person person1 = people.get(person);
            person1.print();
        }
    }

    private static class Position {
        int[] pos;

        public Position(int[] pos) {
            this.pos = pos;
        }

        public int getRow() {
            return pos[0];
        }

        public int getCol() {
            return pos[1];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Arrays.equals(pos, position.pos);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(pos);
        }
    }

    private static class Person {
        int[][] positions = new int[4][2];

        public Person(int row, int col) {
            positions[0][0] = row;
            positions[0][1] = col;
        }

        public boolean isConflict(int depth, int row, int col) {
            int[] position = positions[depth + 1];
            return position[0] == row && position[1] == col;
        }

        public int getPoint() {
            int sum = 0;
            for (int i = 1; i < 4; i++) {
                sum += map[positions[i][0]][positions[i][1]];
            }

            return sum;
        }

        public int[] getPosition(int depth) {
            return positions[depth];
        }

        public void go(int depth, int nextRow, int nextCol) {
            positions[depth + 1][0] = nextRow;
            positions[depth + 1][1] = nextCol;
        }

        public void print() {
            for (int i = 1; i < 4; i++) {
                System.out.println(i + ": " + positions[i][0] + ", " + positions[i][1]);
            }
        }
    }
}
