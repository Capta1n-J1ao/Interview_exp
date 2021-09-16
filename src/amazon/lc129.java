package amazon;
/*
一开始想的方法是自下而上的，要注意这个是不行的，要自上而下才可以！
https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/solution/0-ms-jiao-ke-shu-ji-jie-da-by-liuzhaoce/

方法二用的BFS，也很巧妙：
https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/solution/dfshe-bfsliang-chong-fang-shi-jie-jue-zui-hao-de-j/
* */

import java.util.LinkedList;
import java.util.Queue;

public class lc129 {
//    自下而上做不出，因为没法用 高位*10 + 低位的方法
//    public int sumNumbers(TreeNode root) {
//        if(root.left == null && root.right == null) return root.val;
//        int preLeft = sumNumbers(root.left), leftTens = 1;
//        while (preLeft != 0){
//            preLeft /= 10;
//            leftTens *= 10;
//        }
//        int left = root.left == null ? 0 : sumNumbers(root.left) + root.val * leftTens;
//        int preRight = sumNumbers(root.right), rightTens = 1;
//        while (preRight != 0){
//            preRight /= 10;
//            rightTens *= 10;
//        }
//        int right = root.right == null ? 0 : sumNumbers(root.right) + root.val * rightTens;
//        System.out.println("left = " + left + " right = "+ right);
//        return left + right;
//    }

//    方法一，重点就是要搞清楚helper函数的含义，否则还是有一个坑的
//    public int sumNumbers(TreeNode root) {
//        if(root == null) return 0;
//        return helper(root, 0);
//    }
//
////    helper代表的是以当前node为终点所能得到的sum值
//    public int helper(TreeNode node, int sum){
//        if(node == null) return 0;
//        sum = sum * 10 + node.val;
//        if(node.left == null && node.right == null) return sum;
//        int left = helper(node.left, sum);
//        int right = helper(node.right, sum);
////        System.out.println("left = " + left + " right = "+ right);
//        return left + right;
//    }


//    方法二，BFS,这里有一个很重要的点其实和方法一里面搞清helper所代表的含义是一样的那就是搞清这两个Queue的对应关系
//    具体为resQueue在本轮BFS放入的值是以temp为终点节点所对应的sum值
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        int res = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> resQueue = new LinkedList<>();
        nodeQueue.add(root);
        resQueue.add(root.val);
        while (!nodeQueue.isEmpty() && !resQueue.isEmpty()){
            int qLen = nodeQueue.size();
            for(int i = 0; i < qLen; i++){
                TreeNode temp = nodeQueue.poll();
                int curRes = resQueue.poll();
                if(temp.left == null && temp.right == null) res += curRes;
                if(temp.left != null){
                    nodeQueue.add(temp.left);
                    resQueue.add(curRes * 10 + temp.left.val);
                }
                if(temp.right != null){
                    nodeQueue.add(temp.right);
                    resQueue.add(curRes * 10 + temp.right.val);
                }
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
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(0);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        System.out.println(new lc129().sumNumbers(n1));
    }
}
