package amazon;

/*
有歧义的点就是root = nuLl的话是true还是false
回答：无所谓，case里面也没有验证，都可以

一开始可能以为这道题目很简单，但是要想一个情况：
5,4,6,null,null,3,7

这道题目用了两种方法，题解参考：
https://leetcode-cn.com/problems/validate-binary-search-tree/solution/san-chong-jie-jue-fang-shi-liang-chong-ji-bai-liao/
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class lc98 {
//    public boolean isValidBST(TreeNode root) {
//        if(root == null) return false;
////        这里用Long的原因就是因为test case里面有取Integer最大最小值的用例
//        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
//    }
//
//    private boolean helper(TreeNode node, Long leftBound, Long rightBound){
//        if(node == null) return true;
//        boolean left = helper(node.left, leftBound, (long) node.val);
//        boolean right = helper(node.right, (long)node.val, rightBound);
//        boolean checkLeft = true, checkRight = true;
//        if(node.val <= leftBound) {
//            checkLeft = false;
//        }
//        if(node.val >= rightBound) {
//            checkRight = false;
//        }
//        return checkLeft && checkRight && left && right;
//    }


//  解法二：活用中序遍历来进行计算
    private Deque<TreeNode> stack;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return false;
        stack = new ArrayDeque<>();
//        这个pre是一定要的
        TreeNode pre = null;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
//            这里这个pre的赋值时机遵照的规律就是在stack.pop以后，
//            是按照stack.pop出来的顺序来确定pre，
//            换句话来说pre应该代表的是stack上次pop出来的值，然后拿来和stack本次pop出来的值作对比
            if(pre != null && temp.val <= pre.val) return false;
            pre = temp;
            root = temp.right;
        }
        return true;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        System.out.println(new lc98().isValidBST(n1));
    }
}
