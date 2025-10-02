package org.poo.myclasses;

import java.util.ArrayList;

public class Masa {
    public final ArrayList<ArrayList<Cards>> getMasa() {
        return masa;
    }
    private static final int MAX_CARDS_PER_ROW = 4;
    private static final int MAX_ROWS = 5;
    private static final int MAX_HEALTH = 30;
    private static final int PLAYER_ONE_FRONT_ROW = 2;
    private static final int PLAYER_TWO_FRONT_ROW = 1;
    private static final int PLAYER_ONE_BACK_ROW = 3;
    private static final int PLAYER_TWO_BACK_ROW = 0;
    private ArrayList<ArrayList<Cards>> masa = new ArrayList<ArrayList<Cards>>(MAX_CARDS_PER_ROW);
    public Masa() {
    for (int i = 0; i < MAX_CARDS_PER_ROW; i++) {
        ArrayList<Cards> linie = new ArrayList<>(MAX_ROWS);
        masa.add(linie);
    } }
    /**
     * Places a card from the player's hand into the front row of the table.
     * The front row is represented by specific indexes of the `masa` (game board).
     * Each player has a front row at index 1 for Player 2 and index 2 for Player 1.
     *
     * @param inedxPl The player index (1 or 2).
     * @param pl The player who owns the card.
     * @param index The index of the card in the player's hand.
     */
    public final void placeFrontRow(final int inedxPl,
                                    final Player pl, final int index) {
        if (inedxPl == PLAYER_TWO_FRONT_ROW) {
            if (this.masa.get(PLAYER_ONE_FRONT_ROW).size() > MAX_CARDS_PER_ROW) {
                return;
            }
            else {
                this.masa.get(PLAYER_ONE_FRONT_ROW).add(pl.cartiInMana.get(index));
                return;
            }
        }
        if (inedxPl == 2) {
            if (this.masa.get(PLAYER_TWO_FRONT_ROW).size() > MAX_CARDS_PER_ROW) {
            }
            else {
                this.masa.get(PLAYER_TWO_FRONT_ROW).add(pl.cartiInMana.get(index));
            }

        }
    }
    /**
     * Places a card from the player's hand into the back row of the table.
     * The back row is represented by specific indexes of the `masa` (game board).
     * Each player has a back row at index 0 for Player 2 and index 3 for Player 1.
     *
     * @param inedxPl The player index (1 or 2).
     * @param pl The player who owns the card.
     * @param index The index of the card in the player's hand.
     */
    public final void placeBackRow(final int inedxPl, final Player pl, final int index) {
        if (inedxPl == 1) {
            if (this.masa.get(PLAYER_ONE_BACK_ROW).size() > MAX_CARDS_PER_ROW) {
                return;
            }
            else {
                this.masa.get(PLAYER_ONE_BACK_ROW).add(pl.cartiInMana.get(index));
                return;
            }

        }
        if (inedxPl == 2) {
            if (this.masa.get(PLAYER_TWO_BACK_ROW).size() > MAX_CARDS_PER_ROW) {

            }
            else {
                this.masa.get(PLAYER_TWO_BACK_ROW).add(pl.cartiInMana.get(index));
            }
        }
    }
}
