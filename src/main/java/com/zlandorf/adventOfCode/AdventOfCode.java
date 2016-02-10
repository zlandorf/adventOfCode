package com.zlandorf.adventOfCode;

import com.google.common.collect.Lists;
import com.zlandorf.adventOfCode.problems.*;

import java.util.List;

public class AdventOfCode {
    public static void main(String[] argv) throws Exception {
        List<AdventProblem> problems = Lists.newArrayList(
//            new Day1(),
//            new Day2(),
//            new Day3(),
////            new Day4(), // long to compute
//            new Day5(),
//            new Day6(),
//            new Day7(),
//            new Day8(),
//            new Day9(),
//            new Day10(),
//            new Day11(),
//            new Day12(),
//            new Day14(),
//            new Day15(),
//            new Day16(),
//            new Day17(),
//            new Day18(),
//            new Day19(),
//            new Day20(),
            new Day21()
        );

        problems.stream().forEach(problem -> {
            try {
                System.out.println("-----------------------------------------------------");
                System.out.println(String.format("%s first  : %s", problem.getClass().getSimpleName(), problem.solveFirst()));
                System.out.println(String.format("%s second : %s", problem.getClass().getSimpleName(), problem.solveSecond()));
            } catch (Exception e) {
                System.err.println("failed to run problem : " + problem.getClass().getSimpleName());
                e.printStackTrace();
            }
        });
    }


}
