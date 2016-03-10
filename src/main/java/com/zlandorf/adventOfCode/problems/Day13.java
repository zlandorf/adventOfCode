package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 implements AdventProblem<Integer> {

    private static final Pattern HAPPINNESS_PATTERN = Pattern.compile("(\\w+) would (gain|lose) (\\d+) happiness units by sitting next to (\\w+).");

    private List<String> rawSeatings;

    private Map<String, Map<String, Integer>> happinnessBySeatings;
    private Map<Integer, String> attendeesByIndex;

    public Day13() throws Exception {
        rawSeatings = Files.readLines(new File(Resources.getResource("day13_input.txt").toURI()), Charsets.UTF_8);
        happinnessBySeatings = Maps.newHashMap();
        attendeesByIndex = Maps.newHashMap();
    }

    @Override
    public Integer solveFirst() throws Exception {
        parseSeatings();
        return getMaxScore();
    }

    @Override
    public Integer solveSecond() throws Exception {
        parseSeatings();
        List<String> attendees = Lists.newArrayList(happinnessBySeatings.keySet());
        String you = "you";
        happinnessBySeatings.put(you, Maps.newHashMap());
        for (String attendee : attendees) {
            happinnessBySeatings.get(you).put(attendee, 0);
            happinnessBySeatings.get(attendee).put(you, 0);
        }


        return getMaxScore();
    }

    private int getMaxScore() {
        int numberOfGuests = happinnessBySeatings.size();
        Integer[] initialSeatingArrangement = new Integer[numberOfGuests];
        for (int i = 0; i < numberOfGuests; i++) {
            initialSeatingArrangement[i] = i;
        }
        int attendeeIndex = 0;
        for (String attendee : happinnessBySeatings.keySet()) {
            attendeesByIndex.put(attendeeIndex, attendee);
            attendeeIndex++;
        }

        List<List<Integer>> seatingArrangements = computePermutations(initialSeatingArrangement);

        int maxScore = Integer.MIN_VALUE;
        for (List<Integer> seatingArrangment : seatingArrangements) {
            int score = score(seatingArrangment);
            if (score > maxScore) {
                maxScore = score;
            }
        }

        return maxScore;
    }


    private int score(List<Integer> seatingArrangment) {
        int score = 0;

        int numberOfAttendees = attendeesByIndex.size();

        for (int i = 0; i < seatingArrangment.size(); i++) {
            int attendeeIndex = seatingArrangment.get(i);
            int attendeeLeftIndex = seatingArrangment.get((numberOfAttendees + i - 1) % numberOfAttendees); // get the absolute modulo, instead of negative one
            int attendeeRightIndex = seatingArrangment.get((i + 1) % numberOfAttendees);

            String attendee = attendeesByIndex.get(attendeeIndex);
            String attendeeLeft = attendeesByIndex.get(attendeeLeftIndex);
            String attendeeRight = attendeesByIndex.get(attendeeRightIndex);

            score += happinnessBySeatings.get(attendee).get(attendeeLeft);
            score += happinnessBySeatings.get(attendee).get(attendeeRight);
        }

        return score;
    }

    private void parseSeatings() {
        for (String rawSeating : rawSeatings) {
            Matcher seatingMatcher = HAPPINNESS_PATTERN.matcher(rawSeating);
            if (seatingMatcher.matches()) {
                String attendee = seatingMatcher.group(1);
                String other = seatingMatcher.group(4);
                int happinessValue = Integer.valueOf(seatingMatcher.group(3));

                if (seatingMatcher.group(2).equals("lose")) {
                    happinessValue *= -1;
                }

                if (!happinnessBySeatings.containsKey(attendee)) {
                    happinnessBySeatings.put(attendee, Maps.newHashMap());
                }

                happinnessBySeatings.get(attendee).put(other, happinessValue);

            } else {
                throw new RuntimeException(String.format("Seating pattern did not match the line %s", rawSeating));
            }
        }
    }

    private List<List<Integer>> computePermutations(Integer[] a) {
        List<List<Integer>> permutations = computePermutations(a, a.length);
        permutations.add(Arrays.asList(Arrays.copyOf(a, a.length)));
        return permutations;
    }

    private List<List<Integer>> computePermutations(Integer[] a, int N) {
        List<List<Integer>> permutations = Lists.newArrayList();
        if (N == 1) {
            permutations.add(Arrays.asList(Arrays.copyOf(a, a.length)));
            return permutations;
        }

        for (int i = 0; i < N; i++) {
            swap(a, i, N - 1);
            permutations.addAll(computePermutations(a, N - 1));
            swap(a, i, N - 1);
        }
        return permutations;
    }

    private void swap(Integer[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
