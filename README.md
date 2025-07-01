# 🚗 Simple Java Car Game

A 2D Java car game built with Java SE, Swing, and SQLite. Features player-controlled car, traffic obstacles, bonus pickups, persistent leaderboard, and basic socket server.

## Technologies
- Java SE 24
- Swing (GUI)
- SQLite via JDBC
- Socket programming

## Project Structure
```
CarGameProject/
├── client/       # Game UI and event handling
├── server/       # Socket server
├── models/       # Car, Player, TrafficCar
├── database/     # SQLite leaderboard
└── tests/        # Unit tests
```

## How to Run
1. Start server: `java -cp ".;sqlite-jdbc-3.50.2.0.jar" server.Server`
2. Start client: `java -cp ".;sqlite-jdbc-3.50.2.0.jar" client.Client`

## How to Test
```bash
javac -cp ".:sqlite-jdbc-3.50.2.0.jar" tests/*.java models/*.java database/*.java server/*.java
java -cp ".:sqlite-jdbc-3.50.2.0.jar" tests.TestRunner
```
