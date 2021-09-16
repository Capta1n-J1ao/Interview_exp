package amazon;

/*
这道题目一开始写了挺顺利，思路也是对的，但是卡在了怎么找新ListNode的第一个元素，
因为是和k有关的，所以并不确定哪个会作为头元素，所以这就是这道题目最大的难点

这道题解完美解决这个难点，确实需要仔细想：
https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/di-gui-java-by-reedfan-2/
* */

public class lc25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head;
//        lastNode有两个作用，一个是作为reverse的node，
//        还有一个就是可以节省一个变量，让head一直往后走
        ListNode firstNode = head, lastNode = head, preHead = new ListNode(-1);
//        注意这里经过循环以后，lastNode是指到下一个Node去了，比如用下面这个corner case来看的话
//        如果是1 -> 2 -> 3， k = 2， 循环结束以后lastNode停在3而不是2，这个很重要，
//        试了一下如果不把下一个node包括进去的话，会在第一个循环结束以后死掉，
//        因为这样就把前面的Node和后面Node的连接完全切断了
        for(int i = 0; i < k; i++){
            if(lastNode == null) return head;
            lastNode = lastNode.next;
        }
        preHead.next = reverse(firstNode, lastNode);
        firstNode.next = reverseKGroup(lastNode, k);
        return preHead.next;
    }

    private ListNode reverse(ListNode fNode, ListNode lNode){
        ListNode pre = new ListNode(-1);
        ListNode next = new ListNode(-1);
        while (fNode != lNode){
            next = fNode.next;
            fNode.next = pre;
            pre = fNode;
            fNode = next;
        }
        return pre;
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
        System.out.println(new lc25().reverseKGroup(n1, 2));
    }
}
