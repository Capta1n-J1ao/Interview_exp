package amazon;

/*
无悬念是动态规划，注意有一步没想到，就是只对dp[num]设置初始化参数，其他不用
我一开始想的是全部设置初始化，但是后序不太好判断了
https://leetcode-cn.com/problems/minimum-cost-for-tickets/solution/dong-tai-gui-hua-si-lu-he-01wan-quan-bei-bao-yi-zh/
* */

public class lc983 {
    public int mincostTickets(int[] days, int[] costs) {
        if(days == null || costs == null) return 0;
        int dLen = days.length, cLen = costs.length, lastDay = days[dLen - 1];
//        因为天数范围是[1,365]，所以为了下标和数据的统一长度就+1
        int[] dp = new int[lastDay + 1];
        for(int num : days){
            dp[num] = Integer.MAX_VALUE;
        }
        for(int i = 1; i <= lastDay; i++){
            if(dp[i] == 0) dp[i] = dp[i - 1];
            else {
                int one = dp[i - 1] + costs[0];
//                这下面两个写法和题解不一样，但是我觉得我的理解是更正确的
//                如果用i > 7的话那么i = 8才能够适用，这样不合适，
//                因为dp[0]是一定等于0的，所以如果i = 7的话也应该能进入seven的那个循环
                int seven = i > 6 ? dp[i - 7] + costs[1] : costs[1];
                int thirty = i > 29 ? dp[i - 30] + costs[2] : costs[2];
                dp[i] = Math.min(Math.min(one, seven), thirty);
            }
        }
        return dp[lastDay];
    }


}
