package amazon;

/*
一开始写出来的第一个版本虽然是对的，但是会超时，所以明显要优化

liweiwei的题解启发性最强，一步步推导为啥要用双端队列即deque，但是对于整个代码的解释不是很清晰：
https://leetcode-cn.com/problems/sliding-window-maximum/solution/zui-da-suo-yin-dui-shuang-duan-dui-lie-cun-suo-yin/

代码参考如下题解：
https://leetcode-cn.com/problems/sliding-window-maximum/solution/3chong-jie-jue-fang-shi-by-sdwwld/

这道题目其实有新练习了一种双端队列Deque，之前一直不知道deque和queue的区别，
其实deque就是加强版的queue，他有queue的所有功能，并且融合了stack的功能。
* */

import java.util.*;

public class lc239 {
//    private PriorityQueue<Integer> priorityQueue;
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//
//        int numLen = nums.length;
//        if(k > numLen){
//            for(int num : nums){
//                priorityQueue.add(num);
//            }
//            int res1 = priorityQueue.peek();
//            return new int[]{res1};
//        }
//
//        int[] res = new int[numLen - (k - 1)];
//        int start = 0, end = k - 1;
//        while (end < numLen){
//            for(int i = start; i <= end; i++){
//                priorityQueue.add(nums[i]);
//            }
//            int curRes = priorityQueue.peek();
//            res[start] = curRes;
//            priorityQueue.clear();
//            start++;
//            end++;
//        }
//        return res;
//    }

//    public int[] maxSlidingWindow(int[] nums, int k) {
//        if (nums == null || k <= 0) return new int[0];
//        int[] res = new int[nums.length - (k - 1)];
//        int index = 0, numLen = nums.length;
////      这里的deque里面放的是下标，而不是数
//        Deque<Integer> deque = new ArrayDeque<>();
//        for(int i = 0; i < numLen; i++){
////            这个去除窗口最左边的多余元素是最后写的，
////            但是仔细想想还是要写这里才可以，其他地方都不行
//            if(!deque.isEmpty() && deque.peekFirst() <= i - k) deque.removeFirst();
////            下面的所有逻辑这个是先写出来的主体
////            这个写错了，在第二个case的时候能看出错误，
////            因为一定要把最大值放在first，所以一定要保证first是最大值，
////            while (!deque.isEmpty() && nums[i] > nums[deque.peekFirst()]){
////                deque.removeFirst();
////            }
//            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]){
//                deque.removeLast();
//            }
//            deque.addLast(i);
//            if(i >= (k - 1)){
//                res[index] = nums[deque.peekFirst()];
//                index++;
//            }
//        }
//        return res;
//    }

//    二刷0309,解法OK但是会超时
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int numLen = nums.length;
//        if (nums == null || k <= 0) return new int[0];
//        int[] res = new int[numLen - (k - 1)];
//        Deque<Integer> deque = new ArrayDeque<>();
//        int start = k, index = 1, curMax = Integer.MIN_VALUE;
//        for(int i = 0; i < k; i++){
//            deque.addFirst(nums[i]);
//            curMax = Math.max(curMax, nums[i]);
//        }
//        res[0] = curMax;
//        while (start < numLen){
//            if(nums[start] >= curMax){
//                res[index] = nums[start];
//                curMax = nums[start];
//            }else {
//                int first = deque.peekLast();
//                if (first == curMax) {
//                    int temp = Integer.MIN_VALUE;
//                    for (int i = start - k + 1; i <= start; i++) {
//                        temp = Math.max(temp, nums[i]);
//                    }
//                    curMax = temp;
//                }
//                res[index] = curMax;
//            }
//            deque.addFirst(nums[start]);
//            deque.removeLast();
//            index++;
//            start++;
//
//        }
//        return res;
//    }

//    所以还是用了题解里面的方法,但是这次用的deque方向和一刷的时候是反的
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int[] res = new int[nums.length - (k - 1)];
        int index = 0, numLen = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < numLen; i++){
            if(!deque.isEmpty() && deque.peekLast() <= i - k) deque.removeLast();
            while (!deque.isEmpty() && nums[i] > nums[deque.peekFirst()]) deque.removeFirst();
            deque.addFirst(i);
            if(i >= k -  1){
                res[index] = nums[deque.peekLast()];
                index++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        答案3,3,5,5,6,7
//        int[] test = {1,3,-1,-3,5,3,6,7};
//        System.out.println(Arrays.toString(new lc239().maxSlidingWindow(test, 3)));

//        答案3,3,2,5
        int[] test = {1,3,1,2,0,5};
        System.out.println(Arrays.toString(new lc239().maxSlidingWindow(test, 3)));


//        答案1,-1
//        int[] test = {1,-1};
//        System.out.println(Arrays.toString(new lc239().maxSlidingWindow(test, 1)));
    }
}
