package org.poo.myclasses;

import org.poo.fileio.CardInput;

public class StartGame {
    private int playerOneDeckIdx;
    private int playerTwoDeckIdx;
    private Hero playerOneHero;
    private Hero playerTwoHero;
    private int startingPlayer;
    private Deck playerOneDecks;
    private Deck playerTwoDecks;
    public StartGame() {
    }

    public final int getPlayerOneDeckIdx() {
        return playerOneDeckIdx;
    }

    public final void setPlayerOneDeckIdx(final int playerOneDeckIdx) {
        this.playerOneDeckIdx = playerOneDeckIdx;
    }

    public final int getPlayerTwoDeckIdx() {
        return playerTwoDeckIdx;
    }

    public final void setPlayerTwoDeckIdx(final int playerTwoDeckIdx) {
        this.playerTwoDeckIdx = playerTwoDeckIdx;
    }


    public final Hero getPlayerOneHero() {
        return playerOneHero;
    }

    public final void setPlayerOneHero(final CardInput playerOneHero) {
        this.playerOneHero = new Hero(playerOneHero);
    }

    public final Hero getPlayerTwoHero() {
        return playerTwoHero;
    }

    public final void setPlayerTwoHero(final CardInput playerTwoHero) {
        this.playerTwoHero = new Hero(playerTwoHero);
    }

    public final int getStartingPlayer() {
        return startingPlayer;
    }

    public final void setStartingPlayer(final int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
    public final Deck getPlayerOneDecks() {
        return playerOneDecks;
    }

    public final void setPlayerOneDecks(final Deck playerOneDecks) {
        this.playerOneDecks = playerOneDecks;
    }

    public final Deck getPlayerTwoDecks() {
        return playerTwoDecks;
    }

    public final void setPlayerTwoDecks(final Deck playerTwoDecks) {
        this.playerTwoDecks = playerTwoDecks;
    }

}
