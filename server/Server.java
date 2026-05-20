package server;

import java.net.*;
import java.io.*;

public class Server {
    private static final int PORT = 1234;
    private static final GameManager gameManager = new GameManager();

    public static void main(String[] args) {
        System.out.println("Starting CarGame server on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setReuseAddress(true);
            System.out.println("Server ready. Waiting for connections...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                String clientAddr = clientSocket.getInetAddress().getHostAddress();
                System.out.println("New connection from: " + clientAddr);
                Thread t = new Thread(() -> handleClient(clientSocket));
                t.setDaemon(true);
                t.start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket socket) {
        String playerName = "unknown";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("WELCOME CarGame Server v1.0");
            String input;
            while ((input = in.readLine()) != null) {
                String response = processCommand(input.trim(), playerName, out);
                if (input.startsWith("NAME:")) {
                    playerName = input.substring(5).trim();
                }
                if (response != null) out.println(response);
                if ("QUIT".equalsIgnoreCase(input.trim())) break;
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            gameManager.removePlayer(playerName);
            try { socket.close(); } catch (IOException ignored) {}
        }
    }

    private static String processCommand(String input, String player, PrintWriter out) {
        if (input.startsWith("NAME:")) {
            String name = input.substring(5).trim();
            gameManager.registerPlayer(name);
            return "OK:NAME:" + name;
        } else if (input.startsWith("SCORE:")) {
            try {
                int score = Integer.parseInt(input.substring(6).trim());
                gameManager.updateScore(player, score);
                return "OK:SCORE:" + score;
            } catch (NumberFormatException e) {
                return "ERR:invalid score";
            }
        } else if (input.startsWith("STATUS:")) {
            String status = input.substring(7).trim();
            gameManager.setStatus(player, status);
            return "OK:STATUS:" + status;
        } else if ("LEADERBOARD".equalsIgnoreCase(input)) {
            return gameManager.getLeaderboard();
        } else if ("PLAYERS".equalsIgnoreCase(input)) {
            return "PLAYERS:" + gameManager.getPlayerCount();
        } else if ("QUIT".equalsIgnoreCase(input)) {
            return "BYE";
        } else {
            return "ACK: " + input;
        }
    }
}
