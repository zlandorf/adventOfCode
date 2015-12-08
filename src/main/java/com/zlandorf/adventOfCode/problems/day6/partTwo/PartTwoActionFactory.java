package com.zlandorf.adventOfCode.problems.day6.partTwo;

import com.zlandorf.adventOfCode.problems.day6.Action;
import com.zlandorf.adventOfCode.problems.day6.ActionFactory;

public class PartTwoActionFactory implements ActionFactory<Integer> {
    @Override
    public Action<Integer> newInstance(String action) {
        switch (action) {
            case "turn on" :
                return new AddOne();
            case "turn off" :
                return new RemoveOne();
            case "toggle" :
                return new AddTwo();
            default:
                throw new RuntimeException(String.format("phrase not recognised %s", action));
        }
    }
}
