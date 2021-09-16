package amazon;

/*
此题用了两种方法，第一种方法是我自己想的，没有参考，最后也是做出来了，性能也还行。

但是见到kth的题目，第一反应还是要使用priorityqueue来解决，所以有了方法二：
https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/duo-tu-yan-shi-23-he-bing-kge-pai-xu-lian-biao-by-/

可以先做lc21然后再做这题，这样比较进阶。是同一个类型
* */

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class lc23 {
//    public ListNode mergeKLists(ListNode[] lists) {
//        LinkedList<Integer> allNum = new LinkedList<>();
//        if(lists.length == 0 || lists == null) return null;
//        int count = 0;
//        for(ListNode list : lists){
//            while (list != null){
//                count++;
//                allNum.add(list.val);
//                list = list.next;
//            }
//        }
////        这句话是针对corner case的，否则会有最后几个例子不对
//        if(count == 0) return null;
//        int listLen = allNum.size();
//        int[] numArray = new int[listLen];
//        for(int i = 0; i < listLen; i++){
//            numArray[i] = allNum.get(i);
//        }
//        Arrays.sort(numArray);
//        ListNode res = new ListNode(-1, new ListNode());
//        ListNode temp = res;
//        for(int i = 0; i < listLen; i++){
//            ListNode node = new ListNode(numArray[i]);
//            temp.next = node;
//            temp = temp.next;
//        }
//        return res.next;
//    }


//    方法二用的PriorityQueue，大幅度优化运行时间。
//
//    时间复杂度：O(kn * log k)
//    考虑优先队列中的元素不超过 k 个，那么插入和删除的时间代价为 O(logk)，这里最多有 kn 个点，
//    对于每个点都被插入删除各一次，故总的时间代价即渐进时间复杂度为 O(kn * log k)
//
//    空间复杂度O(K)：(其中K代表List的数目)
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0 || lists == null) return null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for(ListNode list : lists){
            if(list != null) minHeap.add(list);
        }

        ListNode res = new ListNode(-1, new ListNode());
        ListNode temp = res;
        while (!minHeap.isEmpty()){
            ListNode temp1 = minHeap.poll();
            temp.next = temp1;
            temp = temp.next;
            if(temp1.next != null){
                minHeap.add(temp1.next);
            }

        }
        return res.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public static void main(String[] args) {
        ListNode n3 = new ListNode(5, null);
        ListNode n2 = new ListNode(4, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode n6 = new ListNode(4, null);
        ListNode n5 = new ListNode(3, n6);
        ListNode n4 = new ListNode(1, n5);
        ListNode[] test = {n1,n4};
        ListNode res = new lc23().mergeKLists(test);
    }
}
