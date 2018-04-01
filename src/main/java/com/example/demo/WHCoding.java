package com.example.demo;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pasha on 28.03.18.
 */
public class WHCoding {

    public static long[] arrayMultiply(long[] inputData) {
        if (inputData == null) {
            throw new IllegalArgumentException();
        }
        if (inputData.length == 1) {
            return inputData;
        }
        long[] result = new long[inputData.length];

        long allMult = 1;
        for (int i = 0; i < inputData.length ; i++) {
            allMult *= inputData[i];
        }

        for (int i = 0; i < inputData.length ; i++) {
            if (inputData[i] != 0) {
                result[i] = allMult / inputData[i];
            } else {
                result[i] = allMult;
            }
        }

        return result;
    }

    public static boolean isPalindrome(String inputData) {
        boolean result = true;
        for (int i = 0; i < inputData.length() / 2; i++) {
            if (inputData.charAt(i) != inputData.charAt(inputData.length() - i -1 )) {
                result = false;
            }
        }
        return result;
    }

    // Write Java code to find K-complementary pairs in a given array of integers.
    // Given Array A, pair (i, j) is K- complementary if K = A[i] + A[j];
    // {1,2,3,4}
    // for K=5 =>{1,4}, {2,3}

    public static int getComplimantary(int[] inputData, int k) {
        Map<Integer, Integer> tmp = new HashMap<>();

        for (int i = 0 ; i < inputData.length; i++) {
            int compl = k - inputData[i];
            if (tmp.containsKey(compl)) {
                tmp.put(compl, tmp.get(compl) + 1);
            } else {
                tmp.put(compl, 1);
            }
        }

        int result = 0;
        for (int i = 0; i<inputData.length; i++) {
            if (tmp.containsKey(inputData[i]))
                result += tmp.get(inputData[i]);
        }
        return result;
    }

    // Write a Java method to calculate the Nth integer in the Fibonacci sequence.
    // f(n) = 0,1,1,2,3,5
    public static long getFibN(int n) {
        long result = 0;
        long prev1  = 0;
        long prev2  = 1;
        for (int i = 2; i< n; i++) {
            result = prev1 + prev2;
            prev1 = prev2;
            prev2 = result;
        }
        return result;
    }

}
