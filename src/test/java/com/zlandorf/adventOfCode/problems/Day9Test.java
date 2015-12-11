package com.zlandorf.adventOfCode.problems;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day9Test {

    @Test
    public void testSolveFirst() throws Exception {
        Day9 day9 = new Day9();
        assertEquals(605, day9.solveFirst());
    }

    @Test
    public void testSolveSecond() throws Exception {
        Day9 day9 = new Day9();
        assertEquals(982, day9.solveSecond());
    }
}