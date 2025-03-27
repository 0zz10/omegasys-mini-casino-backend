package com.example.omegasysminicasinobackend.controller;

import org.springframework.web.bind.annotation.*;
import com.example.omegasysminicasinobackend.model.Player;
import com.example.omegasysminicasinobackend.service.GameService;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final GameService gameService;

    public PlayerController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Player player) {
        if (gameService.getPlayers().containsKey(player.getUsername())) {
            return "Username already exists";
        }
        if (LocalDate.now().minusYears(18).isBefore(player.getBirthdate())) {
            return "Must be at least 18 years old";
        }
        player.setBalance(0);
        gameService.getPlayers().put(player.getUsername(), player);
        return "Player registered";
    }

    @GetMapping("/balance/{username}")
    public double getBalance(@PathVariable String username) {
        return gameService.getPlayers().get(username).getBalance();
    }

    @PostMapping("/deposit/{username}")
    public String deposit(@PathVariable String username, @RequestParam double amount) {
        Player player = gameService.getPlayers().get(username);
        player.setBalance(player.getBalance() + amount);
        return "Deposit successful";
    }
}