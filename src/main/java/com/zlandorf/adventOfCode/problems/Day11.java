package com.zlandorf.adventOfCode.problems;

import com.zlandorf.adventOfCode.AdventProblem;

import java.util.regex.Pattern;

public class Day11 implements AdventProblem<String> {
    private static final String INPUT = "hepxcrrq";

    @Override
    public String solveFirst() throws Exception {
        return nextValidPassword(INPUT);
    }

    @Override
    public String solveSecond() throws Exception {
        return nextValidPassword("hepxxyzz");
    }

    protected String nextValidPassword(String password) {
        do {
            password = next(password);
        } while(!isPasswordValid(password));
        return password;
    }

    protected String next(String password) {
        StringBuilder builder = new StringBuilder(password);
        for (int i = password.length() - 1; i >= 0; i--) {
            if (password.charAt(i) == 'z') {
                builder.setCharAt(i, 'a');
            } else {
                builder.setCharAt(i, (char)(password.charAt(i) + 1));
                break;
            }
        }
        return builder.toString();
    }

    protected boolean isPasswordValid(String password) {
        return hasDoublePair(password)
            && !hasIllegalCharacter(password)
            && hasThreeLetterIncreasingStraight(password);
    }

    protected boolean hasDoublePair(String password) {
        return Pattern.compile(".*(.)\\1.*([^\\\\1])\\2.*").matcher(password).matches();
    }

    protected boolean hasIllegalCharacter(String password) {
        return Pattern.compile(".*[iol].*").matcher(password).matches();
    }

    protected boolean hasThreeLetterIncreasingStraight(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (isThreeLetterIncreasingStraight(
                password.charAt(i),
                password.charAt(i + 1),
                password.charAt(i + 2)
            )) {
                return true;
            }
        }
        return false;
    }

    protected boolean isThreeLetterIncreasingStraight(char a, char b, char c) {
        return (int) a + 1 == (int) b && (int) a + 2 == (int) c;
    }
}
