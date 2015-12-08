package com.zlandorf.adventOfCode.problems.day6.partOne;

import com.zlandorf.adventOfCode.problems.day6.Action;

public class TurnOff implements Action<Boolean> {

    @Override
    public Boolean process(Boolean light) {
        return false;
    }
}
