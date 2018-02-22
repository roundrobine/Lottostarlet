package com.lottostarlet.backendtest.app.domainlogic;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lottostarlet.backendtest.app.model.GameHand;
import com.lottostarlet.backendtest.app.model.RoundResult;
@JsonDeserialize(as = RandomPlayer.class)
public interface TurnPlayer {

    Object getResultOfTurn();
    GameHand getGameHand();
    GameHand generateGameHand();
    void setGameHand(GameHand gameHand);
    int getPlayerNumber();
    void setResultOfTurn(RoundResult roundResult);

}
