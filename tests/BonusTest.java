package tests;

import models.Bonus;
import java.awt.Color;

public class BonusTest {
    public static void run() {
        testScoreBonusColor();
        testShieldBonusColor();
        testExtraLifeBonusColor();
        testBonusMoves();
        testBonusOffScreen();
        testBonusCollect();
    }

    static void testScoreBonusColor() {
        Bonus b = new Bonus(100, 100, Bonus.Type.SCORE, 5);
        TestRunner.assertEquals("Score bonus is orange", Color.ORANGE, b.getColor());
    }

    static void testShieldBonusColor() {
        Bonus b = new Bonus(100, 100, Bonus.Type.SHIELD, 5);
        TestRunner.assertEquals("Shield bonus is cyan", Color.CYAN, b.getColor());
    }

    static void testExtraLifeBonusColor() {
        Bonus b = new Bonus(100, 100, Bonus.Type.EXTRA_LIFE, 5);
        TestRunner.assertEquals("Extra life bonus is green", Color.GREEN, b.getColor());
    }

    static void testBonusMoves() {
        Bonus b = new Bonus(100, 50, Bonus.Type.SCORE, 10);
        b.move();
        TestRunner.assertEquals("Bonus moves down by speed", 60, b.getY());
    }

    static void testBonusOffScreen() {
        Bonus b = new Bonus(100, 50, Bonus.Type.SCORE, 5);
        b.move(); b.move();
        TestRunner.assertTrue("Bonus not off screen at y=60", !b.offScreen(800));
        Bonus b2 = new Bonus(100, 790, Bonus.Type.SCORE, 20);
        b2.move();
        TestRunner.assertTrue("Bonus off screen past height", b2.offScreen(800));
    }

    static void testBonusCollect() {
        Bonus b = new Bonus(100, 100, Bonus.Type.SCORE, 5);
        TestRunner.assertTrue("Bonus initially active", b.isActive());
        b.collect();
        TestRunner.assertTrue("Bonus inactive after collect", !b.isActive());
    }
}
