package com.zlandorf.adventOfCode.problems.day6.partOne;

import com.zlandorf.adventOfCode.problems.day6.Action;

public class CountLights implements Action<Boolean> {
    private int count = 0;

    @Override
    public Boolean process(Boolean light) {
        if (light) {
            count++;
        }
        return light;
    }

    public int getCount() {
        return count;
    }
}
