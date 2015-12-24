package com.zlandorf.adventOfCode.problems;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day11Test {

    private Day11 problem;

    @Before
    public void setup() {
        problem = new Day11();
    }

    @Test
    public void meetsFirstRequirement() {
        assertTrue(problem.hasThreeLetterIncreasingStraight("hijklmmn"));
    }

    @Test
    public void meetsSecondRequirement() {
        assertFalse(problem.hasIllegalCharacter("abbceffg"));
    }

    @Test
    public void meetsThirdRequirement() {
        assertTrue(problem.hasDoublePair("abbceffg"));
    }

    @Test
    public void testFailsSecondRequirement() {
        assertFalse(problem.isPasswordValid("hijklmmn"));
    }

    @Test
    public void testFailsFirstRequirement() {
        assertFalse(problem.isPasswordValid("abbceffg"));
    }

    @Test
    public void testFailsThirdRequirement() {
        assertFalse(problem.isPasswordValid("abbcegjk"));
    }

    @Test
    public void testMeetsAllRequirements() {
        assertTrue(problem.isPasswordValid("abcdffaa"));
        assertTrue(problem.isPasswordValid("ghjaabcc"));
    }

    @Test
    public void testNext() {
        assertEquals("xy", problem.next("xx"));
        assertEquals("xz", problem.next("xy"));
        assertEquals("ya", problem.next("xz"));
        assertEquals("yb", problem.next("ya"));
    }

    @Test
    public void testNextValidPassword() {
        assertEquals("abcdffaa", problem.nextValidPassword("abcdefgh"));
    }

    @Test
    public void testNextValidPassword2() {
        assertEquals("ghjaabcc", problem.nextValidPassword("ghijklmn"));
    }

}