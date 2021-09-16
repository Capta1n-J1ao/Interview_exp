package amazon;

/*
可以和lc300/674一起服用
下面这个题解有个动图，一看就能懂了：
https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/solution/dong-tai-gui-hua-dong-tu-fu-zhu-li-jie-ru-you-bang/

代码参考，说的很简洁，并且清楚：
https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/solution/673-zui-chang-di-zeng-zi-xu-lie-de-ge-sh-g7a0/
* */

import java.util.Arrays;

public class lc673 {
    public int findNumberOfLIS(int[] nums) {
        if(nums == null || nums.length < 2) return nums.length;
        int numLen = nums.length;
        int[] longestLen = new int[numLen];
        Arrays.fill(longestLen, 1);
        int[] longestCounts = new int[numLen];
        Arrays.fill(longestCounts, 1);
        int maxLen = 0;
        for(int i = 1; i < numLen; i++){
            for(int k = 0; k < i; k++){
                if(nums[i] > nums[k] && longestLen[k] + 1 > longestLen[i]){
                    longestLen[i] = longestLen[k] + 1;
//                    下面这句容易忽略
                    longestCounts[i] = longestCounts[k];
//                    这里一定要用else if，否则就可能有错误，在写代码的时候还特地考虑过，但是写的时候觉得没影响
                }else if(nums[i] > nums[k] && longestLen[k] + 1 == longestLen[i]){
                    longestCounts[i] += longestCounts[k];
                }
            }
            maxLen = Math.max(maxLen, longestLen[i]);
        }
        int res = 0;
        for(int i = 0; i < numLen; i++){
            if(longestLen[i] == maxLen) res += longestCounts[i];
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] test = {1,3,5,4,7};
//        System.out.println(new lc673().findNumberOfLIS(test));

        int[] test = {1};
        System.out.println(new lc673().findNumberOfLIS(test));
    }
}
