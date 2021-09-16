package amazon;

/*
可以借lc218和lc729熟悉一下TreeMap这种数据结构
一刷已经花了很多时间，所以还是用的简单方法，但是二刷的时候可以尝试一下TreeMap，
似乎在相同的方法下性能会好很多

*************************关于TreeMap***************************

关于TreeMap的话可以看下视频讲解原理，主要核心底层其实就是红黑树
下面这个链接讲解了HashMap和TreeMap的区别
https://www.bilibili.com/video/BV1GU4y187cb?from=search&seid=15863792657718429394

下面这个讲解了TreeMap的具体原理，从P3 ~ P5 时间比较长，有时间可以看
没时间的话就知道TreeMap放进去的数可以按照key的某一种默认顺序或者自定义顺序排列即可
https://www.bilibili.com/video/BV1864y1S7si?from=search&seid=12571456772939573705

对于目前做到的leetcode来说，主要就是使用floorkey/ceilingkey这两种命令，相关命令的使用说明
总价下来就是
1. floorkey返回在所有小于等于给定值里面的最大值
2. ceilingkey返回在所有大于等于给定值里面的最小是
https://blog.csdn.net/cumtb2009/article/details/107796577

*************************关于TreeMap***************************

题目思路的话可以看花花的视频，说的挺清楚，看到pseudo code那里就可以了：
https://www.bilibili.com/video/BV1hb411c7Q4?from=search&seid=1678321967878203137
然后再看一下这个视频，其实把代码运行过程都说的很清楚了，一刷的时候看了这个视频以后完全懂了，代码也很好：
https://www.bilibili.com/video/BV164411r7QC?from=search&seid=1678321967878203137

总结下来这道题目有如下注意点：
1. 掌握List的排序是要用Collections.sort，剩下的和其他数据结构的自定义排序一致
2. 因为一刷的代码里面用了priorityQueue的remove，所以效率是低很多的，因此二刷建议
   一定要尝试用一下TreeMap
* */

import java.util.*;

public class lc218 {
    private List<List<Integer>> res = new LinkedList<>();
    private List<int[]> buildingPara = new LinkedList<>();
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int bLen = buildings.length;
        if(buildings == null || bLen == 0) return res;
        for(int[] building : buildings){
            buildingPara.add(new int[]{building[0], -building[2]});
            buildingPara.add(new int[]{building[1], building[2]});
        }
//        掌握List的排序，很好的办法
        Collections.sort(buildingPara, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
//                这里两个都是升序但是意义完全不同，因为用的是进maxHeap的时候是负数
//                出maxHeap的时候是正数，所以进的时候降序说明高的在前，低的在后
//                然后出的时候越需要升序，和进的时候同理，不能降到半山腰而是一下子降到底
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
//        这步很关键，一定要加
        maxHeap.add(0);
        int curRes = 0;
        for(int[] building : buildingPara){
//            注意题目说了height >= 1
//            还有就是这里的逻辑其实可以优化的，可以参考第二个视频的代码
            if(building[1] < 0){
                maxHeap.add(-building[1]);
                if(-building[1] > curRes){
                    curRes = -building[1];
                    List<Integer> temp = new LinkedList<>();
                    temp.add(building[0]);
                    temp.add(-building[1]);
                    res.add(temp);
                }else continue;
            }else {
                maxHeap.remove(building[1]);
                if(maxHeap.peek() < curRes){
                    curRes = maxHeap.peek();
                    List<Integer> temp = new LinkedList<>();
                    temp.add(building[0]);
                    temp.add(curRes);
                    res.add(temp);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] test = {{2,9,10},{3,7,15},{5,12,12}};
        System.out.println(new lc218().getSkyline(test));
    }
}
