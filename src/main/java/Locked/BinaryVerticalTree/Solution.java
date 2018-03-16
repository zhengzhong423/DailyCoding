package Locked.BinaryVerticalTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zhonzhen on 7/24/16.
 *
 * 题目:
 * http://www.cnblogs.com/yrbbest/p/5065457.html
 */
public class Solution {

    class TreeVerticalNode{
        TreeNode treeNode;
        int col;
        TreeVerticalNode(TreeNode treeNode, int col){
            this.treeNode = treeNode;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root){
        List<List<Integer>> rs = new LinkedList<List<Integer>>();
        HashMap<Integer, LinkedList<Integer>> hash = new HashMap<Integer, LinkedList<Integer>>();
        int minCol = 0, maxCol = 0;
        Stack<TreeVerticalNode> s = new Stack<TreeVerticalNode>();
        if(root == null)
            return rs;
        s.push(new TreeVerticalNode(root, 0));

        while(!s.isEmpty()){
            TreeVerticalNode cur = s.pop();
            if(cur.treeNode.right != null)
                s.push(new TreeVerticalNode(cur.treeNode.right, cur.col + 1));
            if(cur.treeNode.left != null)
                s.push(new TreeVerticalNode(cur.treeNode.left, cur.col - 1));
            if(!hash.containsKey(cur.col))
                hash.put(cur.col, new LinkedList<Integer>());
            hash.get(cur.col).add(cur.treeNode.val);
            minCol = Math.min(minCol, cur.col);
            maxCol = Math.max(maxCol, cur.col);
        }

        for(int i = minCol; i <= maxCol; i++)
            rs.add(hash.get(i));
        return rs;
    }
}
