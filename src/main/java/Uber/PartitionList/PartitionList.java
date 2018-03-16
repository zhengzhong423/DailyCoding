package Uber.PartitionList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
 }


public class PartitionList {

    public static void main(String[] args) {
        PartitionList pl = new PartitionList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        pl.partition(head, 1);
    }
    public ListNode partition(ListNode head, int x) {
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode fPart = root, sPart = root, cur = head;

        while(cur != null) {
            if (x >= cur.val) {
                cur = cur.next;
            } else {
                cur = addAfter(fPart, cur, sPart);
                fPart = fPart.next;
            }
            sPart = sPart.next;
        }

        return root.next;
    }

    ListNode addAfter(ListNode head, ListNode cur, ListNode prev) {
        ListNode tmp = cur.next;
        prev.next = cur.next;
        cur.next = head.next;
        head.next = cur;
        return tmp;
    }
}
