package com.zlandorf.adventOfCode.problems.day6.partOne;

import com.zlandorf.adventOfCode.problems.day6.*;

public class PartOneActionFactory implements ActionFactory<Boolean> {
    @Override
    public Action<Boolean> newInstance(String action) {
        switch (action) {
            case "turn on" :
                return new TurnOn();
            case "turn off" :
                return new TurnOff();
            case "toggle" :
                return new Toggle();
            default:
                throw new RuntimeException(String.format("phrase not recognised %s", action));
        }
    }
}
