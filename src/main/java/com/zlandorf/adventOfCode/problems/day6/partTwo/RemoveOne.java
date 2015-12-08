package com.zlandorf.adventOfCode.problems.day6.partTwo;

import com.zlandorf.adventOfCode.problems.day6.Action;

public class RemoveOne implements Action<Integer> {
    @Override
    public Integer process(Integer light) {
        return Math.max(0, light - 1);
    }
}
