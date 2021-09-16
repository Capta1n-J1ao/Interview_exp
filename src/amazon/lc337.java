package amazon;

/*
方法一是自己想的，但是在最后两个例子超时了，这次Liweiwei的题解不是很好，下面这个题解非常好，一点点优化，和我的思路很像
https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
* */

import java.util.HashMap;

public class lc337 {
//    方法一超时了
//    public int rob(TreeNode root) {
//        if(root == null) return 0;
//        return helper(root);
//    }
//
//    private int helper(TreeNode node){
//        if(node == null) return 0;
//        int res = 0, left = 0, right = 0, noLeft = 0, noRight = 0;
//        if(node.left != null) {
//            left = helper(node.left);
//            noLeft = helper(node.left.left) + helper(node.left.right);
//        }
//        if(node.right != null) {
//            right = helper(node.right);
//            noRight = helper(node.right.left) + helper(node.right.right);
//        }
////        System.out.println("root " + node.val + " left "+ left + " right " + right);
//        return Math.max(node.val + noLeft + noRight, left + right);
//    }

//    方法二，其实就是方法一的优化版，增加了一个记忆化
//    private HashMap<TreeNode, Integer> hashMap = new HashMap<>();
//    public int rob(TreeNode root) {
//        if(root == null) return 0;
//        return helper(root);
//    }
//
//    private int helper(TreeNode node){
//        if(node == null) return 0;
//        if(hashMap.containsKey(node)) return hashMap.get(node);
//        int res = 0, left = 0, right = 0, noLeft = 0, noRight = 0;
//        if(node.left != null) {
//            left = helper(node.left);
//            noLeft = helper(node.left.left) + helper(node.left.right);
//        }
//        if(node.right != null) {
//            right = helper(node.right);
//            noRight = helper(node.right.left) + helper(node.right.right);
//        }
////        System.out.println("root " + node.val + " left "+ left + " right " + right);
//        res = Math.max(node.val + noLeft + noRight, left + right);
//        hashMap.put(node, res);
//        return res;
//    }

//    方法三，本质上其实和方法二的时间复杂度是一样的，但是由于一个是数组，一个是hashMap,所以性能有差距
    private HashMap<TreeNode, Integer> hashMap = new HashMap<>();
    public int rob(TreeNode root) {
        if(root == null) return 0;
        return Math.max(helper(root)[0], helper(root)[1]);
    }

    private int[] helper(TreeNode node){
        if(node == null) return new int[2];
        int[] dp = new int[2];
        int[] left = helper(node.left);
        int[] right = helper(node.right);
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        dp[1] = node.val + left[0] + right[0];
//        System.out.println("root " + node.val + " dp0 "+ dp[0] + " dp1 " + dp[1]);
        return dp;
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
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.right = n5;
        System.out.println(new lc337().rob(n1));
    }
}
