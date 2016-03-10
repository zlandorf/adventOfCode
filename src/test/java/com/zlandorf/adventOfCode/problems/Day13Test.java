package com.zlandorf.adventOfCode.problems;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day13Test {
    @Test
    public void solveFirst() throws Exception {
        Day13 day13 = new Day13();
        assertEquals(330, (int)day13.solveFirst());
    }
}
