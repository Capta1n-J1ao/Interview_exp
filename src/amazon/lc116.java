package amazon;

/*
116和117基本就是一道题，做了其中一道之后另一道可以不用做
* */

import java.util.LinkedList;
import java.util.Queue;

public class lc116 {
    public Node connect(Node root) {
        if(root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int qLen = queue.size();
            Node pre = new Node(-1);
            for(int i = 0; i < qLen; i++){
                Node temp = queue.poll();
                if(temp.left != null) queue.add(temp.left);
                if(temp.right != null) queue.add(temp.right);
                pre.next = temp;
                pre = temp;
            }
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
