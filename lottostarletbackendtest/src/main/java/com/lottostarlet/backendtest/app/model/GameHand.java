package com.lottostarlet.backendtest.app.model;

import java.util.Random;

/**
 * Set of possible hands in this game
 *
 * @author roundrobine
 *
 */
public enum GameHand {

    ROCK {
        @Override
        public boolean isBetterThan(GameHand hand) {
            return (SCISSORS == hand);
        }
    },

    PAPER {
        @Override
        public boolean isBetterThan(GameHand hand) {
            return (ROCK == hand);
        }
    },

    SCISSORS {
        @Override
        public boolean isBetterThan(GameHand hand) {
            return (PAPER == hand);
        }
    };

    public abstract boolean isBetterThan(GameHand hand);

    public static final GameHand[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static GameHand getRandomHand()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }

}
