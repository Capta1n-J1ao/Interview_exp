package amazon;

/*
这道题目主要看下test case，如果这两个能对，基本问题就不大了
本质上就是所有元素都一样，或者最后几个元素一样的情况
* */

public class lc83 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode post = head.next;
        while (post != null){
            while (post != null && pre.val == post.val) {
                post = post.next;
            }
            pre.next = post;
            pre = pre.next;
            post = pre == null ? null : pre.next;
        }
        return head;
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
//        需要考虑的test case [1,2,2]
//        需要考虑的test case [1,1,1]
//        ListNode n1 = new ListNode(1);
//        ListNode n2 = new ListNode(1);
//        ListNode n3 = new ListNode(1);
//        n1.next = n2;
//        n2.next = n3;
//        System.out.println(new lc83().deleteDuplicates(n1));

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(2);
        n1.next = n2;
        n2.next = n3;
        System.out.println(new lc83().deleteDuplicates(n1));
    }

}
