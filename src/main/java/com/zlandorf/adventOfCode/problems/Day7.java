package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;
import com.zlandorf.adventOfCode.problems.day7.WireFactory;
import com.zlandorf.adventOfCode.problems.day7.sources.Wire;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Day7 implements AdventProblem<Integer> {
    private List<String> rawInstructions;

    public Day7() throws Exception {
        rawInstructions = Files.readLines(new File(Resources.getResource("day7_input.txt").toURI()), Charsets.UTF_8);
    }

    @Override
    public Integer solveFirst() throws Exception {
        Map<String, Wire> wireMap = Maps.newHashMap();
        WireFactory wireFactory = new WireFactory();

        for (String rawInstruction : rawInstructions) {
            Wire wire = wireFactory.getWireFromInstruction(rawInstruction);
            wireMap.put(wire.getName(), wire);
        }

        return wireMap.get("a").getSignal().getValue();
    }

    @Override
    public Integer solveSecond() throws Exception {
        Map<String, Wire> wireMap = Maps.newHashMap();
        WireFactory wireFactory = new WireFactory();

        for (String rawInstruction : rawInstructions) {
            if (rawInstruction.matches("^(\\d+) -> b$")) {
                // use the result of part one as the input for 'b' in part two
                rawInstruction = rawInstruction.replaceAll("(\\d+)", String.valueOf(solveFirst()));
            }

            Wire wire = wireFactory.getWireFromInstruction(rawInstruction);
            wireMap.put(wire.getName(), wire);
        }

        return wireMap.get("a").getSignal().getValue();
    }
}
