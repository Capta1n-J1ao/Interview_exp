package amazon;

/*
这道题目递归和迭代都可以做，但是迭代会简单很多，但是想联系递归，于是先做递归，再做迭代

同时，这里区分一下递归和迭代：
1. 递归是重复调用函数自身实现循环。
2. 迭代是函数内某段代码实现循环,循环代码中参与运算的变量同时是保存结果的变量,
   当前保存的结果作为下一次循环计算的初始值。
* */

public class lc701 {
//    方法一：迭代
//    public TreeNode insertIntoBST(TreeNode root, int val) {
//        TreeNode toInsert = new TreeNode(val);
//        if(root == null) return toInsert;
//        TreeNode curNode = root;
//        while (curNode != null){
////            System.out.println(curNode.val);
//            if(curNode.val > val) {
//                if(curNode.left == null) {
//                    curNode.left = toInsert;
//                    break;
//                }
//                else curNode = curNode.left;
//            }
//            else {
//                if(curNode.right == null) {
//                    curNode.right = toInsert;
//                    break;
//                }
//                else curNode = curNode.right;
//            }
//
//        }
//        return root;
//    }

//    方法二：递归
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode toInsert = new TreeNode(val);
        if(root == null) return toInsert;
//        System.out.println(root.val);
        if(root.val > val)root.left = insertIntoBST(root.left, val);
        else root.right = insertIntoBST(root.right, val);
        return root;
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
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(55);
        TreeNode n3 = new TreeNode(39);
        TreeNode n4 = new TreeNode(11);
        TreeNode n5 = new TreeNode(23);
        n1.right = n2;
        n2.left = n3;
        n3.left = n4;
        n3.right = n5;
        System.out.println(new lc701().insertIntoBST(n1, 17));

//        TreeNode n1 = new TreeNode(5);
//        TreeNode n2 = new TreeNode(14);
//        TreeNode n3 = new TreeNode(10);
//        TreeNode n4 = new TreeNode(77);
//        TreeNode n5 = new TreeNode(95);
//        n1.right = n2;
//        n2.left = n3;
//        n2.right = n4;
//        n4.right = n5;
//        System.out.println(new lc701().insertIntoBST(n1, 4));
    }
}
