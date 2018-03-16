package Adobe.RunLengthEncoding;

class RunLengthNode {
    int val;
    int len;
    public RunLengthNode(int val, int len) {
        this.val = val;
        this.len = len;
    }
    RunLengthNode next;
}

public class RunLengthMap {
    RunLengthNode dummyHead;

    public RunLengthMap (int initLen, int initVal) {
        dummyHead = new RunLengthNode(-1, -1);
        dummyHead.next = new RunLengthNode(initVal, initLen);
    }

    public void set (int index, int val) {
        RunLengthNode pre = dummyHead;
        RunLengthNode curNode = dummyHead.next;
        int curIndex = 0;
        int nextIndex = curNode.len + curIndex;
        while (index >= nextIndex) {
            curIndex = nextIndex;
            pre = curNode;
            curNode = curNode.next;
            nextIndex = curNode.len + curIndex;
        }

        int preIndices = index - curIndex;
        int postIndices = nextIndex - index - 1;

        RunLengthNode node = pre;
        if (preIndices == 0) {
            node.next = node.next.next;
        } else {
            node.next = new RunLengthNode(curNode.val, preIndices);
            node = node.next;
        }

        node.next = new RunLengthNode(val, 1);
        node = node.next;

        if (postIndices != 0) {
            node.next = new RunLengthNode(curNode.val, postIndices);
            node = node.next;
        }

        node.next = curNode.next;

        while(pre!= null && pre.next != null && pre.val == pre.next.val) {
            pre.len = pre.len + pre.next.len;
            pre.next = pre.next.next;
            if (pre == curNode.next) {
                break;
            }
        }
    }

    private RunLengthNode combine(RunLengthNode head, RunLengthNode end) {
        RunLengthNode node = head;
        while (node != end) {
            if (node.val == node.next.val) {
                node.len = node.len + node.next.len;
                node.next = node.next.next;
            }
            node = node.next;
        }
        return head;
    }

    public int get (int index) {
        RunLengthNode node = dummyHead.next;
        int local = 0;
        while (node != null) {
            if (local + node.len > index) {
                return node.val;
            }
            local += node.len;
            node = node.next;
        }
        return -1;
    }

    public static void main(String[] args) {
        RunLengthMap myMap = new RunLengthMap(10, 0);
        myMap.set(1, 1);
        myMap.set(3, 3);
        myMap.set(4, 4);
        myMap.set(0, 10);
        myMap.set(6, 4);
        myMap.set(5, 4);
        System.out.println(myMap.get(5));
    }
}
