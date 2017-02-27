package Arrays.ReverseList;

/**
 * Created by zhonzhen on 10/13/16.
 */
public class Solution {
    ListNode reverseIter(ListNode node) {
        if(node == null)
            return null;
        ListNode pre = null;
        ListNode cur = node;

        while(cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;
    }

}
