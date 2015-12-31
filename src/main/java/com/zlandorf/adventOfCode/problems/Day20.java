package com.zlandorf.adventOfCode.problems;

import com.zlandorf.adventOfCode.AdventProblem;

public class Day20 implements AdventProblem<Integer> {

    private static final Integer ONE_MILLION = 1000000;
    private static final Integer MAX_HOUSES = ONE_MILLION;

    @Override
    public Integer solveFirst() throws Exception {
        int[] presentCounts = new int[MAX_HOUSES];
        for (int i = 0; i < presentCounts.length; i++) {
            presentCounts[i] = 0;
        }

        for (int elfNumber = 1; elfNumber < MAX_HOUSES; elfNumber++) {
            for (int i = elfNumber; i < MAX_HOUSES; i += elfNumber) {
                presentCounts[i] += elfNumber * 10;
            }
        }

        for (int i = 1; i < MAX_HOUSES; i++) {
            if (presentCounts[i] > 36000000) {
                return i;
            }
        }

        return null;
    }

    @Override
    public Integer solveSecond() throws Exception {
        int[] presentCounts = new int[MAX_HOUSES];
        int maximumHouseVisits = 50;
        for (int i = 0; i < presentCounts.length; i++) {
            presentCounts[i] = 0;
        }

        for (int elfNumber = 1; elfNumber < MAX_HOUSES; elfNumber++) {
            for (int i = elfNumber, j = 0; i < MAX_HOUSES && j < maximumHouseVisits; i += elfNumber, j++) {
                presentCounts[i] += elfNumber * 11;
            }
        }

        for (int i = 1; i < MAX_HOUSES; i++) {
            if (presentCounts[i] > 36000000) {
                return i;
            }
        }

        return null;
    }
}
