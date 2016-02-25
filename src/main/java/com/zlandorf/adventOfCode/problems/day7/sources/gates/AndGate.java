package com.zlandorf.adventOfCode.problems.day7.sources.gates;

import com.zlandorf.adventOfCode.problems.day7.sources.Signal;
import com.zlandorf.adventOfCode.problems.day7.SignalSource;

public class AndGate implements SignalSource {
    private SignalSource leftSource;
    private SignalSource rightSource;

    public AndGate(SignalSource leftWire, SignalSource rightWire) {
        this.leftSource = leftWire;
        this.rightSource = rightWire;
    }

    @Override
    public Signal getSignal() {
        return new Signal(leftSource.getSignal().getValue() & rightSource.getSignal().getValue());
    }
}
