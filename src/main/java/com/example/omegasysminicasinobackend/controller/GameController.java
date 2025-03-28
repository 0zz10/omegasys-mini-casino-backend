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
        game1.setMinBet(1);
        game1.setImageUrl("https://store-images.s-microsoft.com/image/apps.20111.14611647304108263.3c4a2624-706b-453c-ad28-8b58f73e9446.0c0084bc-c0eb-4ddb-8802-8b28a44b346b");

        Game game2 = new Game();
        game2.setId("game2");
        game2.setName("Mega Dice");
        game2.setChanceOfWinning(0.3);
        game2.setWinningMultiplier(3.0);
        game2.setMaxBet(200);
        game2.setMinBet(5);
        game2.setImageUrl("https://www.olg.ca/content/dam/olg/web/product/lottery/product/mega-dice/mega-dice.png");

        Game game3 = new Game();
        game3.setId("game3");
        game3.setName("Roulette Royale");
        game3.setChanceOfWinning(0.2);
        game3.setWinningMultiplier(5.0);
        game3.setMaxBet(150);
        game3.setMinBet(5);
        game3.setImageUrl("https://gan-games.s3.amazonaws.com/1457974301.e13270c44651b70fd629cb0d6414188a.jpg");

        Game game4 = new Game();
        game4.setId("game4");
        game4.setName("Slot Blast");
        game4.setChanceOfWinning(0.5);
        game4.setWinningMultiplier(2.0);
        game4.setMaxBet(75);
        game4.setMinBet(1);
        game4.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaWJbCL5Rpb1-5JVcbMdzfFLy5o9WjH30H3Q&s");

        Game game5 = new Game();
        game5.setId("game5");
        game5.setName("Poker King");
        game5.setChanceOfWinning(0.35);
        game5.setWinningMultiplier(2.8);
        game5.setMaxBet(120);
        game5.setMinBet(10);
        game5.setImageUrl("https://is5-ssl.mzstatic.com/image/thumb/Purple127/v4/97/ed/c7/97edc728-b763-73dc-d7fe-a753c7e7d050/source/512x512bb.jpg");

        Game game6 = new Game();
        game6.setId("game6");
        game6.setName("Blackjack Pro");
        game6.setChanceOfWinning(0.45);
        game6.setWinningMultiplier(2.2);
        game6.setMaxBet(90);
        game6.setMinBet(5);
        game6.setImageUrl("https://casino.betmgm.com/en/blog/wp-content/uploads/2024/01/premium-blackjack-pro.jpg");

        Game game7 = new Game();
        game7.setId("game7");
        game7.setName("Jackpot Wheel");
        game7.setChanceOfWinning(0.25);
        game7.setWinningMultiplier(4.0);
        game7.setMaxBet(100);
        game7.setMinBet(5);
        game7.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSLe-SJ-q_prHUgcgzXsqlT7GXP5exSjOl5Q&s");

        gameService.getGames().put(game1.getId(), game1);
        gameService.getGames().put(game2.getId(), game2);
        gameService.getGames().put(game3.getId(), game3);
        gameService.getGames().put(game4.getId(), game4);
        gameService.getGames().put(game5.getId(), game5);
        gameService.getGames().put(game6.getId(), game6);
        gameService.getGames().put(game7.getId(), game7);
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