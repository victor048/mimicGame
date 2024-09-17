package com.example.mimicGame.controller;

import com.example.mimicGame.model.GameRound;
import com.example.mimicGame.model.Player;
import com.example.mimicGame.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/setPlayersCount")
    public String setPlayersCount(@RequestParam("playersCount") int playersCount) {
        gameService.setPlayersCount(playersCount);
        return "redirect:/game/addPlayers";
    }

    @GetMapping("/addPlayers")
    public String showAddPlayerForm(Model model) {
        model.addAttribute("remainingPlayers", gameService.getRemainingPlayers());
        return "addPlayers";
    }

    @PostMapping("/addPlayers")
    public String addPlayer(@RequestParam("playerName") String playerName, Model model) {
        boolean allPlayersAdded = gameService.addPlayer(playerName);
        if (allPlayersAdded) {
            return "redirect:/game/mimic";
        }
        model.addAttribute("remainingPlayers", gameService.getRemainingPlayers());
        return "addPlayers";
    }

    @GetMapping("/mimic")
    public String playRound(Model model) {
        if (gameService.isGameEnded()) {
            return "redirect:/game/ranking";
        }

        GameRound round = gameService.startNewRound();
        if (round == null) {
            return "redirect:/game/ranking";
        }

        model.addAttribute("playerName", round.getCurrentPlayer().getName());
        model.addAttribute("mimicSuggestion", round.getMimicSuggestion());
        model.addAttribute("remainingRounds", gameService.getTotalRounds() - gameService.getCurrentRound());
        model.addAttribute("players", gameService.getPlayersSortedByScore());
        return "mimic";
    }

    @PostMapping("/next")
    public String nextRound(@RequestParam(value = "winnerName", required = false) String winnerName, Model model) {
        if (winnerName != null && !winnerName.isEmpty()) {
            gameService.nextRound(winnerName);
            model.addAttribute("message", "A imitação foi acertada! Pontos adicionados.");
        } else {
            model.addAttribute("message", "Nenhum vencedor informado. A rodada será reiniciada.");
        }

        if (gameService.isGameEnded()) {
            return "redirect:/game/ranking";
        }

        return "redirect:/game/mimic";
    }

    @GetMapping("/ranking")
    public String showRanking(Model model) {
        List<Player> sortedPlayers = gameService.getPlayersSortedByScore();
        model.addAttribute("players", sortedPlayers);

        if (!sortedPlayers.isEmpty()) {
            int highestScore = sortedPlayers.get(0).getScore();
            List<String> topPlayers = sortedPlayers.stream()
                    .filter(p -> p.getScore() == highestScore)
                    .map(Player::getName)
                    .collect(Collectors.toList());

            if (topPlayers.size() > 1) {
                model.addAttribute("message", "Houve um empate entre: " + String.join(", ", topPlayers));
            } else {
                model.addAttribute("message", "Primeiro Lugar: " + topPlayers.get(0) + " - " + highestScore);
            }
        }

        return "ranking";
    }
}
