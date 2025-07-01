package models;

public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void increaseScore() {
        this.score++;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
}
