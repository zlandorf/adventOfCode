package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day18 implements AdventProblem<Integer> {

    private static final int NUMBER_OF_STEPS = 100;
    protected boolean[][] lights;

    @Override
    public Integer solveFirst() throws Exception {
        initaliseLights();
        boolean[][] dst = new boolean[lights.length][lights[0].length];
        boolean[][] tmp;
        for (int i = 0; i < NUMBER_OF_STEPS; i++) {
            iterate(lights, dst);
            tmp = lights;
            lights = dst;
            dst = tmp;
        }

        return getLightsOnCount();
    }

    @Override
    public Integer solveSecond() throws Exception {
        initaliseLights();
        boolean[][] dst = new boolean[lights.length][lights[0].length];
        boolean[][] tmp;
        for (int i = 0; i < NUMBER_OF_STEPS; i++) {
            iterate(lights, dst);
            // four corners remain on all the time
            dst[0][0] = dst[lights.length - 1][0] = dst[0][lights[0].length - 1] = dst[lights.length - 1][lights[0].length - 1] = true;
            tmp = lights;
            lights = dst;
            dst = tmp;
        }

        return getLightsOnCount();
    }

    private Integer getLightsOnCount() {
        int lightsCount = 0;
        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[i].length; j++) {
                lightsCount += lights[i][j] ? 1 : 0;
            }
        }

        return lightsCount;
    }

    protected void iterate(boolean[][] src, boolean[][] dst) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                int numberOfLitNeighbours = numberOfLitNeighbours(src, i, j);
                dst[i][j] = src[i][j] && (numberOfLitNeighbours == 2 || numberOfLitNeighbours == 3)
                    || !src[i][j] && numberOfLitNeighbours == 3;
            }
        }
    }

    protected int numberOfLitNeighbours(boolean[][] lights, int iSrc, int jSrc) {
        int count = 0;
        for (int i = Math.max(0, iSrc - 1); i < Math.min(lights.length, iSrc + 2); i++) {
            for (int j = Math.max(0, jSrc - 1); j < Math.min(lights[i].length, jSrc + 2); j++) {
                if (i == iSrc && j == jSrc) {
                    continue;
                }
                count += lights[i][j] ? 1 : 0;
            }
        }
        return count;
    }


    protected void initaliseLights() throws IOException, URISyntaxException {
        List<String> lines = Files.readLines(new File(Resources.getResource("day18_input.txt").toURI()), Charsets.UTF_8);
        lights = new boolean[lines.size()][lines.size()];

        int i = 0;
        for (String line : lines) {
            for (int j = 0; j < line.length(); j++) {
                lights[i][j] = line.charAt(j) == '#';
            }
            i++;
        }
    }
}
