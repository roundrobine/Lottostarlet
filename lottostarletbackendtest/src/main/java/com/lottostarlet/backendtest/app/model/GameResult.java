package com.lottostarlet.backendtest.app.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameResult {

    @Id
    private String id;
    private List<Round> rounds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }


}
