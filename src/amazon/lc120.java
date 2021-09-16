package amazon;

/*
这道题目难点：
1. 怎么判断每一行第一个数
2. 怎么判断每一行最后一个数

对应题解：
https://leetcode-cn.com/problems/triangle/solution/san-chong-jie-fa-duo-tu-xiang-jie-120-san-jiao-xin/
* */

import java.util.LinkedList;
import java.util.List;

public class lc120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int tLen = triangle.size();
        int[][] dp = new int[tLen][tLen];
        dp[0][0] = triangle.get(0).get(0);
//        解决难点一
        for(int i = 1; i < tLen; i++) dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
        for(int i = 1; i < tLen; i++){
            int k = 1;
            while (k < triangle.get(i).size() - 1){
                dp[i][k] = Math.min(dp[i - 1][k - 1], dp[i - 1][k]) + triangle.get(i).get(k);
                k++;
            }
//            解决难点二
            dp[i][k] = dp[i - 1][k - 1] + triangle.get(i).get(k);
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < tLen; i++){
            res = Math.min(res, dp[tLen - 1][i]);
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> l1 = new LinkedList<>();
        l1.add(2);
        List<Integer> l2 = new LinkedList<>();
        l2.add(3);
        l2.add(4);
        List<Integer> l3 = new LinkedList<>();
        l3.add(6);
        l3.add(5);
        l3.add(7);
        List<Integer> l4 = new LinkedList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);
        List<List<Integer>> test = new LinkedList<>();
        test.add(l1);
        test.add(l2);
        test.add(l3);
        test.add(l4);
        System.out.println(new lc120().minimumTotal(test));
    }
}
