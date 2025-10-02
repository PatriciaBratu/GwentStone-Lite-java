package org.poo.myclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PregatireMeci { //in acesta clasa voi initializa jocul
    private int shuffleSeed;
    StartGame start = new StartGame();
    public int castig = 0;
    public final void setIndexPl(final int indexPl) {
        this.indexPl = indexPl;
    }

    public final int getIndexPl() {
        return indexPl;
    }

    private int indexPl;
    public ArrayList<Cards> deck1 = new ArrayList<Cards>();
    public ArrayList<Cards> deck2 = new ArrayList<Cards>();
    public final int getShuffleSeed() {
        return shuffleSeed;
    }

    public final void setShuffleSeed(final int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
    }
    public PregatireMeci() {

    };
    public PregatireMeci(final StartGame start) {
        this.start = start;
        setIndexPl(start.getStartingPlayer());
        this.deck1 = new ArrayList<>(getCartilePL1()); // Deep copy deck1
        this.deck2 = new ArrayList<>(getCartilePL2());
    }
    /**
     * Retrieves the cards from player one's deck.
     *
     * @return An ArrayList of Cards representing player one's deck.
     */
    public final ArrayList<Cards>  getCartilePL1() {
        int index = start.getPlayerOneDeckIdx();
        int nrCards = start.getPlayerOneDecks().getNrCardsInDeck();
        ArrayList<Cards> cartilePL1 = new ArrayList<>();
        for (int i = 0; i <= nrCards - 1; i++) {
            Cards carte = (start.getPlayerOneDecks().getDecks().get(index)).get(i);
            cartilePL1.add(carte);
        }
        return cartilePL1;
    }
    /**
     * Retrieves the cards from player two's deck.
     *
     * @return An ArrayList of Cards representing player two's deck.
     */
    public final ArrayList<Cards>  getCartilePL2() {
        int index = start.getPlayerTwoDeckIdx();
        int nrCards = start.getPlayerTwoDecks().getNrCardsInDeck();
//        System.out.println("nr cards: " + nrCards +  " iar index = " + index);
       ArrayList<Cards> cartilePL2 = new ArrayList<>();

        for (int i = 0; i <= nrCards - 1; i++) {
            Cards carte = (start.getPlayerTwoDecks().getDecks().get(index)).get(i);
            cartilePL2.add(carte);
        }
        return cartilePL2;
    }
    /**
     * Shuffles a given deck of cards using a random seed.
     *
     * @param deck The deck of cards to shuffle.
     */
    public final void amestec(final ArrayList<Cards> deck) {
        Random random = new Random(this.shuffleSeed);
        Collections.shuffle(deck, random);
    }
    /**
     * Retrieves and shuffles player one's deck of cards.
     *
     * This method fetches player one's deck, shuffles it, and assigns it to deck1.
     */
    public final void getPlayerOneD() {
        ArrayList<Cards> playerOneDeck = this.getCartilePL1();
        this.amestec(playerOneDeck);
        this.deck1 = playerOneDeck;
    }
    /**
     * Retrieves and shuffles player two's deck of cards.
     *
     * This method fetches player two's deck, shuffles it, and assigns it to deck2.
     */
    public final void getPlayerTwoD() {
        ArrayList<Cards> playerTwoDeck = this.getCartilePL2();
        this.amestec(playerTwoDeck);
        this.deck2 = playerTwoDeck;
    }

}
