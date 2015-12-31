package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 implements AdventProblem<Integer> {

    private static final Pattern AUNT_SUE_PATTERN = Pattern.compile("Sue (\\d+): (\\w+): (\\d+), (\\w+): (\\d+), (\\w+): (\\d+)");

    protected Map<Integer, Map<String, Integer>> auntSues;

    public Day16() throws Exception {
        this.auntSues = Maps.newHashMap();
        for (String line : Files.readLines(new File(Resources.getResource("day16_input.txt").toURI()), Charsets.UTF_8)) {
            Matcher matcher = AUNT_SUE_PATTERN.matcher(line);
            if (matcher.matches()) {
                int sueNumber = Integer.valueOf(matcher.group(1));
                Map<String, Integer> attributes = Maps.newHashMap();

                attributes.put(matcher.group(2), Integer.valueOf(matcher.group(3)));
                attributes.put(matcher.group(4), Integer.valueOf(matcher.group(5)));
                attributes.put(matcher.group(6), Integer.valueOf(matcher.group(7)));

                auntSues.put(sueNumber, attributes);
            } else {
                throw new RuntimeException("Couldn't match line " + line);
            }
        }
    }

    @Override
    public Integer solveFirst() throws Exception {
        Map<String, Integer> mfcsamAttributes = Maps.newHashMap();
        mfcsamAttributes.put("children", 3);
        mfcsamAttributes.put("cats", 7);
        mfcsamAttributes.put("samoyeds", 2);
        mfcsamAttributes.put("pomeranians", 3);
        mfcsamAttributes.put("akitas", 0);
        mfcsamAttributes.put("vizslas", 0);
        mfcsamAttributes.put("goldfish", 5);
        mfcsamAttributes.put("trees", 3);
        mfcsamAttributes.put("cars", 2);
        mfcsamAttributes.put("perfumes", 1);

        int matchingSue = 0;
        for (Integer sue : auntSues.keySet()) {
            Map<String, Integer> attributes = auntSues.get(sue);
            boolean matches = true;
            for (String key : attributes.keySet()) {
                if (!mfcsamAttributes.get(key).equals(attributes.get(key))) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                matchingSue = sue;
            }
        }

        return matchingSue;
    }

    @Override
    public Integer solveSecond() throws Exception {
        Map<String, Integer> mfcsamAttributes = Maps.newHashMap();
        mfcsamAttributes.put("children", 3);
        mfcsamAttributes.put("cats", 7);
        mfcsamAttributes.put("samoyeds", 2);
        mfcsamAttributes.put("pomeranians", 3);
        mfcsamAttributes.put("akitas", 0);
        mfcsamAttributes.put("vizslas", 0);
        mfcsamAttributes.put("goldfish", 5);
        mfcsamAttributes.put("trees", 3);
        mfcsamAttributes.put("cars", 2);
        mfcsamAttributes.put("perfumes", 1);

        int matchingSue = 0;
        mainLoop: for (Integer sue : auntSues.keySet()) {
            Map<String, Integer> sueAttributes = auntSues.get(sue);
            for (String key : sueAttributes.keySet()) {
                boolean matches;
                Integer mfcsamValue = mfcsamAttributes.get(key);
                Integer sueValue = sueAttributes.get(key);

                switch (key) {
                    case "cats":
                    case "trees":
                        matches = mfcsamValue < sueValue;
                        break;
                    case "pomeranians":
                    case "goldfish":
                        matches = mfcsamValue > sueValue;
                        break;
                    default:
                        matches = mfcsamValue.intValue() == sueValue.intValue();
                        break;
                }

                if (!matches) {
                    continue mainLoop;
                }
            }
            matchingSue = sue;
            break;
        }

        return matchingSue;
    }
}
