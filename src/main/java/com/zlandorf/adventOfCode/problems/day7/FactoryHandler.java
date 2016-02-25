package com.zlandorf.adventOfCode.problems.day7;

public interface FactoryHandler {
    SignalSource handle(String rawSource);
    void setSuccessor(FactoryHandler successor);
}
