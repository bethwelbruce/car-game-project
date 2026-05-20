package tests;

import models.Player;

public class PlayerTest {
    public static void run() {
        testInitialScore();
        testIncreaseScore();
        testMultipleIncreases();
        testGetName();
        testLives();
        testLoseLife();
        testIsAlive();
        testDifficultyHard();
        testSpeedCap();
        testIncreaseScoreByAmount();
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

    static void testLives() {
        Player p = new Player("Alice");
        TestRunner.assertEquals("Normal player starts with 3 lives",
            Player.MAX_LIVES, p.getLives());
    }

    static void testLoseLife() {
        Player p = new Player("Alice");
        p.loseLife();
        TestRunner.assertEquals("Lives decrease after loseLife",
            Player.MAX_LIVES - 1, p.getLives());
    }

    static void testIsAlive() {
        Player p = new Player("Alice");
        TestRunner.assertTrue("Player alive with lives > 0", p.isAlive());
        p.loseLife(); p.loseLife(); p.loseLife();
        TestRunner.assertTrue("Player dead after losing all lives", !p.isAlive());
    }

    static void testDifficultyHard() {
        Player p = new Player("Alice", "hard");
        TestRunner.assertEquals("Hard mode starts with 1 life", 1, p.getLives());
    }

    static void testSpeedCap() {
        Player p = new Player("Alice");
        for (int i = 0; i < 1000; i++) p.increaseScore();
        TestRunner.assertTrue("Speed is capped at 20", p.getSpeed() <= 20);
    }

    static void testIncreaseScoreByAmount() {
        Player p = new Player("Alice");
        p.increaseScore(5);
        TestRunner.assertEquals("Score increases by amount", 5, p.getScore());
    }
}
