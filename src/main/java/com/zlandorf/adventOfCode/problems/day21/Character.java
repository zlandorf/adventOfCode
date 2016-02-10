package com.zlandorf.adventOfCode.problems.day21;

public class Character {
    private int hitpoints;
    private int damage;
    private int armor;

    public Character(int hitpoints, int damage, int armor) {
        this.hitpoints = hitpoints;
        this.damage = damage;
        this.armor = armor;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public void takeDamage(int damage) {
        hitpoints -= Math.max(1, damage - armor);
    }
}
