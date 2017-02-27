package FB;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhonzhen on 2/12/17.
 */
public class BinaryFind {
    public static void main(String[] args) {
        BinaryFind BF = new BinaryFind();
        int[] arr = new int[] {12, 16, 22, 30, 35, 39, 42,
                45, 48, 50, 53, 55, 57};
        int num = 35;
        int K = 4;
        int index = BF.binaryFind(arr, num);

        System.out.println(BF.nearK(arr, index, K, num));


    }

    public List<Integer> nearK(int[] arr, int index, int K, int num) {
        int l = index;
        int r = index + 1;
        int radisL = Integer.MAX_VALUE;
        int radisR = Integer.MAX_VALUE;

        List<Integer> list = new LinkedList<Integer>();
        if (K == 0)
            return list;

        while(l >= 0 || r <arr.length) {
            if (l >= 0) {
                radisL = Math.abs(num - arr[l]);
            }

            if (r < arr.length) {
                radisR = Math.abs(num - arr[r]);
            }

            radisL = radisL == 0 ? Integer.MAX_VALUE : radisL;
            radisR = radisR == 0 ? Integer.MAX_VALUE : radisR;

            if (radisL < radisR) {
                list.add(arr[l]);
                l--;
            } else {
                list.add(arr[r]);
                r++;
            }
            radisL = Integer.MAX_VALUE;
            radisR = Integer.MAX_VALUE;

            if (list.size() == K)
                return list;
        }

        return list;
    }

    public int binaryFind(int[] arr, int num) {
        int s = 0, e = arr.length - 1;
        while(s < e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] <= num) {
                if (mid + 1 < arr.length) {
                    if (arr[mid + 1] > num) {
                        return mid;
                    } else {
                        s = mid + 1;
                    }
                } else {
                    return mid;
                }
            } else {
                e = mid - 1;
            }
        }
        return s;
    }


}
