package com.zlandorf.adventOfCode.problems.day6;

public interface Action<T> {
    T process(T light);
}
