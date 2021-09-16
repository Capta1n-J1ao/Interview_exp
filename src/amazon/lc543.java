package amazon;
/*
一共有三道针对性的题目，顺序的话687/543先做，特别是543的视频要看！然后124最后做即可
官方题解讲解的很清楚，一定要看视频
https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/er-cha-shu-de-zhi-jing-by-leetcode-solution/
* */

public class lc543 {
    private int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return res;
    }

    private int helper(TreeNode root){
        if(root == null) return 0;
        int leftRes = helper(root.left);
        int rightRes = helper(root.right);
        res = Math.max(res, leftRes + rightRes);
        return Math.max(leftRes, rightRes) + 1;
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
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        System.out.println(new lc543().diameterOfBinaryTree(n1));
    }
}
