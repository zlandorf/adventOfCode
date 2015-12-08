package com.zlandorf.adventOfCode.problems.day6.partTwo;

import com.zlandorf.adventOfCode.problems.day6.Action;

public class CountLightIntensity implements Action<Integer> {
    private Integer count = 0;

    @Override
    public Integer process(Integer light) {
        count += light;
        return light;
    }

    public Integer getCount() {
        return count;
    }
}
