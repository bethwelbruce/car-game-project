package models;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    public enum Phase { MENU, PLAYING, PAUSED, GAME_OVER }

    private Phase phase;
    private Player player;
    private Car car;
    private List<TrafficCar> trafficCars;
    private List<Bonus> bonuses;
    private int tick;
    private int highScore;

    public GameState(String playerName) {
        this.phase = Phase.MENU;
        this.player = new Player(playerName);
        this.trafficCars = new ArrayList<>();
        this.bonuses = new ArrayList<>();
        this.tick = 0;
        this.highScore = 0;
    }

    public void reset(int screenWidth, int screenHeight) {
        if (player.getScore() > highScore) highScore = player.getScore();
        this.player = new Player(player.getName(), player.getDifficulty());
        this.car = new Car(screenWidth / 2 - 25, screenHeight - 100);
        this.trafficCars.clear();
        this.bonuses.clear();
        this.tick = 0;
        this.phase = Phase.PLAYING;
    }

    public void tick() { this.tick++; }
    public void setPhase(Phase phase) { this.phase = phase; }

    public Phase getPhase() { return phase; }
    public Player getPlayer() { return player; }
    public Car getCar() { return car; }
    public List<TrafficCar> getTrafficCars() { return trafficCars; }
    public List<Bonus> getBonuses() { return bonuses; }
    public int getTick() { return tick; }
    public int getHighScore() { return highScore; }
    public boolean isPlaying() { return phase == Phase.PLAYING; }
}
