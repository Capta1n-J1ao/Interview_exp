package amazon;

/*
注意这道题目TreeNode里面都是负的，然后targetSum也都是负的是可以的，要注意到这个情况
* */

import java.util.LinkedList;
import java.util.List;

public class lc113 {
    private List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new LinkedList<>();
        if(root == null) return res;
        BackTracking(root, targetSum, new LinkedList<>());
        return res;
    }

    private void BackTracking(TreeNode root, int target, LinkedList<Integer> curRes){
        if(target == root.val && root.left== null && root.right == null){
            curRes.add(root.val);
            res.add(new LinkedList<>(curRes));
            return;
        }else if(root == null) return;
//        System.out.println(root.val + " " + target);
        curRes.add(root.val);
        if(root.left != null){
            BackTracking(root.left, target - root.val, curRes);
            curRes.remove(curRes.size() - 1);
        }
        if(root.right != null){
            BackTracking(root.right, target - root.val, curRes);
            curRes.remove(curRes.size() - 1);
        }
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
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        System.out.println(new lc113().pathSum(n1, 8));
    }
}
