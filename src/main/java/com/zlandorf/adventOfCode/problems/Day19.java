package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Day19 implements AdventProblem<Integer> {

    protected static final String INPUT = "ORnPBPMgArCaCaCaSiThCaCaSiThCaCaPBSiRnFArRnFArCaCaSiThCaCaSiThCaCaCaCaCaCaSiRnFYFArSiRnMgArCaSiRnPTiTiBFYPBFArSiRnCaSiRnTiRnFArSiAlArPTiBPTiRnCaSiAlArCaPTiTiBPMgYFArPTiRnFArSiRnCaCaFArRnCaFArCaSiRnSiRnMgArFYCaSiRnMgArCaCaSiThPRnFArPBCaSiRnMgArCaCaSiThCaSiRnTiMgArFArSiThSiThCaCaSiRnMgArCaCaSiRnFArTiBPTiRnCaSiAlArCaPTiRnFArPBPBCaCaSiThCaPBSiThPRnFArSiThCaSiThCaSiThCaPTiBSiRnFYFArCaCaPRnFArPBCaCaPBSiRnTiRnFArCaPRnFArSiRnCaCaCaSiThCaRnCaFArYCaSiRnFArBCaCaCaSiThFArPBFArCaSiRnFArRnCaCaCaFArSiRnFArTiRnPMgArF";

    protected Map<String, List<String>> replacements;

    public Day19() throws Exception {
        replacements = Maps.newHashMap();
        for (String line : Files.readLines(new File(Resources.getResource("day19_input.txt").toURI()), Charsets.UTF_8)) {
            String[] split = line.split(" => ");
            String key = split[0];
            String replacement = split[1];
            if (!replacements.containsKey(key)) {
                replacements.put(key, Lists.newArrayList());
            }
            replacements.get(key).add(replacement);
        }
    }

    @Override
    public Integer solveFirst() throws Exception {
        Set<String> replacementKeys = replacements
            .keySet()
            .stream()
            .sorted((s1, s2) -> s1.length() - s2.length())
            .collect(Collectors.toSet());
        Set<String> distinctMolecules = getDistinctMolecules(replacements, replacementKeys, INPUT);
        return distinctMolecules.size();
    }

    @Override
    public Integer solveSecond() throws Exception {
        Map<String, List<String>> reverseReplacements = Maps.newHashMap();
        for (String replacementKey : replacements.keySet()) {
            for (String replacement : replacements.get(replacementKey)) {
                if (!reverseReplacements.containsKey(replacement)) {
                    reverseReplacements.put(replacement, Lists.newArrayList());
                }
                reverseReplacements.get(replacement).add(replacementKey);
            }
        }

        Set<String> sortedReplacementKeys = reverseReplacements
            .keySet()
            .stream()
            .sorted((s1, s2) -> s1.length() - s2.length())
            .collect(Collectors.toSet());

        return find(reverseReplacements, sortedReplacementKeys, INPUT, 0);
    }

    protected Integer find(Map<String, List<String>> replacements, Set<String> sortedReplacementKeys, String source, int depth) {
        // Doesn't necessarily find the minimum number of steps for a generic input, but in the case of this problem, it does
        if (source.equals("e")) {
            return depth;
        }
        Set<String> possibleSources = getDistinctMolecules(replacements, sortedReplacementKeys, source);
        Set<String> sortedPossibleSources = possibleSources
            .stream()
            .sorted((s1, s2) -> s1.length() - s2.length())
            .collect(Collectors.toSet());

        for (String possibleSource : sortedPossibleSources) {
            Integer result = find(replacements, sortedReplacementKeys, possibleSource, depth + 1);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    protected Set<String> getDistinctMolecules(Map<String, List<String>> replacements, Set<String> sortedReplacementKeys, String source) {
        Set<String> distinctMolecules = Sets.newHashSet();

        for (int i = 0; i < source.length(); i++) {
            for (String replacementKey : sortedReplacementKeys) {
                if (source.startsWith(replacementKey, i)) {
                    for (String replacement : replacements.get(replacementKey)) {
                        String molecule = source.replaceFirst(source.substring(0, i) + replacementKey, source.substring(0, i) + replacement);
                        distinctMolecules.add(molecule);
                    }
                }
            }
        }
        return distinctMolecules;
    }

}
