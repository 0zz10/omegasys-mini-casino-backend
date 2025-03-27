package com.example.omegasysminicasinobackend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.xml.bind.JAXBContext;
import java.util.*;
import com.example.omegasysminicasinobackend.model.*;
import com.example.omegasysminicasinobackend.service.GameService;

import jakarta.annotation.PostConstruct;
import java.util.Collection;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostConstruct
    public void initGames() {
        Game game1 = new Game();
        game1.setId("game1");
        game1.setName("Lucky Spin");
        game1.setChanceOfWinning(0.4);
        game1.setWinningMultiplier(2.5);
        game1.setMaxBet(100);
        game1.setMinBet(5);

        Game game2 = new Game();
        game2.setId("game2");
        game2.setName("Mega Dice");
        game2.setChanceOfWinning(0.3);
        game2.setWinningMultiplier(3.0);
        game2.setMaxBet(200);
        game2.setMinBet(10);

        gameService.getGames().put(game1.getId(), game1);
        gameService.getGames().put(game2.getId(), game2);
    }

    @GetMapping
    public Collection<Game> listGames() {
        return gameService.getGames().values();
    }

    @PostMapping("/upload")
    public String uploadGames(@RequestParam("file") MultipartFile file) {
        try {
            JAXBContext context = JAXBContext.newInstance(GameList.class);
            GameList gameList = (GameList) context.createUnmarshaller().unmarshal(file.getInputStream());
            for (Game game : gameList.getGames()) {
                gameService.getGames().put(game.getId(), game);
            }
            return "Uploaded " + gameList.getGames().size() + " games successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload games: " + e.getMessage();
        }
    }
}