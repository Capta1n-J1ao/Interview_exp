package amazon;

/*
这道题目一开始往出入度的方向想了，想复杂了，就是正常方法，
常规做就可以了，然后就是有点奇怪为什么下面题解的方法一会占用内存少，按理说数组应该比hashmap高效才对
https://leetcode-cn.com/problems/all-paths-from-source-to-target/solution/797-suo-you-ke-neng-de-lu-jing-by-a-fei-x7m83/
* */

import edu.princeton.cs.algs4.In;

import java.util.*;

public class lc797 {
//    在进行初始化的时候一直出问题，就是下面两个数组的形式，已经写在excel里面，有机会记得找下原因
//    下面这句就会报错
//    for(int i = 0; i < gLen; i++) lists[i] = new LinkedList<>();
//    private HashMap<Integer, HashSet<Integer>> hashSetHashMap = new HashMap<>();
//    private HashSet<Integer>[] hashSets;
//    private List<Integer>[] lists;
    private List<List<Integer>> res = new LinkedList<>();
    private int gLen;
    private int[][] graph;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.gLen = graph.length;
        this.graph = graph;
        List<Integer> curRes = new LinkedList<>();
        curRes.add(0);
        BackTracking(curRes, 0);
        return res;
    }

    private void BackTracking(List<Integer> curRes, int curNode){
//        System.out.println(curRes);
        if(curNode == gLen - 1){
            res.add(new LinkedList<>(curRes));
            return;
        }

//        System.out.println(curRes);
        for(int node : graph[curNode]){
            curRes.add(node);
//            System.out.println(curRes);
            BackTracking(curRes, node);
            curRes.remove(curRes.size() - 1);
//            System.out.println(curRes);
        }

    }

    public static void main(String[] args) {
        int[][] test = {{4,3,1},{3,2,4},{3},{4},{}};
        System.out.println(new lc797().allPathsSourceTarget(test));
    }
}
