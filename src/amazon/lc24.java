package amazon;

/*
看官解动画一目了然：
https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-91/
其实思路没错，但是忘记了用两个listNode作辅助，一般都是用一个
三刷还是不熟练，需要多做
* */

public class lc24 {
    public ListNode swapPairs(ListNode head) {
        if(head == null) return null;
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode curNode = newHead;
        while (curNode.next != null && curNode.next.next != null){
            ListNode temp1 = curNode.next;
            ListNode temp2 = curNode.next.next;
            curNode.next = temp2;
            temp1.next = temp2.next;
            temp2.next = temp1;
            curNode = temp1;
        }
        return newHead.next;
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
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        System.out.println(new lc24().swapPairs(l1));
    }
}
