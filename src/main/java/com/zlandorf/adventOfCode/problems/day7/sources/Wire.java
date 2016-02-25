package com.zlandorf.adventOfCode.problems.day7.sources;

import com.zlandorf.adventOfCode.problems.day7.SignalSource;

public class Wire implements SignalSource {
    private SignalSource signalSource;
    private String name;

    public Wire(SignalSource signalSource, String name) {
        this.signalSource = signalSource;
        this.name = name;
    }

    public Wire(String name) {
        this.name = name;
    }

    public void setSignalSource(SignalSource signalSource) {
        this.signalSource = signalSource;
    }

    public Signal getSignal() {
        if (signalSource != null) {
            return signalSource.getSignal();
        } else {
            return null;
        }
    }

    public String getName() {
        return name;
    }
}
