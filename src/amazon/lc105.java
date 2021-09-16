package amazon;

/*
看一下liweiwei的视频基本原理就能懂了，但是代码的细节很多：
https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/qian-xu-bian-li-python-dai-ma-java-dai-ma-by-liwei/

其实一共做了两种方法，第一种方法的时间复杂度是O(N2),第二种是O(N),
其实就是一个很简单的优化，但是一定要先用第一种方法做一下， 这样更能深刻理解为什么优化成第二种

和lc106一起服用
* */

import java.util.HashMap;

public class lc105 {
//    private int[] preorder, inorder;
//    private int preLen, inLen;
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        this.preorder = preorder;
//        this.inorder = inorder;
//        preLen = preorder.length;
//        inLen = inorder.length;
//        return helper(0, preLen - 1, 0, inLen - 1);
//    }
//
////    时间复杂度：O(N^2)：
////    这里 N 是二叉树的结点个数，每调用一次递归方法创建一个结点，一共创建 N 个结点，
////    在中序遍历中找到根结点在中序遍历中的位置，是与 N 相关的，这里不计算递归方法占用的时间。
//
////    空间复杂度：O(1)O(1)，这里不计算递归方法占用的空间。
//
//    private TreeNode helper(int preStart, int preEnd, int inStart, int inEnd){
////        这个终止条件需要一步一步debug才能想到
//        if(preStart > preEnd || inStart > inEnd) return null;
//        int curRootVal = preorder[preStart];
////        System.out.println(curRootVal + " " + preStart + " " + preEnd + " " + inStart + " " + inEnd);
//        TreeNode curRoot = new TreeNode(curRootVal);
//        int inRootIndex = 0;
//        while (inorder[inRootIndex] != curRootVal) inRootIndex++;
//        TreeNode leftNode = helper(preStart + 1, preStart + (inRootIndex - inStart), inStart, inRootIndex - 1);
//        TreeNode rightNode = helper(preStart + (inRootIndex - inStart + 1), preEnd, inRootIndex + 1, inEnd);
//        curRoot.left = leftNode;
//        curRoot.right = rightNode;
//        return curRoot;
//    }

//    主要就是解决每次都要寻找inRootIndex都要用while循环的问题

//    private int[] preorder, inorder;
//    private int preLen, inLen;
//    private HashMap<Integer, Integer> hashMap;
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        this.preorder = preorder;
//        this.inorder = inorder;
//        preLen = preorder.length;
//        inLen = inorder.length;
//        hashMap = new HashMap<>();
//        for(int i = 0; i < inLen; i++){
//            hashMap.put(inorder[i], i);
//        }
//        return helper(0, preLen - 1, 0, inLen - 1);
//    }
//
////    时间复杂度：O(N)：
////    用HashMap解决了这个问题
////    空间复杂度：O(1)O(1)，这里不计算递归方法占用的空间。
//
//    private TreeNode helper(int preStart, int preEnd, int inStart, int inEnd){
////        这个终止条件需要一步一步debug才能想到
//        if(preStart > preEnd || inStart > inEnd) return null;
//        int curRootVal = preorder[preStart];
////        System.out.println(curRootVal + " " + preStart + " " + preEnd + " " + inStart + " " + inEnd);
//        TreeNode curRoot = new TreeNode(curRootVal);
//        int inRootIndex = hashMap.get(curRootVal);
//        TreeNode leftNode = helper(preStart + 1, preStart + (inRootIndex - inStart), inStart, inRootIndex - 1);
//        TreeNode rightNode = helper(preStart + (inRootIndex - inStart + 1), preEnd, inRootIndex + 1, inEnd);
//        curRoot.left = leftNode;
//        curRoot.right = rightNode;
//        return curRoot;
//    }

//    二刷,20210721
    private int[] preorder, inorder;
    private int preLen, inLen;
    private HashMap<Integer, Integer> hashMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        preLen = preorder.length;
        inLen = inorder.length;
        hashMap = new HashMap<>();
        for(int i = 0; i < inLen; i++) hashMap.put(inorder[i], i);
        return helper(0, preLen - 1, 0, inLen - 1);
    }

//    时间复杂度：O(N)：
//    用HashMap解决了这个问题
//    空间复杂度：O(1)O(1)，这里不计算递归方法占用的空间。

    private TreeNode helper(int preStart, int preEnd, int inStart, int inEnd){
//        这个终止条件需要一步一步debug才能想到
        if(preStart > preEnd || inStart > inEnd) return null;
        int curRootVal = preorder[preStart];
//        System.out.println(curRootVal + " " + preStart + " " + preEnd + " " + inStart + " " + inEnd);
        TreeNode curRoot = new TreeNode(curRootVal);
        int inRootIndex = hashMap.get(curRootVal);
        curRoot.left = helper(preStart + 1, preStart + (inRootIndex - inStart), inStart, inRootIndex - 1);
        curRoot.right = helper(preStart + (inRootIndex - inStart + 1), preEnd, inRootIndex + 1, inEnd);
        return curRoot;
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

    public static void main(String[] args) {
        int[] preTest = {3,9,20,15,7};
        int[] inTest = {9,3,15,20,7};
        System.out.println(new lc105().buildTree(preTest, inTest));
    }
}
