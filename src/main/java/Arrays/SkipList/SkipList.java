package Arrays.SkipList;

class Node {
    int val;
    Node next;
    Node down;
}


public class SkipList {

    public boolean findElement(Node head, int val) {
        while (head != null) {
            if (head.val == val) {
                return true;
            }

            if (head.next == null) {
                return false;
            }

            if (head.val < val && head.next.val > val) {
                head = head.down;
            } else if (head.val < val && head.next.val <= val) {
                head = head.next;
            } else {
                return false;
            }
        }
        return false;
    }
}
