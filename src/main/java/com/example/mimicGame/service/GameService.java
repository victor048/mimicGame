package com.example.mimicGame.service;

import com.example.mimicGame.model.GameRound;
import com.example.mimicGame.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private List<Player> players = new ArrayList<>();
    private int remainingPlayers;
    private int totalRounds = 10;
    private int currentRound = 0;
    private GameRound currentGameRound;

    public void setPlayersCount(int playersCount) {
        this.remainingPlayers = playersCount;
        this.players.clear();
        this.currentRound = 0;
    }

    public int getRemainingPlayers() {
        return remainingPlayers;
    }

    public boolean addPlayer(String playerName) {
        if (remainingPlayers > 0) {
            players.add(new Player(playerName));
            remainingPlayers--;
            return remainingPlayers == 0;
        }
        return false;
    }

    public GameRound startNewRound() {
        if (currentRound >= totalRounds) {
            return null;
        }

        currentRound++;
        Player currentPlayer = getNextPlayer();
        String mimicSuggestion = generateMimicSuggestion();
        currentGameRound = new GameRound(currentPlayer, mimicSuggestion);
        return currentGameRound;
    }

    public boolean nextRound(String winnerName) {
        if (winnerName != null && !winnerName.isEmpty()) {
            addPointsToPlayer(winnerName, 10);
        }

        if (currentRound >= totalRounds) {
            return true;
        }

        return startNewRound() == null;
    }

    public boolean isGameEnded() {
        return currentRound >= totalRounds;
    }

    public List<Player> getPlayersSortedByScore() {
        return players.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .toList();
    }

    public void addPointsToPlayer(String playerName, int points) {
        for (Player player : players) {
            if (player.getName() != null && player.getName().equals(playerName)) {
                player.setScore(player.getScore() + points);
                break;
            }
        }
    }

    private Player getNextPlayer() {
        Random random = new Random();
        return players.get(random.nextInt(players.size()));
    }

    private String generateMimicSuggestion() {
        List<String> suggestions = List.of(
                "Imite um animal",
                "Imite um personagem famoso",
                "Imite uma situação engraçada",
                "Imite um som estranho"
        );
        Random random = new Random();
        return suggestions.get(random.nextInt(suggestions.size()));
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }
}
