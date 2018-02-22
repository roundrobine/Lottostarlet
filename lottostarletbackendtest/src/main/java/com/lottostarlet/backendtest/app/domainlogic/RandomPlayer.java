package com.lottostarlet.backendtest.app.domainlogic;

import com.lottostarlet.backendtest.app.model.GameHand;
import com.lottostarlet.backendtest.app.model.RoundResult;

public class RandomPlayer implements TurnPlayer {

    private int playerNumber;
    private GameHand gameHand;
    private RoundResult resultOfTurn;

    public RandomPlayer() {
    }

    public RandomPlayer(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public RandomPlayer(RandomPlayer original) {
        this.playerNumber = original.getPlayerNumber();
        this.gameHand = original.getGameHand();
        this.resultOfTurn = original.getResultOfTurn();
    }

    @Override
    public GameHand generateGameHand(){
        gameHand = GameHand.getRandomHand();
        return gameHand;
    }

    @Override
    public int getPlayerNumber(){
        return playerNumber;
    }

    @Override
    public void setGameHand(GameHand gameHand) {
        this.gameHand = gameHand;
    }

    @Override
    public GameHand getGameHand() {
        return gameHand;
    }

    @Override
    public String toString() {
        return "Player " + playerNumber;
    }

    @Override
    public RoundResult getResultOfTurn() {
        return resultOfTurn;
    }

    @Override
    public void setResultOfTurn(RoundResult resultOfTurn) {
        this.resultOfTurn = resultOfTurn;
    }
}
