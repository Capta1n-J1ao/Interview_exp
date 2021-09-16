package amazon;

import java.util.LinkedList;
import java.util.Queue;

public class lc993 {
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null || root.val == x || root.val == y || x == y) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode xDad = null, yDad = null;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                TreeNode temp = queue.poll();
                if(temp.left != null){
                    if(temp.left.val == x) xDad = temp;
                    else if(temp.left.val == y) yDad = temp;
                    else queue.add(temp.left);
                }
                if(temp.right != null){
                    if(temp.right.val == x) xDad = temp;
                    else if(temp.right.val == y) yDad = temp;
                    else queue.add(temp.right);
                }
            }
            if(xDad != null && yDad != null) return xDad != yDad;
            else if(xDad != null || yDad != null) return false;
            else continue;
        }
        return false;
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
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.right = n5;
        System.out.println(new lc993().isCousins(n1, 4,5));
    }
}
