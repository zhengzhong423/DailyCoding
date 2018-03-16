package Paypal.longestNoDupSequence;

import java.util.HashSet;
import java.util.Set;

public class LongestNoDupSequence {
    public static void main(String[] args) {
        LongestNoDupSequence instance = new LongestNoDupSequence();
        Integer[] input = new Integer[]{0, 1, 2, 3, 4, 1, 6, 7, 8, 3, 9};
        System.out.println(instance.dedup(input));
    }

    public Integer dedup(Integer[] input) {
        int fastPointer = 0, slowPointer = 0, maxLen = 0;
        Set<Integer> set = new HashSet<Integer>();

        while(fastPointer < input.length) {
            if (!set.contains(input[fastPointer])) {
                set.add(input[fastPointer]);
                fastPointer++;
                maxLen = Math.max(maxLen, fastPointer - slowPointer);
            } else {
                set.remove(input[slowPointer]);
                slowPointer++;
            }
        }
        return maxLen;
    }
}
