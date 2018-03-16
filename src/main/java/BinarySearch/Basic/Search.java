package BinarySearch.Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {
    public static void main(String[] args) {
        Search search = new Search();
        System.out.println(search.inArray(new int[] {1, 2, 3}, -1));
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,4,5}));
        System.out.println(search.binarySearch(list, 1));
    }

    public boolean inArray(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] == target) {
                return true;
            } else if (arr[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return arr[l] == target;
    }

    public int binarySearch(List<Integer> input, int val) {
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
