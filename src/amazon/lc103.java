package amazon;

/*
这道题目一开始没想到用BFS，因为之前做的BFS题型比较固定，所以没有往BFS想。但是确实就使用BFS
* */


import java.util.*;

public class lc103 {
//    方法一，用Collections.reverse来实现的倒序
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
//        Corner Case
        if(root == null) return res;
        boolean reverse = false;
        queue.add(root);
        while (!queue.isEmpty()){
            int qLen = queue.size();
            List<Integer> curRes = new LinkedList<>();
            for(int i = 0; i < qLen; i++){
                TreeNode temp = queue.poll();
                curRes.add(temp.val);
                if(temp.left != null) queue.add(temp.left);
                if(temp.right != null) queue.add(temp.right);
            }
            if(reverse) Collections.reverse(curRes);
            reverse = !reverse;
            res.add(curRes);
        }
        return res;
    }


//    方法二：用另一种方法写的，注意当前版本是没有addFirst命令的！
//    所以要用Deque来代替，并且在最后res.add的时候重新建一个LinkedList
//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        Queue<TreeNode> queue = new LinkedList<>();
//        List<List<Integer>> res = new LinkedList<>();
////        Corner Case
//        if(root == null) return res;
//        boolean reverse = false;
//        queue.add(root);
//        while (!queue.isEmpty()){
//            int qLen = queue.size();
//            Deque<Integer> curRes = new LinkedList<>();
//            for(int i = 0; i < qLen; i++){
//                TreeNode temp = queue.poll();
//                if(!reverse) curRes.add(temp.val);
//                else curRes.offerFirst(temp.val);
//                if(temp.left != null) queue.add(temp.left);
//                if(temp.right != null) queue.add(temp.right);
//            }
//            reverse = !reverse;
//            res.add(new LinkedList<>(curRes));
//        }
//        return res;
//    }

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
