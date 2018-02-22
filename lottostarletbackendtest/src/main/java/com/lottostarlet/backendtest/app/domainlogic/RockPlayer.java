package com.lottostarlet.backendtest.app.domainlogic;

import com.lottostarlet.backendtest.app.model.GameHand;

public class RockPlayer extends RandomPlayer {

    public RockPlayer(int playerNumber) {
        super(playerNumber);
    }

    @Override
    public GameHand generateGameHand(){
        super.setGameHand(GameHand.ROCK);
        return GameHand.ROCK;
    }
}
