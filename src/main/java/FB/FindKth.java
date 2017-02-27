package FB;

/**
 * Created by zhonzhen on 2/11/17.
 */
public class FindKth {
    public static void main(String[] args) {
        FindKth FK = new FindKth();

        //TC1
        int[] arr1 = {1,2,3,4,5,6,7};
        System.out.println(FK.findKth(arr1, 4));

        //TC2
        int[] arr2 = {7, 10, 4, 3, 20, 15};
        System.out.println(FK.findKth(arr2, 3));
    }


    public int findKth(int[] arr, int k) {
        return helper(arr, 0, arr.length - 1, k - 1);
    }

    private int helper(int[] arr, int s, int e, int index) {
        if (e == s && index == 0) {
            return s;
        }
        if (e < s || index > e - s || s == arr.length || s < 0) {
            return -1;
        }
        int mid = partition(arr, s, e);
        if (mid - s == index) {
            return arr[mid];
        } else if (mid - s > index) {
            return helper(arr, s, mid - 1, index);
        } else if (mid - s < index) {
            return helper(arr, mid + 1, e, index - 1 - (mid - s));
        }

        return -1;
    }

    private int partition(int[] arr, int s, int e) {
        int pivot = arr[s];
        while(e > s) {
            while(e > s && arr[e] > pivot) {
                e--;
            }
            swap(arr, s, e);
            while(e > s && arr[s] < pivot) {
                s++;
            }
            swap(arr, s, e);
        }

        return s;
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
