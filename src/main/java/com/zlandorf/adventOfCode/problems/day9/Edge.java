package com.zlandorf.adventOfCode.problems.day9;

public class Edge {
    private String destination;
    private int distance;

    public Edge(String destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }

    public String getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }
}
