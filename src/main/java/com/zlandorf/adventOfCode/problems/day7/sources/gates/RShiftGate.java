package com.zlandorf.adventOfCode.problems.day7.sources.gates;

import com.zlandorf.adventOfCode.problems.day7.sources.Signal;
import com.zlandorf.adventOfCode.problems.day7.SignalSource;

public class RShiftGate implements SignalSource {
    private SignalSource signalSource;
    private int count;

    public RShiftGate(SignalSource signalSource, int count) {
        this.signalSource = signalSource;
        this.count = count;
    }

    @Override
    public Signal getSignal() {
        return new Signal(signalSource.getSignal().getValue() >> count);
    }
}
