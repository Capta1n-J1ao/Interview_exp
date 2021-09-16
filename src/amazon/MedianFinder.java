package amazon;

/*
对应lc295

第一种暴力法太傻了，参考Liweiwei：
https://leetcode-cn.com/problems/find-median-from-data-stream/solution/you-xian-dui-lie-python-dai-ma-java-dai-ma-by-liwe/

写的时候注意addNum是有简便的解决办法的，要总结以下规律
* */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class MedianFinder {
//    方法一， 自己想的，但是效率不高
//    ArrayList<Double> arrayList;
//    /** initialize your data structure here. */
//    public MedianFinder() {
//        arrayList = new ArrayList<>();
//    }
//
//    public void addNum(int num) {
//        arrayList.add((double) num);
//    }
//
//    public double findMedian() {
//        arrayList.sort(new Comparator<Double>() {
//            @Override
//            public int compare(Double o1, Double o2) {
//                return (int) (o1 - o2);
//            }
//        });
//        int aLen = arrayList.size();
////        if(aLen == 1) return arrayList.get(0);
////        if(aLen == 2) return arrayList.get(0);
//        int index = aLen / 2;
//        if((aLen & 1) == 0){
//            return (arrayList.get(index) + arrayList.get(index - 1)) / 2;
//        }else return arrayList.get(index - 1);
//    }


    private int count;
    private PriorityQueue<Integer> maxLeftHeap;
    private PriorityQueue<Integer> minRightHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
        maxLeftHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        minRightHeap = new PriorityQueue<>();
        count = 0;
    }

//    这个方法其实在总结归纳以后发现用很简单的方法就能写出来,
//    但是如果没找对方法会有很多tricky的地方
//    思路可以参考如下：
//    1. 如果count为偶数，那就说明之前所有数的数目为奇数，那么新加入一个数的话直接加到minRightHeap即可
//    2. 如果count为奇数，那就说明之前所有数的数目为偶数，那么新加入一个数的话直接加到maxLeftHeap即可
    public void addNum(int num) {
        count++;
        maxLeftHeap.add(num);
        minRightHeap.add(maxLeftHeap.poll());
        if((count & 1) == 1) maxLeftHeap.add(minRightHeap.poll());
    }

    public double findMedian() {
        if((count & 1) == 1){
            return (double)maxLeftHeap.peek();
        }else return (double) (maxLeftHeap.peek() + minRightHeap.peek()) / 2;
    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(2);
        double median1 = obj.findMedian();
        obj.addNum(3);
        double median2 = obj.findMedian();
    }
}
