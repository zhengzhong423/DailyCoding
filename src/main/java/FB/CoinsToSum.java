package FB;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhonzhen on 2/11/17.
 */
public class CoinsToSum {
    public static void main(String[] args) {
        CoinsToSum CTS = new CoinsToSum();

        //TC1
        System.out.println(CTS.coinsToSum(new int[] {2, 3, 5}, 10));
        System.out.println(CTS.coinToSumNum(new int[] {2, 3, 5}, 10));
    }

    private Integer coinToSumNum(int[] nums, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for(int j = 0; j < nums.length; j++) {
            for(int i = 0; i <= sum; i++) {
                if (i - nums[j] >= 0)
                    dp[i] += dp[i - nums[j]];
            }
        }
        for (int i = 0; i <= sum; i++)
            System.out.print(dp[i] + " ");
        System.out.println();
        return dp[sum];
    }

    private List<List<Integer>> coinsToSum(int[] nums, int sum) {
        List<List<Integer>> rs = new LinkedList<List<Integer>>();
        helper(rs, new LinkedList<Integer>(), nums, sum, 0);
        return rs;
    }

    private void helper(List<List<Integer>> rs, List<Integer> cur,
                        int[] nums, int sum, int index) {
        if (sum == 0) {
            rs.add(cur);
            return;
        }
        if (sum < 0) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            List<Integer> tmp = new LinkedList<Integer>(cur);
            tmp.add(nums[i]);
            helper(rs, tmp, nums, sum - nums[i], i);
        }

    }



}
