package com.lottostarlet.backendtest.app.model;

import com.lottostarlet.backendtest.app.domainlogic.TurnPlayer;

import java.util.List;

public class Round {
    private int round;
    private List<TurnPlayer> turnPlayers;

    public Round() {}

    public Round(int round, List<TurnPlayer> turnPlayers) {
        this.round = round;
        this.turnPlayers = turnPlayers;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<TurnPlayer> getTurnPlayers() {
        return turnPlayers;
    }

    public void setTurnPlayers(List<TurnPlayer> turnPlayers) {
        this.turnPlayers = turnPlayers;
    }
}

