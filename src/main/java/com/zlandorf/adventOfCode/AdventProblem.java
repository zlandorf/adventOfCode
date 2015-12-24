package com.zlandorf.adventOfCode;

public interface AdventProblem<T> {
    T solveFirst() throws Exception;
    T solveSecond() throws Exception;
}
