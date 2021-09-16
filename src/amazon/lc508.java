package amazon;

/*
这道题目很难懂，特别是示例2，这里就来解释下
        5
       / \
      2   -5
这里返回{2}的原因就是以第一行的5来看，他的子树元素和为2
以左下的数来看，他的子树元素和为2
以右下的数来看，他的子树元素和为-5
所以2出现两次，-5出现一次

推荐此人视频，点B站那个链接即可：
https://leetcode-cn.com/problems/most-frequent-subtree-sum/solution/java-si-lu-qing-xi-yi-dong-dai-ma-hao-li-dhk6/
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class lc508 {
    private HashMap<Integer, Integer> hashMap = new HashMap<>();
//    private List<Integer> tempRes = new LinkedList<>();
    private int res = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) return new int[0];
//        用ArrayList代码能简单一点
        ArrayList<Integer> tempRes = new ArrayList<>();
        helper(root);
        for(int key : hashMap.keySet()){
            if(hashMap.get(key) == res) tempRes.add(key);
        }


        int[] result = new int[tempRes.size()];
        for(int i = 0; i < tempRes.size(); i++){
            result[i] = tempRes.get(i);
        }
        return result;
    }

    private int helper(TreeNode node){
        if(node == null) return 0;
        int left = helper(node.left);
        int right = helper(node.right);
        int sum = node.val + left + right;
        hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
        res = Math.max(res, hashMap.get(sum));
        return sum;
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
