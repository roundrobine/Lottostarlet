package com.lottostarlet.backendtest;

import com.lottostarlet.backendtest.app.domainlogic.BackendService;
import com.lottostarlet.backendtest.app.model.GameHand;
import com.lottostarlet.backendtest.app.model.RoundResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BackendServiceTest {

    @Parameterized.Parameters(name = "{index}: {0} vs. {1}")
    public static Collection<Object[]> data() {
        Object[][] test = new Object[][] {
                {GameHand.ROCK,		Arrays.asList(GameHand.ROCK),       RoundResult.TIE},
                {GameHand.ROCK,		Arrays.asList(GameHand.PAPER),		RoundResult.LOOSE},
                {GameHand.ROCK,		Arrays.asList(GameHand.SCISSORS),	RoundResult.WIN},
                {GameHand.PAPER,	Arrays.asList(GameHand.ROCK),		RoundResult.WIN},
                {GameHand.PAPER,	Arrays.asList(GameHand.PAPER),		RoundResult.TIE},
                {GameHand.PAPER,	Arrays.asList(GameHand.SCISSORS),	RoundResult.LOOSE},
                {GameHand.SCISSORS, Arrays.asList(GameHand.ROCK),		RoundResult.LOOSE},
                {GameHand.SCISSORS, Arrays.asList(GameHand.PAPER),		RoundResult.WIN},
                {GameHand.SCISSORS, Arrays.asList(GameHand.SCISSORS),	RoundResult.TIE},
        };
        return Arrays.asList(test);
    }

    private GameHand playerOne;
    private List<GameHand> otherPlayers;
    private RoundResult expectedResult;

    public BackendServiceTest(GameHand playerOne, List<GameHand> otherPlayers, RoundResult expectedResult) {
        this.playerOne = playerOne;
        this.otherPlayers = otherPlayers;
        this.expectedResult = expectedResult;
    }


    @Test
    public void testEvaluateHandsMethod() {
        BackendService service = new BackendService(9, 2);
        RoundResult actualResult = service.evaluateHands(playerOne, otherPlayers);
        assertEquals(expectedResult, actualResult);
    }

}
