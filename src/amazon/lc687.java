package amazon;

/*
和lc124/543一起服用,顺序的话687/543先做，特别是543的视频要看！然后124最后做即可
下面这个题解最好懂，而且和lc124的思路也吻合
https://leetcode-cn.com/problems/longest-univalue-path/solution/jian-dan-yi-dong-ban-by-a380922457-7/
* */

public class lc687 {
//    private int res ;
//    public int longestUnivaluePath(TreeNode root) {
//        res = 0;
////        一开始想用的思路是helper(TreeNode root, int ref)，其中ref是上一层的数，
////        但是这样是不行的，会有问题，就差这一个代码就不一样了，还是要在当层做处理。
//        helper(root);
//        return res ;
//    }
//
//    private int helper(TreeNode root){
//        if(root == null) return 0;
//        int rootRes = 0;
//        int leftRes = helper(root.left);
////        System.out.println("rootval是 " + root.val + " left " + leftRes);
//        int rightRes = helper(root.right);
////        System.out.println("rootval是 " + root.val + " right " + rightRes);
//
//        leftRes = root.left != null && root.val == root.left.val ? leftRes + 1 : 0;
//        rightRes = root.right != null && root.val == root.right.val ? rightRes + 1 : 0;
//        res = Math.max(res, leftRes + rightRes);
////        helper函数的作用就是为了算出当前节点的深度，那么返回的就是左右孩子的最大深度，这样就是当前节点的深度
//        return Math.max(leftRes, rightRes);
//    }


//    2021/9/4二刷，一开始想尽办法想把解放在一个函数里面，但是发现不行，原因就是因为这道题目的解需要返回答案，
//    但是我对于子函数要的不是答案而是以当前node为根节点得到的最长路径，所以不能用在一个函数里面。
    private int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return res;
    }

//    这个函数的意义是以node为根节点得到的最长路径。然后如果看不懂代码先看下lc543的视频。
    private int helper(TreeNode node){
        if(node == null) return 0;
//        下面这两行我的理解为其实已经是以node.left/right为根节点得到路径长度了。
//        而这两个就是整个helper函数对应的return，换句话说就是之所以return max(left,right)
//        其实就是因为函数的隐藏意义就是是以node为根节点，而不是以left,right为根节点，
//        那么在这种情况下你只能返回一个最长路径，因为你是以node为根节点，也就是必须经过Node
        int left = helper(node.left);
//        System.out.println("rootval是 " + node.val + " left = " + left);
        int right = helper(node.right);
//        System.out.println("rootval是 " + node.val + " right " + right);
//        这两行才是在求以当前node为根节点得到的值，上面其实已经不是以node为根节点了
//        上面两行指的是node.left的左右子节点和node.right的左右子节点得到的答案，需要自己体会下，
//        是node向下两层的关系
        left = (node.left != null && node.val == node.left.val) ? left + 1 : 0;
        right = (node.right != null && node.val == node.right.val) ? right + 1 : 0;
        res = Math.max(res, left + right);
//        这句话要先看下lc543的那个视频，其实是一样的。
        return Math.max(left, right);
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
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(4);
        TreeNode r3 = new TreeNode(5);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(4);
        TreeNode r6 = new TreeNode(5);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        System.out.println(new lc687().longestUnivaluePath(r1));
    }
}
