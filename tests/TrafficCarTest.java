package tests;

import models.TrafficCar;

public class TrafficCarTest {
    public static void run() {
        testSpawnsAboveScreen();
        testMoves();
        testOffScreenFalseWhenAbove();
        testOffScreenTrueWhenBelow();
        testWidthInRange();
    }

    static void testSpawnsAboveScreen() {
        TrafficCar tc = new TrafficCar(100, 1000);
        TestRunner.assertTrue("TrafficCar spawns above screen (y < 0)", tc.getY() < 0);
    }

    static void testMoves() {
        TrafficCar tc = new TrafficCar(100, 1000);
        int initialY = tc.getY();
        tc.move(10);
        TestRunner.assertEquals("TrafficCar moves down by speed", initialY + 10, tc.getY());
    }

    static void testOffScreenFalseWhenAbove() {
        TrafficCar tc = new TrafficCar(100, 1000);
        TestRunner.assertTrue("TrafficCar not off screen when above", !tc.offScreen(800));
    }

    static void testOffScreenTrueWhenBelow() {
        TrafficCar tc = new TrafficCar(100, 1000);
        tc.move(1000);
        TestRunner.assertTrue("TrafficCar off screen after moving past height", tc.offScreen(800));
    }

    static void testWidthInRange() {
        for (int i = 0; i < 20; i++) {
            TrafficCar tc = new TrafficCar(100, 1000);
            TestRunner.assertTrue("TrafficCar width in range 40-59",
                tc.getWidth() >= 40 && tc.getWidth() < 60);
        }
    }
}
