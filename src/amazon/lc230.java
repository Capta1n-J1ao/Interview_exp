package amazon;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class lc230 {
    public int kthSmallest(TreeNode root, int k) {
//        小顶堆,但是由于是搜索二叉树，所以用不上，在这里就当复习了
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });

        int count = 0;
        TreeNode res = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.addFirst(root);
                root = root.left;
            }
            TreeNode temp = stack.pollFirst();
            count++;
            if(count == k) res = temp;
            root = temp.right;
        }
        return res.val;
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
