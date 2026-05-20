package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found", e);
        }
        String url = "jdbc:sqlite:car_game.db";
        return DriverManager.getConnection(url);
    }

    public static void initDB() {
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS scores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "player TEXT NOT NULL, " +
                "score INTEGER NOT NULL, " +
                "difficulty TEXT DEFAULT 'normal', " +
                "played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
            );
        } catch (SQLException e) {
            System.err.println("Error initialising DB: " + e.getMessage());
        }
    }

    public static void saveScore(String name, int score) {
        saveScore(name, score, "normal");
    }

    public static void saveScore(String name, int score, String difficulty) {
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS scores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "player TEXT NOT NULL, " +
                "score INTEGER NOT NULL, " +
                "difficulty TEXT DEFAULT 'normal', " +
                "played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
            );
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO scores (player, score, difficulty) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.setString(3, difficulty);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }

    public static String getTopScores() {
        return getTopScores("normal");
    }

    public static String getTopScores(String difficulty) {
        StringBuilder result = new StringBuilder("Top Scores (" + difficulty + "):\n");
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT player, score FROM scores WHERE difficulty='" + difficulty +
                "' ORDER BY score DESC LIMIT 3");
            int rank = 1;
            while (rs.next()) {
                String name = rs.getString("player");
                int score = rs.getInt("score");
                result.append(rank++).append(". ").append(name)
                      .append(" - ").append(score).append("\n");
            }
            if (rank == 1) result.append("No scores yet.");
        } catch (SQLException e) {
            result.append("Error loading leaderboard: ").append(e.getMessage());
        }
        return result.toString();
    }

    public static void clearScores() {
        try (Connection conn = connect()) {
            conn.createStatement().executeUpdate("DELETE FROM scores");
        } catch (SQLException e) {
            System.err.println("Error clearing scores: " + e.getMessage());
        }
    }

    public static int getPersonalBest(String name) {
        try (Connection conn = connect()) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT MAX(score) FROM scores WHERE player = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Error getting personal best: " + e.getMessage());
        }
        return 0;
    }
}
