package amazon;

/*
方法很巧妙，必须秒会，并且在递归的方法中，要搞清楚辅助函数所代表的含义！！
其实可以用两种方法，二刷的时候可以好好想想，参考题解：
https://leetcode-cn.com/problems/symmetric-tree/solution/dong-hua-yan-shi-101-dui-cheng-er-cha-shu-by-user7/
* */

public class lc101 {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return false;
        return helper(root.left, root.right);
    }

//    这个辅助函数的含义是：n1和n2是否为镜像对称节点
    private boolean helper(TreeNode n1, TreeNode n2){
        if(n1 == null && n2 == null) return true;
        if(n1 == null || n2 == null) return false;
        if(n1.val == n2.val) return helper(n1.left, n2.right) && helper(n1.right, n2.left);
        return false;
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
