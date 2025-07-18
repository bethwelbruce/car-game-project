FROM openjdk:21.0.2-slim

WORKDIR /app

RUN apt-get update --no-install-recommends && \
    apt-get install -y --no-install-recommends git=1:2.39.2-1.1 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY . .

RUN javac -cp ".:sqlite-jdbc-3.50.2.0.jar" \
    models/*.java \
    database/*.java \
    server/*.java \
    tests/*.java
