package amazon;

import java.util.*;

public class lc145 {
    private List<Integer> res = new LinkedList<>();
    private Deque<TreeNode> stack = new ArrayDeque<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return res;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                res.add(root.val);
                root = root.right;
            }
            //可以按照模板写，也可以用这个写法，更加符合我的习惯
            TreeNode temp = stack.pop();
            root = temp.left;
        }
        Collections.reverse(res);
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
