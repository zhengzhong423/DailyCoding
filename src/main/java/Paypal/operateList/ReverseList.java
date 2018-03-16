package Paypal.operateList;

public class ReverseList {

    private Node reverse (Node node) {
        Node pre = null;
        Node cur = node;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

