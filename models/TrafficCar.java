package models;

import java.awt.*;
import java.util.Random;

public class TrafficCar {
    private int x, y;
    private final int width;
    private final int height = 30;
    private final Color color;

    public TrafficCar(int laneX, int screenWidth) {
        this.x = laneX;
        this.y = -30;
        this.width = 40 + new Random().nextInt(20);
        this.color = randomColor();
    }

    public void move(int speed) { y += speed; }
    public boolean offScreen(int screenHeight) { return y > screenHeight; }
    public Color getColor() { return color; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private Color randomColor() {
        Color[] colors = {
            Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN,
            Color.MAGENTA, Color.GREEN, Color.ORANGE, Color.PINK
        };
        return colors[new Random().nextInt(colors.length)];
    }
}
