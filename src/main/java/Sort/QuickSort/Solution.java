package Sort.QuickSort;

/**
 * Created by zhonzhen on 9/7/16.
 */
public class Solution {
    void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
        for(int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }

    void quickSortHelper(int[] arr, int left, int right) {
        if(right - left <= 0) {
            return;
        }
        int partition = arr[right];
        int start = left, end = right;
        while(start < end) {
            while(start < end && arr[start] < partition)
                start++;
            swap(arr, start ,end);
            while(start < end && arr[end] >= partition)
                end--;
            swap(arr, start, end);
        }

        quickSortHelper(arr, left, start - 1);
        quickSortHelper(arr, start + 1, right);
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}
