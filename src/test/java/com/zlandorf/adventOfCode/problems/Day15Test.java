package com.zlandorf.adventOfCode.problems;

import com.google.common.collect.Lists;
import com.zlandorf.adventOfCode.problems.day15.Ingredient;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day15Test {

    @Test
    public void testSolveFirst() throws Exception {
        Day15 problem = new Day15();
        problem.kitchenIngredients = Lists.newArrayList(
            new Ingredient("Butterscotch", -1, -2, 6, 3, 8),
            new Ingredient("Cinnamon", 2, 3, -2, -1, 3)
        );

        assertEquals(62842880, (int)problem.solveFirst());
    }
}