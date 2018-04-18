package com.example.demo;

import javafx.util.Pair;
import sun.text.normalizer.Trie;

import java.util.*;

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

    public static long getFibNReq(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        return getFibN(n-2) + getFibN(n-1);
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


    // implement sum operation
    public static int sumBinary1Bit(int i, int j) {

        int res = (i & 1) ^ (j & 1);

        if (i == 1 && j == 1) {
            return 0b10;
        }
        return res;
    }

    public static int sumBinary(int i, int j) {
        int result = 0;
        short mask = 1;

        int idx = 0;
        while (i > 0 || j > 0) {
            int bitI = i & mask;
            int bitJ = j & mask;

            int resSumBit = sumBinary1Bit(bitI, bitJ);

            // add to result
            int resultBit = (result & (1 << idx)) > 0 ? 1 : 0;

            int resultBitWithRes = sumBinary1Bit(resSumBit, resultBit);

            if (resSumBit > 1) {
                // 0b10
                resultBitWithRes |= 0b10;
            }
            result = (resultBitWithRes << idx) | (result & ((1 << idx) - 1));

            if (i > 0) {
                i >>= 1;
            }
            if (j > 0) {
                j >>= 1;
            }
            idx++;
        }
        return result;

    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer value;
    }

    public static int getHeight(TreeNode nodeHead) {
        int heightLeft = 0;
        if (nodeHead.left != null) {
            heightLeft = getHeight(nodeHead.left);
        }
        int heightRight = 0;
        if (nodeHead.right != null) {
            heightRight = getHeight(nodeHead.right);
        }
        if (Math.abs(heightLeft - heightRight) > 1) {
            throw new RuntimeException("not balanced!");
        }
        return Math.max(heightLeft,heightRight);
    }

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            }
            if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.empty()) {
                    return false;
                }
                Character prev = stack.pop();
                if ((ch == '}' && !prev.equals('{')) ||
                        (ch == ']' && !prev.equals('[')) ||
                        (ch == ')' && !prev.equals('(')) ) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static String getString(int number) {
        if (number == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int n = number;
        while (n > 0) {
            int mod = n % 10;
            sb.append((char)('0' + mod));
            n = n / 10;
        }
        return sb.reverse().toString();
    }



}
