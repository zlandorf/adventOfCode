package com.zlandorf.adventOfCode.problems;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day5Test {

    @Test
    public void hasThreeVowels() throws Exception {
        assertTrue(Day5.hasThreeVowels("aaa"));
        assertTrue(Day5.hasThreeVowels("ugknbfddgicrmopn"));
        assertFalse(Day5.hasThreeVowels("dvszwmarrgswjxmb"));
    }

    @Test
    public void hasDoubleLetter() throws Exception {
        assertTrue(Day5.hasDoubleLetter("aaa"));
        assertTrue(Day5.hasDoubleLetter("ugknbfddgicrmopn"));
        assertFalse(Day5.hasDoubleLetter("jchzalrnumimnmhp"));
    }

    @Test
    public void hasNoBadString() throws Exception {
        assertTrue(Day5.hasNoBadString("ugknbfddgicrmopn"));
        assertFalse(Day5.hasNoBadString("haegwjzuvuyypxyu"));
    }

    @Test
    public void testSolveFirst() throws Exception {
        Day5 day5 = new Day5();

        assertEquals("2", day5.solveFirst());
    }

    @Test
    public void testDoublePair() throws Exception {
        assertTrue(Day5.hasDoublePair("xyxy"));
        assertTrue(Day5.hasDoublePair("aabcdefgaa"));
        assertFalse(Day5.hasDoublePair("aaa"));
        assertFalse(Day5.hasDoublePair("aabcdefga"));
    }

    @Test
    public void testDoubleWithLetterInBetween() throws  Exception {
        assertTrue(Day5.hasDoubleLetterWithLetterInBetween("abcdefeghi"));
        assertTrue(Day5.hasDoubleLetterWithLetterInBetween("xyx"));
        assertFalse(Day5.hasDoubleLetterWithLetterInBetween("abcdeeghi"));
    }
}