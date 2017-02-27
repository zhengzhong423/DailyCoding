package BinarySearch.FindValRotatedArray;

/**
 * Created by zhonzhen on 9/7/16.
 */
public class Solution {
    int findValRotatedArray(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(arr[m] == val)
                return m;
            if(arr[r] > arr[l]) // array is sorted
                return binaryFind(arr, l, r, val);
            if(arr[m] < arr[r]) {
                if(val > arr[r] || val <= arr[m])
                    r = m;
                else
                    l = m + 1;
            } else if(arr[m] > arr[r]){
                if(val >= arr[l] && val <= arr[m])
                    r = m;
                else
                    l = m + 1;
            } else {
                r--;
            }
        }
        return arr[l] == val ? l : -1;
    }

    int binaryFind(int[] arr, int start, int end, int val) {
        int l = start, r = end;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(arr[m] == val)
                return m;
            else if(arr[m] > val)
                r = m;
            else
                l = m + 1;
        }
        return arr[l] == val ? l : -1;
    }


}
