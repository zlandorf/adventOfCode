package com.zlandorf.adventOfCode.problems;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10Test {
    @Test
    public void testFirstIteration() {
        Day10 problem = new Day10();
        assertEquals("11", problem.process("1"));
    }

    @Test
    public void testSecondIteration() {
        Day10 problem = new Day10();
        assertEquals("21", problem.process("11"));
    }

    @Test
    public void testThirdIteration() {
        Day10 problem = new Day10();
        assertEquals("1211", problem.process("21"));
    }

    @Test
    public void testFourthIteration() {
        Day10 problem = new Day10();
        assertEquals("111221", problem.process("1211"));
    }

    @Test
    public void testFifthIteration() {
        Day10 problem = new Day10();
        assertEquals("312211", problem.process("111221"));
    }
}