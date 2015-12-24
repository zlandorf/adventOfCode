package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.zlandorf.adventOfCode.AdventProblem;
import org.json.*;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 implements AdventProblem<Integer> {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("(-?\\d+)");

    protected String jsonContents;

    public Day12() throws Exception {
        jsonContents = Files.toString(new File(Resources.getResource("day12_input.txt").toURI()), Charsets.UTF_8);
    }

    @Override
    public Integer solveFirst() throws Exception {
        return computeSum(jsonContents);
    }

    @Override
    public Integer solveSecond() throws Exception {
        return computeSum(removeRedStructures(jsonContents));
    }

    protected int computeSum(String contents) {
        Matcher numberMatcher = NUMBER_PATTERN.matcher(contents);
        int sum = 0;

        while (numberMatcher.find()) {
            sum += Integer.valueOf(numberMatcher.group());
        }

        return sum;
    }

    protected String removeRedStructures(String input) throws JSONException {
        return removeRedStructuresFromJSON(new JSONTokener(input).nextValue()).toString();
    }

    protected Object removeRedStructuresFromJSON(Object object) throws JSONException {
        if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.get(i) instanceof JSONObject || jsonArray.get(i) instanceof JSONArray) {
                    jsonArray.put(i, removeRedStructuresFromJSON(jsonArray.get(i)));
                }
            }
            return object;
        }

        JSONObject jsonObject = (JSONObject) object;
        List<String> keys = Lists.newArrayList();
        Iterator keyIterator = jsonObject.keys();

        while (keyIterator.hasNext()) {
            keys.add((String)keyIterator.next());
        }

        for (String key : keys) {
            Object value = jsonObject.get(key);

            if (value != null && value.equals("red")) {
                return null;
            }

            if (value instanceof JSONObject || value instanceof JSONArray) {
                jsonObject.put(key, removeRedStructuresFromJSON(value));
            }
        }
        return jsonObject;
    }
}
