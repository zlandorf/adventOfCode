package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.util.List;

public class Day2 implements AdventProblem {

    private List<String> presentDimensionsList;

    public Day2() throws Exception {
        presentDimensionsList = Files.readLines(new File(Resources.getResource("day2_input.txt").toURI()), Charsets.UTF_8);
    }

    @Override
    public int solveFirst() throws Exception {
        int total = 0;
        for (String presentDimensions : presentDimensionsList) {
            String[] dimensions = presentDimensions.split("x");
            int l = Integer.valueOf(dimensions[0]);
            int w = Integer.valueOf(dimensions[1]);
            int h = Integer.valueOf(dimensions[2]);
            int surfaceRequired = 2*l*w + 2*w*h + 2*h*l;
            // add the area of the smallest side as slack
            surfaceRequired += Math.min(Math.min(l*w, w*h), h*l);

            total += surfaceRequired;
        }

        return total;
    }

    @Override
    public int solveSecond() throws Exception {
        int total = 0;
        for (String presentDimensions : presentDimensionsList) {
            String[] dimensions = presentDimensions.split("x");
            int l = Integer.valueOf(dimensions[0]);
            int w = Integer.valueOf(dimensions[1]);
            int h = Integer.valueOf(dimensions[2]);

            // Get the minimum distance for the ribbon (which is the size of the two smallest sides)
            int ribbonRequired = l * 2 + w * 2 + h * 2 - Math.max(Math.max(l, w), h) * 2;
            ribbonRequired += l * w * h;

            total += ribbonRequired;
        }

        return total;
    }
}
