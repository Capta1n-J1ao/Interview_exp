package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

public class lc445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> l1Stack = new ArrayDeque<>();
        Deque<Integer> l2Stack = new ArrayDeque<>();
        ListNode res = null;
        int carry = 0;
        while (l1 != null){
            l1Stack.addFirst(l1.val);
            l1 = l1.next;
        }

        while (l2 != null){
            l2Stack.addFirst(l2.val);
            l2 = l2.next;
        }

        while (!l1Stack.isEmpty() || !l2Stack.isEmpty() || carry > 0){
            int add1 = l1Stack.isEmpty() ? 0 : l1Stack.pollFirst();
            int add2 = l2Stack.isEmpty() ? 0 : l2Stack.pollFirst();
            int curRes = add1 + add2 + carry;
            if(curRes > 9){
                carry = 1;
                curRes %= 10;
            }else carry = 0;
            if(res == null) res = new ListNode(curRes);
            else {
                ListNode cur = new ListNode(curRes);
                cur.next = res;
                res = cur;
            }
        }
        return res;
    }

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int x){
            val = x;
            next = null;
        }
    }
}
