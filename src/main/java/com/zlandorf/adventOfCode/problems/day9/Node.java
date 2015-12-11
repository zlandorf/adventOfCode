package com.zlandorf.adventOfCode.problems.day9;

import com.google.common.collect.Lists;

import java.util.List;

public class Node {
    private String name;
    private int distance;
    private List<Node> destinations;

    public Node(String name, int distance) {
        this.name = name;
        this.distance = distance;
        destinations = Lists.newArrayList();
    }

    public String getName() {
        return name;
    }

    public int getMinDistance() {
        if (isLeaf()) {
            return distance;
        }
        int minimum = Integer.MAX_VALUE;
        for (Node destination : destinations) {
            int destinationDistance = destination.getMinDistance();
            if (destinationDistance < minimum) {
                minimum = destinationDistance;
            }
        }
        return minimum + distance;
    }

    public int getMaxDistance() {
        if (isLeaf()) {
            return distance;
        }
        int maximum = 0;
        for (Node destination : destinations) {
            int destinationDistance = destination.getMaxDistance();
            if (destinationDistance > maximum) {
                maximum = destinationDistance;
            }
        }
        return maximum + distance;
    }

    public void addDestination(Node node) {
        destinations.add(node);
    }

    public boolean isLeaf() {
        return destinations.size() == 0;
    }
}
