package amazon;

import java.util.List;

public class lc328 {
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return null;
        ListNode singleNode = head;
        ListNode headDouble = head.next;
        ListNode doubleNode = headDouble;
//        这个判断条件还是需要好好想想的，一开始doubleNode.next != null没写
//        写的是singleNode != null 就不对，所以要仔细思考下的
        while (singleNode != null &&  doubleNode != null && doubleNode.next != null ){
            singleNode.next = doubleNode.next;
            singleNode = singleNode.next;
            doubleNode.next = singleNode.next;
            doubleNode = doubleNode.next;
        }
        singleNode.next = headDouble;
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x){
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
//        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
//        l4.next = l5;
        System.out.println(new lc328().oddEvenList(l1));
    }
}
