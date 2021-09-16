package amazon;

/*
这道题目非常巧妙，思路非常难想，首先可能会想到BFS，但是如果碰到要从左子树往右子树走的情况就不能处理了
所以这里很明显使用递归，但是怎么递归，以及怎么定义子问题便是难点
个人认为这道题目的思路算hard也不过分
如果树的叶子仅包含 0 枚金币（与它所需相比，它的 过载量 为 -1），
那么我们需要从它的父亲节点移动一枚金币到这个叶子节点上。
如果说，一个叶子节点包含 4 枚金币（它的 过载量 为 3），
那么我们需要将这个叶子节点中的 3 枚金币移动到别的地方去。
总的来说，对于一个叶子节点，需要移动到它中或需要从它移动到它的父亲中的金币数量为
过载量 = Math.abs(num_coins - 1)。
然后，在接下来的计算中，我们就再也不需要考虑这些已经考虑过的叶子节点了。

在实际写的时候又会碰到以下几个很重要的点，一定要考虑清楚，
1. helper函数的返回值代表什么，也就是helper函数的定义
   返回的值是当前node以及他的左右子树所需要的金币值，这个值是可正可负的，正的代表多，负的代表少
2. 然后还有一个就是所需要的答案，是金币的移动次数，这个一定是正的。
   以上两个概念是分开的，在搞清了这个点以后，这道题目就相对好做一点了。

这个思路很抽象，但是可以从最底层的叶子节点思考，如果叶子节点有4个金币
那么可以肯定的是从叶子节点往他上一层会走三步，因为一定会有三个金币移走
有了这个基础，那其余的就容易思考了

参考题解说的很清楚：
https://leetcode-cn.com/problems/distribute-coins-in-binary-tree/solution/zai-er-cha-shu-zhong-fen-pei-ying-bi-by-leetcode/
* */

public class lc979 {
    //    这个参数指的上面的第二点
    private int res = 0;
    public int distributeCoins(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return res;
    }

    //    注意这个函数返回的不是res所需要的值，而是node以及子树所需要的金币数
    private int helper(TreeNode node){
        if(node.left == null && node.right == null) return node.val - 1;
        int left = node.left != null ? helper(node.left) : 0;
        int right = node.right != null ? helper(node.right) : 0;
//        这里对应2, 步数一定是正数
        res += Math.abs(left) + Math.abs(right);
        return node.val - 1 + left + right;
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
