package com.lottostarlet.backendtest.model;

import com.lottostarlet.backendtest.app.model.GameHand;
import org.junit.Assert;
import org.junit.Test;

public class PaperGameHandTest {
    private GameHand gameHand = GameHand.PAPER;

    @Test
    public void testPaperIsBetterThanPaper() {
        Assert.assertFalse("Paper is not better than Paper", gameHand.isBetterThan(GameHand.PAPER));
    }

    @Test
    public void testPaperIsBetterThanScissors() {
        Assert.assertFalse("Paper is weaker than Scissors", gameHand.isBetterThan(GameHand.SCISSORS));
    }

    @Test
    public void testPaperIsBetterThanRock() {
        Assert.assertTrue("Paper is better than Rock", gameHand.isBetterThan(GameHand.ROCK));
    }

}
