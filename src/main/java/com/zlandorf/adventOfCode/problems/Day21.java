package com.zlandorf.adventOfCode.problems;

import com.google.common.collect.Lists;
import com.zlandorf.adventOfCode.AdventProblem;
import com.zlandorf.adventOfCode.problems.day21.Gear;
import com.zlandorf.adventOfCode.problems.day21.Item;
import com.zlandorf.adventOfCode.problems.day21.Character;

import java.util.List;

public class Day21 implements AdventProblem<Integer> {

    private static final int PLAYER_HITPOINTS = 100;

    private static final int BOSS_HITPOINTS = 100;
    private static final int BOSS_DAMAGE = 8;
    private static final int BOSS_ARMOR = 2;

    private List<Item> weapons = Lists.newArrayList(
        new Item("Dagger", 8, 4, 0),
        new Item("Shrotsword", 10, 5, 0),
        new Item("Warhammer", 25, 6, 0),
        new Item("Longsword", 40, 7, 0),
        new Item("Greataxe", 74, 8, 0)
    );

    private List<Item> armors = Lists.newArrayList(
        null,
        new Item("Leather", 13, 0, 1),
        new Item("Chainmail", 31, 0, 2),
        new Item("Splintmail", 53, 0, 3),
        new Item("Bandedmail", 75, 0, 4),
        new Item("Platemail", 102, 0, 5)
    );

    private List<Item> rings = Lists.newArrayList(
        null,
        null,
        new Item("Damage +1", 25, 1, 0),
        new Item("Damage +2", 50, 2, 0),
        new Item("Damage +3", 100, 3, 0),
        new Item("Armor +1", 20, 0, 1),
        new Item("Armor +2", 40, 0, 2),
        new Item("Armor +3", 80, 0, 3)
    );

    @Override
    public Integer solveFirst() throws Exception {
        int minimumCost = Integer.MAX_VALUE;
        for (Item weapon : weapons) {
            for (Item armor : armors) {
                for (int i = 0; i < rings.size(); i++) {
                    for (int j = i+1; j < rings.size(); j++) {
                        Gear gear = new Gear(weapon, armor, rings.get(i), rings.get(j));
                        Character player = new Character(PLAYER_HITPOINTS, gear.getDamage(), gear.getArmor());
                        Character boss = new Character(BOSS_HITPOINTS, BOSS_DAMAGE, BOSS_ARMOR);
                        if (fight(player, boss) && gear.getCost() < minimumCost) {
                            minimumCost = gear.getCost();
                        }
                    }
                }
            }
        }

        return minimumCost;
    }

    @Override
    public Integer solveSecond() throws Exception {
        int maximumCost = 0;
        for (Item weapon : weapons) {
            for (Item armor : armors) {
                for (int i = 0; i < rings.size(); i++) {
                    for (int j = i+1; j < rings.size(); j++) {
                        Gear gear = new Gear(weapon, armor, rings.get(i), rings.get(j));
                        Character player = new Character(PLAYER_HITPOINTS, gear.getDamage(), gear.getArmor());
                        Character boss = new Character(BOSS_HITPOINTS, BOSS_DAMAGE, BOSS_ARMOR);
                        if (!fight(player, boss) && gear.getCost() > maximumCost) {
                            maximumCost = gear.getCost();
                        }
                    }
                }
            }
        }

        return maximumCost;
    }

    private boolean fight(Character player, Character boss) {
        do {
            boss.takeDamage(player.getDamage());
            if (boss.getHitpoints() > 0) {
                player.takeDamage(boss.getDamage());
            }
        } while (player.getHitpoints() > 0 && boss.getHitpoints() > 0);

        return player.getHitpoints() > 0;
    }
}
