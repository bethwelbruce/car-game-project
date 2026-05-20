package tests;

import models.GameState;

public class GameStateTest {
    public static void run() {
        testInitialPhase();
        testReset();
        testHighScoreTracking();
        testTick();
    }

    static void testInitialPhase() {
        GameState gs = new GameState("Alice");
        TestRunner.assertEquals("Initial phase is MENU",
            GameState.Phase.MENU, gs.getPhase());
    }

    static void testReset() {
        GameState gs = new GameState("Alice");
        gs.reset(1000, 800);
        TestRunner.assertEquals("Phase is PLAYING after reset",
            GameState.Phase.PLAYING, gs.getPhase());
        TestRunner.assertTrue("Car is not null after reset", gs.getCar() != null);
    }

    static void testHighScoreTracking() {
        GameState gs = new GameState("Alice");
        gs.reset(1000, 800);
        gs.getPlayer().increaseScore(10);
        int score = gs.getPlayer().getScore();
        gs.reset(1000, 800);
        TestRunner.assertTrue("High score tracks previous score",
            gs.getHighScore() >= score);
    }

    static void testTick() {
        GameState gs = new GameState("Alice");
        TestRunner.assertEquals("Tick starts at 0", 0, gs.getTick());
        gs.tick();
        gs.tick();
        TestRunner.assertEquals("Tick increments correctly", 2, gs.getTick());
    }
}
