package com.example.mimicGame.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class GameRound {
    // Getters e Setters
    private Player currentPlayer;
    private String mimicSuggestion;

    public GameRound(Player currentPlayer, String mimicSuggestion) {
        this.currentPlayer = currentPlayer;
        this.mimicSuggestion = mimicSuggestion;
    }

    @Override
    public String toString() {
        return "GameRound{" +
                "currentPlayer=" + currentPlayer +
                ", mimicSuggestion='" + mimicSuggestion + '\'' +
                '}';
    }
}
