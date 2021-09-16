package amazon;

/*
一开始graph的定义忘记了，可以看下lc785复习一下原理
然后题目本身是很有难度的，直接看视频，一开始可以先看huahua的开拓一下思路：
https://www.bilibili.com/video/BV1hW411d7MU?from=search&seid=4256559669435607043
然后在看java版本的会很清晰
https://www.bilibili.com/video/BV1ri4y1u7Mo?from=search&seid=4256559669435607043
其实这道题目的难点就是难在用二进制来表示visited，在知道了这个思路以后，剩下的BFS都是自己写的，没有参考
* */

import java.util.LinkedList;
import java.util.Queue;

public class lc847 {
    public int shortestPathLength(int[][] graph) {
        int gLen = graph.length;
//        corner case
        if(graph == null || gLen == 0 || (gLen == 1 && graph[0].length == 0)) return 0;
        int goal = (1 << gLen) - 1, res = 0;
        boolean[][] visited = new boolean[gLen][1 << gLen];
        Queue<int[]> queue = new LinkedList<>();
//        初始化，把每个Node都放入queue中
        for(int i = 0; i < gLen; i++){
            int curPos = 1 << i;
            queue.add(new int[]{i, curPos});
            visited[i][curPos] = true;
        }
//        BFS
        while (!queue.isEmpty()){
            res++;
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int[] curRes = queue.poll();
                int curNode = curRes[0];
                int curPosRes = curRes[1];
                for(int linkNode : graph[curNode]){
                    int newPosRes = curPosRes | (1 << linkNode);
                    if(newPosRes == goal) return res;
                    if(visited[linkNode][newPosRes]) continue;
                    queue.add(new int[]{linkNode, newPosRes});
                    visited[linkNode][newPosRes] = true;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[][] test = {{1,2,3},{0},{0},{0}};
//        System.out.println(new lc847().shortestPathLength(test));

        int[][] test = {{}};
        System.out.println(new lc847().shortestPathLength(test));
    }
}
