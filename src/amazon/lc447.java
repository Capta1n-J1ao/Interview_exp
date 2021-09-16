package amazon;

/*
这道题目的难点在于理解题意，其实意思就是说points中的点到其他点的距离相等的话就在一个平面
比如a和b/c/d在一个平面，代表着a到b/c/d的距离相等，那么他们一共有12中组合，结果+12
同时a和e/f/g在另一个平面，结果就需要再加12,就是这个意思
那可能points里面有很多歌平面，目的就是找到这所有平面包含的每一对点

还有要注意：
题目中顺序不同需要重复计数的，可以看一下样例
这个题解说得很清楚，而且下面也有java版：
https://leetcode-cn.com/problems/number-of-boomerangs/solution/mei-ju-ha-xi-biao-by-wangdh15/
* */

import java.util.HashMap;

public class lc447 {
    public int numberOfBoomerangs(int[][] points) {
        int pLen = points.length, res = 0;
        if(pLen < 2) return 0;
        for(int i = 0; i < pLen; i++){
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            int ix = points[i][0];
            int iy = points[i][1];
            for(int k = 0; k < pLen; k++){
                int kx = points[k][0];
                int ky = points[k][1];
//                这里如果dist为0可以直接continue，也可以不管，因为只会和自己dist为0，
//                也就是说只有一个数，下面计算式dist * (dist - 1) 会自己筛选掉的
                int dist = dist(ix, iy, kx, ky);
                hashMap.put(dist, hashMap.getOrDefault(dist, 0) + 1);
            }
            for(int key : hashMap.keySet()){
                res += hashMap.get(key) * (hashMap.get(key) - 1);
            }
        }
        return res;
    }

    private int dist(int x1, int y1, int x2, int y2){
//        return (int) (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        return (x1 - x2) * (x1 - x2) + (y1 - y2) *(y1 - y2);
    }

    public static void main(String[] args) {
        int[][] test = {{0,0},{1,0},{2,0}};
        System.out.println(new lc447().numberOfBoomerangs(test));
    }
}
