package amazon;

/*
本题和lc530相同
注意题目是搜索二叉树，所以是easy
* */


import java.util.ArrayDeque;
import java.util.Deque;

public class lc783 {
    private Deque<TreeNode> stack = new ArrayDeque<>();
    private TreeNode prev = null;
    private int res = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        if(root == null) return 0;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.addFirst(root);
                root = root.left;
            }
            TreeNode temp = stack.pollFirst();
            if(prev != null) res = Math.min(res, temp.val - prev.val);
            prev = temp;
            root = temp.right;
        }
        return res;
    }

    public class TreeNode {
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
}
