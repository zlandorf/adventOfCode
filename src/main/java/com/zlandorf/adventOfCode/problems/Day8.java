package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.util.List;

public class Day8 implements AdventProblem {
    private List<String> strings;

    public Day8() throws Exception {
        strings = Files.readLines(new File(Resources.getResource("day8_input.txt").toURI()), Charsets.UTF_8);
    }

    @Override
    public int solveFirst() throws Exception {
        return strings.stream()
            .mapToInt(s -> s.length() - unescape(s).length())
            .sum();
    }

    @Override
    public int solveSecond() throws Exception {
        return strings.stream()
            .mapToInt(s -> escape(s).length() - s.length())
            .sum();
    }

    protected String escape(String string) {
        String escaped = string
            .replaceAll("\\\\", "\\\\\\\\")
            .replaceAll("\"", "\\\\\"");
        return String.format("\"%s\"", escaped);
    }

    protected String unescape(String string) {
        // replace all special chars with "a"
        return string
            .replaceAll("\\\\\\\\", "a")
            .replaceAll("(\\\\x[a-fA-F0-9]{2})", "a")
            .replaceAll("\\\\\"", "a")
            .replaceAll("\"", "");
    }
}
