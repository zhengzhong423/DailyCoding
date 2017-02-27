package Arrays.ReverseList;

/**
 * Created by zhonzhen on 10/13/16.
 */
public class Test {
    private Solution sol = new Solution();

    @org.junit.Test
    public void testAssertion() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        ListNode rev = sol.reverseIter(node);

        while (rev != null) {
            System.out.println(rev.val);
            rev = rev.next;
        }
    }

}
