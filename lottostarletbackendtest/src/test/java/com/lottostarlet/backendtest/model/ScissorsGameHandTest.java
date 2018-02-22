package com.lottostarlet.backendtest.model;

import com.lottostarlet.backendtest.app.model.GameHand;
import org.junit.Assert;
import org.junit.Test;

public class ScissorsGameHandTest {
    private GameHand gameHand = GameHand.SCISSORS;

    @Test
    public void testPaperIsBetterThanPaper() {
        Assert.assertTrue("Scissors are better than Paper", gameHand.isBetterThan(GameHand.PAPER));
    }

    @Test
    public void testPaperIsBetterThanScissors() {
        Assert.assertFalse("Scissors are not better than Scissors", gameHand.isBetterThan(GameHand.SCISSORS));
    }

    @Test
    public void testPaperIsBetterThanRock() {
        Assert.assertFalse("Scissors are weaker than Rock", gameHand.isBetterThan(GameHand.ROCK));
    }

}
