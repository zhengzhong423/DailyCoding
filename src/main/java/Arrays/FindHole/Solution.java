package Arrays.FindHole;

/**
 * Created by zhonzhen on 8/25/16.
 */
public class Solution {
    int findHole(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            while (nums[i] < nums.length && nums[i] != i) {
                swap(i, nums[i], nums);
            }
            i++;
        }

        for(i = 0; i < nums.length; i++)
            if(nums[i] != i)
                return i;
        return nums.length;
    }

    private void swap(int index2, int index1, int[] nums) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }

}
