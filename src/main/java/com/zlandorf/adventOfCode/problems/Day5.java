package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.util.List;

public class Day5 implements AdventProblem {


    @Override
    public String solveFirst() throws Exception {
        List<String> strings = Files.readLines(new File(Resources.getResource("day5_input.txt").toURI()), Charsets.UTF_8);

        long count = strings.stream()
            .filter(Day5::hasThreeVowels)
            .filter(Day5::hasDoubleLetter)
            .filter(Day5::hasNoBadString)
            .count();

        return String.valueOf(count);
    }

    @Override
    public String solveSecond() throws Exception {
        List<String> strings = Files.readLines(new File(Resources.getResource("day5_input.txt").toURI()), Charsets.UTF_8);

        long count = strings.stream()
            .filter(Day5::hasDoublePair)
            .filter(Day5::hasDoubleLetterWithLetterInBetween)
            .count();

        return String.valueOf(count);
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
