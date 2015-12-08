package com.zlandorf.adventOfCode.problems.day7;

public class CachedSignalSource implements SignalSource {
    private SignalSource signalSource;
    private Signal cache = null;

    public CachedSignalSource(SignalSource signalSource) {
        this.signalSource = signalSource;
    }

    @Override
    public Signal getSignal() {
        if (cache == null) {
            cache = signalSource.getSignal();
        }
        return cache;
    }
}
