package Paypal.operateList;

public class MergeSort {
    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        Node lNode = new Node(5);
        lNode.next = new Node(4);
        lNode.next.next = new Node(3);
        lNode.next.next.next = new Node(2);

        Node rNode = new Node(6);
        rNode.next = new Node(7);
        rNode.next.next = new Node(1);
        rNode.next.next.next = new Node(8);
        rNode.next.next.next.next = new Node(10);

        lNode.next.next.next.next = rNode;

        ms.output(ms.mergeSort(lNode));

    }

    private void output(Node node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public Node mergeSort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        
        Node mid = mindMid(node);
        Node tmp = mid.next;
        mid.next = null;
        Node left = mergeSort(node);
        Node right = mergeSort(tmp);

        return merge(left, right);
    }

    private Node merge(Node left, Node right) {
        Node header = new Node(-1);
        Node cur = header;
        while(left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left == null) {
            cur.next = right;
        } else {
            cur.next = left;
        }
        return header.next;
    }

    private Node mindMid(Node node) {
        Node fastPointer = node, slowPointer = node;
        while (fastPointer.next != null && fastPointer.next.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        return slowPointer;
    }
}
