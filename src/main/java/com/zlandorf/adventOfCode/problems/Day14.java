package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.sun.javafx.geom.transform.Identity;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day14 implements AdventProblem<Integer> {

    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.");

    class ReindeerDescription {
        String name;
        int speed; //km/s
        int flyingTime; //s
        int restingTime; //s
    }

    private List<ReindeerDescription> reindeerDescriptions;

    public Day14() throws Exception {
        reindeerDescriptions = Lists.newArrayList();

        for (String description: Files.readLines(new File(Resources.getResource("day14_input.txt").toURI()), Charsets.UTF_8)) {
            ReindeerDescription reindeerDescription = new ReindeerDescription();
            Matcher matcher = DESCRIPTION_PATTERN.matcher(description);
            if (matcher.matches()) {
                reindeerDescription.name = matcher.group(1);
                reindeerDescription.speed = Integer.valueOf(matcher.group(2));
                reindeerDescription.flyingTime = Integer.valueOf(matcher.group(3));
                reindeerDescription.restingTime = Integer.valueOf(matcher.group(4));

                reindeerDescriptions.add(reindeerDescription);
            } else {
                throw new RuntimeException("Failed to match reindeer description " + description);
            }
        }
    }

    @Override
    public Integer solveFirst() throws Exception {
        return reindeerDescriptions
            .stream()
            .mapToInt(d -> getDistance(d, 2503))
            .max()
            .getAsInt();
    }

    @Override
    public Integer solveSecond() throws Exception {
        Map<String, Integer> points = reindeerDescriptions
            .stream()
            .collect(Collectors.toMap(d -> d.name, d -> 0));

        for (int i = 1; i <= 2503; i++) {
            final int seconds = i;
            Map<String, Integer> distances = reindeerDescriptions
                .stream()
                .collect(Collectors.toMap(d -> d.name, d -> getDistance(d, seconds)));

            int maxDistance = distances
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();

            points.keySet().stream().filter(reindeer -> distances.get(reindeer) >= maxDistance).forEach(reindeer -> {
                points.put(reindeer, points.get(reindeer) + 1);
            });
        }

        return points.values().stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    protected int getDistance(ReindeerDescription reindeerDescription, int seconds) {
        int distance = 0;
        do {
            distance += reindeerDescription.speed * Math.min(reindeerDescription.flyingTime, seconds);
            seconds -= reindeerDescription.flyingTime;
            seconds -= reindeerDescription.restingTime;
        } while (seconds > 0);

        return distance;
    }
}
