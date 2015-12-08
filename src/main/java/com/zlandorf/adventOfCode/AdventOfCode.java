package com.zlandorf.adventOfCode;

import com.google.common.collect.Lists;
import com.zlandorf.adventOfCode.problems.*;

import java.util.List;

public class AdventOfCode {
    public static void main(String[] argv) throws Exception {
        List<AdventProblem> problems = Lists.newArrayList(
            new Day1(),
            new Day2(),
            new Day3(),
//            new Day4(), // long to compute
            new Day5(),
            new Day6()
        );

        problems.stream().forEach(problem -> {
            try {
                System.out.println("-----------------------------------------------------");
                System.out.println(String.format("%s first  : %d", problem.getClass().getSimpleName(), problem.solveFirst()));
                System.out.println(String.format("%s second : %d", problem.getClass().getSimpleName(), problem.solveSecond()));
            } catch (Exception e) {
                System.err.println("failed to run problem : " + problem.getClass().getSimpleName());
                e.printStackTrace();
            }
        });
    }


}
