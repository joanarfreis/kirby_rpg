package com.joana_reis.kirby_rpg.entities;

import com.joana_reis.kirby_rpg.enums.Weakness;
import java.io.Serializable;

public class Monster extends Character implements Serializable {

    private Weakness weakness;
    private String greeting;

    public Monster(Weakness weakness, String name, String greeting, int life, int position) {
        this.weakness = weakness;
        this.greeting = greeting;
        super.setName(name);
        super.setLife(life);
        super.setPosition(position);
    }

    /**
     * Getters and Setters
     **/

    public Weakness getWeakness() {
        return weakness;
    }

    public String getGreeting() {
        return greeting;
    }

}