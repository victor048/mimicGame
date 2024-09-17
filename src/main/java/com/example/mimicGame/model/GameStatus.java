package com.example.mimicGame.model;

import lombok.Data;

@Data
public class GameStatus {
    private int totalRounds;
    private int currentRound;
    private boolean gameEnded;

    public GameStatus(int totalRounds) {
        this.totalRounds = totalRounds;
        this.currentRound = 0;
        this.gameEnded = false;
    }

    public void incrementRound() {
        currentRound++;
        if (currentRound >= totalRounds) {
            gameEnded = true;
        }
    }
}
