package com.example.omegasysminicasinobackend.controller;

import com.example.omegasysminicasinobackend.model.Player;
import com.example.omegasysminicasinobackend.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    private final Map<String, Player> players = new HashMap<>();

    @BeforeEach
    public void setup() {
        Player player = new Player();
        player.setUsername("alice123");
        player.setBirthdate(LocalDate.of(2000, 1, 1));
        player.setBalance(50);
        players.put("alice123", player);

        when(gameService.getPlayers()).thenReturn(players);
    }

    @Test
    public void testRegisterPlayer() throws Exception {
        Player newPlayer = new Player();
        newPlayer.setName("Alice");
        newPlayer.setUsername("newuser");
        newPlayer.setBirthdate(LocalDate.of(2000, 1, 1));

        mockMvc.perform(post("/players/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().findAndRegisterModules().writeValueAsString(newPlayer)))
                .andExpect(status().isOk())
                .andExpect(content().string("Player registered"));
    }

    @Test
    public void testGetBalance() throws Exception {
        mockMvc.perform(get("/players/balance/alice123"))
                .andExpect(status().isOk())
                .andExpect(content().string("50.0"));
    }

    @Test
    public void testDeposit() throws Exception {
        mockMvc.perform(post("/players/deposit/alice123")
                        .param("amount", "20"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deposit successful"));
    }
}