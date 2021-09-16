package amazon;

/*
对应lc729
lc731和lc218可以和这道题目一起服用，并且前提是用TreeMap
关于TreeMap的话可以看下视频讲解原理，主要核心底层其实就是红黑树
下面这个链接讲解了HashMap和TreeMap的区别
https://www.bilibili.com/video/BV1GU4y187cb?from=search&seid=15863792657718429394

下面这个讲解了TreeMap的具体原理，从P3 ~ P5 时间比较长，有时间可以看
没时间的话就知道TreeMap放进去的数可以按照key的某一种默认顺序或者自定义顺序排列即可
https://www.bilibili.com/video/BV1864y1S7si?from=search&seid=12571456772939573705

对于目前做到的leetcode来说，主要就是使用floorkey/ceilingkey这两种命令，相关命令的使用说明
总价下来就是
1. floorkey返回在所有“小于等于”给定值里面的最大值
2. ceilingkey返回在所有”大于等于“给定值里面的最小值
https://blog.csdn.net/cumtb2009/article/details/107796577

自己写了一种代码，但是速度不是很理想，毕竟用了sort的命令，
顶多NlogN的复杂度，同时要对所有情况进行分类：
1. start 落在某个已存在的int[]的范围内
2. end 落在某个已存在的int[]的范围内
3. [start, end)包含了某个已存在的int[]的范围
4. [start, end)是某个已存在的int[]的范围内的子集

但是和lc218 一样，可以学习一下TreeMap，这样性能应该更好

这道题目huahua的题解对于暴力解法有一个很好的代码，就是一次性判断是不是重叠：
https://www.bilibili.com/video/BV15W411r7sA?from=search&seid=5801084462473975769
* */

import java.util.*;

public class MyCalendar {
//    完全自己写的方法，但是由于没用treeMap,所以搜索效率不高
//    private List<int[]> arrayList;
//    private int size;
//    public MyCalendar() {
//        arrayList = new ArrayList<>();
//        size = 0;
//    }
//
//    public boolean book(int start, int end) {
//        if(size == 0){
//            arrayList.add(new int[]{start, end});
//            size++;
//            return true;
//        }else if(size > 0){
//            Collections.sort(arrayList, new Comparator<int[]>() {
//                @Override
//                public int compare(int[] o1, int[] o2) {
//                    return o1[1] - o2[1];
//                }
//            });
//            for(int i = 0; i < arrayList.size(); i++){
//                if(start >= arrayList.get(arrayList.size() - 1)[1]) break;
//                int[] curRes = arrayList.get(i);
//                if(curRes[0] < end && curRes[1] >= end) return false;
//                else if(curRes[0] <= start && curRes[1] > start) return false;
//                else if(curRes[0] >= start && curRes[1] <= end) return false;
//                else if(curRes[0] <= start && curRes[1] >= end) return false;
//            }
//            arrayList.add(new int[]{start, end});
//            size++;
//            return true;
//        }else return false;
//    }

//    方法二，结合TreeMap
    private TreeMap<Integer, Integer> treeMap;
    public MyCalendar() {
        treeMap = new TreeMap<>();
    }

    public boolean book(int start, int end) {
//        这里一定要用Integer而不能用int，因为int不能为Null
        Integer right = treeMap.ceilingKey(start);
        Integer left = treeMap.floorKey(start);
//        注意这里不需要考虑万一比left小的key对应的end大于left对应的end，
//        因为这样的话在Treemap里面相当于存在重叠，但是这是不可能的
//        因为如果有重叠的话在之前的操作就不会加进map里面，所以不用担心
        if((right == null || right >= end) && (left == null || treeMap.get(left) <= start)){
            treeMap.put(start, end);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        MyCalendar test = new MyCalendar();
//        System.out.println(test.book(97,100));
//        System.out.println(test.book(33,51));
//        System.out.println(test.book(89,100));
//        System.out.println(test.book(83,100));
//        System.out.println(test.book(75,92));
//        System.out.println(test.book(76,95));
//        System.out.println(test.book(19,30));
//        System.out.println(test.book(53,63));
//        System.out.println(test.book(8,23));
//        System.out.println(test.book(18,37));
//        System.out.println(test.book(87,100));
//        System.out.println(test.book(83,100));
//        System.out.println(test.book(54,67));
//        System.out.println(test.book(35,48));
//        System.out.println(test.book(58,75));
//        System.out.println(test.book(70,89));
//        System.out.println(test.book(13,32));
//        System.out.println(test.book(44,63));
//        System.out.println(test.book(51,62));
//        System.out.println(test.book(2,15));

        MyCalendar test = new MyCalendar();
        System.out.println(test.book(1,8));
        System.out.println(test.book(3,5));
        System.out.println(test.book(5,6));
    }
}
