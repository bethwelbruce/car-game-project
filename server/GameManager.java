package server;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameManager {
    private final Map<String, Integer> playerScores = new ConcurrentHashMap<>();
    private final Map<String, String> playerStatus = new ConcurrentHashMap<>();
    private final List<String> connectedPlayers = Collections.synchronizedList(new ArrayList<>());

    public void registerPlayer(String name) {
        playerScores.put(name, 0);
        playerStatus.put(name, "waiting");
        connectedPlayers.add(name);
        System.out.println("Player registered: " + name);
    }

    public void removePlayer(String name) {
        playerScores.remove(name);
        playerStatus.remove(name);
        connectedPlayers.remove(name);
        System.out.println("Player removed: " + name);
    }

    public void updateScore(String name, int score) {
        if (playerScores.containsKey(name)) {
            playerScores.put(name, score);
        }
    }

    public void setStatus(String name, String status) {
        if (playerStatus.containsKey(name)) {
            playerStatus.put(name, status);
        }
    }

    public int getScore(String name) {
        return playerScores.getOrDefault(name, 0);
    }

    public String getStatus(String name) {
        return playerStatus.getOrDefault(name, "unknown");
    }

    public List<String> getConnectedPlayers() {
        return Collections.unmodifiableList(connectedPlayers);
    }

    public String getLeaderboard() {
        StringBuilder sb = new StringBuilder("=== Live Leaderboard ===\n");
        playerScores.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(e -> sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n"));
        return sb.toString();
    }

    public int getPlayerCount() {
        return connectedPlayers.size();
    }
}
