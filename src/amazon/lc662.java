package amazon;

/*
注意这道题目的一个特点在题目里面已经说了：
           1
         /   \
        3     2
       / \     \
      5   3     9

输出: 4
解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。


          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
输出: 8
解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。

每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

题解的话，下面这个说的最清楚，其实就是相当于给每个TreeNode编号，然后用最左和最右的编号相减来得到相应的值：
https://leetcode-cn.com/problems/maximum-width-of-binary-tree/solution/java-di-yi-ci-shuang-bai-mu-ha-ha-ji-nian-xia-by-z/
* */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class lc662 {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(root, 0));
        int res = 0;
        while (!queue.isEmpty()){
            int curRes = 0, qLen = queue.size();
            int leftIndex = queue.peekFirst().index, rightIndex = queue.peekLast().index;
            curRes = rightIndex - leftIndex + 1;
            res = Math.max(res, curRes);
            for(int i = 0; i < qLen; i++){
                Node temp = queue.poll();
                TreeNode tempNode = temp.node;
                int tempIndex = temp.index;
                if(tempNode.left != null) queue.add(new Node(tempNode.left, tempIndex * 2));
                if(tempNode.right != null) queue.add(new Node(tempNode.right, tempIndex * 2 + 1));
            }
        }
        return res;
    }

    public class Node{
        int index;
        TreeNode node;
        public Node(TreeNode Node, int index){
            this.node = Node;
            this.index = index;
        }
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
