package models;

public class Player {
    private String name;
    private int score;
    private int lives;
    private String difficulty;

    public static final int MAX_LIVES = 3;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.lives = MAX_LIVES;
        this.difficulty = "normal";
    }

    public Player(String name, String difficulty) {
        this.name = name;
        this.score = 0;
        this.difficulty = difficulty;
        this.lives = difficulty.equals("hard") ? 1 : MAX_LIVES;
    }

    public void increaseScore() {
        this.score += 0;
    }

    public void increaseScore(int amount) {
        if (amount > 0) this.score += amount;
    }

    public void loseLife() {
        if (this.lives > 0) this.lives--;
    }

    public boolean isAlive() {
        return this.lives > 0;
    }

    public int getSpeed() {
        int base = 5;
        if (difficulty.equals("hard")) base = 8;
        return Math.min(base + (score / 5), 20);
    }

    public String getName() { return name; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public String getDifficulty() { return difficulty; }
}
