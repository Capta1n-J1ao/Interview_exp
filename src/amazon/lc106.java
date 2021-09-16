package amazon;

/*
和lc105一起服用，题解参考lc106即可，一模一样
二刷刷的是105,106同理
* */

import java.util.HashMap;

public class lc106 {
    private int[] postorder, inorder;
    private int postLen, inLen;
    private HashMap<Integer, Integer> hashMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        inLen = inorder.length;
        postLen = postorder.length;
        hashMap = new HashMap<>();
        for(int i = 0; i < inLen; i++){
            hashMap.put(inorder[i], i);
        }
        return helper(0, inLen - 1, 0, postLen - 1);
    }

//    时间复杂度：O(N)：
//    用HashMap解决了这个问题
//    空间复杂度：O(1)O(1)，这里不计算递归方法占用的空间。

    private TreeNode helper(int inStart, int inEnd, int postStart, int postEnd){
        if(inStart > inEnd || postStart > postEnd) return null;
        int curRootVal = postorder[postEnd];
//        System.out.println(curRootVal + " " + postStart + " " + postEnd + " " + inStart + " " + inEnd);
        TreeNode curRoot = new TreeNode(curRootVal);
        int inRootIndex = hashMap.get(curRootVal);
        TreeNode leftNode = helper(inStart, inRootIndex - 1, postStart, postEnd - (inEnd - inRootIndex) - 1);
        TreeNode rightNode =helper(inRootIndex + 1, inEnd, postEnd - (inEnd - inRootIndex), postEnd - 1);
        curRoot.left = leftNode;
        curRoot.right = rightNode;
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
        System.out.println(new lc106().buildTree(preTest, inTest));
    }
}
