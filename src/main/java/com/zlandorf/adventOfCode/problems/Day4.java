package com.zlandorf.adventOfCode.problems;

import com.google.common.base.Charsets;
import com.zlandorf.adventOfCode.AdventProblem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 implements AdventProblem<Integer> {

    private static final String SECRET = "ckczppom";

    @Override
    public Integer solveFirst() throws Exception {
        int lowestNumber = 0;
        String hash;
        do {
            lowestNumber++;
            hash = hexMd5(String.format("%s%d", SECRET, lowestNumber));
        } while(!hash.startsWith("00000"));

        return lowestNumber;
    }

    @Override
    public Integer solveSecond() throws Exception {
        int lowestNumber = 0;
        String hash;
        do {
            lowestNumber++;
            hash = hexMd5(String.format("%s%d", SECRET, lowestNumber));
        } while(!hash.startsWith("000000"));

        return lowestNumber;
    }

    private String hexMd5(String code) throws NoSuchAlgorithmException {
        byte[] digest = MessageDigest.getInstance("MD5").digest(code.getBytes(Charsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (int i = 0 ; i < digest.length; i++) {
            String hex=Integer.toHexString(0xff & digest[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
