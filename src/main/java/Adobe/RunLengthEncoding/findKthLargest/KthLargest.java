package Adobe.RunLengthEncoding.findKthLargest;
import java.util.Arrays;

public class KthLargest {
    public static void main(String[] args) {
        KthLargest kl = new KthLargest();
        System.out.println(kl.findKthLargest(new int[] {-1, 2, 0}, 1));
    }

    public int findKthLargest(int[] nums, int k) {
        int targetIdx = nums.length - k;
        return helper(nums, targetIdx, 0, nums.length - 1);
    }

    public int helper(int[] nums, int k, int s, int e) {
        if (s >= e) {
            return nums[s];
        }
        int idx = getPartition(nums, s, e);

        if (idx == k) {
            return nums[idx];
        }

        if (idx > k) {
            return helper(nums, k, s, idx - 1);
        } else {
            return helper(nums, k,idx + 1, e);
        }
    }

    private int getPartition(int[] nums, int s, int e) {
        if (e <= s) {
            return s;
        }

        int pivot = nums[s];

        while (s < e) {
            while (s < e && nums[e] >= pivot) {
                e--;
            }
            nums[s] = nums[e];
            while (s < e && nums[s] < pivot) {
                s++;
            }
            nums[e] = nums[s];
        }
        nums[s] = pivot;
        return s;
    }
}
