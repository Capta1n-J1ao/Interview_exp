package amazon;


/*
这道题目受之前几道题目的影响，首先选择了priorityqueue的做法，但是时间上面没有常规方法好。
建议可以先做这题，然后做lc23，是同一个系列。
* */
import java.util.Comparator;
import java.util.PriorityQueue;

public class lc21 {
//    方法一，使用priorityqueue，性能尚可，但是没常规方法好
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if(l1 == null) return l2;
//        if(l2 == null) return l1;
//        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
//            @Override
//            public int compare(ListNode o1, ListNode o2) {
//                return o1.val - o2.val;
//            }
//        });
//
//        ListNode res = new ListNode(-1);
//        ListNode node = res;
//        priorityQueue.add(l1);
//        priorityQueue.add(l2);
//        while (!priorityQueue.isEmpty()){
//            ListNode temp = priorityQueue.poll();
//            if(temp.next != null) priorityQueue.add(temp.next);
//            node.next = temp;
//            node = node.next;
//        }
//        return res.next;
//    }


//    方法二：常规方法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode res = new ListNode(-1);
        ListNode temp = res;
        while (l1 != null && l2 != null){
            if(l1.val < l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
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
        ListNode n3 = new ListNode(4, null);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode n6 = new ListNode(4, null);
        ListNode n5 = new ListNode(3, n6);
        ListNode n4 = new ListNode(1, n5);

        ListNode test = new lc21().mergeTwoLists(n1, n4);
    }
}
