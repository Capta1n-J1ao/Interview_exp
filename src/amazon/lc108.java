package amazon;

/*
这道题目做的时候leetcode崩溃了，所以用的国外leetcode写的
* */

public class lc108 {
//    private int numLen;
//    private int[] nums;
//    public TreeNode sortedArrayToBST(int[] nums) {
//        this.nums = nums;
//        if(nums == null) return null;
//        numLen = nums.length;
//        TreeNode root = helper(0, numLen - 1);
//        return root;
//    }
//
//    private TreeNode helper(int left, int right){
////        一开始这么写，错了，主要是二分法这里的细节问题
////        if(right == left) return null;
//        if(right < left) return null;
//        int mid = left + (right - left) / 2;
//        TreeNode root = new TreeNode(nums[mid]);
//        root.left = helper(left, mid - 1);
//        root.right = helper(mid + 1, right);
//        return root;
//    }


//    二刷，2021/06/27
    private int[] nums;
    private int numLen;
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        this.numLen = nums.length;
        if(nums == null) return null;
        TreeNode root = helper(0, numLen - 1);
        return root;
    }

    private TreeNode helper(int left, int right){
//        这句话是难点，不能用left == right
        if(left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(left, mid - 1);
        root.right = helper(mid + 1, right);
        return root;
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
