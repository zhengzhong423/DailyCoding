package BinarySearch.InsertPos;

/**
 * Created by zhonzhen on 9/7/16.
 *
 * [1,2,4] 3 --> 2
 */
public class Solution {
    int findInsertPos(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while(r > l) {
            int m = l + (r - l) / 2;
            if(arr[m] == val) {
                return m;
            } else if(arr[m] > val) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return arr[l] < val ? l + 1 : l;
    }

}
