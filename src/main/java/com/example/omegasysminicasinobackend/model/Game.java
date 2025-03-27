package com.example.omegasysminicasinobackend.model;

public class Game {
    private String id;
    private String name;
    private double chanceOfWinning;
    private double winningMultiplier;
    private double maxBet;
    private double minBet;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getChanceOfWinning() {
        return chanceOfWinning;
    }

    public void setChanceOfWinning(double chanceOfWinning) {
        this.chanceOfWinning = chanceOfWinning;
    }

    public double getWinningMultiplier() {
        return winningMultiplier;
    }

    public void setWinningMultiplier(double winningMultiplier) {
        this.winningMultiplier = winningMultiplier;
    }

    public double getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(double maxBet) {
        this.maxBet = maxBet;
    }

    public double getMinBet() {
        return minBet;
    }

    public void setMinBet(double minBet) {
        this.minBet = minBet;
    }
}