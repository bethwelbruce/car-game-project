package models;

import java.awt.*;
import java.util.Random;

public class TrafficCar {
    private int x, y;
    private final int width;
    private final int height = 30;
    private final Color color;
    private int speed;
    private boolean isTruck;

    public TrafficCar(int laneX, int screenWidth) {
        this.x = laneX;
        this.y = -30;
        this.isTruck = new Random().nextInt(5) == 0;
        this.width = isTruck ? 60 : 40 + new Random().nextInt(20);
        this.speed = isTruck ? 2 : 3 + new Random().nextInt(3);
        this.color = isTruck ? Color.DARK_GRAY : randomColor();
    }

    public TrafficCar(int laneX, int screenWidth, int baseSpeed) {
        this(laneX, screenWidth);
        if (!isTruck) this.speed = baseSpeed + new Random().nextInt(3);
    }

    public void move(int externalSpeed) {
        y += speed;
    }

    public void accelerate(int amount) {
        this.speed = Math.min(speed + amount, 15);
    }

    public boolean offScreen(int screenHeight) {
        return y > screenHeight;
    }

    public boolean collidesWith(int cx, int cy, int cw, int ch) {
        return x < cx + cw && x + width > cx && y < cy + ch && y + height > cy;
    }

    public Color getColor() { return color; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isTruck() { return isTruck; }
    public int getSpeed() { return speed; }

    private Color randomColor() {
        Color[] colors = {
            Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN,
            Color.MAGENTA, Color.GREEN, Color.ORANGE, Color.PINK
        };
        return colors[new Random().nextInt(colors.length)];
    }
}
