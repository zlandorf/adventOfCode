package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.util.List;

public class Day5 implements AdventProblem<Integer> {

    private List<String> strings;

    public Day5() throws Exception {
        strings = Files.readLines(new File(Resources.getResource("day5_input.txt").toURI()), Charsets.UTF_8);
    }

    @Override
    public Integer solveFirst() throws Exception {
        return (int) strings.stream()
            .filter(Day5::hasThreeVowels)
            .filter(Day5::hasDoubleLetter)
            .filter(Day5::hasNoBadString)
            .count();
    }

    @Override
    public Integer solveSecond() throws Exception {
        return (int) strings.stream()
            .filter(Day5::hasDoublePair)
            .filter(Day5::hasDoubleLetterWithLetterInBetween)
            .count();
    }

    static boolean hasThreeVowels(String str) {
        return str.matches(".*([aeiou].*){3,}.*");
    }

    static boolean hasDoubleLetter(String str) {
        return str.matches(".*(.)\\1.*");
    }

    static boolean hasNoBadString(String str) {
        return !str.matches(".*(ab|cd|pq|xy)+.*");
    }

    static boolean hasDoublePair(String str) {
        return str.matches(".*(..).*\\1.*");
    }

    static boolean hasDoubleLetterWithLetterInBetween(String str) {
        return str.matches(".*(.).\\1.*");
    }
}
