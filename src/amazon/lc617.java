package amazon;

public class lc617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
//        if(root1 == null) return root2;
//        if(root2 == null) return root1;
        TreeNode res = helper(root1, root2);
        return res;
    }

    private TreeNode helper(TreeNode n1, TreeNode n2){
        if(n1 == null && n2 == null) return null;
        if(n1 == null) return n2;
        if(n2 == null) return n1;
        int n1Val = n1 == null ? 0 : n1.val;
        int n2Val = n2 == null ? 0 : n2.val;
        n1.val = n1Val + n2Val;
        n1.left = helper(n1.left, n2.left);
        n1.right = helper(n1.right, n2.right);
        return n1;
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
        n1.left = n2;
        n3.right = n4;
        System.out.println(new lc617().helper(n1, n3));
    }
}
