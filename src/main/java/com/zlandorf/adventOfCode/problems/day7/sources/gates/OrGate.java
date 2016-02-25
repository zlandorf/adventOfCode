package com.zlandorf.adventOfCode.problems.day7.sources.gates;

import com.zlandorf.adventOfCode.problems.day7.sources.Signal;
import com.zlandorf.adventOfCode.problems.day7.SignalSource;

public class OrGate implements SignalSource {
    private SignalSource leftSource;
    private SignalSource rightSource;

    public OrGate(SignalSource leftSource, SignalSource rightSource) {
        this.leftSource = leftSource;
        this.rightSource = rightSource;
    }

    @Override
    public Signal getSignal() {
        return new Signal(leftSource.getSignal().getValue() | rightSource.getSignal().getValue());
    }
}
