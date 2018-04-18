package com.example.demo;

import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import java.util.*;

/**
 * Created by pasha on 18.04.18.
 */
public class ObjectTest {

    public static class LogEntry {
        long timeStart;
        long timeEnd;

        public LogEntry(long timeStart, long timeEnd) {
            this.timeStart = timeStart;
            this.timeEnd = timeEnd;
        }
    }

    @Test
    public void logTest() {
        List<LogEntry> logs = new ArrayList<LogEntry>() ;
        logs.add(new LogEntry(1L,2L));
        logs.add(new LogEntry(4L,6L));
        logs.add(new LogEntry(5L,7L));
        logs.add(new LogEntry(13L,14L));
        int max = getAgentCount(logs);
        System.out.println(max);
    }


    public static int getAgentCount(List<LogEntry> logEntries) {
        int max = 0;
        for (int i = 0; i < logEntries.size(); i++) {
            int maxInCol = 0;
            for (int j = 0; j < logEntries.size(); j++) {
                if (i == j) {
                    maxInCol++;
                    continue;
                }
                if (ObjectTest.isIntersect(logEntries.get(i), logEntries.get(j))) {
                    maxInCol++;
                }
            }
            if (maxInCol > max) {
                max = maxInCol;
            }
        }
        return max;
    }

    private static boolean isIntersect(LogEntry logEntry1, LogEntry logEntry2) {
        if (logEntry1.timeStart >= logEntry2.timeStart && logEntry1.timeStart <= logEntry2.timeEnd ||
                logEntry1.timeEnd >= logEntry2.timeStart && logEntry1.timeEnd <= logEntry2.timeEnd)
            return true;
        return false;
    }


    public static class RatingRec {
        int idx;
        double price;

        public RatingRec(int idx, double price) {
            this.idx = idx;
            this.price = price;
        }
    }


    public static List<Pair<Integer, Integer>> getCheapest(List<RatingRec> list) {
        if (list == null || list.size() < 2) return null;
        RatingRec cheapest = list.get(0);
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = 1; i<list.size(); i++) {
            RatingRec curr = list.get(i);
            if (curr.price < cheapest.price) {
                cheapest = curr;
            } else {
                Pair<Integer, Integer> pair = new Pair<>(curr.idx, cheapest.idx);
                result.add(pair);
            }
        }
        return result;
    }

    @Test
    public void cheapestTest() {
        List<RatingRec> list = new ArrayList<>();
        list.add(new RatingRec(8,250));
        list.add(new RatingRec(9,200));
        list.add(new RatingRec(11,220));
        list.add(new RatingRec(15,180));
        list.add(new RatingRec(7,210));
        list.add(new RatingRec(6,220));
        List<Pair<Integer, Integer>> res = ObjectTest.getCheapest(list);
        System.out.println(res);
    }


    public static class SiteNode {
        int idx;
        List<SiteNode> children = new ArrayList<>();

        public SiteNode(int idx) {
            this.idx = idx;
        }
    }

    public static int testGoodSite(SiteNode homePage) {
        List<SiteNode> children = ObjectTest.f(homePage);
        if (children == null) {
            return 0;
        }
        int max = 0;
        for (SiteNode page : children) {
            int j = ObjectTest.testGoodSite(page);
            max = Math.max(max, j);
        }
        return max+1;
    }

    private static List<SiteNode> f(SiteNode homePage) {
        return homePage == null || homePage.children.size() == 0 ? null : homePage.children;
    }

    @Test
    public void goodSiteTest() {
        SiteNode s1 = new SiteNode(1);
        SiteNode s2 = new SiteNode(2);
        SiteNode s3 = new SiteNode(3);
        SiteNode s4 = new SiteNode(4);
        SiteNode s5 = new SiteNode(5);
        SiteNode s6 = new SiteNode(6);
        SiteNode s7 = new SiteNode(7);
        SiteNode s8 = new SiteNode(8);
        SiteNode s9 = new SiteNode(9);
        SiteNode s10 = new SiteNode(10);

        s9.children.add(s10);

        s7.children.add(s8);
        s7.children.add(s9);

        s6.children.add(s7);
        s3.children.add(s6);
        s4.children.add(s5);

        s2.children.add(s3);
        s7.children.add(s4);

        SiteNode homePage = new SiteNode(0);
        homePage.children.add(s1);
        homePage.children.add(s2);

        int res = ObjectTest.testGoodSite(homePage);
        System.out.println(res);

    }


    public static int getLongestSeq(int[] arr) {
        int posStart = 0;
        int posEnd = 0;
        int max = 0;
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i<arr.length; i++) {
            int value = arr[i];
            if (value - prev == 1) {
                posEnd++;
                max = Math.max(max, posEnd - posStart + 1);
            } else {
                posStart = i;
                posEnd = i;
            }
            prev = value;
        }
        return max;
    }

    @Test
    public void maxLength() {
        int[] arr = new int[] {1,2,3,2,3,4,5,3,3,4,5,6,6,8};
        int res = ObjectTest.getLongestSeq(arr);
        System.out.println(res);
    }

    public static Set<String> getSortedAnagrams(List<String> list1, List<String> list2, List<String> list3) {
        Set<String> result = new TreeSet<>();
        Map<String, Integer> mapWords = new HashMap<>();

        Set<String> set1 = ObjectTest.getSet(list1);
        Set<String> set2 = ObjectTest.getSet(list2);
        Set<String> set3 = ObjectTest.getSet(list3);

        ObjectTest.setCount(mapWords, set1, set2);
        ObjectTest.setCount(mapWords, set2, set1);
        ObjectTest.setCount(mapWords, set1, set3);
        ObjectTest.setCount(mapWords, set3, set1);
        ObjectTest.setCount(mapWords, set2, set3);
        ObjectTest.setCount(mapWords, set3, set2);

        result.addAll(mapWords.keySet());
        return result;
    }

    private static void setCount(Map<String, Integer> mapWords, Set<String> set1, Set<String> set2) {
        for (String word: set1) {
            if (set2.contains(word)) {
                if (mapWords.containsKey(word)) {
                    mapWords.put(word, mapWords.get(word) + 1);
                } else {
                    mapWords.put(word, 1);
                }
            }
        }
    }

    private static Set<String> getSet(List<String> list) {
        Set<String> set = new HashSet<>();
        for (String word : list) {
            char[] wordChar = word.toCharArray();
            Arrays.sort(wordChar);
            word = new String(wordChar);
            set.add(word);
        }
        return set;
    }


    @Test
    public void getAnagrCount() {
        List<String> list1 = new ArrayList<>();
        list1.add("cat");
        list1.add("dog");
        list1.add("cat3");
        List<String> list2 = new ArrayList<>();
        list2.add("tac");
        list2.add("dogss");
        list2.add("catf3");
        List<String> list3 = new ArrayList<>();
        list3.add("ca3t");
        list3.add("doffg");
        list3.add("cat333");
        System.out.println(ObjectTest.getSortedAnagrams(list1, list2, list3));
    }


    public static int getCntCoin(int amount, HashMap<Integer, Integer> map) {
        if (map.containsKey(amount)) {
            return map.get(amount);
        }
        if (amount <= 0) return 0;
        if (amount == 1) return 1;
        if (amount == 3) return 1;
        if (amount == 5) return 1;
        if (amount == 10) return 1;
        int f1 = getCntCoin(amount - 1, map);
        int f3 = getCntCoin(amount - 3, map);
        int f5 = getCntCoin(amount - 5, map);
        int f10 = getCntCoin(amount - 10, map);
        int result = f1;
        if (f3 > 0) result = Math.min(result, f3);
        if (f5 > 0) result = Math.min(result, f5);
        if (f10 > 0) result = Math.min(result, f10);
        if (!map.containsKey(amount)) {
            map.put(amount, result+1);
        }
        return result + 1;
    }


    @Test
    public void getCoinsTest() {
        System.out.println(ObjectTest.getCntCoin(1000, new HashMap<Integer, Integer>()));
    }

    public static boolean is2NumWithSum(int[] arr, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< arr.length; i++) {
            map.put(sum - arr[i], i);
        }
        for (int j =0; j< arr.length; j++) {
            if (map.containsKey(arr[j]) && map.get(arr[j]) != j) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void isSumTest() {
        int[] arr = new int[] {1,2,3,4,5};
        System.out.println(ObjectTest.is2NumWithSum(arr, 3));
    }


    public static class HotelRating {
        int idx;
        int userid;
        int score;

        public HotelRating(int idx, int userid, int score) {
            this.idx = idx;
            this.userid = userid;
            this.score = score;
        }
    }

    public static class MedianValue {
        double totalscore;
        int count;

        public double getAverage() {
            return this.totalscore / this.count;
        }

        public MedianValue(double totalscore, int count) {
            this.totalscore = totalscore;
            this.count = count;
        }
    }

    public static Set<Integer> get_hotels(HotelRating[] hotels, double score) {
        HashMap<Integer, MedianValue> map = new HashMap<>();
        for (HotelRating rating : hotels) {
            if (map.containsKey(rating.idx)) {
                MedianValue entry = map.get(rating.idx);
                entry.count++;
                entry.totalscore += rating.score;
            } else {
                map.put(rating.idx, new MedianValue(rating.score, 1));
            }
        }
        Iterator<Map.Entry<Integer, MedianValue>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, MedianValue> entry = iter.next();
            if (entry.getValue().getAverage()< score) {
                iter.remove();
            }
        }
        return map.keySet();
    }

    @Test
    public void getHotelsTest() {
        HotelRating[] hotels = new HotelRating[3] ;
        hotels[0] = new HotelRating(2,1,1);
        hotels[1] = new HotelRating(2,1,3);
        hotels[2] = new HotelRating(3,1,5);
        System.out.println(get_hotels(hotels, 3));
    }


    public static class Logentry {
        String user;
        String page;

        public Logentry(String user, String page) {
            this.user = user;
            this.page = page;
        }
    }

    public static Map<String, Set<String>> discover_site_map(List<Logentry> log) {
        Map<String, Set<String>> result = new HashMap<>();
        Map<String, String> oldPageByUser = new HashMap<>();
        for (Logentry entry : log) {

            Set<String> pages = null;

            if (oldPageByUser.containsKey(entry.user)) {
                String oldPage = oldPageByUser.get(entry.user);
                pages = result.get(oldPage);
                if (pages == null) {
                    pages = new HashSet<>();
                    result.put(oldPage, pages);
                }
            }
            if (pages != null) {
                pages.add(entry.page);
            }

            oldPageByUser.put(entry.user, entry.page);
        }

        return result;
    }

    @Test
    public void discover_test() {
        List<Logentry> list = new ArrayList<>();
        list.add(new Logentry("A", "1"));
        list.add(new Logentry("B", "5"));
        list.add(new Logentry("A", "2"));
        list.add(new Logentry("A", "1"));
        list.add(new Logentry("B", "2"));
        list.add(new Logentry("C", "7"));
        list.add(new Logentry("C", "3"));
        list.add(new Logentry("A", "3"));
        list.add(new Logentry("C", "1"));
        System.out.println(ObjectTest.discover_site_map(list));
    }


    public static Set<Integer> getInterSection(int[][] arr, int numInArr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < arr[i].length; j++) {
                set.add(arr[i][j]);
            }
            for (Integer value : set) {
                map.put(value, (map.get(value) == null ? 0 : map.get(value)) + 1);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            if (entry.getValue() != numInArr) {
                iter.remove();
            }
        }
        return map.keySet();
    }

    @Test
    public void getIntersect_test() {
        int[][] arr = new int[][] { {1,1,2}, {1,2,3,233,4,5,6}, {2,3,54,53,3,3,5} };
        System.out.println(ObjectTest.getInterSection(arr, 2));
    }

}
