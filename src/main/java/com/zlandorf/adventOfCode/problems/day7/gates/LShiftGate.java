package com.zlandorf.adventOfCode.problems.day7.gates;

import com.zlandorf.adventOfCode.problems.day7.Signal;
import com.zlandorf.adventOfCode.problems.day7.SignalSource;

public class LShiftGate implements SignalSource {
    private SignalSource signalSource;
    private int count;

    public LShiftGate(SignalSource signalSource, int count) {
        this.signalSource = signalSource;
        this.count = count;
    }

    @Override
    public Signal getSignal() {
        return new Signal(signalSource.getSignal().getValue() << count);
    }
}
