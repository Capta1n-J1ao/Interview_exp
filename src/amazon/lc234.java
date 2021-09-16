package amazon;

/*
这道题目的方法完全是靠自己想出来并且写出来的，其实445和234有点像，都是借助stack这个工具，
所以在做ListNode的题目特别是和顺序有要求的时候一定要和stack结合起来,0525刷的时候从1ms变为15ms，因为测试用例多了很多

但是这样做不能满足空间复杂度O（1）
所以参考了以下的题解，做了一个优化版，
评论下面还有一个更优化版，我也参考了点，但因为他的初始化是fast = head.next，不是我习惯的风格，
这个优化版本可能需要很久才能写出来，如果有一小时以上的时间可以看，一小时以下慎重！
https://leetcode-cn.com/problems/palindrome-linked-list/solution/zhu-shi-ban-kuai-man-zhi-zhen-qian-bu-fan-zhuan-2m/
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class lc234 {
//    public boolean isPalindrome(ListNode head) {
//        if(head == null || head.next == null) return true;
//        Deque<Integer> stack = new ArrayDeque<>();
//        ListNode slow = head;
//        ListNode fast = head;
//        stack.push(slow.val);
//        while (fast != null && fast.next != null){
//            slow = slow.next;
//            fast = fast.next.next;
//            if(fast == null){
//                break;
//            }else if(fast.next == null){
//                slow = slow.next;
//                break;
//            }else {
//                stack.push(slow.val);
//            }
//        }
//        while (slow != null){
//            if(slow.val != stack.pop()) return false;
//            slow = slow.next;
//        }
//        return true;
//    }

//    方法二：再次挑战翻转函数,因为亚麻确实考了很多ListNode
//    最纠结的就是单双数的时候翻转以后的联系关系，以1 -> 2 -> 3 -> 4 -> null为例
//    在反转以后变成了1 -> 2 -> null 和 1 -> 2 -> 2 -> null，虽然2被指了两次但是无所谓的
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reverseHead = reverse(slow);
        while (head !=null && reverseHead != null){
            if(head.val != reverseHead.val) return false;
            head = head.next;
            reverseHead = reverseHead.next;
        }
        return true;
    }

    private ListNode reverse(ListNode node){
        ListNode cur = node;
        ListNode pre = null;
        while (node != null){
            ListNode temp = node.next;
            node.next = pre;
            pre = node;
            node = temp;
        }
        return pre;
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
        ListNode head = new ListNode(1);
        ListNode fristNode = new ListNode(2);
        ListNode secondNode = new ListNode(2);
        ListNode ThirdNode = new ListNode(1);
        head.next=fristNode;
        fristNode.next=secondNode;
        secondNode.next=ThirdNode;
        ThirdNode.next = null;
        System.out.println(new lc234().isPalindrome(head));
    }
}
