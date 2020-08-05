package com.joana_reis.kirby_rpg.entities;

import java.io.Serializable;

public class Map implements Serializable {

    /** Exploration map **/
    private int[] map;
    private int n;
    private int m;

    public Map(int n, int m)  {

        this.n = n;
        this.m = m;

        int numberOfSites = n*m;
        map = new int[numberOfSites];

        for(int i = 0; i < map.length; i++){
            map[i] = 0;
        }

    }

    /** Method to track the player's movements **/
    public void updatePlayerPosition(Player player, int direction){

        int currentPosition = player.getPosition();

        // Left border limits
        if (direction == 1 && currentPosition % m != 0){
            map[currentPosition] = 2;
            currentPosition--;
            map[currentPosition] = 1;
            // Upper border limits
        } else if (direction == 2 && (currentPosition - m >= 0)) {
            map[currentPosition] = 2;
            currentPosition = currentPosition - m;
            map[currentPosition] = 1;
            // Right border limits
        } else if (direction == 3 && ((currentPosition + 1) % m != 0)) {
            map[currentPosition] = 2;
            currentPosition++;
            map[currentPosition] = 1;
            // Lower border limits
        } else if (direction == 4 && currentPosition + m < map.length) {
            map[currentPosition] = 2;
            currentPosition = currentPosition + m;
            map[currentPosition] = 1;
        } else {
            System.out.println("You can't move into this direction :(");
        }

        player.setPosition(currentPosition);
    }


    /** Method to print the map with player's position and sites player has explored **/
    public void printPosition() {

        for (int l = 0; l < map.length; l = l + m) {
            for (int k = l + 0; k < (map.length / n) + l; k++) {
                if (map[k] == 0) {
                    System.out.print(".");
                } else if (map[k] == 1) {
                    System.out.print("~");
                } else {
                    System.out.print("%");
                }
            }
            System.out.println();
        }
    }

    /** Method to reset the player's position (at the start of each mission) **/
    public void resetPlayerPosition(int position) {

        for(int i = 0; i < map.length; i++){
            map[i] = 0;
        }

        map[position] = 1;

    }

    /** Getters and Setters **/

    public int[] getMap() {
        return map;
    }

    public void setMap(int[] map) {
        this.map = map;
    }
}