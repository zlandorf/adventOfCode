package com.zlandorf.adventOfCode.problems.day7.gates;

import com.zlandorf.adventOfCode.problems.day7.Signal;
import com.zlandorf.adventOfCode.problems.day7.SignalSource;

public class NotGate implements SignalSource {
    private SignalSource signalSource;

    public NotGate(SignalSource signalSource) {
        this.signalSource = signalSource;
    }

    @Override
    public Signal getSignal() {
        return new Signal(~signalSource.getSignal().getValue());
    }
}
