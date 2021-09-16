package amazon;

/*
先做lc95，再做96，下面这个视频说的很清楚：
https://www.bilibili.com/video/BV12C4y1h7Ao?t=609

这道题目dp的版本很不好理解，不推荐，还是用分治最方便
* */

import java.util.LinkedList;
import java.util.List;

public class lc95 {
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }

    private List<TreeNode> helper(int left, int right){
        List<TreeNode> curTree = new LinkedList<>();
        if(left > right){
            curTree.add(null);
            return curTree;
        }
//        这个条件仔细想的话其实是不需要的
//        if(left == right){
//            curTree.add(new TreeNode(left));
//            return curTree;
//        }
        for(int i = left; i <= right; i++){
//            System.out.println("left = " + left + " right = " + right);
            List<TreeNode> leftTrees = helper(left, i - 1);
            List<TreeNode> rightTrees = helper(i + 1, right);
            for(TreeNode leftTree : leftTrees){
                for(TreeNode rightTree : rightTrees){
                    TreeNode curNode = new TreeNode(i);
                    curNode.left = leftTree;
                    curNode.right = rightTree;
                    curTree.add(curNode);
                }
            }
        }
        return curTree;
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

        public static void main(String[] args) {
            System.out.println(new lc95().generateTrees(3));
        }
    }
}
