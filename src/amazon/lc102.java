package amazon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class lc102 {
    private List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int qLen = queue.size();
            List<Integer> curRes = new LinkedList<>();
            for(int i = 0; i < qLen; i++){
                TreeNode temp = queue.poll();
                curRes.add(temp.val);
                if(temp.left != null) queue.add(temp.left);
                if(temp.right != null) queue.add(temp.right);
            }
            res.add(curRes);
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
