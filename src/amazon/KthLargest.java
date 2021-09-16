package amazon;

/*
lc703

这道题目有一个很重要的点就是用priority queue放元素的时候，第一次全部放完以后里面的数据是排序的，
但是如果再用add放元素的话，priority queue只会在第一个显示对应数组，
也就是说如果是maxheap那么poll出来的就是max，但是下面的顺序其实是不定的，
看了下源码的时候发现如果执行了poll方法的话，在取出了对应元素以后才会重新排序，
所以如果仅仅是add的话只是放入了数据，此时这个放入的数据如果不是最大的（针对maxheap），那么她就放在最后
等下次poll了以后才重新排序
* */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class KthLargest {
    PriorityQueue<Integer> priorityQueue;
    int numLen, k;
    public KthLargest(int k, int[] nums) {
        numLen = nums.length;
        this.k = k;
        priorityQueue = new PriorityQueue<>();
        for(int num : nums){
            priorityQueue.add(num);
            if(priorityQueue.size() > k) priorityQueue.poll();
        }
    }

    public int add(int val) {
        if(priorityQueue.size() < k) priorityQueue.add(val);
        else {
            int minNum = priorityQueue.poll();
            if(val > minNum) priorityQueue.add(val);
            else priorityQueue.add(minNum);
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int[] test = {4,5,8,2};
        KthLargest res = new KthLargest(3, test);
        System.out.println(res.add(6));
        System.out.println(res.add(7));
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
