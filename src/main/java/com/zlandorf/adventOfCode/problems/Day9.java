package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;
import com.zlandorf.adventOfCode.problems.day9.Edge;
import com.zlandorf.adventOfCode.problems.day9.Node;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 implements AdventProblem {

    private static final Pattern DISTANCE_PATTERN = Pattern.compile("(\\w+) to (\\w+) = (\\d+)");

    private Map<String, List<Edge>> edgesByNode;

    public Day9() throws Exception {
        List<String> rawDistances = Files.readLines(new File(Resources.getResource("day9_input.txt").toURI()), Charsets.UTF_8);
        edgesByNode = Maps.newHashMap();

        for (String rawDistance : rawDistances) {
            Matcher matcher = DISTANCE_PATTERN.matcher(rawDistance);
            if (matcher.matches()) {
                String src = matcher.group(1);
                String dst = matcher.group(2);
                int distance = Integer.valueOf(matcher.group(3));

                if (!edgesByNode.containsKey(src)) {
                    edgesByNode.put(src, Lists.newArrayList());
                }
                if (!edgesByNode.containsKey(dst)) {
                    edgesByNode.put(dst, Lists.newArrayList());
                }

                edgesByNode.get(src).add(new Edge(dst, distance));
                edgesByNode.get(dst).add(new Edge(src, distance));
            } else {
                throw new RuntimeException(String.format("Failed to match pattern on %s", rawDistance));
            }
        }
    }

    @Override
    public int solveFirst() throws Exception {
        List<Node> possibleRoutes = Lists.newArrayList();
        for (String node : edgesByNode.keySet()) {
            possibleRoutes.add(getNodeTree(new Node(node, 0), Lists.newArrayList(node)));
        }

        int minimumDistance = Integer.MAX_VALUE;
        for (Node route : possibleRoutes) {
            int distance = route.getMinDistance();
            if (distance < minimumDistance) {
                minimumDistance = distance;
            }
        }
        return minimumDistance;
    }

    @Override
    public int solveSecond() throws Exception {
        List<Node> possibleRoutes = Lists.newArrayList();
        for (String node : edgesByNode.keySet()) {
            possibleRoutes.add(getNodeTree(new Node(node, 0), Lists.newArrayList(node)));
        }

        int maximumDistance = 0;
        for (Node route : possibleRoutes) {
            int distance = route.getMaxDistance();
            if (distance > maximumDistance) {
                maximumDistance = distance;
            }
        }
        return maximumDistance;
    }

    /**
     * Creates a tree of all the possible routes from a given root node
     * All locations already visited are excluded
     * @param root the starting location from where to compute the new routes
     * @param visited the locations already visited
     * @return returns a tree of possibles routes
     */
    private Node getNodeTree(Node root, List<String> visited) {
        for (Edge edge : edgesByNode.get(root.getName())) {
            if (!visited.contains(edge.getDestination())) {
                List<String> newVisitedList = Lists.newArrayList(visited);
                newVisitedList.add(edge.getDestination());
                Node child = new Node(edge.getDestination(), edge.getDistance());
                root.addDestination(getNodeTree(child, newVisitedList));
            }
        }
        return root;
    }
}
