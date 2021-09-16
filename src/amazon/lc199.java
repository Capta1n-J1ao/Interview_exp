package amazon;

/*
在做题之前先看下第一个test case是不是和思路一致
* */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class lc199 {
    private List<Integer> res;
    public List<Integer> rightSideView(TreeNode root) {
        res = new LinkedList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                TreeNode temp = queue.poll();
                if(i == 0) res.add(temp.val);
                if(temp.right != null) queue.add(temp.right);
                if(temp.left != null) queue.add(temp.left);
            }
        }
        return res;
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
        n1.right = n3;
        n2.left = n4;
        System.out.println(new lc199().rightSideView(n1));
    }
}
