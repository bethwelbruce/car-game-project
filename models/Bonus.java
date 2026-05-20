package models;

import java.awt.Color;

public class Bonus {
    public enum Type {
        SCORE, SHIELD, EXTRA_LIFE
    }

    private int x, y;
    private final int size = 20;
    private final Type type;
    private boolean active;
    private int speed;

    public Bonus(int x, int y, Type type, int speed) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.active = true;
        this.speed = speed;
    }

    public void move() {
        y += speed;
    }

    public boolean offScreen(int screenHeight) {
        return y > screenHeight;
    }

    public void collect() {
        this.active = false;
    }

    public Color getColor() {
        switch (type) {
            case SCORE: return Color.ORANGE;
            case SHIELD: return Color.CYAN;
            case EXTRA_LIFE: return Color.GREEN;
            default: return Color.WHITE;
        }
    }

    public boolean isActive() { return active; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }
    public Type getType() { return type; }
}
