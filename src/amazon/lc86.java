package amazon;

public class lc86 {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        ListNode minHeadpre = new ListNode(-200), maxHeadpre = new ListNode(-200),
                temp = head, minHead = minHeadpre, maxHead = maxHeadpre;
        while (temp != null){
            if(temp.val < x){
                minHead.next = temp;
                minHead = minHead.next;
            }else {
                maxHead.next = temp;
                maxHead = maxHead.next;
            }
            temp = temp.next;
        }
        minHead.next = maxHeadpre.next;
//        这句不加就会让ListNode有cycle，会报错
        maxHead.next = null;
        return minHeadpre.next;
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
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(2);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        System.out.println(new lc86().partition(l1, 3));
    }
}
