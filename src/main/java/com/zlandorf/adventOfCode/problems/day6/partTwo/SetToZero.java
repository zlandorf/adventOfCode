package com.zlandorf.adventOfCode.problems.day6.partTwo;

import com.zlandorf.adventOfCode.problems.day6.Action;

public class SetToZero implements Action<Integer> {
    @Override
    public Integer process(Integer light) {
        return 0;
    }
}
