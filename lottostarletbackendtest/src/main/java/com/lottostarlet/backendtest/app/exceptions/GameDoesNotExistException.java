package com.lottostarlet.backendtest.app.exceptions;

public class GameDoesNotExistException extends Exception {

    public GameDoesNotExistException(String id) {
        super(String.format("The game with id %s does not exist!", id));
    }
}
