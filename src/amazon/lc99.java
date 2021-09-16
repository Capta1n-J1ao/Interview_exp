package amazon;

/*
这道题目是搜索二叉树，所以我第一反应就是用中序遍历，这点是没有问题的
但是怎样记录哪两个是错误的节点是本题的重点，需要仔细想一下

思路参考的以下题解，不是java的，但是图很清楚：
https://leetcode-cn.com/problems/recover-binary-search-tree/solution/tu-jie-hui-fu-yi-ge-er-cha-sou-suo-shu-by-hyj8/
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class lc99 {
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode firstErr = null, secondErr = null, pre = null;
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.addFirst(root);
                root = root.left;
            }
            TreeNode temp = stack.pollFirst();
            if(pre != null && firstErr == null &&temp.val < pre.val) firstErr = pre;
            if(firstErr != null && temp.val < pre.val) secondErr = temp;
            pre = temp;
            root = temp.right;
        }
        swap(firstErr, secondErr);
        return;
    }

//    注意这里交换要用val而不是TreeNode
    private void swap(TreeNode first, TreeNode second){
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
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
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n3;
        n3.right = n2;
        new lc99().recoverTree(n1);
    }

}
