package tests;

import models.Player;

public class PlayerTest {
    public static void run() {
        testInitialScore();
        testIncreaseScore();
        testMultipleIncreases();
        testGetName();
    }

    static void testInitialScore() {
        Player p = new Player("Alice");
        TestRunner.assertEquals("Player initial score is 0", 0, p.getScore());
    }

    static void testIncreaseScore() {
        Player p = new Player("Alice");
        p.increaseScore();
        TestRunner.assertEquals("Score increases to 1 after one call", 1, p.getScore());
    }

    static void testMultipleIncreases() {
        Player p = new Player("Alice");
        for (int i = 0; i < 5; i++) p.increaseScore();
        TestRunner.assertEquals("Score is 5 after 5 increases", 5, p.getScore());
    }

    static void testGetName() {
        Player p = new Player("Bob");
        TestRunner.assertEquals("Player name is Bob", "Bob", p.getName());
    }
}
