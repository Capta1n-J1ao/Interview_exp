package amazon;

import java.util.LinkedList;

public class lc203 {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        ListNode preHead = new ListNode(-1);
        ListNode temp = preHead;
        preHead.next = head;
        while (head != null){
            if(head.val == val){
                temp.next = head.next;
                head = temp.next;
            }else {
                temp = head;
                head = head.next;
            }
        }
        return preHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int x){
            val = x;
            next = null;
        }
    }
}
