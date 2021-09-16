package amazon;

/*
一开始想到滑动窗口，但是要注意这道题目数组里面可以是负值！这个特点很关键，所以就不能用滑动窗口！
例如当我们得到一个「窗口」的和恰好等于 k 的时候，右边界向右边继续扩张，只要扩张的区间和为 0，
依然可以得到一个和为 k 的更长的「窗口」，这种情况不能被排除掉。
因为数据并不是递增
例如：k = 3，[0, 0, 1, 1, 1, -1, 1, -1, 1]。

这道题目liweiwei相当于是一个渐进的思路，非常好：
https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/bao-li-jie-fa-qian-zhui-he-qian-zhui-he-you-hua-ja/

然后还有一个题解图画的挺好，一下子就能看懂，然后时间复杂度O(n)的方法也是下面这个题解最清晰：
https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/de-liao-yi-wen-jiang-qian-zhui-he-an-pai-yhyf/



类似专题：
560/454/724/1248/974/523/930
* */

import java.util.HashMap;

public class lc560 {
//    public int subarraySum(int[] nums, int k) {
//        int numLen = nums.length, curRes = 0, res = 0;
//        if(nums == null || numLen == 0) return 0;
//        int[] preSum = new int[numLen + 1];
//        for(int i = 0; i < numLen; i++){
//            preSum[i + 1] = preSum[i] + nums[i];
//        }
//        for(int right = 0; right < numLen; right++){
//            for(int left = 0; left <= right; left++){
////                注意下标偏移
//                if(preSum[right + 1] - preSum[left] == k) res++;
//            }
//        }
//        return res;
//    }


//    优化方法，其实就是借用了twoSum的思路
    public int subarraySum(int[] nums, int k) {
        int numLen = nums.length, res = 0;
        if(nums == null || numLen == 0) return 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] preSum = new int[numLen + 1];
        hashMap.put(preSum[0], 1);
        for(int i = 0; i < numLen; i++){
//            由于这里是i+1开始的，所以要把preSum[0]考虑进去，
//            因此要在一开始初始化的时候吧它加进hashmap里面
            preSum[i + 1] = preSum[i] + nums[i];
            if(hashMap.containsKey(preSum[i + 1] - k)) res += hashMap.get(preSum[i + 1] - k);
            hashMap.put(preSum[i + 1], hashMap.getOrDefault(preSum[i + 1], 0) + 1);
        }

        return res;
    }

    public static void main(String[] args) {
//        int[] test = {1,1,1};
//        System.out.println(new lc560().subarraySum(test, 2));

//        int[] test = {1};
//        System.out.println(new lc560().subarraySum(test, 0));

//        int[] test = {-1,-1,1};
//        System.out.println(new lc560().subarraySum(test, 0));

        int[] test = {1,2,3};
        System.out.println(new lc560().subarraySum(test, 3));
    }
}
