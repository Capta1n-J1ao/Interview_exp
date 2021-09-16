package amazon;

/*
一开始用的helper返回的是int，但是发现不行，所以改用了boolean
但是要有一个敏锐的嗅觉那就是一旦改成了boolean，而且传的参数都是一样的话，就要想到自己作为自己的递归函数
* */

public class lc112 {
//    第一版自己写的比较啰嗦
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//        if(root == null) return false;
//        if(root.left == null && root.right == null) return root.val == targetSum;
//        return helper(root.left, targetSum - root.val) || helper(root.right, targetSum - root.val);
//    }
//
//    private boolean helper(TreeNode node, int target){
////        System.out.println("node " + node.val + " target " + target);
//        if(node == null) return false;
//        if(node.left == null && node.right == null) return node.val == target;
//        if(node.left == null) return helper(node.right, target - node.val);
//        if(node.right == null) return helper(node.left, target - node.val);
//        else return helper(node.right, target - node.val) || helper(node.left, target - node.val);
//    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
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
        n1.left = n2;
        System.out.println(new lc112().hasPathSum(n1, 0));
    }
}
