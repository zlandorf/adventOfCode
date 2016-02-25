package com.zlandorf.adventOfCode.problems.day7;

import com.zlandorf.adventOfCode.problems.day7.factoryhandlers.*;
import com.zlandorf.adventOfCode.problems.day7.sources.CachedSignalSource;

public class SignalSourceFactory {

    private FactoryHandler handler;

    public SignalSourceFactory(WireFactory wireFactory) {
        this.handler = new FactoryHandlerBuilder()
            .first(new SignalFactoryHandler())
            .then(new NotGateFactoryHandler(this))
            .then(new AndGateFactoryHandler(this))
            .then(new OrGateFactoryHandler(this))
            .then(new LShiftGateFactoryHandler(this))
            .then(new RShiftGateFactoryHandler(this))
            .then(new WireFactoryHandler(wireFactory))
            .build();
    }

    public SignalSource getSignalSource(String rawSource) {
        return new CachedSignalSource(handler.handle(rawSource));
    }
}
