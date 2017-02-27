package Graph.LongestConsequence;

/**
 * Created by zhonzhen on 10/3/16.
 */


public class Solution {

    public static void main(String[] args) {

    }

    public int getLongest(Node node, int val, int combo) {
        if (node == null)
            return combo;
        if (node.val == val + 1)
            combo++;
        else
            combo = 1;
        int left = getLongest(node.left, node.val, combo);
        int right = getLongest(node.right, node.val, combo);
        return Math.max(Math.max(left, right), combo);
    }


}
