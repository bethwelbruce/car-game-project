package tests;

import models.Car;

public class CarTest {
    public static void run() {
        testInitialPosition();
        testMoveRight();
        testMoveLeft();
        testMoveUp();
        testMoveDown();
        testLeftBoundary();
        testRightBoundary();
        testTopBoundary();
        testBottomBoundary();
    }

    static void testInitialPosition() {
        Car car = new Car(100, 200);
        TestRunner.assertEquals("Car initial x", 100, car.getX());
        TestRunner.assertEquals("Car initial y", 200, car.getY());
    }

    static void testMoveRight() {
        Car car = new Car(200, 200);
        car.move(50, 0, 1000, 800);
        TestRunner.assertEquals("Car moves right", 250, car.getX());
    }

    static void testMoveLeft() {
        Car car = new Car(200, 200);
        car.move(-50, 0, 1000, 800);
        TestRunner.assertEquals("Car moves left", 150, car.getX());
    }

    static void testMoveUp() {
        Car car = new Car(200, 200);
        car.move(0, -50, 1000, 800);
        TestRunner.assertEquals("Car moves up", 150, car.getY());
    }

    static void testMoveDown() {
        Car car = new Car(200, 200);
        car.move(0, 50, 1000, 800);
        TestRunner.assertEquals("Car moves down", 250, car.getY());
    }

    static void testLeftBoundary() {
        Car car = new Car(200, 200);
        car.move(-500, 0, 1000, 800); // try to go far left
        int minX = 1000 / 10; // = 100
        TestRunner.assertTrue("Car stays within left boundary", car.getX() >= minX);
    }

    static void testRightBoundary() {
        Car car = new Car(200, 200);
        car.move(5000, 0, 1000, 800); // try to go far right
        int maxX = 1000 * 9 / 10 - car.getWidth(); // = 850
        TestRunner.assertTrue("Car stays within right boundary", car.getX() <= maxX);
    }

    static void testTopBoundary() {
        Car car = new Car(200, 200);
        car.move(0, -5000, 1000, 800);
        TestRunner.assertTrue("Car stays within top boundary", car.getY() >= 0);
    }

    static void testBottomBoundary() {
        Car car = new Car(200, 200);
        car.move(0, 5000, 1000, 800);
        int maxY = 800 - car.getHeight();
        TestRunner.assertTrue("Car stays within bottom boundary", car.getY() <= maxY);
    }
}
