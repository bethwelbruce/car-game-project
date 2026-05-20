package tests;

import models.TrafficCar;

public class TrafficCarTest {
    public static void run() {
        testSpawnsAboveScreen();
        testMoves();
        testOffScreenFalseWhenAbove();
        testOffScreenTrueWhenBelow();
        testWidthInRange();
        testCollision();
    }

    static void testSpawnsAboveScreen() {
        TrafficCar tc = new TrafficCar(100, 1000);
        TestRunner.assertTrue("TrafficCar spawns above screen (y < 0)", tc.getY() < 0);
    }

    static void testMoves() {
        TrafficCar tc = new TrafficCar(100, 1000);
        int initialY = tc.getY();
        int speed = tc.getSpeed();
        tc.move(speed);
        TestRunner.assertEquals("TrafficCar moves down by its own speed",
            initialY + speed, tc.getY());
    }

    static void testOffScreenFalseWhenAbove() {
        TrafficCar tc = new TrafficCar(100, 1000);
        TestRunner.assertTrue("TrafficCar not off screen when above", !tc.offScreen(800));
    }

    static void testOffScreenTrueWhenBelow() {
        TrafficCar tc = new TrafficCar(100, 1000);
        for (int i = 0; i < 200; i++) tc.move(10);
        TestRunner.assertTrue("TrafficCar off screen after moving past height",
            tc.offScreen(800));
    }

    static void testWidthInRange() {
        for (int i = 0; i < 20; i++) {
            TrafficCar tc = new TrafficCar(100, 1000);
            TestRunner.assertTrue("TrafficCar width >= 40",
                tc.getWidth() >= 40);
            TestRunner.assertTrue("TrafficCar width <= 60",
                tc.getWidth() <= 60);
        }
    }

    static void testCollision() {
        TrafficCar tc = new TrafficCar(100, 1000);
        // manually set y to 0 via moves
        boolean hit = tc.collidesWith(tc.getX(), tc.getY(), tc.getWidth(), tc.getHeight());
        TestRunner.assertTrue("TrafficCar collides with itself", hit);
        boolean miss = tc.collidesWith(500, 500, 50, 30);
        TestRunner.assertTrue("TrafficCar doesn't collide with distant rect", !miss);
    }
}
