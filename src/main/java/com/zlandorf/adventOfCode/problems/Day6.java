package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;
import com.zlandorf.adventOfCode.problems.day6.*;
import com.zlandorf.adventOfCode.problems.day6.partOne.*;
import com.zlandorf.adventOfCode.problems.day6.partTwo.CountLightIntensity;
import com.zlandorf.adventOfCode.problems.day6.partTwo.PartTwoActionFactory;
import com.zlandorf.adventOfCode.problems.day6.partTwo.SetToZero;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 implements AdventProblem {

    private static final Pattern INSTRUCTIONS_PATTERN = Pattern.compile("^(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)$");

    private List<String> rawInstructions;

    public Day6() throws Exception {
        rawInstructions = Files.readLines(new File(Resources.getResource("day6_input.txt").toURI()), Charsets.UTF_8);
    }

    @Override
    public int solveFirst() throws Exception {
        Boolean[][] lights = new Boolean[1000][1000];
        // turn off all lights first
        lights = execute(lights, new Instruction<>(new Position(0, 0), new Position(999, 999), new TurnOff()));

        for (Instruction<Boolean> instruction : parse(rawInstructions, new PartOneActionFactory())) {
            lights = execute(lights, instruction);
        }

        CountLights countLights = new CountLights();
        execute(lights, new Instruction<>(new Position(0, 0), new Position(999, 999), countLights));

        return countLights.getCount();
    }

    @Override
    public int solveSecond() throws Exception {
        Integer[][] lights = new Integer[1000][1000];
        // set all intensities to zero first
        lights = execute(lights, new Instruction<>(new Position(0, 0), new Position(999, 999), new SetToZero()));

        for (Instruction<Integer> instruction : parse(rawInstructions, new PartTwoActionFactory())) {
            lights = execute(lights, instruction);
        }

        CountLightIntensity countLightIntensity = new CountLightIntensity();
        execute(lights, new Instruction<>(new Position(0, 0), new Position(999, 999), countLightIntensity));

        return countLightIntensity.getCount();
    }

    private <T> T[][] execute(T[][] lights, Instruction<T> instruction) {
        for (int i = instruction.getStart().x; i <= instruction.getEnd().x; i++) {
            for (int j = instruction.getStart().y; j <= instruction.getEnd().y; j++) {
                lights[i][j] = instruction.getAction().process(lights[i][j]);
            }
        }
        return lights;
    }

    private <T> List<Instruction<T>> parse(List<String> rawInstructions, ActionFactory<T> actionFactory) throws Exception {
        List<Instruction<T>> instructions = Lists.newArrayList();
        Matcher instructionMatcher;
        for (String rawInstruction : rawInstructions) {
            instructionMatcher = INSTRUCTIONS_PATTERN.matcher(rawInstruction);

            if (instructionMatcher.matches()) {
                Position start = new Position(
                    Integer.valueOf(instructionMatcher.group(2)),
                    Integer.valueOf(instructionMatcher.group(3))
                );

                Position end = new Position(
                    Integer.valueOf(instructionMatcher.group(4)),
                    Integer.valueOf(instructionMatcher.group(5))
                );

                instructions.add(new Instruction<>(start, end, actionFactory.newInstance(instructionMatcher.group(1))));
            } else {
                throw new RuntimeException(String.format("instruction '%s' didn't match with pattern", rawInstruction));
            }
        }

        return instructions;
    }
}
