package BinarySearch.IsSub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.IntStream;

public class IsSubsequence {
    public static void main(String[] args) {
        IsSubsequence isSub = new IsSubsequence();
        System.out.println(isSub.isSubsequence("abc", "ahbgdc"));
        HashMap<Integer[], List<Integer>> map = new HashMap<>();
    }

    public boolean isSubsequence(String s, String t) {
        List<List<Integer>> statsList = new ArrayList<>();
        IntStream.range(0, 26).forEach(i -> statsList.add(new ArrayList<>()));


        char[] chs = t.toCharArray();
        for (int i = 0; i < t.length(); i++) {
            statsList.get(chs[i] - 'a').add(i);
        }

        System.out.println(statsList);
        int indexInT = -1;
        for (char ch: s.toCharArray()) {
            int indexInStats = binarySearch(statsList.get(ch - 'a'), indexInT);

            if (indexInStats == -1) {
                return false;
            }
            indexInT = statsList.get(ch - 'a').get(indexInStats);
        }

        return true;
    }

    public int binarySearch(List<Integer> input, int val) {
        if (input.size() == 0) {
            return -1;
        }
        int s = 0, e = input.size() - 1;
        if (e == 0 || input.get(0) > val) {
            return input.get(0) > val ? 0 : -1;
        }

        while (s < e) {
            int mid = s + (e - s) / 2;
            if ((input.get(mid) < val && input.get(mid + 1) > val) || input.get(mid) == val) {
                return mid + 1;
            }

            if (input.get(mid) > val) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }

        if ((input.get(s) < val && input.get(s + 1) > val) || input.get(s) == val) {
            return s + 1;
        }

        return -1;
    }
}
