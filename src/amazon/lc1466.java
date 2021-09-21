package amazon;

/*
DRW OA第一题
最接近我的思路的题解如下，可以看下：
https://leetcode-cn.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/solution/duo-tang-bian-li-by-li-qiu-shui-xd8c/
* */

import java.util.HashMap;
import java.util.HashSet;

public class lc1466 {
    private int[][] connections;
    private int n, res;
    private HashSet<Integer> hashSet = new HashSet<>();
    public int minReorder(int n, int[][] connections) {
        this.connections = connections;
        this.n = n;
        this.res = 0;
        hashSet.add(0);
        for(int[] con : connections){
            if (con[1] == 0) hashSet.add(con[0]);
        }
//        下面这句是重点，可以结合case2看一下就懂了
        while (hashSet.size() < n){
            for(int[] con : connections){
                if(hashSet.contains(con[1])){
                    hashSet.add(con[0]);
                }else if(hashSet.contains(con[0])){
                    res++;
                    hashSet.add(con[1]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[][] test = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
//        System.out.println(new lc1466().minReorder(6, test));

        int[][] test = {{4,3}, {2,3}, {1,2}, {1, 0}};
        System.out.println(new lc1466().minReorder(5, test));
    }
}
