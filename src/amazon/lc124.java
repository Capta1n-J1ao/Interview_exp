package amazon;

/*
这道题和lc687/543有些类似，顺序的话687/543先做，特别是543的视频要看！然后124最后做即可

注意有一个难点就是要考虑到负数，比如
root = {-3}
这个情况,要仔细思考平衡root=null和root.val < 0时候的判断条件,这也是这道题最难得地方
要仔细思考用什么方法去表明最大元素和，递归的思路也需要仔细思考，很难！
提示：可以先从必须经过根节点开始想
题解：
https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-30/
* */

public class lc124 {
    private int res;
    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }

    private int helper(TreeNode root){
        if(root == null) return 0;
        int leftRes = Math.max(helper(root.left), 0);
        int rightRes = Math.max(helper(root.right), 0);
        res = Math.max(res, root.val + leftRes + rightRes);
//        这句话很关键，既然选择了root，那就说明只能够选择root的一边，详细看题解里面的解释
//        二刷：其实就是要了解helper的定义，就是指当前节点的最大和，
//        那么最大和的话就需要从左右孩子中挑出一个最大的，然后加上本节点自己的值
        return root.val + Math.max(leftRes, rightRes);
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
//        case 1
        TreeNode root1 = new TreeNode(-3);
        System.out.println(new lc124().maxPathSum(root1));

//        case 2
//        TreeNode root1 = new TreeNode(1);
//        TreeNode root2 = new TreeNode(2);
//        root1.left = root2;
//        System.out.println(new lc124().maxPathSum(root1));
    }
}
