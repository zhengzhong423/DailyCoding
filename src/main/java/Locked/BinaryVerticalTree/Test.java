package Locked.BinaryVerticalTree;

/**
 * Created by zhonzhen on 7/24/16.
 */
public class Test {
    private Solution sol = new Solution();

    @org.junit.Test
    public void testCase0(){
        /*
              1
            /    \
           2      3
          / \    / \
         4   5  6   7
                 \   \
                  8   9


         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.right = new TreeNode(9);

        System.out.println(sol.verticalOrder(root));
    }
}
