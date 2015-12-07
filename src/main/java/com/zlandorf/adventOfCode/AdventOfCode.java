package com.zlandorf.adventOfCode;

import com.google.common.collect.Lists;
import com.zlandorf.adventOfCode.problems.Day1;
import com.zlandorf.adventOfCode.problems.Day2;
import com.zlandorf.adventOfCode.problems.Day3;
import com.zlandorf.adventOfCode.problems.Day5;

import java.util.List;

public class AdventOfCode {
    public static void main(String[] argv) throws Exception {
        List<AdventProblem> problems = Lists.newArrayList(
            new Day1(),
            new Day2(),
            new Day3(),
//            new Day4() // long to compute
            new Day5()
        );

        problems.stream().forEach(problem -> {
            try {
                System.out.println("-----------------------------------------------------");
                System.out.println(String.format("%s first  : %s", problem.getClass().getSimpleName(), problem.solveFirst()));
                System.out.println(String.format("%s second : %s", problem.getClass().getSimpleName(), problem.solveSecond()));
            } catch (Exception e) {
                System.err.println("failed to run problem : " + problem.getClass().getSimpleName());
            }
        });
    }


}
