package amazon;

/*
这里有一个问题那就是答案会有几个？
根据目前的题目来说只有一个，但是在面试的时候一定要问清楚，不要急

然后这道题目一定要做进阶版本，即：
设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。

进阶版的话要用一个叫做摩尔投票法来做，具体看题解就行，类似于军旗里面的对拼的感觉：
https://leetcode-cn.com/problems/majority-element/solution/3chong-fang-fa-by-gfu-2/
* */

public class lc169 {
    public int majorityElement(int[] nums) {
//        先思考corner case
        int res = 0, count = 0;
        for(int num : nums){
//            注意下面这个if不用count++,因为if...else...才是判断语句
            if(count == 0) res = num;
            if(res == num){
                count++;
            }else count--;
        }
        return res;
    }
}
