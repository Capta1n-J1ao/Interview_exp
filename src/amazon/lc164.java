package amazon;

/*
这道题目下面这个视频想的听清楚的,其实这里还能够复习一下快排，也是O(N)复杂度，相关题目973/215/23/692/179/406：
https://leetcode-cn.com/problems/maximum-gap/solution/java-xiang-xi-jiang-jie-si-lu-qing-xi-tong-pai-xu-/

然后有以下几个点需要思考：
1. 有一个很巧妙的点就是为什么确保了最大值是在桶间而不是桶内
   原因就是因为保证必须有一个空桶，所以可以确保不会在桶内
2. 为什么把整个数组的max和min不考虑进去，其实原因就是由1可以知道最大值一定产生在桶间而不是桶内，
   而桶间的计算是后一个桶的min减去前一个桶的max，那么对于min来说，不会被计算到。同理max也不会被计算到
   所以一共n - 2个数放进n - 1个桶里，一定会有一个桶是空的
3. 复习了一下ceil的用法，和218/729/164一起服用
* */

import java.util.Arrays;

public class lc164 {
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int numLen = nums.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int num : nums){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if(max == min) return 0;

        int[] bucketMax = new int[numLen - 1];
        int[] bucketMin = new int[numLen - 1];
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        Arrays.fill(bucketMin, Integer.MAX_VALUE);

        int interval = (int) Math.ceil((double) (max - min) / (numLen - 1));
//        将每个元素放入桶中，但是min和max不放入，参考上面第2点
        for(int num : nums){
            int index = (num - min) / interval;
            if(num == max || num == min) continue;
            bucketMax[index] = Math.max(bucketMax[index], num);
            bucketMin[index] = Math.min(bucketMin[index], num);
        }

        int res = 0, preMax = min;
        for(int i = 0; i < numLen - 1; i++){
            if(bucketMax[i] == Integer.MIN_VALUE) continue;
            res = Math.max(res, bucketMin[i] - preMax);
            preMax = bucketMax[i];
        }
//        针对corner case
        res = Math.max(res, max - preMax);
        return res;
    }

    public static void main(String[] args) {
        int[] test = {3,6,9,1};
        System.out.println(new lc164().maximumGap(test));
    }
}
