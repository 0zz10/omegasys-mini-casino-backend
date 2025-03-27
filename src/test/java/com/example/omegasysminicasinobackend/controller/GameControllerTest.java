package com.example.omegasysminicasinobackend.controller;

import com.example.omegasysminicasinobackend.model.*;
import com.example.omegasysminicasinobackend.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @BeforeEach
    public void setup() {
        Game game = new Game();
        game.setId("game1");
        game.setName("Test Game");
        game.setChanceOfWinning(0.5);
        game.setWinningMultiplier(2.0);
        game.setMaxBet(100);
        game.setMinBet(5);
        when(gameService.getGames()).thenReturn(Map.of("game1", game));
    }

    @Test
    public void testListGames() throws Exception {
        mockMvc.perform(get("/games"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("game1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test Game"));
    }
}