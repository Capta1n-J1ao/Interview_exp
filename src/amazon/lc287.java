package amazon;
/*
这道题目一定要按照进阶要求来做，否则没难度：
不能更改原数组（假设数组是只读的）。
只能使用额外的 O(1) 的空间。
时间复杂度小于 O(n2) 。
数组中只有一个重复的数字，但它可能不止重复出现一次。

直接liweiwei，说得很清楚：
https://leetcode-cn.com/problems/find-the-duplicate-number/solution/er-fen-fa-si-lu-ji-dai-ma-python-by-liweiwei1419/

还有就是如果面试的时候碰到这道题目一定要提到这么做是时间换空间，效率很低，不好

而且几个月以后刷题目，性能从2mS变为27mS，所以确实性能不好
* */

public class lc287 {
    public int findDuplicate(int[] nums) {
        int numLen = nums.length;
        int left = 1, right = numLen - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            int count = 0;
            for(int num : nums){
                if(num <= mid) count++;
            }
            if(count <= mid){
                left = mid + 1;
            }else right = mid;
        }
        return left;
    }
}
