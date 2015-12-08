package com.zlandorf.adventOfCode.problems.day6.partOne;

import com.zlandorf.adventOfCode.problems.day6.Action;

public class Toggle implements Action<Boolean> {
    @Override
    public Boolean process(Boolean light) {
        return !light;
    }
}
