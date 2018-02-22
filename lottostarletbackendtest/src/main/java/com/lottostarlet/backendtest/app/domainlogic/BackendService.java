package com.lottostarlet.backendtest.app.domainlogic;

import com.lottostarlet.backendtest.app.model.GameHand;
import com.lottostarlet.backendtest.app.model.Round;
import com.lottostarlet.backendtest.app.model.RoundResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BackendService {

    private int roundsToPlay;
    private int numberOfPlayers;
    private List<TurnPlayer> players;
    private static final int NUMBER_OF_ROUNDS = 100;
    private static final int NUMBER_OF_PLAYERS = 3;
    private static final int MIN_NUMBER_OF_PLAYERS = 2;

    public BackendService() {
        init(NUMBER_OF_ROUNDS, NUMBER_OF_PLAYERS);
    }

    public BackendService(int numberOfRounds, int numberOfPlayers) {
        init(numberOfRounds, numberOfPlayers);
    }

    /**
     * Initialize number of rounds and number of players
     */
    private void init(int numberOfRounds, int numberOfPlayers) {
        if(numberOfPlayers < MIN_NUMBER_OF_PLAYERS){
            throw new IllegalArgumentException("Minimum two players can play the game.");
        }
        this.roundsToPlay = numberOfRounds;
        this.numberOfPlayers = numberOfPlayers;
        initPlayers();
    }

    /**
     * Initialize players
     */
    private void initPlayers() {
        players = new ArrayList<>();
        TurnPlayer rockPlayer = new RockPlayer(1);
        players.add(rockPlayer);
        for (int i = 1; i < numberOfPlayers; i++) {
            TurnPlayer randomPlayer = new RandomPlayer(i+1);
            players.add(randomPlayer);
        }
    }

    /**
     * Simulate game play i.e. 100 rounds of "Rock Paper Scissors" played
     *
     * @return list of all played {@link Round}s
     */
    public List<Round> playGame() {
        int currentRound = 0;
        List<Round> roundArr = new ArrayList<>();

        while(currentRound < roundsToPlay) {
            ++currentRound;
            for (TurnPlayer turnPlayer : this.players) {
                GameHand playerHand = turnPlayer.generateGameHand();
                turnPlayer.setGameHand(playerHand);
            }

            for (TurnPlayer player: this.players) {
                List<GameHand> otherPlayersHands = this.players.stream()
                        .filter(p -> p.getPlayerNumber() != player.getPlayerNumber())
                        .map(TurnPlayer::getGameHand)
                        .collect(Collectors.toList());
                RoundResult resultOfTurn = evaluateHands(player.getGameHand(), otherPlayersHands);
                player.setResultOfTurn(resultOfTurn);
            }

            List<TurnPlayer> playersWithResults = new ArrayList<>(this.players.size());
            for(TurnPlayer p : this.players) {
                playersWithResults.add(new RandomPlayer((RandomPlayer)p));
            }
            Round round = new Round(currentRound, playersWithResults);
            roundArr.add(round);

            //set GameHand and resultOfTurn to null in the existing players list
            this.players = this.players.stream()
                    .peek(p -> p.setResultOfTurn(null))
                    .peek(p -> p.setGameHand(null))
                    .collect(Collectors.toList());
        }

        return roundArr;
    }

    /**
     * Takes player's {@link GameHand} and compares with the rest players' {@link GameHand}s
     * and figures out the result of that round from the player's perspective
     *
     * @param playerHand {@link GameHand} player hand that is compared to other players' hands
     * @param hands {@link GameHand} other players' hands
     * @return {@link RoundResult} result of the round from the user perspective
     */
    public RoundResult evaluateHands(GameHand playerHand, List<GameHand> hands) {

        RoundResult result = RoundResult.TIE;

        //When there are three different hands in one round the result is tie
        //First condition refers to three game players, second to other scenarios
        List<GameHand> distinctHands = hands.stream().distinct().collect(Collectors.toList());
        if((!distinctHands.contains(playerHand) && distinctHands.size() == 2)
        || (distinctHands.contains(playerHand) && distinctHands.size() > 2)){
            return result;
        }

        for (GameHand hand: hands) {
            if(hand.isBetterThan(playerHand)){
                result = RoundResult.LOOSE;
                return result;
            } else if(playerHand.isBetterThan(hand)){
                result = RoundResult.WIN;
            }
        }
        return result;
    }

}
