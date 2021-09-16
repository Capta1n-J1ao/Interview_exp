package amazon;

public class lc110 {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int roorLeft = root.left == null ? 0 : helper(root.left);
        int roorRight = root.right == null ? 0 : helper(root.right);
//        后两个很重要，比如[1,2,2,3,null,null,3,4,null,null,4]这个例子
        return Math.abs(roorLeft - roorRight) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int helper(TreeNode n1){
        if(n1.left == null && n1.right == null) return 1;
        int leftHeight = n1.left != null ? helper(n1.left) : 0;
        int rightHeight = n1.right != null ? helper(n1.right) : 0;
        return Math.max(leftHeight, rightHeight) + 1;
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
        n1.right = n2;
        n2.right = n3;
        System.out.println("正确答案是 false, 代码答案为 ： " + new lc110().isBalanced(n1));
    }
}
