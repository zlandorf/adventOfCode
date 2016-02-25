package com.zlandorf.adventOfCode.problems.day7;

public class FactoryHandlerBuilder {
    private FactoryHandler handler;
    private FactoryHandler last;

    public FactoryHandlerBuilder first(FactoryHandler handler) {
        this.handler = handler;
        this.last = handler;
        return this;
    }

    public FactoryHandlerBuilder then(FactoryHandler handler) {
        this.last.setSuccessor(handler);
        this.last = handler;
        return this;
    }

    public FactoryHandler build() {
        return this.handler;
    }
}
