package amazon;

/*
lc297、lc477是一道题目
题目可能会有歧义，那就是serialize里面的String是什么格式？ 可以自定义，不强制要求，但是要考虑到后面deserialize

题解：
https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/bfshe-dfsliang-chong-fang-shi-jie-jue-by-sdwwld-3/
* */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Codec {
    // Encodes a tree to a single string.
    StringBuilder res = new StringBuilder();
    public String serialize(TreeNode root) {
        if(root == null) return "null";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                TreeNode temp = queue.poll();
                if(temp == null) {
                    res.append("null ");
                    continue;
                }
                res.append(temp.val );
                res.append(' ');
                queue.add(temp.left );
                queue.add(temp.right );
            }
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
//        下面这两句都可以
        if(data.equals("null")) return  null;
//        if(data == "null") return null;
        Queue<TreeNode> queue = new LinkedList<>();
        String[] node = data.split(" ");
//        System.out.println(node);
        TreeNode root = new TreeNode(Integer.parseInt(node[0]));
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                TreeNode temp = queue.poll();
                if(!node[index].equals("null")){
                    temp.left = new TreeNode(Integer.parseInt(node[index]));
                    queue.add(temp.left);
                }
                index++;
                if(!node[index].equals("null")){
                    temp.right = new TreeNode(Integer.parseInt(node[index]));
                    queue.add(temp.right);
                }
                index++;
            }
        }
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
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

//        String ser = new Codec().serialize(n1);
//        System.out.println(ser);
//        TreeNode res = new Codec().deserialize(ser);

        String ser = new Codec().serialize(null);
        System.out.println(ser);
        TreeNode res = new Codec().deserialize(ser);
    }
}