package amazon;

/*
这道题最难得地方是要搞清楚怎么网上找爹的距离，往下找很容易，但是网上找就需要思考
第一反应是想用BFS，但是还是问题在于怎么网上BFS，方法很巧妙，想通了以后自然而然就能做出来

题解看官解里面的snowball的评论：
https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/solution/er-cha-shu-zhong-suo-you-ju-chi-wei-k-de-jie-dian-/
* */

import java.util.*;

public class lc863 {
    private List<Integer> res = new LinkedList<>();
    private TreeNode root, target;
    private int K;
    private HashMap<TreeNode, TreeNode> hashMap = new HashMap<>();
    private HashSet<TreeNode> visited = new HashSet<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        this.root = root;
        this.target = target;
        this.K = K;
        if(target == null) return res;
        findDad(root, null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        visited.add(target);
        while (!queue.isEmpty()){
            if(K == 0){
                while (!queue.isEmpty()){
                    TreeNode temp = queue.poll();
                    res. add(temp.val);
                }
                break;
            }
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                TreeNode temp1 =  queue.poll();
                if(temp1.left != null && !visited.contains(temp1.left)) {
                    queue.add(temp1.left);
                    visited.add(temp1.left);
                }
                if(temp1.right != null && !visited.contains(temp1.right)) {
                    queue.add(temp1.right);
                    visited.add(temp1.right);
                }
                TreeNode dadNode = hashMap.get(temp1);
                if(dadNode != null && !visited.contains(dadNode)){
                    queue.add(dadNode);
                    visited.add(dadNode);
                }
            }
            K--;
        }
        return res;
    }

    private void findDad(TreeNode son, TreeNode dad){
        if(son == null) return;
        hashMap.put(son,dad);
        findDad(son.left, son);
        findDad(son.right, son);
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
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(2);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(4);
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n4.left = n5;
        n4.right = n6;
        System.out.println(new lc863().distanceK(n1, n1.left, 2));
    }
}
