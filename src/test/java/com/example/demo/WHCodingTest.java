package com.example.demo;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by pasha on 28.03.18.
 */
public class WHCodingTest {


    // Given an array of numbers, create another array of numbers.
    // Calculate each new array number by multiplying all input array numbers, except for the current number position in the new array.


    @Test
    public void arrayNum_test2() {
        long[] inputData = new long[] {1L, 2L, 3L, 4L} ;

        long[] result = WHCoding.arrayMultiply(inputData);
        System.out.println(Arrays.toString(result));
        //Assert.assertArrayEquals(new long[] {2, 1}, result);

    }



    @Test
    public void palindrome_test() {
        String inputData = "qzq";
        boolean result = WHCoding.isPalindrome(inputData);
        System.out.println(result);
    }

    @Test
    public void findConplim_test() {
        int[] inputData = new int[] { 1,2,3,4,5 };
        int result = WHCoding.getComplimantary(inputData, 3);
        System.out.println(result);
    }

    @Test
    public void getFibN_test() {
        long res = WHCoding.getFibN(900);
        System.out.println(res);
    }

}
