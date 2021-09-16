package amazon;

/*
这道题目非常好，写的第一种方法虽然是对的，但是会超时，所以要换思路，贴了一下之前做的思路


题目讲解看liweiwei即可：
https://leetcode-cn.com/problems/combination-sum-iv/solution/dong-tai-gui-hua-python-dai-ma-by-liweiwei1419/
这里对状态转移为什么牵扯到顺序相关就使用一维做一个说明，其实就是把Liweiwei说的再透一点，
以nums = {1,2,3} target = 4为例
dp[0]   dp[1]   dp[2]               dp[3]                           dp[4]
  1        1    dp[0] + dp[1] = 2   dp[0] + dp[1] + dp[2] = 4       dp[1] + dp[2] + dp[3] = 7

其实在做这个dp图表的时候，暗含的逻辑是在计算3的时候，dp[0]代表只取了一个3的可能，dp[1]代表2 + dp[1]的可能性
dp[2]代表1 + dp[2]的可能性，
但是其实dp[1]中的2 + dp[1]
     与dp[2]中的1 + dp[2]
在计算式上包含的是 2 + 1，以及1 + 2这两种顺序相关的可能性，这样其他就比较好懂了。
建议和lc518一起做
* */

import java.util.HashSet;

public class lc377 {
//    这个方法虽然对，但是在做第三个case的时候会超时
//    private int res = 0;
//    private int[] nums;
//    private HashSet<Integer> hashSet = new HashSet<>();
//    public int combinationSum4(int[] nums, int target) {
//        this.nums = nums;
//        int numLen = nums.length;
//        for(int num : nums) hashSet.add(num);
//        return helper(target);
//    }
//
//    private int helper(int goal){
//        int curRes = 0;
//        if(hashSet.contains(goal)) curRes++;
//        for(int num : nums){
//            if(goal - num > 0) curRes += helper(goal - num);
////            System.out.println(helper(goal - num));
//        }
//        return curRes;
//    }

//    方法二，用dp不会超时
    public int combinationSum4(int[] nums, int target) {
        int numLen = nums.length;
        int[] dp = new int[target + 1];
//        我这里这个方法和liweiwei的不一样，但是感觉我的更直观更好理解
//        这里代替的就是他dp[0] = 1的那一步，我觉得我这个更清晰
        for(int num : nums) {
//            针对第二个test case
            if(num > target) continue;
            dp[num] = 1;
        }
        for(int i = 1; i <= target; i++){
            for(int num : nums){
                if(i - num > 0){
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
//        int[] test = {1,2,3};
//        System.out.println(new lc377().combinationSum4(test, 4));

        int[] test = {9};
        System.out.println(new lc377().combinationSum4(test, 3));

//        int[] test = {1,2,3};
//        System.out.println(new lc377().combinationSum4(test, 32));
    }
}
