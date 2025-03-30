package com.example.omegasysminicasinobackend.service;

import com.example.omegasysminicasinobackend.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
/**
 * Service layer for managing game-related logic,
 * such as retrieving all games or a game by its ID.
 */
public class GameService {
    private final Map<String, Player> players = new HashMap<>();
    private final Map<String, Game> games = new HashMap<>();
    private final List<Bet> bets = new ArrayList<>();

    public Map<String, Player> getPlayers() {
        return players;
    }

    public Map<String, Game> getGames() {
        return games;
    }

    public List<Bet> getBets() {
        return bets;
    }
}
