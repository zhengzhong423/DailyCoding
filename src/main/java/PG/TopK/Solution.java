package PG.TopK;

import java.util.*;

/**
 * Created by zhonzhen on 9/13/16.
 */
public class Solution {
    public static void main(String[] args) {
        char[] chs = "abfdsfsddddwer".toCharArray();
        int k = 2;
        HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
        for(Character c: chs) {
            hash.put(c, hash.containsKey(c) ? hash.get(c) + 1 : 1);
        }

        Comparator<Map.Entry<Character, Integer>> cmp =
                new Comparator<Map.Entry<Character, Integer>>() {
                    public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                        return o2.getValue() - o1.getValue();
                    }
                };
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<Map.Entry<Character, Integer>>(k, cmp);

        for(Map.Entry<Character, Integer> entry: hash.entrySet()) {
            pq.add(entry);
        }
        System.out.println(pq.poll().getKey());

    }
}
