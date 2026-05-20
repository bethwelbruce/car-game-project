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
        testPersonalBest();
        testDifficultyFilter();
        cleanup();
    }

    static void cleanup() {
        new File("car_game.db").delete();
    }

    static void testSaveScore() {
        cleanup();
        DatabaseConnector.saveScore("Alice", 10);
        try {
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
        DatabaseConnector.initDB();
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
        for (int i = 0; i < 10; i++) DatabaseConnector.saveScore("Player" + i, i * 10);
        String result = DatabaseConnector.getTopScores();
        TestRunner.assertTrue("Top scores shows rank 1", result.contains("1."));
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
        TestRunner.assertTrue("High score appears before Mid", highPos < midPos);
    }

    static void testPersonalBest() {
        cleanup();
        DatabaseConnector.saveScore("Alice", 10);
        DatabaseConnector.saveScore("Alice", 50);
        DatabaseConnector.saveScore("Alice", 30);
        int best = DatabaseConnector.getPersonalBest("Alice");
        TestRunner.assertEquals("Personal best returns max score", 50, best);
    }

    static void testDifficultyFilter() {
        cleanup();
        DatabaseConnector.saveScore("Alice", 10, "normal");
        DatabaseConnector.saveScore("Bob", 20, "hard");
        String normal = DatabaseConnector.getTopScores("normal");
        String hard = DatabaseConnector.getTopScores("hard");
        TestRunner.assertTrue("Normal leaderboard contains Alice", normal.contains("Alice"));
        TestRunner.assertTrue("Hard leaderboard contains Bob", hard.contains("Bob"));
        TestRunner.assertTrue("Normal leaderboard does not contain Bob", !normal.contains("Bob"));
    }
}
