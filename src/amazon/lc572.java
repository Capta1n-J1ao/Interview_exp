package amazon;

/*
重新做了以后发现这个题解写的不错
https://leetcode-cn.com/problems/subtree-of-another-tree/solution/di-gui-jie-fa-by-onthewaylcb/

代码还是用之前的
* */

public class lc572 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(t == null) return true;
        if(s == null) return false;
        boolean left = isSubtree(s.left, t);
        boolean right = isSubtree(s.right, t);
        return left || right || helper(s, t);
    }

    private boolean helper(TreeNode s, TreeNode t){
        if(s == null && t == null) return true;
            //下面这句话一定要加，要把一切的false情况列出来才行
        else if(s == null || t == null) return false;
        else if(s.val == t.val) {
            return helper(s.left, t.left) && helper(s.right, t.right);
        }
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
