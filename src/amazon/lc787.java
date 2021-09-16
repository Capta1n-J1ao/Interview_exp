package amazon;

/*
做这题的第一反应就是用BFS，这个思路是可以的，并且效率也不错，但是仔细思考在Queue里面放什么
BFS题解：
https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/solution/yan-du-you-xian-bian-li-by-enterprise-2/

二刷的时候如果感兴趣可以想想dp的办法
* */

import java.util.LinkedList;
import java.util.Queue;

public class lc787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for(int[] flight : flights){
            graph[flight[0]][flight[1]] = flight[2];
        }
        Queue<int[]> queue = new LinkedList<>();
//        往queue里面加的元素形式非常重要！！
        queue.add(new int[]{src, 0});
        int count = 0, res = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int[] temp = queue.poll();
                for(int k = 0; k < n; k++){
                    if(graph[temp[0]][k] > 0){
                        if(k == dst){
                            res = Math.min(res, temp[1] + graph[temp[0]][k]);
//                            下面这个其实是剪枝，否则如果只用else没用if条件那么会超时
                        }else if(temp[1] + graph[temp[0]][k] < res){
                            queue.add(new int[]{k, temp[1] + graph[temp[0]][k]});
                        }
                    }
                }
            }
            if(count == K) break;
            count++;
        }
        if(res == Integer.MAX_VALUE) return -1;
        return res;
    }

    public static void main(String[] args) {
        int[][] test = {{0,1,100},{1,2,100},{0,2,500}};
//        结果为200
        System.out.println(new lc787().findCheapestPrice(3, test, 0,2,1));
//        结果为500
        System.out.println(new lc787().findCheapestPrice(3, test, 0,2,0));
    }
}
