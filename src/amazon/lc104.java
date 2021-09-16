package amazon;

public class lc104 {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        int leftDepth = 0, rightDepth = 0;
        if(root.left != null) {
            leftDepth = maxDepth(root.left) ;
        }
        if(root.right != null) {
            rightDepth = maxDepth(root.right);
        }
        return Math.max(leftDepth, rightDepth) + 1;
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
