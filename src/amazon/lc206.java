package amazon;

public class lc206 {
    public ListNode reverseList(ListNode head) {
        ListNode preHead = head, last = null;
        while (preHead != null){
            preHead = preHead.next;
            head.next = last;
            last = head;
            head = preHead;
        }
        return last;
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
        n1.next = n2;
        n2.next = n3;
        System.out.println(new lc206().reverseList(n1));
    }
}
