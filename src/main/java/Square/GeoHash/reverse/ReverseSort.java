package Square.GeoHash.reverse;

public class ReverseSort {

    public static void main(String[] args) {
        ReverseSort rs = new ReverseSort();
        int[] arr = new int[]{8, 7, 6, 5, 10, 1};
        rs.sort(arr);
        boolean[] arr2 = new boolean[]{true, false, false, false, true, true};
        rs.sort(arr2);
    }

    private void sort(int[] arr) {
        int limit = arr.length - 1;

        while (limit > 0) {
            int index = findMax(arr, 0, limit);
            reverse(arr, index);
            reverse(arr, limit);
            limit--;
        }
    }

    private void sort(boolean[] arr) {
        int limit = arr.length - 1;

        while (limit > 0) {
            if (!arr[limit]) {
                int index = findTrue(arr, limit);
                if (index > -1) {
                    reverse(arr, index);
                }
                reverse(arr, limit);
            }
            limit--;
        }
    }

    private int findTrue(boolean[] arr, int limit) {
        for (int i = 0; i <= limit; i++) {
            if (arr[i]) {
                return i;
            }
        }
        return -1;
    }

    private int findMax(int[] arr, int from, int limit) {
        int maxIndex = from;
        int maxVal = arr[from];
        for (int i = from + 1; i <= limit; i++) {
            if (arr[i] > maxVal) {
                maxIndex = i;
                maxVal = arr[i];
            }
        }
        return maxIndex;
    }

    private void reverse(boolean[] arr, int num) {
        for (int i = 0; i <= num; i++) {
            arr[i] = !arr[i];
        }
    }
    private void reverse(int[] arr, int num) {
        for (int i = 0; i <= num / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[num - i];
            arr[num - i] = tmp;
        }
    }
}
