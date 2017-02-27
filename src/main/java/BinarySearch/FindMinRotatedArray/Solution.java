package BinarySearch.FindMinRotatedArray;

/**
 * Created by zhonzhen on 9/7/16.
 * [3,4,1,2,3] --> 1
 */
public class Solution {
    int findMin(int[] arr) {
        int l = 0, r = arr.length - 1;
        while(l < r && arr[l] >= arr[r]) {
            int m = l + (r - l) / 2;
            if(arr[m] > arr[l])
                l = m + 1;
            else if(arr[m] < arr[l])
                r = m;
            else
                l++;
        }
        return arr[l];
    }

}
