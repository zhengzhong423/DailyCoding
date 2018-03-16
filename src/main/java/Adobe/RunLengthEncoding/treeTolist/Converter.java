package Adobe.RunLengthEncoding.treeTolist;

public class Converter {
    ListNode head = null;
    ListNode cur = null;
    ListNode prev = null;

    public static void main(String[] args) {
        Converter converter = new Converter();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(8);
        converter.convert(root);
    }

    public void convert(TreeNode root) {
        if (root == null) {
            return;
        }
        convert(root.left);
        prev = cur;
        if (head == null) {
            head = new ListNode(root.val);
            cur = head;
        } else {
            cur = new ListNode(root.val);
            cur.pre = prev;
            if (prev != null) {
                prev.next = cur;
            }
        }
        convert(root.right);
    }
}
