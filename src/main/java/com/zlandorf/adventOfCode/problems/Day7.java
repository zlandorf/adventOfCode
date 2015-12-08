package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;
import com.zlandorf.adventOfCode.problems.day7.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 implements AdventProblem {
    private static final Pattern INSTRUCTIONS_PATTERN = Pattern.compile("^(.*) -> (\\w+)$");

    private List<String> rawInstructions;

    public Day7() throws Exception {
        rawInstructions = Files.readLines(new File(Resources.getResource("day7_input.txt").toURI()), Charsets.UTF_8);
    }

    @Override
    public int solveFirst() throws Exception {
        Map<String, Wire> wireMap = Maps.newHashMap();
        SignalSourceFactory gateFactory = new SignalSourceFactory(wireMap);

        for (String rawInstruction : rawInstructions) {
            Matcher instructionMatcher = INSTRUCTIONS_PATTERN.matcher(rawInstruction);
            if (instructionMatcher.matches()) {
                SignalSource wireSource = new CachedSignalSource(gateFactory.newInstance(instructionMatcher.group(1)));
                String wireName = instructionMatcher.group(2);
                if (wireMap.containsKey(wireName)) {
                    wireMap.get(wireName).setSignalSource(wireSource);
                } else {
                    wireMap.put(wireName, new Wire(wireSource, wireName));
                }
            } else {
                throw new RuntimeException(String.format("Failed to match instruction '%s'", rawInstruction));
            }
        }

        return wireMap.get("a").getSignal().getValue();
    }

    @Override
    public int solveSecond() throws Exception {
        Map<String, Wire> wireMap = Maps.newHashMap();
        SignalSourceFactory gateFactory = new SignalSourceFactory(wireMap);

        for (String rawInstruction : rawInstructions) {
            if (rawInstruction.matches("^(\\d+) -> b$")) {
                // use the result of part one as the input for 'b' in part two
                rawInstruction = rawInstruction.replaceAll("(\\d+)", String.valueOf(solveFirst()));
            }

            Matcher instructionMatcher = INSTRUCTIONS_PATTERN.matcher(rawInstruction);
            if (instructionMatcher.matches()) {
                SignalSource wireSource = new CachedSignalSource(gateFactory.newInstance(instructionMatcher.group(1)));
                String wireName = instructionMatcher.group(2);
                if (wireMap.containsKey(wireName)) {
                    wireMap.get(wireName).setSignalSource(wireSource);
                } else {
                    wireMap.put(wireName, new Wire(wireSource, wireName));
                }
            } else {
                throw new RuntimeException(String.format("Failed to match instruction '%s'", rawInstruction));
            }
        }

        return wireMap.get("a").getSignal().getValue();
    }
}
