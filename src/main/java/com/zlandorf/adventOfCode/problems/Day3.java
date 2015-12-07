package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Day3 implements AdventProblem {
    private static final String COORD_TEMPLATE = "%s,%s";

    private String directions;

    public Day3() throws Exception {
        directions = Files.toString(new File(Resources.getResource("day3_input.txt").toURI()), Charsets.UTF_8);
    }

    @Override
    public int solveFirst() throws Exception {
        Set<String> visitedCoords = new HashSet<>();
        Position position = new Position(0, 0);
        // the starting position gets a present
        visitedCoords.add(position.toString());

        for (char direction : directions.toCharArray()) {
            switch (direction) {
                case '<' :
                    position.x--;
                    break;
                case '>':
                    position.x++;
                    break;
                case 'v' :
                    position.y--;
                    break;
                case '^':
                    position.y++;
                    break;
                default:
                    throw new RuntimeException("Unrecognised direction");
            }
            visitedCoords.add(position.toString());
        }

        return visitedCoords.size();
    }

    @Override
    public int solveSecond() throws Exception {
        Set<String> visitedCoords = new HashSet<>();

        Position[] positions = new Position[] {
          new Position(0, 0),
          new Position(0, 0),
        };
        // the starting position gets a present
        visitedCoords.add(positions[0].toString());

        int i = 0;
        for (char direction : directions.toCharArray()) {
            Position position = positions[i++ & 1];
            switch (direction) {
                case '<' :
                    position.x--;
                    break;
                case '>':
                    position.x++;
                    break;
                case 'v' :
                    position.y--;
                    break;
                case '^':
                    position.y++;
                    break;
                default:
                    throw new RuntimeException("Unrecognised direction");
            }
            visitedCoords.add(position.toString());
        }

        return visitedCoords.size();
    }

    class Position {
        public Integer x;
        public Integer y;

        public Position(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format(COORD_TEMPLATE, x, y);
        }
    }
}
