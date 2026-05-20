# 🚗 Java Car Game

A 2D car game I built using Java Swing and SQLite. You control a car dodging traffic, collecting bonus pickups to rack up your score — which gets saved to a local leaderboard.

---

## Features

- Arrow key controls to move the car in all directions
- Randomly spawning traffic cars as obstacles
- Orange bonus pickups that increase your score
- Speed increases as your score gets higher — harder over time
- SQLite-backed leaderboard that saves your score after each game
- Lightweight socket server running alongside the game client

---

## Tech Stack

- **Java SE** — core language
- **Swing** — game rendering and UI
- **SQLite + JDBC** — local score persistence
- **Java Sockets** — basic client-server communication

---

## Project Structure

```
CarGameProject/
├── client/           # Game panel, input handling, rendering
├── server/           # Socket server
├── models/           # Car, TrafficCar, Player
├── database/         # SQLite connector and leaderboard
├── tests/            # Unit tests (no framework, plain Java)
└── Dockerfile        # Container build
```

---

## How to Run

**1. Start the server:**
```bash
java -cp ".:sqlite-jdbc-3.50.2.0.jar" server.Server
```

**2. Start the game:**
```bash
java -cp ".:sqlite-jdbc-3.50.2.0.jar" client.Client
```

Or on Windows use the included `run_game.bat`.

---

## Running Tests

```bash
javac -cp ".:sqlite-jdbc-3.50.2.0.jar" models/*.java database/*.java server/*.java tests/*.java
java -cp ".:sqlite-jdbc-3.50.2.0.jar" tests.TestRunner
```

---

## How to Play

- **Arrow keys** — move your car
- **Avoid** the coloured traffic cars coming down the road
- **Collect** orange circles for points
- Your score is saved automatically when the game ends

---

## Leaderboard

After each game, your score is saved to a local SQLite database (`car_game.db`). The top 5 scores are shown at game over.

---

## Changelog

### v1.3 (Aug 2025)
- Added `GameState` model to centralise phase management
- Added `Bonus` model with SCORE, SHIELD, EXTRA_LIFE types
- Expanded test suite to cover new models

### v1.2 (Jul 2025)
- Added difficulty system (normal / hard) to Player
- Added lives mechanic — lose a life on collision
- Added truck variant to TrafficCar with lower speed
- Added shield powerup to Car
- Refactored Server with full command protocol (NAME, SCORE, STATUS, LEADERBOARD)
- Implemented GameManager with live leaderboard and concurrent player tracking
- Added `clearScores()` and `getPersonalBest()` to DatabaseConnector
- Added difficulty filter to leaderboard queries

### v1.1 (Jul 2025)
- Added unit tests for all models and database layer
- Added Dockerfile for containerised builds

### v1.0 (Jul 2025)
- Initial release: Car, Player, TrafficCar models
- SQLite leaderboard
- Basic socket server
- Swing game UI
