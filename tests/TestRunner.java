package tests;

public class TestRunner {

    static int passed = 0;
    static int failed = 0;

    public static void assertTrue(String name, boolean condition) {
        if (condition) {
            System.out.println("PASS: " + name);
            passed++;
        } else {
            System.out.println("FAIL: " + name);
            failed++;
        }
    }

    public static void assertEquals(String name, Object expected, Object actual) {
        if (expected.equals(actual)) {
            System.out.println("PASS: " + name);
            passed++;
        } else {
            System.out.println("FAIL: " + name + " expected=" + expected + " actual=" + actual);
            failed++;
        }
    }

    public static void main(String[] args) {
        PlayerTest.run();
        CarTest.run();
        TrafficCarTest.run();
        DatabaseTest.run();

        System.out.println("\n=== Results: " + passed + " passed, " + failed + " failed ===");
        System.exit(failed > 0 ? 1 : 0);
    }
}
