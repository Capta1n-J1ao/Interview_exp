package amazon;

/*
注意这道题目其实说的是树里任意两个数组成的集合，并不能用左右子树那一套来做，看第二个test case就懂了。
所以思路从一开始就不能错，错了就会出问题
* */

import java.util.HashMap;
import java.util.HashSet;

public class lc653 {
    private HashSet<Integer> hashSet = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        hashSet.add(root.val);
        if(hashSet.contains(k - root.val) && k - root.val != root.val) return true;
        return findTarget(root.left, k) || findTarget(root.right, k);
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
//        TreeNode n1 = new TreeNode(5);
//        TreeNode n2 = new TreeNode(3);
//        TreeNode n3 = new TreeNode(6);
//        TreeNode n4 = new TreeNode(2);
//        TreeNode n5 = new TreeNode(4);
//        n1.left = n2;
//        n1.right = n3;
//        n2.left = n4;
//        n2.right = n5;
//        System.out.println(new lc653().findTarget(n1, 9));
//
//        TreeNode n1 = new TreeNode(2);
//        TreeNode n2 = new TreeNode(1);
//        TreeNode n3 = new TreeNode(3);
////        TreeNode n4 = new TreeNode(2);
////        TreeNode n5 = new TreeNode(4);
//        n1.left = n2;
//        n1.right = n3;
////        n2.left = n4;
////        n2.right = n5;
//        System.out.println(new lc653().findTarget(n1, 4));

        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(0);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(-4);
        TreeNode n5 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        System.out.println(new lc653().findTarget(n1, -1));
    }
}
