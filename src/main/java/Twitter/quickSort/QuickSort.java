package Twitter.quickSort;

public class QuickSort {


    public void quickSort(int[] arr, int s, int e) {
        if (s < e) {
            int partition = partition(arr, s, e);
            quickSort(arr, s, partition - 1);
            quickSort(arr, partition + 1, e);
        }
    }

    public int partition(int arr[], int s, int e) {

        int pivot = arr[s];
        while (s < e) {
            while (s < e && arr[e] > pivot) {
                e--;
            }
            arr[s] = arr[e];
            while (s < e && arr[s] < pivot) {
                s++;
            }
            arr[e] = arr[s];
        }
        arr[s] = pivot;
        return s;
    }
}
