package amazon;

/*
首先了解下迭代器的工作原理，参考lc341
在熟悉一下树的遍历144/145/94
然后自己做出来的，但是要注意下面是两个while嵌套，别忘了
* */

import java.util.*;

public class BSTIterator {
    private List<Integer> res;
    int index;
    private Deque<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        res = new ArrayList<>();
        index = 0;
        stack = new ArrayDeque<>();
//        注意下面这个while一开始忘了
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
            res.add(temp.val);
            root = temp.right;
        }
    }

    public int next() {
        return res.get(index++);
    }

    public boolean hasNext() {
        return res.size() >= index + 1;
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
        TreeNode n1 = new TreeNode(7);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(15);
        TreeNode n4 = new TreeNode(9);
        TreeNode n5 = new TreeNode(20);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        BSTIterator test = new BSTIterator(n1);
        System.out.println(test.next());
        System.out.println(test.next());
        System.out.println(test.hasNext());
        System.out.println(test.next());
        System.out.println(test.hasNext());
        System.out.println(test.next());
        System.out.println(test.hasNext());
        System.out.println(test.next());
        System.out.println(test.hasNext());
    }
}
