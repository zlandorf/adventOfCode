package com.zlandorf.adventOfCode.problems;

import com.zlandorf.adventOfCode.AdventProblem;

public class Day10 implements AdventProblem {

    private static String INPUT = "1321131112";

    protected String process(String input) {
        StringBuilder builder = new StringBuilder();

        int currentIndex = 0;
        do {
            char number = input.charAt(currentIndex++);
            int count = 1;
            for (; currentIndex < input.length(); currentIndex++) {
                if (input.charAt(currentIndex) == number) {
                    count++;
                } else {
                    break;
                }
            }

            builder.append(String.valueOf(count));
            builder.append(number);
        } while(currentIndex < input.length());

        return builder.toString();
    }


    @Override
    public int solveFirst() throws Exception {
        String result = INPUT;
        for (int i = 0; i < 40; i++) {
            result = process(result);
        }
        return result.length();
    }

    @Override
    public int solveSecond() throws Exception {
        String result = INPUT;
        for (int i = 0; i < 50; i++) {
            System.out.println(i);
            result = process(result);
        }
        return result.length();
    }
}
