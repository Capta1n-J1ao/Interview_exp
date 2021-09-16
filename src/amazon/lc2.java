package amazon;

/*
思考 9 + 99的corner case
* */

public class lc2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode curPos = res;
        int carry = 0;
        int curRes = 0;
        while (l1 != null || l2 != null){
            int l1val = l1 != null ? l1.val : 0;
            int l2val = l2 != null ? l2.val : 0;
            curRes = l1val + l2val + carry;
            carry = curRes / 10;
            curRes = curRes % 10;
            ListNode temp = new ListNode(curRes);
            curPos.next = temp;
            curPos = curPos.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
//        这个是要注意别忘记的
        if(carry == 1){
            ListNode temp = new ListNode(carry);
            curPos.next = temp;
        }
        return res.next;
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
        ListNode n2 = new ListNode(9);
        ListNode n1 = new ListNode(9, n2);

        ListNode n3 = new ListNode(9);
        System.out.println(new lc2().addTwoNumbers(n1, n3));
    }
}
