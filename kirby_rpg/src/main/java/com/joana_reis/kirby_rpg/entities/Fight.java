package com.joana_reis.kirby_rpg.entities;

import com.joana_reis.kirby_rpg.enums.Attack;
import com.joana_reis.kirby_rpg.enums.Weakness;
import java.io.Serializable;

public class Fight implements Serializable {

    Player player;
    Monster monster;

    public Fight(Player player, Monster monster){
        this.player = player;
        this.monster = monster;
    }

    /** Method that gives the information that the fight is over **/
    public boolean isOver() {
        return (player.getLife() <= 0 || monster.getLife() <= 0);
    }

    /** Method used for the player to attack**/
    public void playerAttacks(Attack attack){

        if (attack == Attack.SWORD_CHARGE && monster.getWeakness() == Weakness.PHYSICAL_ATTACKS){
            monster.setLife(monster.getLife() - (20 * player.getExperience()));
        } else if (attack == Attack.ERUPTION && monster.getWeakness() == Weakness.FIRE ) {
            monster.setLife(monster.getLife() - (20 * player.getExperience()));
        } else if (attack == Attack.BLIZZARD && monster.getWeakness() == Weakness.ICE ) {
            monster.setLife(monster.getLife() - (20 * player.getExperience()));
        } else if (attack == Attack.LIGHTENING_ATTACK && monster.getWeakness() == Weakness.THUNDER) {
            monster.setLife(monster.getLife() - (20 * player.getExperience()));
        } else {
            monster.setLife(monster.getLife() - (10 * player.getExperience()));
        }
    }

    /** Method used for the monster to attack**/
    public void monsterAttacks() {
        player.setLife(player.getLife() - 20);
    }


}

