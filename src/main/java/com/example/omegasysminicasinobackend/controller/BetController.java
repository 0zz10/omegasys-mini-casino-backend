package com.example.omegasysminicasinobackend.controller;

import org.springframework.web.bind.annotation.*;
import com.example.omegasysminicasinobackend.model.*;
import com.example.omegasysminicasinobackend.service.GameService;

import java.util.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/bets")
/**
 * REST controller for handling bet-related operations such as placing bets
 * and retrieving bet history for players.
 */
public class BetController {
    private final GameService gameService;

    public BetController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/place")
    public String placeBet(@RequestParam String username, @RequestParam String gameId, @RequestParam double amount) {
        Player player = gameService.getPlayers().get(username);
        Game game = gameService.getGames().get(gameId);

        if (player == null || game == null) return "Invalid player or game";
        if (amount < game.getMinBet() || amount > game.getMaxBet()) return "Invalid bet amount";
        if (player.getBalance() < amount) return "Insufficient balance";

        boolean win = Math.random() < game.getChanceOfWinning();
        double winnings = win ? amount * game.getWinningMultiplier() : 0;
        player.setBalance(player.getBalance() - amount + winnings);

        Bet bet = new Bet();
        bet.setPlayerId(player.getUsername());
        bet.setGameId(gameId);
        bet.setAmount(amount);
        bet.setWin(win);
        bet.setWinnings(winnings);
        bet.setTimestamp(LocalDateTime.now());
        gameService.getBets().add(bet);

        return win ? "You won " + winnings + " EUR" : "You lost " + amount + " EUR";
    }

    @GetMapping("/summary/{username}")
    public Map<String, Object> getSummary(@PathVariable String username) {
        long count = gameService.getBets().stream().filter(b -> b.getPlayerId().equals(username)).count();
        double totalBet = gameService.getBets().stream().filter(b -> b.getPlayerId().equals(username)).mapToDouble(Bet::getAmount).sum();
        double totalWon = gameService.getBets().stream().filter(b -> b.getPlayerId().equals(username)).mapToDouble(Bet::getWinnings).sum();

        Map<String, Object> summary = new HashMap<>();
        summary.put("numberOfBets", count);
        summary.put("totalBetValue", totalBet);
        summary.put("totalWinnings", totalWon);
        return summary;
    }
}