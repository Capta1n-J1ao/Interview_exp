package amazon;

/*
这道题思路不难想，就是第一反应的思路,最好预留一个小时debug，很费时间
但是很考验对于ListNode的编程理解，特别是
1. 快慢指针
2. 颠倒列表
3. 合并列表
这三个其实都可以做三道题目了，但是这题却糅合在一起，所以难度挺高，debug很费时间
* */

public class lc143 {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode slow = head, fast = head;
        while (slow.next !=null && fast.next != null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reverseHead = slow.next;
        ListNode reverseTail = null;
        slow.next = null;
//        颠倒后半部分ListNode
        while (reverseHead != null){
            ListNode temp = reverseHead.next;
            reverseHead.next = reverseTail;
            reverseTail = reverseHead;
            reverseHead = temp;
        }
        ListNode newHead = head;
        while (newHead !=null && reverseTail != null){
            ListNode temp = newHead.next;
            ListNode temp1 = reverseTail.next;
            newHead.next = reverseTail;
            newHead = temp;
            reverseTail.next = newHead;
            reverseTail = temp1;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x){
            this.val = x;
            this.next = null;
        }

        public ListNode(int x, ListNode next){
            this.val = x;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
//        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
//        l5.next = l6;
        new lc143().reorderList(l1);
    }
}
