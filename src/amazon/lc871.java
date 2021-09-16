package amazon;


/*
原理的话下面这个题解说的最清楚：
https://leetcode-cn.com/problems/minimum-number-of-refueling-stops/solution/xian-gao-ming-bai-qi-che-wei-shi-yao-xu-qkpug/
但是代码的话参考这个题解更易懂，简洁：
https://leetcode-cn.com/problems/minimum-number-of-refueling-stops/solution/xiang-bu-chu-dong-tai-gui-hua-yong-liao-you-xian-d/
* */

import java.util.Comparator;
import java.util.PriorityQueue;

public class lc871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int res = 0, curPos = startFuel, curFuel = startFuel, sLen = stations.length;
        for(int i = 0; i < sLen; i++){
//            一开始用的if，大意了，test case2直接教做人为什么if不行
//            就是循环进行到i = 4的时候，可以算一下i = 0 ~3其实总数是到不了492的，
//            但是如果用了if就会导致curPos 仍然小于stations[i][0]但是却挑出了循环
            while (curPos < stations[i][0]){
                if(priorityQueue.isEmpty()) return -1;
                else {
                    curPos += priorityQueue.poll();
                    res++;
                }
            }
            priorityQueue.add(stations[i][1]);
        }
        while (curPos < target){
            if(priorityQueue.isEmpty()) return -1;
            curPos += priorityQueue.poll();
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
//        int[][] test = {{10,60},{20,30},{30,30},{60,40}};
//        System.out.println(new lc871().minRefuelStops(100, 10, test));

//        int[][] test = {{25,27},{36,187},{140,186},{378,6},{492,202},{517,89},{579,234},{673,86},{808,53},{954,49}};
//        System.out.println(new lc871().minRefuelStops(1000, 83, test));
//        下面这个数据是根据上面这个数据改的，更加直观能够看出问题，上面这个是在提交的时候报的错
        int[][] test = {{25,27},{36,187},{140,186},{378,6},{492,202}};
        System.out.println(new lc871().minRefuelStops(510, 83, test));
    }
}
