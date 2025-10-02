    package org.poo.myclasses;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.databind.node.ObjectNode;

    import java.util.ArrayList;

    public interface Afisez {
        /**
         * Creates an output object for a given command and player index.
         *
         * @param command the command to be executed
         * @param index the index of the player
         * @param deck the list of cards to include in the output
         * @return a JSON object containing the command, player index, and deck
         */
        default ObjectNode scriuOutput(final String command,  /* @return objectNode     */
                                      final int index, final ArrayList<Cards> deck) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command);
            objectNode.put("playerIdx", index);
            objectNode.set("output", objectMapper.valueToTree(deck));
            return  objectNode;
        }
        /**
         * Writes hero information to the output.
         *
         * @param command  the command executed
         * @param index    the index of the player
         * @param hero     the hero object to include in the output
         * @param filePath the file path to store the output (unused in this method)
         * @return a JSON object with the command, player index, and hero information
         */
        default ObjectNode writeHero(final String command,
                                    final int index, final Hero hero,
                                    final String filePath) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command);
            objectNode.put("playerIdx", index);
            objectNode.set("output", objectMapper.valueToTree(hero));
            return  objectNode;
        }
        /**
         * Returns the current player index in the output.
         *
         * @param command the command executed
         * @param pl      the current player index
         * @return a JSON object with the command and player index
         */
        default ObjectNode currentPlayer(final String command, final int pl) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command);
            objectNode.put("output", pl);
            return objectNode;
        }
        /**
         * Creates an output with the cards in the player's mana.
         *
         * @param command  the command executed
         * @param mana     the cards in the player's mana
         * @param plIndex  the player's index
         * @param filePath the file path (unused in this method)
         * @return a JSON object with the command, player index, and cards in mana
         */
        default ObjectNode cartiMana(final String command,
                                    final ArrayList<Cards> mana,
                                    final int plIndex, final String filePath) {
    //        ArrayList<Cards> mana = player.cartiInMana;
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command);
            objectNode.put("playerIdx", plIndex);
            objectNode.set("output",  objectMapper.valueToTree(mana));
            return objectNode;
        }
        /**
         * Retrieves the mana of a player.
         *
         * @param command the command executed
         * @param plIndex the player's index
         * @param pl      the player object
         * @return a JSON object with the command, player index, and mana
         */
        default ObjectNode getMana(final String command, final int plIndex, final Player pl) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command);
            objectNode.put("playerIdx", plIndex);
            objectNode.put("output", pl.getMana());
            return  objectNode;

        }
        /**
         * Displays the cards on the table.
         *
         * @param command the command executed
         * @param table   the table object containing cards
         * @return a JSON object with the command and table cards
         */
        default ObjectNode gatCardsTable(final String command, final Masa table) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command);
            objectNode.set("output", objectMapper.valueToTree(table.getMasa()));
            return  objectNode;

        }
        /**
         * Returns an error when there is not enough mana to play a card.
         *
         * @param command the command executed
         * @param handIdx the index of the card in the player's hand
         * @return a JSON object with the error message
         */
        default ObjectNode notenoughMana(final String command, final int handIdx) {
            String afis = "Not enough mana to place card on table.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command);
            objectNode.put("handIdx", handIdx);
            objectNode.put("error", afis);
            return  objectNode;

        }
        /**
         * Reports the current number of games played.
         *
         * @param ac        the action performeds
         * @return a JSON object with the command and game count
         */
        default ObjectNode afisDontBelong(final Action ac) {
            String afis = "Attacked card does not belong to the enemy.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.set("cardAttacker", objectMapper.valueToTree(ac.cardAttacker));
            objectNode.set("cardAttacked", objectMapper.valueToTree(ac.cardAttacked));
            objectNode.put("error", afis);
            return  objectNode;

        }
        /**
         * Reports the current number of games played.
         *
         * @param ac        the action performed
         * @param nrJocuri  the number of games
         * @return a JSON object with the command and game count
         */
        default ObjectNode nrJocuri(final Action ac, final int nrJocuri) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.put("output", nrJocuri);
            return objectNode;
        }
        /**
         * Creates an output object indicating the number of games won by Player 1.
         *
         * @param ac    the action performed
         * @param wins1 the number of games won by Player 1
         * @return a JSON object containing the command and the number of games won by Player 1
         */
        default ObjectNode playerOneWins(final Action ac, final int wins1) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
                objectNode.put("output", wins1);
            return objectNode;
        }
        /**
         * Creates an output object indicating the number of games won by Player 2.
         *
         * @param ac    the action performed
         * @param wins2 the number of games won by Player 2
         * @return a JSON object containing the command and the number of games won by Player 2
         */
                default ObjectNode playerTwoWins(final Action ac, final int wins2) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.put("command", ac.getCommand());
                        objectNode.put("output", wins2);
                    return objectNode;
                }
        /**
         * Creates an error output object
         * indicating that the attacked card does
         * not belong to the current player.
         *
         * @param ac the action performed
         * @return a JSON object containing
         * the command, cards involved, and an error message
         */
        default ObjectNode afisDontBelongThis(final Action ac) {
            String afis = "Attacked card does not belong to the current player.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.set("cardAttacker", objectMapper.valueToTree(ac.cardAttacker));
            objectNode.set("cardAttacked", objectMapper.valueToTree(ac.cardAttacked));
            objectNode.put("error", afis);
            return  objectNode;

        }
        /**
         * Creates an error output object indicating
         * that the attacker card is frozen.
         *
         * @param ac the action performed
         * @return a JSON object containing the command,
         * cards involved, and an error message
         */
        default ObjectNode isFrozen(final Action ac) {
            String afis = "Attacker card is frozen.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.set("cardAttacker", objectMapper.valueToTree(ac.cardAttacker));
            if (ac.cardAttacked != null) {
                objectNode.set("cardAttacked", objectMapper.valueToTree(ac.cardAttacked));
            }
            objectNode.put("error", afis);
            return  objectNode;

        }
        /**
         * Creates an output object indicating
         * the end of the game and the winner.
         *
         * @param index the index of the player who won
         *              (1 for Player 1, 2 for Player 2)
         * @return a JSON object with a message indicating the
         * game has ended and the winner
         */
        default ObjectNode wins(final int index) {
            String afis;
            if (index == 1) {
                afis = "Player one killed the enemy hero.";
            } else {
                afis = "Player two killed the enemy hero.";
            }

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("gameEnded", afis);
            return  objectNode;

        }
        /**
         * Creates an error output object indicating
         * that the attacked card is not of type 'Tank'.
         *
         * @param ac the action performed
         * @return a JSON object containing the command,
         * cards involved, and an error message
         */
        default ObjectNode existaTank(final Action ac) {
            String afis = "Attacked card is not of type 'Tank'.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.set("cardAttacker", objectMapper.valueToTree(ac.cardAttacker));
            if (ac.cardAttacked != null) {
                objectNode.set("cardAttacked", objectMapper.valueToTree(ac.cardAttacked));
            }
            objectNode.put("error", afis);
            return  objectNode;

        }
        /**
         * Creates an error output object indicating
         * that there is not enough mana to use the hero's ability.
         *
         * @param ac the action performed
         * @return a JSON object containing the command,
         * affected row, and an error message
         */
        default ObjectNode notEnough(final Action ac) {
            String afis = "Not enough mana to use hero's ability.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.put("affectedRow", ac.getAffectedRow());
            objectNode.put("error", afis);
            return  objectNode;

        }
        /**
         * Creates an error output object indicating
         * that the hero has already attacked this turn.
         *
         * @param ac the action performed
         * @return a JSON object containing the command,
         * affected row, and an error message
         */
        default ObjectNode hasAtt(final Action ac) {
            String afis = "Hero has already attacked this turn.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.put("affectedRow", ac.getAffectedRow());
            objectNode.put("error", afis);
            return  objectNode;

        }
        /**
         * Creates an error output object indicating
         * that the selected row does not belong to the enemy.
         *
         * @param ac the action performed
         * @return a JSON object containing the command,
         * affected row, and an error message
         */
        default ObjectNode doesntBelong(final Action ac) {
    //        String afis = "Her has already attacked this turn.";
            String afis = "Selected row does not belong to the enemy.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.put("affectedRow", ac.getAffectedRow());
            objectNode.put("error", afis);
            return  objectNode;

        }
        /**
         * Creates an error output object indicating
         * that the selected row does not belong to the current player.
         *
         * @param ac the action performed
         * @return a JSON object containing the command,
         * affected row, and an error message
         */
        default ObjectNode doesntBelong1(final Action ac) {
            String afis = "Selected row does not belong to the current player.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.put("affectedRow", ac.getAffectedRow());
            objectNode.put("error", afis);
            return  objectNode;

        }
        /**
         * Creates an error output object indicating
         * that the attacker card has already attacked this turn.
         *
         * @param ac the action performed
         * @return a JSON object containing the command,
         * cards involved, and an error message
         */
        default ObjectNode hasAttacked(final Action ac) {
            String afis = "Attacker card has already attacked this turn.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.set("cardAttacker", objectMapper.valueToTree(ac.cardAttacker));
            if (ac.cardAttacked != null) {
                objectNode.set("cardAttacked", objectMapper.valueToTree(ac.cardAttacked));
            }
            objectNode.put("error", afis);
            return  objectNode;

        }

        /**
         * Creates an output object that represents
         * a card's details based on its position on the board.
         *
         * @param ac   the action performed, containing
         *             the position (x, y)
         * @param masa the game board
         * @return a JSON object containing the command,
         * card position, and the card details
         */
        default ObjectNode printCard(final Action ac, final Masa masa) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.put("x", ac.getX());
            objectNode.put("y", ac.getY());
            objectNode.set("output",
                    objectMapper.valueToTree(masa.getMasa()
                            .get(ac.getX()).get(ac.getY())));
            return  objectNode;

        }
        /**
         * Creates an error output object indicating
         * that no card is available at the specified position.
         *
         * @param ac the action performed, containing the position (x, y)
         * @return a JSON object containing the command,
         * card position, and an error message
         */
        default ObjectNode printerr(final Action ac) {
            String err = "No card available at that position.";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", ac.getCommand());
            objectNode.put("x", ac.getX());
            objectNode.put("y", ac.getY());
            objectNode.put("output", err);
            return  objectNode;

        }

    }
