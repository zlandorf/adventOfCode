package com.zlandorf.adventOfCode.problems;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zlandorf.adventOfCode.AdventProblem;

import java.util.List;
import java.util.Map;

public class Day17 implements AdventProblem<Integer> {

    private static final int EGGNOGG_QUANTITY = 150;
    private static final int[] primeNumbers = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71};

    protected List<Integer> containers;
    protected Map<Integer, Integer> combinations;

    public Day17() {
        containers = Lists.newArrayList(50,44,11,49,42,46,18,32,26,40,21,7,18,43,10,47,36,24,22,40);
    }

    @Override
    public Integer solveFirst() throws Exception {
        if (combinations == null) {
            combinations = getAllCombinations();
        }
        return combinations.keySet().size();
    }

    @Override
    public Integer solveSecond() throws Exception {
        if (combinations == null) {
            combinations = getAllCombinations();
        }
        int minimumContainerCount = combinations
            .values()
            .stream()
            .mapToInt(Integer::intValue)
            .min()
            .getAsInt();

        return (int) combinations
            .values()
            .stream()
            .filter(e -> e == minimumContainerCount)
            .count();
    }

    protected Map<Integer, Integer> getAllCombinations() {
        Map<Integer, Integer> combinations = Maps.newHashMap();
        for (int i = 0; i < containers.size(); i++) {
            List<Integer> visited = Lists.newArrayList();
            visited.add(i);
            getTreeCombinations(combinations, visited, containers.get(i));
        }
        return combinations;
    }

    protected void getTreeCombinations(Map<Integer, Integer> combinations, List<Integer> visited, int currentSum) {
        if (currentSum == EGGNOGG_QUANTITY) {
            int hash = 1;
            for (int visitedIndex : visited) {
                hash *= primeNumbers[visitedIndex];
            }
            if (combinations.containsKey(hash)) {
                combinations.put(hash, Math.min(combinations.get(hash), visited.size()));
            } else {
                combinations.put(hash, visited.size());
            }
        }

        if (visited.size() == containers.size() || currentSum >= EGGNOGG_QUANTITY) {
            return;
        }

        for (int i = 0; i < containers.size(); i++) {
            if (!visited.contains(i)) {
                List<Integer> newVisitedList = Lists.newArrayList(visited);
                newVisitedList.add(i);
                getTreeCombinations(combinations, newVisitedList, currentSum + containers.get(i));
            }
        }
    }
}
