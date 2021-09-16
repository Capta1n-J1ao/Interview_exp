package amazon;

/*
和lc295一个套路，可以放在一起做，题解找了一个和lc295比较通用的：
https://leetcode-cn.com/problems/sliding-window-median/solution/dui-295ti-shao-zuo-gai-jin-by-jerry_nju/

这里有一个巨不容易发现的关于Priority的问题那就是再做大根堆的时候，我之前的方法一直是
return o2 - o1，
但是这个方法在处理int里面的最大数或者最小数的时候会有溢出的问题，所以要像这题里面这么写才可以
这也是之前一直碰到的有时候大根堆不排序的原因！！
* */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class lc480 {
    private PriorityQueue<Integer> maxLeftHeap;
    private PriorityQueue<Integer> minRightHeap;
    private int numLen, k;
    private double[] res;
    public double[] medianSlidingWindow(int[] nums, int k) {
        this.k = k;
//        这句话debug了一个小时！一定要记住！
        maxLeftHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o2 > o1){
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        minRightHeap = new PriorityQueue<>();
        numLen = nums.length;
        res = new double[numLen - k + 1];
        int left = 0, right = 0, count = 0, index = 0;
        for(int i = 0; i <numLen - k + 1; i++){
            while (right < numLen && count < k){
                count++;
                add(nums[right]);
                right++;
            }
            if(count == k){
                if(maxLeftHeap.size() == minRightHeap.size()) res[index] =
                        maxLeftHeap.peek() / 2.0  + minRightHeap.peek() / 2.0;
                else res[index] = (double) maxLeftHeap.peek();
                index++;
            }
            int temp = maxLeftHeap.peek();
            if(nums[i] <= maxLeftHeap.peek()) maxLeftHeap.remove(nums[i]);
            else minRightHeap.remove(nums[i]);
//            System.out.println(maxLeftHeap.size());
//            System.out.println(minRightHeap.size());
//            System.out.println("结束一次");
            count--;
        }
        return res;
    }

    private void add(int num){
        maxLeftHeap.add(num);
        minRightHeap.add(maxLeftHeap.poll());
//        这个点和lc295不一样，不能用count作为判断条件！
//        if((count & 1) == 1) maxLeftHeap.add(minRightHeap.poll());
        if(minRightHeap.size() > maxLeftHeap.size()) maxLeftHeap.add(minRightHeap.poll());
    }

    public static void main(String[] args) {
        int[] test = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(new lc480().medianSlidingWindow(test, 3)));
        System.out.println("预期结果 ： " + "[1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]");

//        int[] test = {-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
//        System.out.println(Arrays.toString(new lc480().medianSlidingWindow(test, 3)));
    }
}
