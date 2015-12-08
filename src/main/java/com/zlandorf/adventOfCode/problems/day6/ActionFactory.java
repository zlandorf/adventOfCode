package com.zlandorf.adventOfCode.problems.day6;

public interface ActionFactory<T> {
    Action<T> newInstance(String action);
}
