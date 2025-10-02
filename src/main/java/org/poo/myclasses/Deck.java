package org.poo.myclasses;



import java.util.ArrayList;

public class Deck {
    private int nrCardsInDeck;
    private int nrDecks;
    private ArrayList<ArrayList<Cards>> decks;

    public Deck() {
    }
    public Deck(final int nrCardsInDeck, final int nrDecks) {
        this.nrCardsInDeck = nrCardsInDeck;
        this.nrDecks = nrDecks;
    }
    public Deck(final int nrCardsInDeck,
                final int nrDecks,
                final ArrayList<ArrayList<Cards>> decks) {
        this.nrCardsInDeck = nrCardsInDeck;
        this.nrDecks = nrDecks;
        this.decks = decks;
    }

    public final int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    public final void setNrCardsInDeck(final int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    public final int getNrDecks() {
        return nrDecks;
    }

    public final void setNrDecks(final int nrDecks) {
        this.nrDecks = nrDecks;
    }

    public final ArrayList<ArrayList<Cards>> getDecks() {
        return decks;
    }

    public final void setDecks(final ArrayList<ArrayList<Cards>> decks) {
        this.decks = decks;
    }

    @Override
    public final String toString() {
        return "InfoInput{"
                + "nr_cards_in_deck="
                + nrCardsInDeck
                +  ", nr_decks="
                + nrDecks
                + ", decks="
                + decks
                + '}';
    }


}


