package org.example._15week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RankingReadyQueue {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int playersCount = Integer.parseInt(st.nextToken());
        Room.maxPlayersLimit = Integer.parseInt(st.nextToken());

        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < playersCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int playerLevel = Integer.parseInt(st.nextToken());
            final String playerName = st.nextToken();

            boolean canEnterIn = false;
            final Player player = new Player(playerLevel, playerName);
            for (int j = 0; j < rooms.size(); j++) {
                final Room room = rooms.get(j);
                canEnterIn = room.canEnterIn(player);
                if (canEnterIn) {
                    room.addPlayer(player);
                    break;
                }
            }

            if (!canEnterIn) {
                final Room room = new Room(player);
                rooms.add(room);
            }
        }

        for (final Room room : rooms) {
            System.out.print(room);
        }
    }

    private static class Room {

        static int maxPlayersLimit;

        boolean started;
        PriorityQueue<Player> players;
        Player boss;

        public Room(final Player player) {
            this.players = new PriorityQueue<>();
            this.players.add(player);
            this.boss = player;
            this.started = players.size() >= maxPlayersLimit;
        }

        boolean canEnterIn(Player player) {
            return !started && boss.between10Level(player);
        }

        void addPlayer(Player player) {
            this.players.add(player);
            this.started = players.size() >= maxPlayersLimit;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(started ? "Started!" : "Waiting!").append("\n");
            while (!players.isEmpty()) {
                final Player poll = players.poll();
                sb.append(poll);
            }

            return sb.toString();
        }
    }

    private static class Player implements Comparable<Player> {
        int level;
        String name;

        public Player(final int level, final String name) {
            this.level = level;
            this.name = name;
        }

        public boolean between10Level(Player player) {
            return this.level - 10 <= player.level && this.level + 10 >= player.level;
        }

        @Override
        public String toString() {
            return level + " " + name + "\n";
        }

        @Override
        public int compareTo(final Player o) {
            return this.name.compareTo(o.name);
        }
    }
}
