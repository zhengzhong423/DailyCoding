package PG.MaxPath;

/**
 * Created by zhonzhen on 2/17/17.
 */
public class MaxRootLeafPath {
    static class Node {
        int val;
        Node left;
        Node right;
        public Node(int val) {this.val = val;}
    }

    public int getMaxSum(Node node) {
        if (node == null) {
            return 0;
        }

        int left = getMaxSum(node.left);
        int right = getMaxSum(node.right);

        return Math.max(left, right) + node.val;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.right = new Node(5);
        root.left.right.right = new Node(9);
        root.right = new Node(3);
        root.right.right = new Node(6);

        MaxRootLeafPath MRLP = new MaxRootLeafPath();

        System.out.println(MRLP.getMaxSum(root));
    }

}
