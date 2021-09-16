package amazon;

/*
这道题目主要是思路的理解，这里给个花花的视频更好理解一点
https://www.bilibili.com/video/BV1QW411d794?from=search&seid=14411936659750415071

题解的话下面这个最漂亮，最简洁：
https://leetcode-cn.com/problems/bus-routes/solution/zi-xue-zhe-gen-ju-cban-ben-gai-xie-de-javayan-du-y/

这道题目有以下几个注意点：
1. 第一反应想到用BFS，但是要考虑是把车站还是线路放入queue，
   经过思考其实可以发现求的是线路的数目，所以需要把车站放入queue，
   按照这个思路往上推的话前提就是要建立station和线路的关系
2. 这道题可以复习一下map里面computeIfAbsent和putIfAbsent的区别，
   我的理解是如果hashmap的key里面没有val，那就用putIfAbsent新建。
   如果hashmap的key里面没有val，并且需要直接用val进行计算，
   比如计算出现次数这种的，那就用computeIfAbsent
* */

import java.util.*;

public class lc815 {
//    建立线路与站点的关系，因为之后放入queue里面的是站点，
//    所以key存放站点，hashSet里面放经过该站点的公交车编号
    private HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<>();
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;
        int busNum = routes.length, res = 0;
        for(int i = 0; i < busNum; i++){
            for(int k = 0; k < routes[i].length; k++){
                int curStop = routes[i][k];
                HashSet<Integer> temp = hashMap.putIfAbsent(curStop, new HashSet<>());
                if(temp == null) temp = hashMap.get(curStop);
                temp.add(i);
            }
        }

        boolean[] visited = new boolean[busNum];
        Queue<Integer> queue = new LinkedList<>();
//        BFS第一步把起点放入
        HashSet<Integer> temp = hashMap.get(source);
//        这里visited有两种写法，一种是我这里的写法，还有一种就是题解的写法，不要搞错；
        for(int busIndex : temp){
            queue.add(busIndex);
//            visited[busIndex] = true;
        }
        while (!queue.isEmpty()){
            res++;
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int curBusIndex = queue.poll();
                if(!visited[curBusIndex]){
                    visited[curBusIndex] = true;
                    for(int curStop : routes[curBusIndex]){
                        if(curStop == target) return res;
                        else {
                            HashSet<Integer> curStop2Bus = hashMap.get(curStop);
                            for(int busIndex : curStop2Bus){
                                if(!visited[busIndex]){
                                    queue.add(busIndex);
//                                    visited[busIndex] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[][] routes = {{1, 2, 7},{3, 6, 7},{3, 4, 9}};
        int[][] routes = {{1, 2, 7},{3, 6, 7},{7, 8, 9}};
//        int[][] routes = {{7,12},{4,5,15},{6},{15,19},{9,12,13}};
        int res = new lc815().numBusesToDestination( routes, 1, 8 );
        System.out.println(res);
    }
}
