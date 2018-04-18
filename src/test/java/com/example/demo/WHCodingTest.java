package com.example.demo;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
        System.out.println(LocalDateTime.now());
        long res = WHCoding.getFibN(900000000);
        System.out.println(res + ", " + LocalDateTime.now());
        long resReq = WHCoding.getFibNReq(900000000);
        System.out.println(resReq + ", " + LocalDateTime.now());
    }

    @Test
    public void sumBinary_test() {
        int i = 556667774;
        int j = 2042356;
        int res = WHCoding.sumBinary(i, j);
        Assert.assertEquals(res, i+j);
    }


    private static LinkedList<Integer> sumList(LinkedList<Integer> num1, LinkedList<Integer> num2) {
        LinkedList<Integer> result = new LinkedList<>();
        // 1->2->3  (321)
        // 5->2  (25)
        Iterator<Integer> iter1 = num1.iterator();
        Iterator<Integer> iter2 = num2.iterator();
        result = WHCodingTest.getSum(iter1, iter2);

        return result;
    }

    private static LinkedList<Integer> getSum(Iterator<Integer> iter1, Iterator<Integer> iter2) {
        while (true) {
            if (!iter1.hasNext() && !iter2.hasNext()) {
                break;
            }

            Integer n1 = iter1.next();

        }

        return null;
    }

    @Test
    public void sumLinkedListRev() {

        LinkedList<Integer> num1 = new LinkedList<Integer>() { { add(1);add(2);add(3);   } };
        LinkedList<Integer> num2 = new LinkedList<Integer>() { { add(5);add(2);   } };

        List<Integer> result = WHCodingTest.sumList(num1, num2);
        System.out.println(result);
    }

    @Test
    public void isBalancedTest() {
        boolean balanced = WHCoding.isBalanced("({}[])");
        System.out.println(balanced);
    }

    @Test
    public void intToStr() {
        String res = WHCoding.getString(1433220);
        System.out.println(res);
    }


}
