package com.joana_reis.kirby_rpg.entities;

import com.joana_reis.kirby_rpg.enums.Attack;
import com.joana_reis.kirby_rpg.enums.Weakness;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FightTest {

    private Player player;
    private Monster monster;

    @Before
    public void setUpFight() {
        player = new Player("TesterPlayer", 1);
        monster = new Monster(Weakness.PHYSICAL_ATTACKS, "TesterMonster", "Hello", 100, 1);
    }

    @Test
    public void isOverTest(){

        Assert.assertEquals(player.getLife(),100);
        Assert.assertEquals(monster.getLife(),100);

        Fight fightTest = new Fight(player, monster);
        Assert.assertFalse(fightTest.isOver());

        player.setLife(0);
        Assert.assertTrue(fightTest.isOver());

        monster.setLife(0);
        player.setLife(100);
        Assert.assertTrue(fightTest.isOver());

        player.setLife(0);
        Assert.assertTrue(fightTest.isOver());
    }

    @Test
    public void playerAttacksTest(){

        Monster monsterA = new Monster(Weakness.PHYSICAL_ATTACKS, "MonsterA", "Hello", 100, 1);
        Monster monsterB = new Monster(Weakness.FIRE, "MonsterB", "Hello", 100, 1);
        Monster monsterC = new Monster(Weakness.ICE, "MonsterC", "Hello", 100, 1);
        Monster monsterD = new Monster(Weakness.THUNDER, "MonsterD", "Hello", 100, 1);

        Fight fight = new Fight(player, monsterA);
        fight.playerAttacks(Attack.SWORD_CHARGE);
        Assert.assertEquals(monsterA.getLife(), 100 - 20);
        fight.playerAttacks(Attack.ERUPTION);
        Assert.assertEquals(monsterA.getLife(), 100 - 20 - 10);
        fight.playerAttacks(Attack.BLIZZARD);
        Assert.assertEquals(monsterA.getLife(), 100 - 20 - 10 - 10 );
        fight.playerAttacks(Attack.LIGHTENING_ATTACK);
        Assert.assertEquals(monsterA.getLife(), 100 - 20 - 10 - 10 - 10);

        fight = new Fight(player, monsterB);
        fight.playerAttacks(Attack.SWORD_CHARGE);
        Assert.assertEquals(monsterB.getLife(), 100 - 10);
        fight.playerAttacks(Attack.ERUPTION);
        Assert.assertEquals(monsterB.getLife(), 100 - 10 - 20);
        fight.playerAttacks(Attack.BLIZZARD);
        Assert.assertEquals(monsterB.getLife(), 100 - 10 - 20 - 10 );
        fight.playerAttacks(Attack.LIGHTENING_ATTACK);
        Assert.assertEquals(monsterB.getLife(), 100 - 10 - 20 - 10 - 10);

        fight = new Fight(player, monsterC);
        fight.playerAttacks(Attack.SWORD_CHARGE);
        Assert.assertEquals(monsterC.getLife(), 100 - 10);
        fight.playerAttacks(Attack.ERUPTION);
        Assert.assertEquals(monsterC.getLife(), 100 - 10 - 10);
        fight.playerAttacks(Attack.BLIZZARD);
        Assert.assertEquals(monsterC.getLife(), 100 - 10 - 10 - 20 );
        fight.playerAttacks(Attack.LIGHTENING_ATTACK);
        Assert.assertEquals(monsterC.getLife(), 100 - 10 - 10 - 20 - 10);

        fight = new Fight(player, monsterD);
        fight.playerAttacks(Attack.SWORD_CHARGE);
        Assert.assertEquals(monsterD.getLife(), 100 - 10);
        fight.playerAttacks(Attack.ERUPTION);
        Assert.assertEquals(monsterD.getLife(), 100 - 10 - 10);
        fight.playerAttacks(Attack.BLIZZARD);
        Assert.assertEquals(monsterD.getLife(), 100 - 10 - 10 - 10 );
        fight.playerAttacks(Attack.LIGHTENING_ATTACK);
        Assert.assertEquals(monsterD.getLife(), 100 - 10 - 10 - 10 - 20);

    }

    @Test
    public void monsterAttacksTest(){

        Fight fight = new Fight(player, monster);
        Assert.assertEquals(player.getLife(), 100);
        fight.monsterAttacks();
        Assert.assertEquals(player.getLife(), 100 - 20);
    }
}
