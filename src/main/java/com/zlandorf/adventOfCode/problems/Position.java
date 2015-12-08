package com.zlandorf.adventOfCode.problems;

public class Position {
    public Integer x;
    public Integer y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", x, y);
    }
}
