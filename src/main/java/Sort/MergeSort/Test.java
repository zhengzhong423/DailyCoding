package Sort.MergeSort;

/**
 * Created by zhonzhen on 10/6/16.
 */
public class Test {
    Solution sol = new Solution();

    @org.junit.Test
    public void mergeSortTest() {
        int[] arr = new int[]{4,3,2,1};
        sol.mergeSort(arr, 0, arr.length - 1);
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }
}
