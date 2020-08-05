package com.joana_reis.kirby_rpg.entities;

import com.joana_reis.kirby_rpg.enums.Weakness;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MissionTest {

    private Player player;
    private Monster monster;
    private Mission mission;

    @Before
    public void setUpMission() {
        player = new Player("PlayerTest", 1);
        monster = new Monster(Weakness.THUNDER,"MonsterTest", "Hello", 100, 10 );
        mission = new Mission("MissionTest", "Hello","Goodbye", monster, player, 10);
    }

    @Test
    public void hasReachedLocationTest(){

        Assert.assertFalse(mission.hasReachedLocation());

        player.setPosition(10);
        Assert.assertTrue(mission.hasReachedLocation());

    }

    @Test
    public void endMissionTest(){

        Assert.assertEquals(player.getExperience(), 1);
        Assert.assertFalse(mission.isFinished());

        mission.endMission();

        Assert.assertEquals(player.getExperience(), 1 + 10);
        Assert.assertTrue(mission.isFinished());
    }

    @Test
    public void restartMissionTest(){

        player.setLife(50);

        Assert.assertEquals(player.getPosition(), 1);
        Assert.assertEquals(player.getLife(), 50);

        mission.restartMission();

        Assert.assertEquals(player.getPosition(), 2);
        Assert.assertEquals(player.getLife(), 100);
    }
}
