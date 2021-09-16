package amazon;

/*
详细自己的思路，一开始就用 PriorityQueue，但是发现Comparator很难写，发现一个题解有一个简单办法：
https://leetcode-cn.com/problems/largest-number/solution/zi-ding-yi-you-xian-dui-lie-by-xiao-cai-4xctg/

这道题目让我基本搞清楚了comparator的原理，参考链接：
https://blog.csdn.net/Tuzi294/article/details/104437345
* */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class lc179 {
    public String largestNumber(int[] nums) {
        int numLen = nums.length;
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            /*
            根据上面链接中的原理，o1其实就是需要新插入的String
            那么判断条件就是这个compare返回的值如果是负值的话那就让o1 siftUp
            换句话说，如果compare这个函数的结果是负数，那么o1就会排在 PriorityQueue的首位
            那么针对这道题目来说就是：
            如果o2o1 - o1o2 < 0，即o2o1 < o1o2，那么o1能够排到队首，这样就符合题意了
            * */
            public int compare(String o1, String o2) {
                String o1o2 = o1 + o2;
                String o2o1 = o2 + o1;
                return o2o1.compareTo(o1o2);
            }
        });
        for(int i = 0; i < numLen; i++){
            String str = String.valueOf(nums[i]);
            priorityQueue.add(str);
        }
        StringBuilder res = new StringBuilder();
//        用来针对Corner case的
        String zero = "0";
//        有一个corner case是nums = {0}
//        while (!priorityQueue.isEmpty() && zero.equals(priorityQueue.peek())) priorityQueue.poll();
        while (priorityQueue.size() > 1 && zero.equals(priorityQueue.peek())) priorityQueue.poll();
        while (!priorityQueue.isEmpty()){
            res.append(priorityQueue.poll());
        }
        return res.toString();
    }
}
