package amazon;

/*
方法很巧妙，看官解最后一个双指针的方法
https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
* */

public class lc19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        while (n > 0){
            fast = fast.next;
            n--;
        }
//        针对corner case，即只有一个node的时候
        if(fast == null) return head.next;
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
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
