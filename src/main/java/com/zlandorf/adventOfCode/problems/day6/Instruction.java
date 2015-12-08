package com.zlandorf.adventOfCode.problems.day6;

import com.zlandorf.adventOfCode.problems.Position;

public class Instruction<T> {
    private Position start;
    private Position end;
    private Action<T> action;

    public Instruction(Position start, Position end, Action<T> action) {
        this.start = start;
        this.end = end;
        this.action = action;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public Action<T> getAction() {
        return action;
    }
}
