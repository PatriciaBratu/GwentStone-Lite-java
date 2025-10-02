package org.poo.myclasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.poo.fileio.CardInput;

import java.util.ArrayList;

public class Hero {

    private int mana;
    private static final int MAX_HEALTH = 30;
    public final void setHealth(final int health) {
        this.health = health;
    }

    //    private int attackDamage;
    private int health = MAX_HEALTH;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public final int getHasAtt() {
        return hasAtt;
    }

    public final void setHasAtt(final int hasAtt) {
        this.hasAtt = hasAtt;
    }

    @JsonIgnore
    private int hasAtt = 0;
    @JsonIgnore
    private int x = -1;
    @JsonIgnore
    private int y = -1;

    public final void setY(final int y) {
        this.y = y;
    }

    public final void setX(final int x) {
        this.x = x;
    }

    public final int getY() {
        return y;
    }

    public final int getX() {
        return x;
    }

    public Hero(final CardInput card) {
        this.mana = card.getMana();
        this.description = card.getDescription();
        this.colors = card.getColors();
        this.name = card.getName();
    }

    public Hero() {

    }

    public final int getMana() {
        return mana;
    }

    public final void setMana(final int mana) {
        this.mana = mana;
    }


    public final int getHealth() {
        return health;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final ArrayList<String> getColors() {
        return colors;
    }

    public final void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    @Override
    public final String toString() {
        return "CardInput{"
                + "mana="
                + mana
                + ", health="
                + health
                + ", description='"
                + description
                + '\''
                + ", colors="
                + colors
                + ", name='"
                + ""
                + name
                + '\''
                + '}';
    }
}
