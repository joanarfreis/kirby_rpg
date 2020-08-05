package com.joana_reis.kirby_rpg.entities;

import java.io.Serializable;

public class Player extends Character implements Serializable {

    private int experience;

    public Player(String name, int position){
        super.setName(name);
        super.setPosition(position);
        super.setLife(100);
        this.experience = 1;
    }

    /** Getters and Setters **/

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }


}
