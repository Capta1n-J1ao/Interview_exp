package amazon;

/*
这道题目写的久了点，但是不算难
* */

public class lc92 {
    private ListNode head;
    public ListNode reverseBetween(ListNode head, int left, int right) {
        this.head = head;
        if(head == null || left == right) return head;
        int count = 0;
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode temp = pre, preLeft = head, leftNode = head, rightNode = head, tail = new ListNode(-1);
        while (count <= right && temp != null){
            if(count == left - 1) preLeft = temp;
            if(count == left) leftNode = temp;
            if(count == right) {
                rightNode = temp;
                tail = rightNode.next;
            }
            temp = temp.next;
            count++;
        }
        reverse(leftNode, rightNode, preLeft, right - left + 1);
        preLeft.next = rightNode;
        leftNode.next = tail;
        return pre.next;
    }

    private void reverse(ListNode left, ListNode right, ListNode preHead, int count){
        ListNode dummy = new ListNode(-1);
        ListNode temp1 = left;
        int num = 0;
        while (num < count){
            ListNode temp = temp1.next;
            temp1.next = dummy;
            dummy = temp1;
            temp1 = temp;
            num++;
//            System.out.println(temp.val);
//            System.out.println(temp1.val);
//            System.out.println(dummy.val);
        }
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
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        System.out.println(new lc92().reverseBetween(n1, 2, 4));
    }
}
