package com.zlandorf.adventOfCode.problems.day6.partTwo;

import com.zlandorf.adventOfCode.problems.day6.Action;

public class AddTwo implements Action<Integer> {
    @Override
    public Integer process(Integer light) {
        return light + 2;
    }
}
