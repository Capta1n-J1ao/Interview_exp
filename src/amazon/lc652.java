package amazon;

/*
这道题目思路很巧妙，题解里面有一个关于二叉树的题的总结
对于我们解二叉树的题无非就以下几种思路：

1. 先序遍历（深度优先搜索）
2. 中序遍历（深度优先搜索）（尤其二叉搜索树）
3. 后序遍历（深度优先搜索）
4. 层序遍历（广度优先搜索）
5. 序列化与反序列化（结构唯一性问题）

然而题解对{0,0,0,0,null,null,0,null,null,null,0}这个数组会出错误答案
答案：[[0]]
输出：[[0],[0,null,0]]

所以需要把中序遍历修改为前序遍历
* */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class lc652 {
    public LinkedList<TreeNode> result = new LinkedList<>();
    public HashMap<String,Integer> map = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if(root == null){
            return result;
        }
        serialize(root);
        return result;
    }

    public String serialize(TreeNode root){
        if(root == null){
            return "null";
        }
        //序列化以当前节点为根节点的二叉树
//        针对case从中序遍历改为先序遍历即可过关
        String str = root.val + ","+ serialize(root.left) + ","+  serialize(root.right);
        //使用一个HashMap来记录所有的子树的序列
        map.put(str,map.getOrDefault(str,0)+1);
        //当其value为2时，则表示出现了重复结构，将这个子树的根节点放入到结果当中。
        if(map.get(str) == 2){
            result.add(root);
        }
        return str;
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
