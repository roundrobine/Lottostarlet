package com.lottostarlet.backendtest.model;

import com.lottostarlet.backendtest.app.model.GameHand;
import org.junit.Assert;
import org.junit.Test;

public class RockGameHandTest {
    private GameHand gameHand = GameHand.ROCK;

    @Test
    public void testPaperIsBetterThanPaper() {
        Assert.assertFalse("Rock is weaker than Paper", gameHand.isBetterThan(GameHand.PAPER));
    }

    @Test
    public void testPaperIsBetterThanScissors() {
        Assert.assertTrue("Rock is better than Scissors", gameHand.isBetterThan(GameHand.SCISSORS));
    }

    @Test
    public void testPaperIsBetterThanRock() {
        Assert.assertFalse("Rock is not better than Rock", gameHand.isBetterThan(GameHand.ROCK));
    }

}
