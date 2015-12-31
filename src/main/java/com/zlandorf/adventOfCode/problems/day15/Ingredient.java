package com.zlandorf.adventOfCode.problems.day15;

public class Ingredient {
    public String name;
    public int capacity;
    public int durability;
    public int flavor;
    public int texture;
    public int calories;

    public Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
        this.name = name;
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;
    }

}
