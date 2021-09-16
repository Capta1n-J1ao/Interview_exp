package amazon;

/*
这道题目一开始不知道需要返回什么，其实就是补全以下Node里面next那个东西，
因为整个树left和right其实已经定死的，只有next是需要重新分配的
也就是说这道题目直接分配root的next以及他的所有左右子树的next，然后返回root即可

这里面可能有一个点需要思考：
每一层最右边的节点指针指向null是怎么实现的？
答案：看下Node的定义可以发现：初始状态下，所有 next 指针都被设置为 NULL。
* */

import java.util.LinkedList;
import java.util.Queue;

public class lc117 {
    public Node connect(Node root) {
        if(root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int qLen = queue.size();
            Node newNode = null;
            for(int i = 0; i < qLen; i++){
                Node temp = queue.poll();
                if(newNode != null) {
                    newNode.next = temp;
                    newNode = newNode.next;
                }
                else newNode = temp;
                if(temp.left != null) queue.add(temp.left);
                if(temp.right != null) queue.add(temp.right);
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
    }
}
