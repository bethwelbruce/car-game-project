package database;

import java.sql.*;

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

    public static void saveScore(String name, int score) {
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS scores (player TEXT, score INTEGER)");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO scores (player, score) VALUES (?, ?)");
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }

    public static String getTopScores() {
        StringBuilder result = new StringBuilder("Top Scores:\n");
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT player, score FROM scores ORDER BY score DESC LIMIT 5");
            int rank = 1;
            while (rs.next()) {
                String name = rs.getString("player");
                int score = rs.getInt("score");
                result.append(rank++).append(". ").append(name).append(" - ").append(score).append("\n");
            }
            if (rank == 1) result.append("No scores yet.");
        } catch (SQLException e) {
            result.append("Error loading leaderboard: ").append(e.getMessage());
        }
        return result.toString();
    }
}
