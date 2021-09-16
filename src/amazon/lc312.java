package amazon;

/*
这道题目绝对属于dp里面最难得哪一种题型了，看了答案也完全没有思路，所以这道题至少预留1-2小时做
唯一一个讲的比较清楚的其实是B站填表格的视频，但是其实对于思路的讲解还是比较少的：
https://www.bilibili.com/video/BV1ra4y1a7Py?from=search&seid=2741858698771682657
代码的话还是参考官解，更加清晰：
https://leetcode-cn.com/problems/burst-balloons/solution/java-qu-jian-dp-yan-shi-dpfen-xi-fa-xiang-xi-zhu-s/
* */

public class lc312 {
    public int maxCoins(int[] nums) {
        int numLen = nums.length, res = 0;
        if(numLen == 0) return 0;
        int[] nums1 = new int[numLen + 2];
        nums1[0] = 1;
        nums1[numLen + 1] = 1;
//        这里可以复习一下arraycopy的用法
        System.arraycopy(nums, 0, nums1, 1, numLen);
//        num1Len = numLen + 2
        int num1Len = nums1.length;
        int[][] dp = new int[num1Len][num1Len];
//        这个for循环很难理解，其实就是因为填表一定要斜着填导致的
        for(int len = 1; len <= numLen; len++){
//            接下来i和k两个参数其实就是在[i,k]范围里面做填表格,
//            m是用来分割[i,m],[m,k]的
//            下面这个判断条件是难点中的难点，就是这个语句实现了斜向填表
//            这里把int k = i + len - 1;写在下面的原因
//            是为了提现for循环里面i的关系，否则用了k不清晰
//            容易产生for循环和i没关系的错觉
            for(int i = 1; i + len - 1 <= numLen; i++){
//                这个就是右边界
                int k = i + len - 1;
                for(int m = i; m <= k; m++){
                    int curRes = nums1[i - 1] * nums1[m] * nums1[k + 1];
                    dp[i][k] = Math.max(
                            dp[i][k],
                            dp[i][m - 1] + dp[m + 1][k] + curRes
                    );
                }
            }
        }
        return dp[1][numLen];
    }

    public static void main(String[] args) {
        int[] test = {3,1,5,8};
        System.out.println(new lc312().maxCoins(test));
    }
}
