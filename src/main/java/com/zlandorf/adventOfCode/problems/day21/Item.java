package com.zlandorf.adventOfCode.problems.day21;

public class Item {
    private String name;
    private int damage;
    private int armor;
    private int cost;

    public Item(String name, int cost, int damage, int armor) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return damage == item.damage
            && armor == item.armor
            && cost == item.cost
            && !(name != null ? !name.equals(item.name) : item.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
