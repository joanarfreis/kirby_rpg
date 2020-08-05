package com.joana_reis.kirby_rpg.entities;

import java.io.Serializable;

public abstract class Character implements Serializable {

    private String name;
    private int life;
    private int position;

    /** Getters and Setters **/

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}