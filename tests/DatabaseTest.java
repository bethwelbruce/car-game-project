package tests;

import database.DatabaseConnector;
import java.sql.*;
import java.io.File;

public class DatabaseTest {
    public static void run() {
        cleanup();
        testSaveScore();
        testGetTopScoresEmpty();
        testGetTopScoresAfterSave();
        testTopScoresLimit();
        testTopScoresOrdering();
        cleanup();
    }

    static void cleanup() {
        new File("car_game.db").delete();
    }

    static void testSaveScore() {
        try {
            DatabaseConnector.saveScore("Alice", 10);
            Connection conn = DatabaseConnector.connect();
            ResultSet rs = conn.createStatement().executeQuery("SELECT count(*) FROM scores");
            int count = rs.getInt(1);
            TestRunner.assertEquals("saveScore inserts one row", 1, count);
            conn.close();
        } catch (Exception e) {
            TestRunner.assertTrue("saveScore should not throw: " + e.getMessage(), false);
        }
    }

    static void testGetTopScoresEmpty() {
        cleanup();
        // Create table but insert nothing
        try {
            Connection conn = DatabaseConnector.connect();
            conn.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS scores (player TEXT, score INTEGER)");
            conn.close();
        } catch (Exception e) {}
        String result = DatabaseConnector.getTopScores();
        TestRunner.assertTrue("getTopScores returns no scores message when empty",
            result.contains("No scores yet."));
    }

    static void testGetTopScoresAfterSave() {
        cleanup();
        DatabaseConnector.saveScore("Alice", 5);
        String result = DatabaseConnector.getTopScores();
        TestRunner.assertTrue("getTopScores contains saved player name", result.contains("Alice"));
        TestRunner.assertTrue("getTopScores contains saved score", result.contains("5"));
    }

    static void testTopScoresLimit() {
        cleanup();
        for (int i = 0; i < 10; i++) {
            DatabaseConnector.saveScore("Player" + i, i);
        }
        String result = DatabaseConnector.getTopScores();
        // Should only show top 5 — count occurrences of rank numbers
        TestRunner.assertTrue("Top scores shows rank 1", result.contains("1."));
        TestRunner.assertTrue("Top scores shows rank 5", result.contains("5."));
        TestRunner.assertTrue("Top scores does not show rank 6", !result.contains("6."));
    }

    static void testTopScoresOrdering() {
        cleanup();
        DatabaseConnector.saveScore("Low", 1);
        DatabaseConnector.saveScore("High", 99);
        DatabaseConnector.saveScore("Mid", 50);
        String result = DatabaseConnector.getTopScores();
        int highPos = result.indexOf("High");
        int midPos = result.indexOf("Mid");
        int lowPos = result.indexOf("Low");
        TestRunner.assertTrue("High score appears before Mid score", highPos < midPos);
        TestRunner.assertTrue("Mid score appears before Low score", midPos < lowPos);
    }
}
