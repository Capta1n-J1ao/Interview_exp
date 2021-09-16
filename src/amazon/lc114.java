package amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc114 {
    public void flatten(TreeNode root) {
        if(root == null) return;
        List<TreeNode> linkedList = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.addFirst(root);
                linkedList.add(root);
                root = root.left;
            }
            TreeNode temp = stack.pollFirst();
            root = temp.right;
        }
        TreeNode pre = new TreeNode(-1);
        TreeNode head = pre;
        for(TreeNode node : linkedList){
            node.left = null;
//            node.right = null;
            pre.right = node;
            pre = pre.right;
        }
        root = head.right;
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
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        new lc114().flatten(n1);
    }
}
