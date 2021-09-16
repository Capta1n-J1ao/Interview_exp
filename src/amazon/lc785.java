package amazon;

/*
并查集类似题目：684/778/785/924

本质为着色问题，看官方解答就够了，看个思路，代码自己写，一刷用的DFS，这次用BFS：
https://www.bilibili.com/video/BV1254y1i7Ut?from=search&seid=3519268716627426840

并查集解法：
https://leetcode-cn.com/problems/is-graph-bipartite/solution/bfs-dfs-bing-cha-ji-san-chong-fang-fa-pan-duan-er-/
* */

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class lc785 {
//    private int[][] graph;
//    private int[] color;
//    public boolean isBipartite(int[][] graph) {
//        this.graph = graph;
//        int gLen = graph.length;
//        color = new int[gLen];
//        Arrays.fill(color, -1);
//        /*
//        理解BFS版本里面为什么要有这个循环很重要
//        原因就是如果没有这个循环，如果在这个graph里面
//        有没有完全独立没有连接的两个环的话就测不出来了
//        * */
//        for(int node = 0; node < gLen; node++){
////            这句很关键，如果已经上过色的话就要跳过
//            if(color[node] != -1) continue;
//            Queue<Integer> queue = new LinkedList<>();
//            queue.add(node);
////        初始着色为0
//            int curColor = 0;
//            color[node] = curColor;
//            while (!queue.isEmpty()){
//                int qLen = queue.size();
//                curColor = 1 - curColor;
//                for(int i = 0; i < qLen; i++){
//                    int[] temp = graph[queue.poll()];
//                    for(int k = 0; k < temp.length; k++){
//                        int neighbour = temp[k];
//                        if(color[neighbour] != -1){
//                            if(color[neighbour] != curColor) return false;
//                            else continue;
//                        }
//                        color[neighbour] = curColor;
//                        queue.add(neighbour);
//                    }
//                }
//            }
//        }
//        return true;
//    }

//    DFS写一遍
//    private int[][] graph;
//    private int[] color;
//    public boolean isBipartite(int[][] graph) {
//        this.graph = graph;
//        int gLen = graph.length, color1 = 0;
//        color = new int[gLen];
//        Arrays.fill(color, -1);
//        for(int i = 0; i < gLen; i++){
//            if(color[i] == -1 && !DFS(i, color1)) return false;
//        }
//        return true;
//    }
//
//    private boolean DFS(int node, int curColor){
//        if(color[node] != -1) return color[node] == curColor;
//        color[node] = curColor;
//        int[] curNode = graph[node];
//        for(int n : curNode){
////            不能把DFS结果作为return结果，
////            原因就是因为只要curNode里面第一个n的判断直接决定了整个函数的判断，
////            而这个函数是应该要判错而不是判对的，可以参考第二个test case
////            if(color[n] == -1) return DFS(n, 1 - curColor);
//            if(!DFS(n, 1 - curColor)) return false;
//        }
//        return true;
//    }

//    并查集写一遍，我们知道如果是二分图的话，
//    那么图中每个顶点的所有邻接点都应该属于同一集合，且不与顶点处于同一集合。
//    因此我们可以使用并查集来解决这个问题，我们遍历图中每个顶点，
//    将当前顶点的所有邻接点进行合并，并判断这些邻接点中是否存在某一邻接点已经和当前顶点处于同一个集合中了，
//    若是，则说明不是二分图。
    private int[][] graph;
    private int[] union;
    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        int gLen = graph.length;
        union = new int[gLen];
        for(int i = 0; i < gLen; i++) union[i] = i;
        for(int i = 0; i < gLen; i++){
            int[] curNode = graph[i];
            for(int n : curNode){
//                这句是关键！和上面的解释结合着看
                if(find(i) != find(n)) merge(curNode[0], n);
                else return false;
            }
        }
        return true;
    }

    private int find(int a){
        while (union[a] != a){
            a = union[a];
        }
        return a;
    }

    private void merge(int a, int b){
        int aDad = find(a);
        int bDad = find(b);
        if(aDad != bDad){
            union[aDad] = bDad;
        }
    }

    public static void main(String[] args) {
//        int[][] test = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
//        System.out.println(new lc785().isBipartite(test));

        int[][] test = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println(new lc785().isBipartite(test));
    }
}
