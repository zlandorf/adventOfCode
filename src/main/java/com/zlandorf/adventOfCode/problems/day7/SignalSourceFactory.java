package com.zlandorf.adventOfCode.problems.day7;

import com.zlandorf.adventOfCode.problems.day7.gates.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignalSourceFactory {
    private static final Pattern SPECIFIC_SIGNAL_PATTERN = Pattern.compile("^(\\d+)$");
    private static final Pattern NOT_GATE_PATTERN = Pattern.compile("NOT (.+)");
    private static final Pattern AND_GATE_PATTERN = Pattern.compile("(.+) AND (.+)");
    private static final Pattern OR_GATE_PATTERN = Pattern.compile("(.+) OR (.+)");
    private static final Pattern LSHIFT_GATE_PATTERN = Pattern.compile("(.+) LSHIFT (\\d+)");
    private static final Pattern RSHIFT_GATE_PATTERN = Pattern.compile("(.+) RSHIFT (\\d+)");

    private Map<String, Wire> wireMap;

    public SignalSourceFactory(Map<String, Wire> wireMap) {
        this.wireMap = wireMap;
    }

    public SignalSource newInstance(String rawSource) {
        Matcher matcher;

        if ((matcher = SPECIFIC_SIGNAL_PATTERN.matcher(rawSource)).matches()) {
            return new Signal(Integer.valueOf(matcher.group(1)));

        } else if ((matcher = NOT_GATE_PATTERN.matcher(rawSource)).matches()) {
            return new NotGate(newInstance(matcher.group(1)));

        } else if ((matcher = AND_GATE_PATTERN.matcher(rawSource)).matches()) {
            return new AndGate(
                newInstance(matcher.group(1)),
                newInstance(matcher.group(2))
            );

        } else if ((matcher = OR_GATE_PATTERN.matcher(rawSource)).matches()) {
            return new OrGate(
                newInstance(matcher.group(1)),
                newInstance(matcher.group(2))
            );

        } else if ((matcher = LSHIFT_GATE_PATTERN.matcher(rawSource)).matches()) {
            return new LShiftGate(
                newInstance(matcher.group(1)),
                Integer.parseInt(matcher.group(2))
            );

        } else if ((matcher = RSHIFT_GATE_PATTERN.matcher(rawSource)).matches()) {
            return new RShiftGate(
                newInstance(matcher.group(1)),
                Integer.parseInt(matcher.group(2))
            );

        } else {
            if (!wireMap.containsKey(rawSource)) {
                wireMap.put(rawSource, new Wire(rawSource));
            }
            return wireMap.get(rawSource);
        }
    }
}
