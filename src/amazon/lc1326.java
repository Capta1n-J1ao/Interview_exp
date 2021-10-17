package amazon;

/*
算是greedy里比较经典的区间覆盖问题，可以转化成interval以后sort，题解参考：
https://leetcode-cn.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/solution/java-tong-yong-de-yi-wei-qu-jian-jiao-bi-qfn0/
* */

import java.util.Arrays;
import java.util.Comparator;

public class lc1326 {
    public int minTaps(int n, int[] ranges) {
        int rLen = ranges.length;
        int[][] tap = new int[n + 1][2];
        for(int i = 0; i <= n; i++){
            tap[i][0] = Math.max(0, i - ranges[i]);
//            tap[i][1] = Math.min(rLen, i + ranges[i]);
            tap[i][1] = Math.min(n, i + ranges[i]);
        }
        Arrays.sort(tap, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                else return Integer.compare(o1[0], o2[0]);
            }
        });
        int res = 0, right = 0;
//        这里用while是有原因的，因为你在遍历数组的时候，
//        要嵌套一个while来让数组找到超过right的那个最新的值，当然for也可以，但是要每次更新变量比较麻烦
        int curTap = 0;
        while (curTap < rLen){
            if(tap[curTap][0] > right) break;
            int temp = right;
//            但是像这种内部循环一定要注意第一个条件，之前遇到过
            while (curTap < rLen && tap[curTap][0] <= right){
                temp = Math.max(temp, tap[curTap][1]);
                curTap++;
            }
            right = temp;
            res++;
            if(right >= n) break;
        }
        return right >= n ? res : -1;
    }

    public static void main(String[] args) {
//        int[] test = {3,4,1,1,0,0};
//        System.out.println(new lc1326().minTaps(5, test));

        int[] test = {1,2,1,0,2,1,0,1};
        System.out.println(new lc1326().minTaps(7, test));
    }
}
