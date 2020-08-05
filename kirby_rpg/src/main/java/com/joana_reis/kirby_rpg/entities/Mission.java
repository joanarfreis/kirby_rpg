package com.joana_reis.kirby_rpg.entities;

import com.joana_reis.kirby_rpg.main.KirbysRescue;

import java.io.Serializable;

public class Mission implements Serializable {

    /** Information about a mission **/
    private String title;
    private String startDialog;
    private String endDialog;
    private int expGained;
    private boolean finished = false;
    private Fight fight;

    /** Player and Monster that will fight with each other **/
    private Monster monster;
    private Player player;

    public Mission(String title, String startDialog, String endDialog, Monster monster, Player player, int expGained) {
        this.title = title;
        this.startDialog = startDialog;
        this.endDialog = endDialog;
        this.monster = monster;
        this.player = player;
        this.expGained = expGained;
    }

    /** Method to show a mission has started **/
    public void startMission() {
        System.out.println("Starting a new mission..");
        System.out.println(KirbysRescue.ANSI_BLUE + getStartDialog() + KirbysRescue.ANSI_RESET);
    }

    /** Method that gives the information whether the player has reached the Monster's location **/
    public boolean hasReachedLocation() {
        return player.getPosition() == monster.getPosition();
    }

    /** Method to show a mission has ended **/
    public void endMission() {
        System.out.println("Congratulations, you have finished your mission: " + title);
        player.setExperience(player.getExperience() + expGained);
        System.out.println(player.getName() + " experience: + " + expGained + ", remaining life: " + player.getLife());
        System.out.println(KirbysRescue.ANSI_BLUE + getEndDialog() +  KirbysRescue.ANSI_RESET);
        setFinished(true);
    }

    /** Method used when the player wants to restart a mission after a 'Game Over' **/
    public void restartMission() {
        player.setPosition(2);
        player.setLife(100);
    }

    public void startFight() {
        fight = new Fight(player, monster);
    }

    /** Getters and Setters **/

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getStartDialog() {
        return startDialog;
    }

    public Monster getMonster() {
        return monster;
    }

    public String getEndDialog() {
        return endDialog;
    }
    public Player getPlayer() {
        return player;
    }

    public Fight getFight() {
        return fight;
    }

}
