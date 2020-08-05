package com.joana_reis.kirby_rpg.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

    private Map map;
    private Player player;

    @Before
    public void setUpMap() {
        map = new Map(3, 3);
        player = new Player("PlayerTest", 1);
    }

    @Test
    public void updatePlayerPositionTest(){

        int[] mapTest = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        Assert.assertArrayEquals(mapTest, map.getMap());

        map.resetPlayerPosition(player.getPosition());
        mapTest[1] = 1;
        Assert.assertArrayEquals(mapTest, map.getMap());

        // Directions: 1 - left, 2 - up, 3 - right, 4 - down
        // Player cannot go up
        map.updatePlayerPosition(player, 2);
        Assert.assertArrayEquals(mapTest, map.getMap());

        map.updatePlayerPosition(player, 1);
        mapTest[1] = 2;
        mapTest[0] = 1;
        Assert.assertArrayEquals(mapTest, map.getMap());

        // Player cannot go left
        map.updatePlayerPosition(player, 1);
        Assert.assertArrayEquals(mapTest, map.getMap());

        map.updatePlayerPosition(player, 4);
        mapTest[0] = 2;
        mapTest[3] = 1;
        Assert.assertArrayEquals(mapTest, map.getMap());

        map.updatePlayerPosition(player, 4);
        mapTest[3] = 2;
        mapTest[6] = 1;
        Assert.assertArrayEquals(mapTest, map.getMap());

        // Player cannot go down
        map.updatePlayerPosition(player, 4);
        Assert.assertArrayEquals(mapTest, map.getMap());

        map.updatePlayerPosition(player, 3);
        mapTest[6] = 2;
        mapTest[7] = 1;
        Assert.assertArrayEquals(mapTest, map.getMap());

        map.updatePlayerPosition(player, 3);
        mapTest[7] = 2;
        mapTest[8] = 1;
        Assert.assertArrayEquals(mapTest, map.getMap());

        // Player cannot go right
        map.updatePlayerPosition(player, 3);
        Assert.assertArrayEquals(mapTest, map.getMap());

    }

    @Test
    public void resetPlayerPositionTest(){

        int[] mapTest = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        map.resetPlayerPosition(player.getPosition());
        mapTest[1] = 1;

       Assert.assertArrayEquals(mapTest, map.getMap());
    }
}
