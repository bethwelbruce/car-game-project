package models;

public class Car {
    private int x, y;
    private final int width = 50;
    private final int height = 30;
    private boolean shielded = false;
    private int shieldTimer = 0;
    private static final int SHIELD_DURATION = 100;

    public Car(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy, int screenWidth, int screenHeight) {
        x += dx;
        y += dy;

        int minX = screenWidth / 10;
        int maxX = screenWidth * 8 / 10 - width;
        int minY = 0;
        int maxY = screenHeight - height;

        if (x < minX) x = minX;
        if (x > maxX) x = maxX;
        if (y < minY) y = minY;
        if (y > maxY) y = maxY;
    }

    public void activateShield() {
        this.shielded = true;
        this.shieldTimer = SHIELD_DURATION;
    }

    public void tickShield() {
        if (shielded && --shieldTimer <= 0) {
            shielded = false;
            shieldTimer = 0;
        }
    }

    public boolean isShielded() { return shielded; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
