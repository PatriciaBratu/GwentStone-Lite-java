package org.poo.myclasses;

import java.util.ArrayList;

public class Player {
    public final int getMana() {
        return mana;
    }
    private static final int MAX_MANA = 10;
    public final void setMana(final int mana) {
        this.mana = mana;
    }

    private int mana = 1;

    public final int getNrcartimana() {
        return nrcartimana;
    }

    public final void setNrcartimana(final int nrcartimana) {
        this.nrcartimana = nrcartimana;
    }

    private int nrcartimana = 0;

    public Player() {
    }

    public final ArrayList<Cards> cartiInMana = new ArrayList<Cards>();
    /**
     * Updates the player's mana based on the current round number.
     * The mana increases as the rounds progress.
     * If the round number is less than 10, the mana increases by the round number.
     * If the round number is 10 or more, the mana is capped at a maximum increase of 10.
     *
     * @param runda The current round number.
     */
    public final void actualizezMana(final int runda) {
        if (runda < MAX_MANA) {
            this.mana += runda;
        }
        else {
            this.mana += MAX_MANA;
        }
    }
    /**
     * Adds a card to the player's mana pool.
     * The card is added to the player's list of cards
     * in hand (`cartiInMana`) and increments the card count (`nrcartimana`).
     *
     * @param carte The card to be added to the player's mana pool.
     */
    public final void adaugCarteMana(final Cards carte) {
        cartiInMana.add(nrcartimana, carte);
        nrcartimana++;
    }
    /**
     * Removes a card from the player's mana pool.
     * The card at the specified index is removed from the list of cards in hand (`cartiInMana`),
     * and the card count (`nrcartimana`) is decremented.
     *
     * @param index The index of the card to be removed from the player's mana pool.
     */
    public final void scotCarteMana(final int index) {
        int manaCurenta = cartiInMana.get(index).getMana();
        this.cartiInMana.remove(index);
        this.nrcartimana--;
    }
}
