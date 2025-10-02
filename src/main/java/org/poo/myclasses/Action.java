package org.poo.myclasses;

import org.poo.fileio.ActionsInput;
import org.poo.fileio.Coordinates;

public final class Action {
    private String command;

    public void setX(final int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    private int x;

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    private int y;

    public String getCommand() {
        return command;
    }

    public void setCommand(final String command) {
        this.command = command;
    }
    public Action(final ActionsInput action) {
        this.setCommand(action.getCommand());
        this.setHandIdx(action.getHandIdx());
        this.cardAttacked = action.getCardAttacked();
        this.cardAttacker = action.getCardAttacker();
        this.affectedRow = action.getAffectedRow();
        this.playerIdx = action.getPlayerIdx();
        this.setX(action.getX());
//        System.out.println("x:" + action.getX());
        this.setY(action.getY());
//        System.out.println("y:" + action.getY());
    }

    public int getHandIdx() {
        return handIdx;
    }

    private int handIdx;
    public Coordinates cardAttacker;
    public Coordinates cardAttacked;

    public int getAffectedRow() {
        return affectedRow;
    }

    public void setAffectedRow(final int affectedRow) {
        this.affectedRow = affectedRow;
    }

    private int affectedRow;
    private int playerIdx;
    public void setPlayerIdx(final int playerIdx) {
        this.playerIdx = playerIdx;
    }


    public void setHandIdx(final int handIdx) {
        this.handIdx = handIdx;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }



}
