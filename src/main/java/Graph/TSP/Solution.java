package Graph.TSP;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhonzhen on 9/14/16.
 */
public class Solution {

    List<List<Integer>> rs = new LinkedList<List<Integer>>();

    public static void main(String[] args) {
        int[][] arr = { {0 , 10, 15, 20},
                        {10, 0 , 35, 25},
                        {15, 35, 0 , 30},
                        {20, 25, 30, 0}};
        Solution sol = new Solution();
        sol.TSPsolver(arr);
    }

    private int TSPsolver(int[][] arr) {
        LinkedList<Integer> cur = new LinkedList<Integer>();
        helper(arr, 0, 0, cur);
        System.out.println(rs);
        return 0;
    }

    private void helper(int[][] arr, int start, int end, List<Integer> cur) {
        if(cur.size() == arr.length) {
            if(arr[start][end] > 0) {
                List<Integer> ans = new LinkedList<Integer>(cur);
                ans.add(end);
                rs.add(ans);
            }
            return;
        }

        if(cur.size() == 0) {
            cur.add(0);
        }

        for(int i = 0; i < arr[start].length; i++) {
            if(!cur.contains(i)) {
                List<Integer> newRoute = new LinkedList<Integer>(cur);
                newRoute.add(i);
                helper(arr, i, end, newRoute);
            }
        }
    }

}
