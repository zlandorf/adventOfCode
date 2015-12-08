package com.zlandorf.adventOfCode.problems.day7;

public class Signal implements SignalSource {
    private int value;

    public Signal(int value) {
        this.value = value & 0xffff;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Signal getSignal() {
        return this;
    }
}
