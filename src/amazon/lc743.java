package amazon;

/*
一开始想到课表那道题目了，lc210/207/997，但是好像全是BFS或者高阶做法，这个题解比较容易懂
https://leetcode-cn.com/problems/network-delay-time/solution/743-wang-luo-yan-chi-shi-jian-tu-de-bfsbian-li-by-/

像lc210/207/997这种出度入度的问题，其实是用来检测有向图是不是有环的问题
而像lc743这种题目有环是无所谓的，所以要区分开来，但是两种题目的本质其实是一样的
那就是BFS
* */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class lc743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n + 1][n + 1];
        for(int i = 0; i < n + 1; i++) Arrays.fill(graph[i], -1);
        for(int[] time : times){
            graph[time[0]][time[1]] = time[2];
        }
//        这个visited的作用是用来存储从k节点到visited中其他节点的最短路径
//        那么根据定义来说k到k的距离为0，所以visited[k] = 0
        int[] visited = new int[n + 1];
        Arrays.fill(visited,Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);
        visited[k] = 0;
        int res = 0;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int curNode = queue.poll();
//                不能这么写，可以思考下为什么
//                for(int neighbour : graph[curNode]){
                for(int index = 1; index < n + 1; index++){
//                    下面这个第二个判断一定需要，可以用第二个test case体会下
//                    本题最难点！这行代码决定了很多特殊case，一开始就想用一个Math.min解决，单事实证明是不行的
                    if(graph[curNode][index] != -1 && visited[curNode] + graph[curNode][index] < visited[index]){
                        visited[index] = visited[curNode] + graph[curNode][index];
                        queue.add(index);
                    }
                }
            }
        }
        for(int i = 1; i < n + 1; i++){
            if(visited[i] == Integer.MAX_VALUE) return -1;
            res = Math.max(res, visited[i]);
        }
        return res;
    }

    public static void main(String[] args) {
//        int[][] test = {{2,1,1},{2,3,1},{3,4,1}};
//        System.out.println(new lc743().networkDelayTime(test, 4, 2));

        int[][] test = {{1,2,1},{2,1,3}};
        System.out.println(new lc743().networkDelayTime(test, 2, 2));
    }
}
