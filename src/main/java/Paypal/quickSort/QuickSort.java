package Paypal.quickSort;

import java.util.Arrays;

public class QuickSort {
    private int[] arr;
    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        qs.arr = new int[] {5,4,6,7,8,10,2,2};
        qs.quickSort(0, qs.arr.length - 1);
        System.out.println(Arrays.toString(qs.arr));
    }
    public void quickSort(int s, int e) {
        if (e <= s) {
            return;
        }
        int pivot = arr[s];
        int i = s, j = e;
        while(j != i) {
            while (j > i && arr[j] >= pivot) {
                j--;
            }

                arr[i] = arr[j];
            while (j > i && arr[i] <= pivot) {
                i++;
            }
                arr[j] = arr[i];
        }
        arr[i] = pivot;
        quickSort(s, i -1);
        quickSort(i + 1, e);
    }

    private void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
