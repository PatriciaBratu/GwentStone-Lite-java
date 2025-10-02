package org.poo.main;

import org.poo.checker.Checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.checker.CheckerConstants;
import org.poo.fileio.*;
import org.poo.myclasses.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);


        /*
         * TODO Implement your function here
         *
         * How to add output to the output array?
         * There are multiple ways to do this, here is one example:
         *
         *         ObjectMapper mapper = new ObjectMapper();
         *
         * ObjectNode objectNode = mapper.createObjectNode();
         * objectNode.put("field_name", "field_value");
         *
         * ArrayNode arrayNode = mapper.createArrayNode();
         * arrayNode.add(objectNode);
         *
         * output.add(arrayNode);
         * output.add(objectNode);
         *
         */
        ArrayNode output = objectMapper.createArrayNode();
        int noGames = 0;
        int wins1 = 0;
        int wins2 = 0;
        int thisRound = 0;
        for (GameInput game:inputData.getGames()) {
            thisRound = 0;
            noGames++;
            StartGame in = new StartGame();
            in.setPlayerOneDeckIdx(game.getStartGame().getPlayerOneDeckIdx());
            in.setPlayerTwoDeckIdx(game.getStartGame().getPlayerTwoDeckIdx());
            in.setStartingPlayer(game.getStartGame().getStartingPlayer()); // iau startingplayer
             ArrayList<ArrayList<Cards>> cardsDecks = new ArrayList<>();
            for (ArrayList<CardInput> deckk : inputData.getPlayerOneDecks().getDecks()) {
                ArrayList<Cards> cardsDeck = new ArrayList<>();
                for (CardInput cardInput : deckk) {
                    Cards card = new Cards(cardInput);
                    cardsDeck.add(card);               // Adaugă cardul în deckul
                }
                cardsDecks.add(cardsDeck);             // Adaugă deckul de Cards în structra
            }
            int nrCardsinDeck1 = inputData.getPlayerOneDecks().getNrCardsInDeck();
            int nrDecks1 = inputData.getPlayerOneDecks().getNrDecks();
            Deck deck = new Deck(nrCardsinDeck1, nrDecks1, cardsDecks);

            in.setPlayerOneDecks(deck);


            ArrayList<ArrayList<Cards>> cardsDecks2 = new ArrayList<>();
            for (ArrayList<CardInput> deckk : inputData.getPlayerTwoDecks().getDecks()) {
                ArrayList<Cards> cardsDeck2 = new ArrayList<>();
                for (CardInput cardInput : deckk) {
                    Cards card = new Cards(cardInput);
                    cardsDeck2.add(card);
                }
                cardsDecks2.add(cardsDeck2);
            }
            int nrCardsInDeck2 = inputData.getPlayerTwoDecks().getNrCardsInDeck();
            int nrDecks2 = inputData.getPlayerTwoDecks().getNrDecks();
            Deck deck2 = new Deck(nrCardsInDeck2, nrDecks2, cardsDecks2);
            in.setPlayerTwoDecks(deck2);
            in.setPlayerOneHero(game.getStartGame().getPlayerOneHero());
            in.setPlayerTwoHero(game.getStartGame().getPlayerTwoHero());
            PregatireMeci meci = new PregatireMeci(in);

            meci.setShuffleSeed(game.getStartGame().getShuffleSeed());
            ArrayList<Action> action = new ArrayList<>();
            for (ActionsInput ac : game.getActions()) {
                Action act = new Action(ac);
                action.add(act);
            }
            DesfasurareMeci desf = new DesfasurareMeci(meci, action);
            desf.incepMeci();
            desf.comandaCurenta(output, noGames, wins1, wins2, thisRound);

        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath2), output);
            ObjectMapper mapper = new ObjectMapper();


            }
        }
