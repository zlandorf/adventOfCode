package com.zlandorf.adventOfCode.problems.day7;

import com.google.common.collect.Maps;
import com.zlandorf.adventOfCode.problems.day7.sources.Wire;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WireFactory {
    private static final Pattern INSTRUCTIONS_PATTERN = Pattern.compile("^(.*) -> (\\w+)$");

    private Map<String, Wire> wireMap;
    private SignalSourceFactory signalSourceFactory;

    public WireFactory() {
        this.wireMap = Maps.newHashMap();
        this.signalSourceFactory = new SignalSourceFactory(this);
    }

    public Wire getWireFromInstruction(String rawInstruction) {
        Matcher instructionMatcher = INSTRUCTIONS_PATTERN.matcher(rawInstruction);

        if (instructionMatcher.matches()) {
            SignalSource wireSource = signalSourceFactory.getSignalSource(instructionMatcher.group(1));
            String wireName = instructionMatcher.group(2);

            Wire wire = getWire(wireName);
            wire.setSignalSource(wireSource);
            return wire;
        } else {
            throw new RuntimeException(String.format("Failed to match instruction '%s'", rawInstruction));
        }
    }

    public Wire getWire(String wireName) {
        if (!wireMap.containsKey(wireName)) {
            wireMap.put(wireName, new Wire(wireName));
        }
        return wireMap.get(wireName);
    }


}
