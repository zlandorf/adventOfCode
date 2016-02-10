package com.zlandorf.adventOfCode.problems.day21;

public class Gear {
    private Item weapon;
    private Item armor;
    private Item leftHandRing;
    private Item rightHandRing;
    private int cost;

    public Gear(Item weapon, Item armor, Item leftHandRing, Item rightHandRing) {
        this.weapon = weapon;
        this.armor = armor;
        this.leftHandRing = leftHandRing;
        this.rightHandRing = rightHandRing;
    }

    public int getDamage() {
        return weapon.getDamage()
            + (leftHandRing != null ? leftHandRing.getDamage() : 0)
            + (rightHandRing != null ? rightHandRing.getDamage() : 0);
    }

    public int getArmor() {
        return (armor != null ? armor.getArmor() : 0)
            + (leftHandRing != null ? leftHandRing.getArmor() : 0)
            + (rightHandRing != null ? rightHandRing.getArmor() : 0);
    }

    public int getCost() {
        return weapon.getCost()
            + (armor != null ? armor.getCost() : 0)
            + (leftHandRing != null ? leftHandRing.getCost() : 0)
            + (rightHandRing != null ? rightHandRing.getCost() : 0);
    }
}
