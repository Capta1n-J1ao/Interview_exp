package amazon;

/*
对应lc731
可以和729,231一起服用
huahua的视频终于把+1/-1的原理讲清楚了，
其实本质上就是基于下面这个思路的优化：
声明一个长度为时间取值范围的数组，
使用此数组存储每个时间点上的占用次数，例如当[5,15)被book掉后，arr[5]~arr[14]的值就都为1，
直观且简单。但本地时间参数的取值范围是[0, 10^9]，所以很明显这个方案是不现实的，需要的内存空间太大。
https://www.bilibili.com/video/BV15W411r7Qa?from=search&seid=832757235371746136
* */

import java.util.TreeMap;

public class MyCalendarTwo {
    private TreeMap<Integer, Integer> treeMap;
    public MyCalendarTwo() {
        treeMap = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        treeMap.put(start, treeMap.getOrDefault(start, 0) + 1);
        treeMap.put(end, treeMap.getOrDefault(end, 0) - 1);
        int sum = 0;
        for(int freq : treeMap.values()){
            sum += freq;
            if(sum > 2) {
                treeMap.put(start, treeMap.get(start) - 1);
//                注意这里是还原
                treeMap.put(end, treeMap.get(end) + 1);
                if(treeMap.get(start) == 0) treeMap.remove(start);
                if(treeMap.get(end) == 0) treeMap.remove(end);
                return false;
            }
        }
        return true;
    }
}
