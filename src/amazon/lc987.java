package amazon;

/*
这道题目对于排序一个tricky的地方，其实题目没有描述清楚：
1. col的优先级最高
2. 在col相同情况下，如果row不同，那么按照row从上到下的顺序来
3. 如果col/row均相同，那么按照node.val值来排序

这道题目一开始想到的是不改TreeNode，然后硬做，思路就是：
1. 首先把TreeNode全部遍历一遍，然后把row/col坐标得到
2. 针对row/col坐标进行BFS

但是用这个遍历的话需要DFS进行，参考题解，但是个人觉得不要浪费时间了，直接重新改TreeNode：
https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree/solution/dfsyu-bsfyi-qi-shi-yong-by-cyingenohalt-wrad/

参考题解如下,这里说下大致思路：
1. 首先全部遍历一遍节点，这样可以把pos全部都赋值
2. 这一步是本题的难点，那就是怎么对节点进行分类，我一开始想用一个TreeSet，但是没看到合适的题解
3. 对遍历的点进行collections.sort
4. 处理被sort过的集合，得到答案
https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree/solution/lc-987-by-bu-neng-geng-duo/
* */

import java.util.*;

public class lc987 {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        List<TreeNodeWPos> toSort = new ArrayList<>();
        Queue<TreeNodeWPos> queue = new LinkedList<>();

        queue.offer(new TreeNodeWPos(0,0,root));
        while(!queue.isEmpty()) {
            TreeNodeWPos nodeWPOS = queue.poll();
            toSort.add(nodeWPOS);

            if(nodeWPOS.node.left != null) {
                queue.add(new TreeNodeWPos(nodeWPOS.row + 1, nodeWPOS.col - 1, nodeWPOS.node.left));
            }
            if(nodeWPOS.node.right != null) {
                queue.add(new TreeNodeWPos(nodeWPOS.row + 1, nodeWPOS.col + 1, nodeWPOS.node.right));
            }
        }


        Collections.sort(toSort, new Comparator<TreeNodeWPos>() {
            @Override
            public int compare(TreeNodeWPos o1, TreeNodeWPos o2) {
//                siftup
                if(o1.col < o2.col) return -1;
                if(o1.col > o2.col) return 1;
//                如果同列不同行，那么按照从上到下的顺序
                if(o1.col == o2.col) {
                    if(o1.row < o2.row) return -1;
                    if(o1.row > o2.row) return 1;
                    if(o1.row == o2.row) return o1.node.val <= o2.node.val? -1 : 1;
                }
                return 0;
            }
        });

//        corner case
        if(toSort.size() <= 0) return res;

//        方法一,更加符合我的风格，而且这里getOrDefault用的也很好
        Map<Integer, List<Integer>> treeMap = new TreeMap<>();
        for(TreeNodeWPos nodePos : toSort) {
            List<Integer> curList = treeMap.getOrDefault(nodePos.col, new LinkedList<>());
            curList.add(nodePos.node.val);
            treeMap.put(nodePos.col, curList);
        }
        for(List<Integer> curRes : treeMap.values()) res.add(curRes);

//        方法二
//        int curCol = toSort.get(0).col;
//        Map<Integer, List<Integer>> map = new HashMap<>();
//        map.put(curCol, new ArrayList<Integer>());
//        for(TreeNodeWPos nodePos : toSort) {
//            if(curCol != nodePos.col) {
//                res.add(map.get(curCol));
//                curCol = nodePos.col;
//                map.put(curCol, new ArrayList<Integer>());
//            }
//
//            map.get(nodePos.col).add(nodePos.node.val);
//        }
//        // 不要忘了加上最后一个 x坐标的序列
//        res.add(map.get(curCol));
        return res;
    }

    private static class TreeNodeWPos {
        private int row;
        private int col;
        private TreeNode node;

        public TreeNodeWPos(int row, int col, TreeNode node) {
            this.row = row;
            this.col = col;
            this.node = node;
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
