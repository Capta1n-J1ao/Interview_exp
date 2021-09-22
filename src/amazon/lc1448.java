package amazon;

/*
挺简单的，直接一次性做出来了，答案都没看
* */

//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class lc1448 {
//    Deque<String> stack = new ArrayDeque<>();
//    Stack<String> stack = new Stack<>();
    private int res = 0;
    public int goodNodes(TreeNode root) {
        if(root == null) return 0;
        isGood(root, root.val);
        return res;
    }

    private void isGood(TreeNode node, int maxVal){
        if(node == null) return;
        if(node.val >= maxVal) res++;
        if(node.left != null) isGood(node.left, Math.max(maxVal, node.val));
        if(node.right != null) isGood(node.right, Math.max(maxVal, node.val));
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
