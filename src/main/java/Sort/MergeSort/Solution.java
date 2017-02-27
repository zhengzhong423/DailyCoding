package Sort.MergeSort;

/**
 * Created by zhonzhen on 10/6/16.
 */
public class Solution {
    public void mergeSort(int[] arr, int left, int right) {
        if(right - left <= 1) {
            int left_val = arr[left];
            int right_val = arr[right];
            arr[left] = Math.min(left_val, right_val);
            arr[right] = Math.max(left_val, right_val);
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        mergeSorted(arr, left, mid + 1, right);
    }

    public void mergeSorted(int[] arr, int left, int right_side_start, int right) {
        int index = 0, i = left, j = right_side_start;
        int[] tmp = new int[right - left + 1];
        while(i < right_side_start && j <= right) {
            if(arr[i] < arr[j]) {
                tmp[index] = arr[i];
                i++;
            } else {
                tmp[index] = arr[j];
                j++;
            }
            index++;
        }

        while(i < right_side_start)
            tmp[index++] = arr[i++];
        while(j <= right)
            tmp[index++] = arr[j++];

        for(index = 0; index < tmp.length; index++) {
            arr[left + index] = tmp[index];
        }
    }

}
