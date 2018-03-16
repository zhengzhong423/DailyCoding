package Adobe.RunLengthEncoding.mergeSort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] arr = new int[] {8,9,3,10,2,1};
        ms.mergeSort(arr, 0 , arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    public void mergeSort(int[] arr, int s, int e) {
        if (s >= e) {
            return;
        }

        int mid = s + (e - s) / 2;
        mergeSort(arr, s, mid);
        mergeSort(arr, mid + 1, e);
        merge(arr, s, mid, e);
    }

    private void merge(int[] arr, int s, int mid, int e) {
        int[] tmp = new int[e - s + 1];
        int cur = mid + 1, idx = 0, start = s;
        while (s <= mid && cur <= e) {
            if (arr[s] < arr[cur]) {
                tmp[idx++] = arr[s++];
            } else {
                tmp[idx++] = arr[cur++];
            }
        }

        while (s <= mid) {
            tmp[idx++] = arr[s++];
        }

        while (cur <= e) {
            tmp[idx++] = arr[cur++];
        }

        for (int i = 0; i < tmp.length; i++) {
            arr[start + i] = tmp[i];
        }
    }
}
