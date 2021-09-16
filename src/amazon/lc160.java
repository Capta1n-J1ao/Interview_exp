package amazon;

/*
这道题目最新写的复杂了，原因是因为我考虑没有相交的情况想多了
其实不用很复杂的原因就是因为只要依次遍历了headA和headB的话，
如果没有相交，那么也会在null那里碰面，导致copyA == copyB
* */

public class lc160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode copyA = headA;
        ListNode copyB = headB;
//        ListNode countNode = headA;
        int aFlag = 0;
        int bFlag = 0;
        while ((copyA != null && aFlag != 2) || copyB != null && bFlag != 2){
            if(copyA == null){
                copyA = headB;
                aFlag++;
            }
            if(copyB == null){
                copyB = headA;
                bFlag++;
            }
            if(copyA == copyB) return copyA;
            copyA = copyA.next;
            copyB = copyB.next;
        }
        return null;
    }

    public class ListNode {
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

}
