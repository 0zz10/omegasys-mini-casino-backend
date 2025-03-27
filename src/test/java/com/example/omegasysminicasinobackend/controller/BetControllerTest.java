package com.example.omegasysminicasinobackend.controller;

import com.example.omegasysminicasinobackend.model.*;
import com.example.omegasysminicasinobackend.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BetController.class)
public class BetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    private final Map<String, Player> players = new HashMap<>();
    private final Map<String, Game> games = new HashMap<>();

    @BeforeEach
    public void setup() {
        Player player = new Player();
        player.setUsername("alice123");
        player.setBalance(100);
        player.setBirthdate(LocalDate.of(2000, 1, 1));
        players.put("alice123", player);

        Game game = new Game();
        game.setId("game1");
        game.setName("Test Game");
        game.setChanceOfWinning(1.0); // Ensure win
        game.setWinningMultiplier(2.0);
        game.setMaxBet(100);
        game.setMinBet(10);
        games.put("game1", game);

        when(gameService.getPlayers()).thenReturn(players);
        when(gameService.getGames()).thenReturn(games);
        when(gameService.getBets()).thenReturn(new ArrayList<>());
    }

    @Test
    public void testPlaceBet() throws Exception {
        mockMvc.perform(post("/bets/place")
                        .param("username", "alice123")
                        .param("gameId", "game1")
                        .param("amount", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("You won")));
    }

    @Test
    public void testBetSummary() throws Exception {
        mockMvc.perform(get("/bets/summary/alice123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfBets").value(0))
                .andExpect(jsonPath("$.totalBetValue").value(0.0))
                .andExpect(jsonPath("$.totalWinnings").value(0.0));
    }
}