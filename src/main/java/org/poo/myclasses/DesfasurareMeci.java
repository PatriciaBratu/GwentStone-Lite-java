package org.poo.myclasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.Coordinates;

import java.util.ArrayList;

public class DesfasurareMeci extends StartGame implements Afisez {
    public final PregatireMeci getMeci() {
        return meci;
    }

    public final void setMeci(final PregatireMeci meci) {
        this.meci = meci;
    }
    private static final int MAX_CARDS_PER_ROW = 4;
    private static final int MAX_ROWS = 5;
    private static final int MAX_HEALTH = 30;
    private static final int PLAYER_ONE_FRONT_ROW = 2;
    private static final int PLAYER_TWO_FRONT_ROW = 1;
    private static final int PLAYER_ONE_BACK_ROW = 3;
    private static final int PLAYER_TWO_BACK_ROW = 0;
    private PregatireMeci meci = new PregatireMeci();
    private int runda = 1;
     private ArrayList<Cards> inMana1 = new ArrayList<>();
    private ArrayList<Cards> inMana2 = new ArrayList<>();
    public final void setInMana1(final ArrayList<Cards> inMana1) {
        this.inMana1 = inMana1;
    }



    public final void setInMana2(final ArrayList<Cards> inMana2) {
        this.inMana2 = inMana2;
    }


    private int indexPlayerCu;
    public final int getIndexPlayerCu() {
        return indexPlayerCu;
     }

    public final void setIndexPlayerCu(final int indexPlayerCu) {
        this.indexPlayerCu = indexPlayerCu;
    }

    private Masa masa = new Masa();
    //    int lastIndex = 0; //cu acest camp voi tine minte cine e la rand
    private Player player1 = new Player();
    private Player player2 = new Player();
    private ArrayList<Action> actions;
//    private Afisez out = new Afisez();
    private int nrEndturn = 0;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectNode objectNode = objectMapper.createObjectNode();
    private ArrayNode output = objectMapper.createArrayNode();

    public final void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    private String filePath;

    public DesfasurareMeci(final PregatireMeci meci, final ArrayList<Action> actions) {
        this.meci = meci;
        this.actions = actions;
        this.setIndexPlayerCu(meci.getIndexPl());
        this.player1 = new Player();
        this.player2 = new Player();
    }
    /**
     * Updates the game state at the end of
     * each round. This includes incrementing the round counter,
     * adding cards to the players' mana pools,
     * updating players' mana, and resetting the attack and special
     * ability statuses for cards on the table.
     */
    public final void actualizezRunda() {

            this.runda++;
            if (meci.deck1 != null
                    && !meci.deck1.isEmpty()
                    && meci.deck2 != null
                    && !meci.deck2.isEmpty()) {
                player1.adaugCarteMana(this.meci.deck1.get(0));
                this.meci.deck1.remove(0);

                player2.adaugCarteMana(this.meci.deck2.get(0));
                this.meci.deck2.remove(0);
            }
            // Update each player's mana
            player1.actualizezMana(this.runda);
            player2.actualizezMana(this.runda);
            for (int i = 0; i < MAX_CARDS_PER_ROW; i++) {
                for (Cards card:this.masa.getMasa().get(i)) {
                    card.setHasAttacked(0);
                }
            }
            for (int i = 0; i < MAX_CARDS_PER_ROW; i++) {
                for (Cards card:this.masa.getMasa().get(i)) {
                    card.setUsedSpecThisTurn(0);
                }
            }
            meci.start.getPlayerOneHero().setHasAtt(0);
            meci.start.getPlayerTwoHero().setHasAtt(0);
//        } else {
//            System.out.println("Ai ramas fara carti");
//        }

    }
//    Player player = new Player();
    /**
     * Places a card from the player's mana pool on
     *to the battlefield, verifying if the player has enough mana
     * to place the card. The card will be placed in the
     * appropriate row based on its type.
     *
     * @param indexPl The player index (1 for player 1, 2 for player 2).
     * @param index The index of the card in the player's mana pool.
     * @return Returns true if the card was
     * successfully placed, false if the player doesn't have enough mana
     *         or the card placement was unsuccessful for any reason.
     */
    public final boolean placeCarte(final int indexPl, final int index) {
    if (indexPl == 1 && player1.cartiInMana.get(index) != null) {  // Player 1
        Cards carte1 = player1.cartiInMana.get(index);

        // Check if player has enough mana
        if (player1.getMana() < carte1.getMana()) {

            return false;  // Indicate failure
        }

        // Deduct the mana and place the card
        player1.setMana(player1.getMana() - carte1.getMana());
        if (carte1.getName().equals("Sentinel")
                || carte1.getName().equals("Berserker")
                || carte1.getName().equals("The Cursed One")
                || carte1.getName().equals("Disciple")) {
            masa.placeBackRow(indexPl, player1, index);
        } else if (carte1.getName().equals("Goliath")
                || carte1.getName().equals("Warden")
                || carte1.getName().equals("The Ripper")
                || carte1.getName().equals("Miraj")) {
            masa.placeFrontRow(indexPl, player1, index);
        }
        return true;  // Indicate success

    } else if (indexPl == 2 && player2.cartiInMana.get(index) != null) {  // Player 2
        Cards carte2 = player2.cartiInMana.get(index);

        // Check if player has enough mana
        if (player2.getMana() < carte2.getMana()) {

            return false;  // Indicate failure
        }

        // Deduct the mana and place the card
        player2.setMana(player2.getMana() - carte2.getMana());
        if (carte2.getName().equals("Sentinel")
                || carte2.getName().equals("Berserker")
                || carte2.getName().equals("The Cursed One")
                || carte2.getName().equals("Disciple")) {
            masa.placeBackRow(indexPl, player2, index);
        } else if (carte2.getName().equals("Goliath")
                || carte2.getName().equals("Warden")
                || carte2.getName().equals("The Ripper")
                || carte2.getName().equals("Miraj")) {
            masa.placeFrontRow(indexPl, player2, index);
        }
        return true;  // Indicate success
    }

    return false;  // Default return in case of an unexpected player index
}

    /**
     * Returns the hero of the player based on the player index.
     *
     * @param index The player index (1 for player 1, 2 for player 2).
     * @return The hero object for the specified player.
     *         Player 1's hero is returned if the index is 1,
     *         and Player 2's hero is returned if the index is 2.
     */
    public final Hero getPLHero(final int index) {
        if (index == 1) {
            return this.meci.start.getPlayerOneHero();
        }
        return this.meci.start.getPlayerTwoHero();
    }
    /**
     * Initializes the match by dealing the first card to each player
     * and removing it from the respective deck.
     *
     * The method retrieves the first card from each player's
     * deck and adds it to the player's mana.
     * Then, the card is removed from the deck to simulate the draw action.
     */
    public final void incepMeci() {
        meci.getPlayerOneD();
        player1.adaugCarteMana(meci.deck1.get(PLAYER_TWO_BACK_ROW));
        meci.deck1.remove(0);

        meci.getPlayerTwoD();
        player2.adaugCarteMana(meci.deck2.get(PLAYER_TWO_BACK_ROW));
        meci.deck2.remove(0);
    }
    /**
     * Verifies whether there is a "Tank" card (e.g., Goliath or Warden)
     * on the player's row.
     *
     * @param indexpl The player's index (1 or 2).
     * @param masa The board where the cards are placed.
     * @return Returns 1 if a tank card is found in the player's row,
     * otherwise returns 0.
     */
    public final int verficTank(final int indexpl, final Masa masa) {
        if (indexpl == 1) {
            for (Cards card:masa.getMasa().get(1)) {
                if (card.getName().equals("Goliath")
                        || card.getName().equals("Warden")) {
                    return 1;
                }
            }
            return 0;
        }
        if (indexpl == 2) {
            for (Cards card:masa.getMasa().get(2)) {
                if (card.getName().equals("Goliath") || card.getName().equals("Warden")) {
                    return 1;
                }
            }
            return 0;
        }
        return 0;
    }
    /**
     * Handles the logic of attacking a card on the board.
     *
     * @param ac The action being performed, containing relevant
     *           attack information.
     * @param cardAttacker Coordinates of the card initiating the attack.
     * @param cardAttacked Coordinates of the card being attacked.
     * @param table The game board.
     * @param out Output array for returning results or errors.
     */
public final void attackCard(final Action ac, final Coordinates cardAttacker,
                       final Coordinates cardAttacked,
                       final Masa table, final ArrayNode out) {
    // Verifică dacă atacul este pe o carte a inamicului
    if (!isEnemyCard(cardAttacked)) {
        this.objectNode = this.afisDontBelong(ac);
        out.add(objectNode);
        return;
    }

    // Obține referințele la cartea atacatoare și atacată
    Cards attackerCard = table.getMasa().get(cardAttacker.getX()).get(cardAttacker.getY());
    Cards attackedCard = table.getMasa().get(cardAttacked.getX()).get(cardAttacked.getY());

    // Verifică dacă atacatorul a atacat deja în această tură
    if (attackerCard.getHasAttacked() == 1 || attackerCard.getUsedSpecThisTurn() == 1) {
        this.objectNode = this.hasAttacked(ac);
        out.add(objectNode);
        return;
    }

    // Verifică dacă atacatorul este înghețat
    if (attackerCard.getFrozen() == 1) {
        this.objectNode = this.isFrozen(ac);
        out.add(objectNode);
        return;
    }

    // Verifică dacă există un Tank pe linia inamicului și dacă atacăm o carte de tip Tank
    if (isEnemyTankPresent() && !isTank(attackedCard)) {
        this.objectNode = this.existaTank(ac);
        out.add(objectNode);
        return;
    }

    // Execută atacul și actualizează starea
    attackedCard.setHealth(attackedCard.getHealth() - attackerCard.getAttackDamage());
    attackerCard.setHasAttacked(1);

    // Elimină cartea de pe masă dacă viața acesteia este <= 0
    if (attackedCard.getHealth() <= 0) {
        table.getMasa().get(cardAttacked.getX()).remove(cardAttacked.getY());
    }
}
    /**
     * Handles the logic for attacking a player's hero directly.
     *
     * @param ac The action being performed, containing relevant
     *           attack information.
     * @param cardAttacker Coordinates of the card initiating the attack.
     * @param table The game board.
     * @param out Output array for returning results or errors.
     */
public final void attackHero(final Action ac,
                             final  Coordinates cardAttacker,
                             final Masa table, final ArrayNode out) {
    Cards attackerCard = table.getMasa().get(cardAttacker.getX()).get(cardAttacker.getY());
    if (attackerCard.getFrozen() == 1) {
        this.objectNode = this.isFrozen(ac);
        out.add(objectNode);
        return;
    }
    if (attackerCard.getHasAttacked() == 1 || attackerCard.getUsedSpecThisTurn() == 1) {
        this.objectNode = this.hasAttacked(ac);
        out.add(objectNode);
        return;
    }
    if (isEnemyTankPresent()) {
        this.objectNode = this.existaTank(ac);
        out.add(objectNode);
        return;
    }
    if (this.indexPlayerCu == 1) {
        meci.start.getPlayerTwoHero().setHealth(
                meci.start.getPlayerTwoHero().getHealth()
                - attackerCard.getAttackDamage());

        if (meci.start.getPlayerTwoHero().getHealth() <= 0) {
            this.objectNode = this.wins(indexPlayerCu);
            out.add(objectNode);
            this.meci.castig = 1;
            return;
        }
        attackerCard.setHasAttacked(1);
        return;
    }
    if (this.indexPlayerCu == 2) {
        meci.start.getPlayerOneHero().setHealth(meci.start.getPlayerOneHero().getHealth()
                - attackerCard.getAttackDamage());

        if (meci.start.getPlayerOneHero().getHealth() <= 0) {
            this.objectNode = this.wins(indexPlayerCu);
            out.add(objectNode);
            this.meci.castig = 1;
            return;
        } attackerCard.setHasAttacked(1);
        return;
    }
}
/**
 * Executes the special ability of the hero, if applicable
  */
    public final void heroAbility(final Action ac, final ArrayNode out) {
        if (indexPlayerCu == 1) {
            if (player1.getMana() < meci.start.getPlayerOneHero().getMana()) {
                this.objectNode = this.notEnough(ac);
                out.add(objectNode);
                return;
            }
            if (meci.start.getPlayerOneHero().getHasAtt() == 1) {
                this.objectNode = this.hasAtt(ac);
                out.add(objectNode);
                return;
            }
            if (meci.start.getPlayerOneHero().getName().equals("Lord Royce")
                    || meci.start.getPlayerOneHero().getName().equals("Empress Thorina")) {
                if (ac.getAffectedRow() == PLAYER_ONE_FRONT_ROW
                        || ac.getAffectedRow() == PLAYER_ONE_BACK_ROW) {
                    this.objectNode = this.doesntBelong(ac);
                    out.add(objectNode);
                    return;
                }
                if (meci.start.getPlayerOneHero().getName().equals("Lord Royce")) {
                    subZero(ac);
                    meci.start.getPlayerOneHero().setHasAtt(1);
                    player1.setMana(player1.getMana() - meci.start.getPlayerOneHero().getMana());
                    return;
                }
                if (meci.start.getPlayerOneHero().getName().equals("Empress Thorina")) {
                    lowBlow(ac);
                    meci.start.getPlayerOneHero().setHasAtt(1);
                    player1.setMana(player1.getMana() - meci.start.getPlayerOneHero().getMana());
                    return;
                }
            }
            if (meci.start.getPlayerOneHero().getName().equals("General Kocioraw")
                    || meci.start.getPlayerOneHero().getName().equals("King Mudface")) {
                if (ac.getAffectedRow() != PLAYER_ONE_FRONT_ROW
                        && ac.getAffectedRow() != PLAYER_ONE_BACK_ROW) {
                    this.objectNode = this.doesntBelong1(ac);
                    out.add(objectNode);
                    return;
                }
                if (meci.start.getPlayerOneHero().getName().equals("General Kocioraw")) {
                    bloodThirst(ac);
                    meci.start.getPlayerOneHero().setHasAtt(1);
                    player1.setMana(player1.getMana() - meci.start.getPlayerOneHero().getMana());
                    return;
                }
                if (meci.start.getPlayerOneHero().getName().equals("King Mudface")) {
                    earthBorn(ac);
                    meci.start.getPlayerOneHero().setHasAtt(1);
                    player1.setMana(player1.getMana() - meci.start.getPlayerOneHero().getMana());
                    return;
                }
            }
        }
        else if (indexPlayerCu == 2) {
            if (player2.getMana() < meci.start.getPlayerTwoHero().getMana()) {
                this.objectNode = this.notEnough(ac);
                out.add(objectNode);
                return;
            }
            if (meci.start.getPlayerTwoHero().getHasAtt() == 1) {
                this.objectNode = this.hasAtt(ac);
                out.add(objectNode);
                return;
            }
            if (meci.start.getPlayerTwoHero().getName().equals("Lord Royce")
                    || meci.start.getPlayerTwoHero().getName().equals("Empress Thorina")) {
                if (ac.getAffectedRow() != PLAYER_ONE_FRONT_ROW
                        && ac.getAffectedRow() != PLAYER_ONE_BACK_ROW) {
                    this.objectNode = this.doesntBelong(ac);
                    out.add(objectNode);
                    return;
                }
                if (meci.start.getPlayerTwoHero().getName().equals("Lord Royce")) {
                    subZero(ac);
                    meci.start.getPlayerTwoHero().setHasAtt(1);
                    player2.setMana(player2.getMana() - meci.start.getPlayerTwoHero().getMana());
                    return;
                }
                if (meci.start.getPlayerTwoHero().getName().equals("Empress Thorina")) {
                    lowBlow(ac);
                    meci.start.getPlayerTwoHero().setHasAtt(1);
                    player2.setMana(player2.getMana() - meci.start.getPlayerTwoHero().getMana());
                    return;
                }
            }
            if (meci.start.getPlayerTwoHero().getName().equals("General Kocioraw")
                    || meci.start.getPlayerTwoHero().getName().equals("King Mudface")) {
                if (ac.getAffectedRow() != PLAYER_TWO_BACK_ROW
                        && ac.getAffectedRow() != PLAYER_TWO_FRONT_ROW) {
                    this.objectNode = this.doesntBelong1(ac);
                    out.add(objectNode);
                    return;
                }
                if (meci.start.getPlayerTwoHero().getName().equals("General Kocioraw")) {
                    bloodThirst(ac);
                    meci.start.getPlayerTwoHero().setHasAtt(1);
                    player2.setMana(player2.getMana() - meci.start.getPlayerTwoHero().getMana());
                    return;
                }
                if (meci.start.getPlayerTwoHero().getName().equals("King Mudface")) {
                    earthBorn(ac);
                    meci.start.getPlayerTwoHero().setHasAtt(1);
                    player2.setMana(player2.getMana() - meci.start.getPlayerTwoHero().getMana());
                    return;
                }
            }
        }
    }
    private void bloodThirst(final Action ac) {
        ArrayList<Cards> row = masa.getMasa().get(ac.getAffectedRow());
        for (Cards card:row) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }
    }
    private void earthBorn(final Action ac) {
        ArrayList<Cards> row = masa.getMasa().get(ac.getAffectedRow());
        for (Cards card:row) {
            card.setHealth(card.getHealth() + 1);
        }
    }
    private void subZero(final Action ac) {
        ArrayList<Cards> row = masa.getMasa().get(ac.getAffectedRow());
        for (Cards card:row) {
            card.setFrozen(1);
        }
    }
    private void lowBlow(final Action ac) {
        int max = 0;
        int indexMax = -1; // Inițializare la -1 pentru a verifica dacă găsim o carte validă
        ArrayList<Cards> row = masa.getMasa().get(ac.getAffectedRow());

        for (int i = 0; i < row.size(); i++) { // Iterează de la stânga la dreapta
            if (row.get(i) != null) {
                if (row.get(i).getHealth() > max) { // Compară pentru viața maximă
                    max = row.get(i).getHealth();
                    indexMax = i;
                }
            }
        }

        // Dacă am găsit o carte validă, o eliminăm
        if (indexMax != -1) {
            row.remove(indexMax);
        }
    }

    private boolean isEnemyCard(final Coordinates cardAttacked) {
        int row = cardAttacked.getX();
        return (this.getIndexPlayerCu() == 1 && (row == 0 || row == 1))
                || (this.getIndexPlayerCu() == PLAYER_ONE_FRONT_ROW
                && (row == PLAYER_ONE_FRONT_ROW || row == PLAYER_ONE_BACK_ROW));
    }
    private boolean belongsItself(final Coordinates cardAttacked) {
        int row = cardAttacked.getX();
        return (this.getIndexPlayerCu() == 1 && (row == PLAYER_ONE_FRONT_ROW
                || row == PLAYER_ONE_BACK_ROW))
                || (this.getIndexPlayerCu() == PLAYER_ONE_FRONT_ROW
                && (row == 0 || row == 1));
    }

    private boolean isEnemyTankPresent() {
        // Verifică dacă există un minion de tip Tank pe rândurile inamicului
        // Aceasta este o funcție care poate verifica întreaga linie a inamicului pentru un Tank
        return verficTank(this.indexPlayerCu, this.masa) == 1;
    }

    private boolean isTank(final Cards card) {
        return card.getName().equals("Goliath")
                || card.getName().equals("Warden");
    }
    private void abilityCard(final Action ac,
                             final Coordinates cardAttacker,
                             final Coordinates cardAttacked, final Masa table,
                             final ArrayNode out) {
        Cards attackerCard = table.getMasa().get(cardAttacker.getX()).get(cardAttacker.getY());
        Cards attackedCard = table.getMasa().get(cardAttacked.getX()).get(cardAttacked.getY());
        if (attackerCard.getFrozen() == 1) {
            this.objectNode = this.isFrozen(ac);
            out.add(objectNode);
            return;
        }

        if (attackerCard.getHasAttacked() == 1 || attackerCard.getUsedSpecThisTurn() == 1) {
            this.objectNode = this.hasAttacked(ac);
            out.add(objectNode);
            return;
        }
        if (attackerCard.getName().equals("Disciple")) {
            if (!belongsItself(cardAttacked)) {
                this.objectNode = this.afisDontBelongThis(ac);
                out.add(objectNode);
                return;
            }
            attackedCard.setHealth(attackedCard.getHealth() + 2);
            attackerCard.setUsedSpecial(1);
            attackerCard.setUsedSpecThisTurn(1);
            return;
        }
        if (attackerCard.getName().equals("The Ripper")
                || attackerCard.getName().equals("Miraj")
                || attackerCard.getName().equals("The Cursed One")) {
            if (!isEnemyCard(cardAttacked)) {
                this.objectNode = this.afisDontBelong(ac);
                out.add(objectNode);
                return;
            }
            if (isEnemyTankPresent() && !isTank(attackedCard)) {
                this.objectNode = this.existaTank(ac);
                out.add(objectNode);
                return;
            }
            if (attackerCard.getName().equals("The Ripper")) {
                if (attackedCard.getAttackDamage() < 2) {
                    attackedCard.setAttackDamage(0);
                    attackerCard.setUsedSpecial(1);
                    attackerCard.setUsedSpecThisTurn(1);
                }
                else {
                    attackedCard.setAttackDamage(attackedCard.getAttackDamage() - 2);
                    attackerCard.setUsedSpecial(1);
                    attackerCard.setUsedSpecThisTurn(1);
                }
                return;
            }
            if (attackerCard.getName().equals("Miraj")) {
                int temp = attackedCard.getHealth();
                attackedCard.setHealth(attackerCard.getHealth());
                attackerCard.setHealth(temp);
                attackerCard.setUsedSpecial(1);
                attackerCard.setUsedSpecThisTurn(1);
                return;
            }
            if (attackerCard.getName().equals("The Cursed One")) {
                if (attackedCard.getAttackDamage() == 0) {
                    table.getMasa().get(cardAttacked.getX()).remove(cardAttacked.getY());
                    attackerCard.setUsedSpecial(1);
                    attackerCard.setUsedSpecThisTurn(1);
                    return;
                } else {
                    int temp = attackedCard.getHealth();
                    attackedCard.setHealth(attackedCard.getAttackDamage());
                    attackedCard.setAttackDamage(temp);
                    attackerCard.setUsedSpecial(1);
                    attackerCard.setUsedSpecThisTurn(1);
                    return;
                }
            }
        }

    }
    /**
     * This method generates an ObjectNode containing information
     * about all frozen cards currently on the table.
     * It checks every card on the game board (represented by the
     * 'masa' object) to see if it is frozen (frozen state = 1).
     * If any card is frozen, it is added to a list. This list is
     * then serialized into JSON and included in the output.
     *
     * @param ac The Action object representing the current command.
     * @return An ObjectNode that contains the command and a list of
     * frozen cards on the table.
     */
    public final ObjectNode frozenOnTable(final Action ac) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", ac.getCommand());

        ArrayList<Cards> lista = new ArrayList<>();
        for (ArrayList<Cards> row:this.masa.getMasa()) {
            for (Cards card:row) {
                if (card.getFrozen() == 1) {
//                    objectNode.set("output",  objectMapper.valueToTree(card));
                    lista.add(card);
                }
            }
        }
        objectNode.set("output", objectMapper.valueToTree(lista));
        return objectNode;
    }
/**
now each command is executed
 */
    public final void comandaCurenta(final ArrayNode out,
                                     final int nrJocuri, int wins1,
                                      int wins2,
                                     int thisRound) {
        for (Action ac : this.actions) {
                String comanda = ac.getCommand();
                switch (comanda) {
                    case "getPlayerDeck":
                        if (ac.getPlayerIdx() == 2) {
                            this.objectNode = this.scriuOutput(ac.getCommand(),
                                    ac.getPlayerIdx(), meci.deck2);
                            out.add(objectNode);
                        } else {
                            this.objectNode = this.scriuOutput(ac.getCommand(),
                                    ac.getPlayerIdx(), meci.deck1);
                            out.add(objectNode);
                        }
                        break;
                    case "getPlayerHero":
                        this.objectNode = this.writeHero(ac.getCommand(),
                                ac.getPlayerIdx(),
                                this.getPLHero(ac.getPlayerIdx()), this.filePath);
                        out.add(objectNode);
                        break;
                    case "getPlayerTurn":
                        this.objectNode = this.currentPlayer(ac.getCommand(),
                                getIndexPlayerCu());
                        out.add(objectNode);
                        break;
                    case "endPlayerTurn":
                        if (thisRound == 0) {
                            nrEndturn += 1;
                            if (getIndexPlayerCu() == 2) {
                                ArrayList<Cards> row0 = this.masa.getMasa().get(0);
                                for (Cards card : row0) {
                                    card.setFrozen(0);
                                }
                                ArrayList<Cards> row1 = this.masa.getMasa().get(1);
                                for (Cards card : row1) {
                                    card.setFrozen(0);
                                }
                            }
                            if (getIndexPlayerCu() == 1) {
                                ArrayList<Cards> row2 = this.masa.getMasa().get(2);
                                for (Cards card : row2) {
                                    card.setFrozen(0);
                                }
                                ArrayList<Cards> row3 = this.masa.
                                        getMasa().get(PLAYER_ONE_BACK_ROW);
                                for (Cards card : row3) {
                                    card.setFrozen(0);
                                }
                            }
                            if (nrEndturn % 2 == 0 && nrEndturn != 0) {
                                this.actualizezRunda();
                            }
                            if (getIndexPlayerCu() == 1) {
                                setIndexPlayerCu(2);
                            } else if (getIndexPlayerCu() == 2) {
                                setIndexPlayerCu(1);

                            }
//                        System.out.println("Pl schimbat: " + this.indexPlayerCu);
                        }
                        break;
                    case "placeCard":
                        int handIndex = ac.getHandIdx();

                        // Attempt to place the card and only remove it if successful
                        if (placeCarte(getIndexPlayerCu(), handIndex)) {
                            // Card placement was successful; remove the card from the player's hand
                            if (getIndexPlayerCu() == 1) {
                                player1.scotCarteMana(handIndex);
                            } else if (getIndexPlayerCu() == 2) {
                                player2.scotCarteMana(handIndex);
                            }
                        } else {
                            this.objectNode = this.notenoughMana(ac.getCommand(), ac.getHandIdx());
                            out.add(objectNode);
                        }

                        break;
                    case "getCardsInHand":
                        this.setInMana1(player1.cartiInMana);
                        this.setInMana2(player2.cartiInMana);
                        if (ac.getPlayerIdx() == 1) {
//                           ObjectMapper mapper = new ObjectMapper();
                            this.objectNode = this.cartiMana(ac.getCommand(),
                                    this.inMana1, ac.getPlayerIdx(), this.filePath);
                            out.add(objectNode);
                        } else if (ac.getPlayerIdx() == 2) {
//                           ObjectMapper mapper = new ObjectMapper();
                            this.objectNode = this.cartiMana(ac.getCommand(),
                                    this.inMana2, ac.getPlayerIdx(), this.filePath);
                            out.add(objectNode);


                        }
                        break;
                    case "getPlayerMana":
                        if (ac.getPlayerIdx() == 1) {
//                           ObjectMapper mapper = new ObjectMapper();
                            this.objectNode = this.getMana(ac.getCommand(),
                                    ac.getPlayerIdx(), player1);
                            out.add(objectNode);
                        } else if (ac.getPlayerIdx() == 2) {
//                           ObjectMapper mapper = new ObjectMapper();
                            this.objectNode = this.getMana(ac.getCommand(),
                                    ac.getPlayerIdx(), player2);
                            out.add(objectNode);
                        }
                        break;
                    case "getCardsOnTable":
                        this.objectNode = this.gatCardsTable(ac.getCommand(), masa);
                        out.add(objectNode);
                        break;
                    case "cardUsesAttack":
                        this.attackCard(ac, ac.cardAttacker,
                                ac.cardAttacked, masa, out);
                        break;
                    case "getCardAtPosition":
                        if (ac.getX() < masa.getMasa().size()
                                && ac.getY() < masa.getMasa().get(ac.getX()).size()) {
                            if (this.masa.getMasa().get(ac.getX()).get(ac.getY()) != null) {
                                this.objectNode = this.printCard(ac, this.masa);
                            }
                        } else {
                            this.objectNode = this.printerr(ac);
                        }
                        out.add(objectNode);
                        break;
                    case "cardUsesAbility":
                        this.abilityCard(ac, ac.cardAttacker,
                                ac.cardAttacked, masa, out);
                        break;
                    case "useAttackHero":
                        this.attackHero(ac, ac.cardAttacker, masa, out);
                        if (indexPlayerCu == 1 && this.meci.castig == 1) {
                            wins1++;
                            thisRound = 1;
                        }
                        else if (indexPlayerCu == 2 && this.meci.castig == 1) {
                            wins2++;
                            thisRound = 1;
                        }
                        break;
                    case "useHeroAbility":
                        this.heroAbility(ac, out);
                        if (this.meci.castig == 1) {
                            return;
                        }
                        break;
                    case "getFrozenCardsOnTable":
                        this.objectNode = this.frozenOnTable(ac);
                        out.add(this.objectNode);
                        break;
                    case "getTotalGamesPlayed":
                        this.objectNode = this.nrJocuri(ac, nrJocuri);
                        out.add(this.objectNode);
                        break;
                    case "getPlayerOneWins":
                        this.objectNode = this.playerOneWins(ac, nrJocuri - wins2);
                        out.add(this.objectNode);
                        break;
                    case "getPlayerTwoWins":
                        this.objectNode = this.playerTwoWins(ac, wins2);
                        out.add(this.objectNode);
                        break;
                    default:
                        break;
                }

//            }

        }
        }
}

