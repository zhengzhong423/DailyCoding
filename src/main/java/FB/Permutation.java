package FB;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhonzhen on 2/12/17.
 */
public class Permutation {
    public static void main(String[] args) {
        Permutation per = new Permutation();
        System.out.println(per.permute(new int[] {1, 2,2, 3}));
    }

//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> rs = new LinkedList<List<Integer>>();
//        helper(nums, 0, rs);
//        return rs;
//    }
//
//    private void helper(int[] nums, int index, List<List<Integer>> rs){
//
//        if (index == nums.length) {
//            List<Integer> tmp = new LinkedList<Integer>();
//            for (int i = 0; i < nums.length; i++) {
//                tmp.add(nums[i]);
//            }
//            rs.add(tmp);
//            return;
//        }
//        for(int i = index; i < nums.length; i++) {
//            swap(nums, index, i);
//            helper(nums, index + 1, rs);
//            swap(nums, index, i);
//        }
//    }
//
//    private void swap(int[] arr, int i, int j) {
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> rs = new LinkedList<List<Integer>>();
        List<Integer> tmp = new LinkedList<Integer>();
        Arrays.sort(nums);
        helper(nums, used, rs, tmp);

        return rs;
    }

    private void helper(int[] nums, boolean[] used, List<List<Integer>> rs, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            rs.add(tmp);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i >= 1 && nums[i] == nums[i - 1] && used[i -1])) continue;
            used[i] = true;
            List<Integer> tmpCur = new LinkedList<Integer>(tmp);
            tmpCur.add(nums[i]);
            helper(nums, used, rs, tmpCur);
            used[i] = false;
        }
    }
}
