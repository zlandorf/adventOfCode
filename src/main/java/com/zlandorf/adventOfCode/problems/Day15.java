package com.zlandorf.adventOfCode.problems;

import com.google.common.collect.Lists;
import com.zlandorf.adventOfCode.AdventProblem;
import com.zlandorf.adventOfCode.problems.day15.Ingredient;

import java.lang.reflect.Field;
import java.util.List;

public class Day15 implements AdventProblem<Integer> {

    private static final int MAX_DOSE = 100;

    protected List<Field> propertyFields;
    protected Field caloriesField;
    protected List<Ingredient> kitchenIngredients;

    public Day15() throws Exception {
        propertyFields = Lists.newArrayList(
            Ingredient.class.getField("capacity"),
            Ingredient.class.getField("durability"),
            Ingredient.class.getField("flavor"),
            Ingredient.class.getField("texture")
        );
        caloriesField = Ingredient.class.getField("calories");
        kitchenIngredients = Lists.newArrayList(
            new Ingredient("Sugar", 3, 0, 0, -3, 2),
            new Ingredient("Sprinkles", -3, 3, 0, 0, 9),
            new Ingredient("Candy", -1, 0, 4, 0, 1),
            new Ingredient("Chocolate", 0, 0, -2, 2, 8)
        );
    }

    @Override
    public Integer solveFirst() throws Exception {
        Integer[] doses = new Integer[kitchenIngredients.size()];
        for (int i = 0; i < kitchenIngredients.size(); i++) {
            doses[i] = 0;
        }
        doses[kitchenIngredients.size() - 1] = MAX_DOSE;

        int maxScore = 0;
        int iterations = 0;
        do {
            int score = score(doses);
            if (score > maxScore) {
                maxScore = score;
            }
            iterations++;
        } while(permute(doses));

        System.out.println("Number of iterations : " + iterations);
        return maxScore;
    }

    @Override
    public Integer solveSecond() throws Exception {
        Integer[] doses = new Integer[kitchenIngredients.size()];
        for (int i = 0; i < kitchenIngredients.size(); i++) {
            doses[i] = 0;
        }
        doses[kitchenIngredients.size() - 1] = MAX_DOSE;

        int maxScore = 0;
        do {
            int score = score(doses);
            if (propertyScore(caloriesField, doses) == 500 && score > maxScore) {
                maxScore = score;
            }
        } while(permute(doses));

        return maxScore;
    }

    protected boolean permute(Integer[] doses) {
        if (doses[0] == MAX_DOSE) {
            //finished permuting all possible values
            return false;
        }

        Integer[] cumulatedValues = new Integer[doses.length];
        int lastNonNullIndex = 0;
        for (int i = 0; i < doses.length; i++) {
            cumulatedValues[i] = doses[i];
            if (i > 0) {
                cumulatedValues[i] += cumulatedValues[i - 1];
            }
            if (doses[i] > 0) {
                lastNonNullIndex = i;
            }
        }

        if (lastNonNullIndex > 0) {
            if (lastNonNullIndex < doses.length - 1 && cumulatedValues[lastNonNullIndex] >= MAX_DOSE) {
                doses[lastNonNullIndex] = 0;
                doses[doses.length - 1] = MAX_DOSE - cumulatedValues[lastNonNullIndex - 1];
            }

            doses[lastNonNullIndex - 1]++;
            doses[lastNonNullIndex]--;
        }

        return true;
    }

    protected int score(Integer[] doses) throws Exception {
        int score = 1;

        for (Field field : propertyFields) {
            score *= Math.max(0, propertyScore(field, doses));
        }

        return score;
    }

    protected int propertyScore(Field field, Integer[] doses) throws Exception {
        int propertyScore = 0;
        for (int i = 0; i < doses.length; i++) {
            propertyScore += doses[i] * field.getInt(kitchenIngredients.get(i));
        }
        return propertyScore;
    }
}
