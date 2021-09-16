package amazon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class lc637 {
    private List<Double> res = new LinkedList<>();
    private Queue<TreeNode> queue = new LinkedList<>();
    public List<Double> averageOfLevels(TreeNode root) {
        if(root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()){
            int qLen = queue.size();
            double curRes = 0;
            for(int i = 0; i < qLen; i++){
                TreeNode node = queue.poll();
                //这句话写在这里不对，这样不是整个一行的变量
                //double curRes = 0;
                curRes += node.val;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            curRes /= qLen;
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
