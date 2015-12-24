package com.zlandorf.adventOfCode.problems;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day8Test {

    @Test
    public void testUnescapeEmptyString() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"\"";
        assertEquals(2, string.length());
        assertEquals(0, day8.unescape(string).length());
    }

    @Test
    public void testEscapeEmptyString() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"\"";
        assertEquals(2, string.length());
        assertEquals(6, day8.escape(string).length());
    }

    @Test
    public void testUnescapeHexadecimal() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"\\x27\"";
        assertEquals(6, string.length());
        assertEquals(1, day8.unescape(string).length());
    }

    @Test
    public void testEscapeHexadecimal() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"\\x27\"";
        assertEquals(6, string.length());
        assertEquals(11, day8.escape(string).length());
    }

    @Test
    public void testUnescapePhonyHexadecimal() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"\\\\x\\\\\"";
        assertEquals(7, string.length());
        assertEquals(3, day8.unescape(string).length());
    }


    @Test
    public void testEscapePhonyHexadecimal() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"\\\\x\\\\\"";
        assertEquals(7, string.length());
        assertEquals(15, day8.escape(string).length());
    }

    @Test
    public void testUnescapeDoubleQuote() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"aaa\\\"aaa\"";
        assertEquals(10, string.length());
        assertEquals(7, day8.unescape(string).length());
    }

    @Test
    public void testEscapeDoubleQuote() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"aaa\\\"aaa\"";
        assertEquals(10, string.length());
        assertEquals(16, day8.escape(string).length());
    }

    @Test
    public void testUnescapeBackslash() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"aa\\\"";
        assertEquals(5, string.length());
        assertEquals(3, day8.unescape(string).length());
    }

    @Test
    public void testEscapeBackslash() throws Exception {
        Day8 day8 = new Day8();
        String string = "\"aa\\\"";
        assertEquals(5, string.length());
        assertEquals(10, day8.escape(string).length());
    }

    @Test
    public void testSolveFirst() throws Exception {
        Day8 day8 = new Day8();
        assertEquals(12, (int)day8.solveFirst());
    }

    @Test
    public void testSolveSecond() throws Exception {
        Day8 day8 = new Day8();
        assertEquals(19, (int)day8.solveSecond());
    }
}