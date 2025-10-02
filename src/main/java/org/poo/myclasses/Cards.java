package org.poo.myclasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.poo.fileio.CardInput;

import java.util.ArrayList;


public class Cards {
     private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public final int getFrozen() {
        return frozen;
    }

    public final void setFrozen(final int frozen) {
        this.frozen = frozen;
    }

    @JsonIgnore
    private int frozen = 0; //nu e frozen

    public final int getHasAttacked() {
        return hasAttacked;
    }

    public final void setHasAttacked(final int hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    @JsonIgnore
    private int hasAttacked = 0;

    public final void setUsedSpecial(final int usedSpecial) {
        this.usedSpecial = usedSpecial;
    }

    public final int getUsedSpecial() {
        return usedSpecial;
    }

    @JsonIgnore
    private int usedSpecial = 0;

    public final int getUsedSpecThisTurn() {
        return usedSpecThisTurn;
    }

    public final void setUsedSpecThisTurn(final int usedSpecThisTurn) {
        this.usedSpecThisTurn = usedSpecThisTurn;
    }

    @JsonIgnore
    private int usedSpecThisTurn = 0;
    @JsonIgnore
    private int x = -1;
    @JsonIgnore
    private int y = -1;
    /**
     * Updates the attributes of the current card object with the values from another card.
     *
     * @param card the card whose attributes are copied to the current object
     */
    public final void atr(final Cards card) {
        this.setMana(card.getMana());
        this.setAttackDamage(card.getAttackDamage());
        this.setHealth(card.getHealth());
        this.setDescription(card.getDescription());
        this.setColors(card.getColors());
        this.setName(card.getName());
        this.frozen = card.frozen;
        this.hasAttacked = card.hasAttacked;
        this.usedSpecial = card.usedSpecial;
        this.usedSpecThisTurn = card.usedSpecThisTurn;
    }
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

    public Cards(final CardInput card) {
        this.mana = card.getMana();
        this.attackDamage = card.getAttackDamage();
        this.health = card.getHealth();
        this.description = card.getDescription();
        this.colors = card.getColors();
        this.name = card.getName();
    }
    public Cards() {

        }

        public final int getMana() {
            return mana;
        }

        public final void setMana(final int mana) {
            this.mana = mana;
        }

        public final int getAttackDamage() {
            return attackDamage;
        }

        public final void setAttackDamage(final int attackDamage) {
            this.attackDamage = attackDamage;
        }

        public final int getHealth() {
            return health;
        }

        public final void setHealth(final int health) {
            this.health = health;
        }

        public final  String getDescription() {
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
                    + ", attackDamage="
                    + attackDamage
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

