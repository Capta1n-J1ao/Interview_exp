package amazon;

/*
这道题目主要的难点的就是怎么在O(n)的时间复杂度里面完成这个操作
虽然是C++的题解，但是更加易懂，对于第二个testcase的解释也更加直白

https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/c-xian-xing-shi-jian-fu-za-du-xiang-xi-jie-xi-da-b/
* */

public class lc334 {
    public boolean increasingTriplet(int[] nums) {
            int numLen = nums.length,first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
            if(numLen < 3) return false;
        for(int i = 0; i < numLen; i++){
            int curNum = nums[i];
            if(curNum <= first) first = nums[i];
            else if(first < nums[i] && curNum <= second) second = nums[i];
            else if(curNum > second) return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] test = {20,100,10,12,5,13};
//        System.out.println(new lc334().increasingTriplet(test));

//        这种情况就是比较难想的一种情况，特别关注下
        int[] test = {20,100,10,130};
        System.out.println(new lc334().increasingTriplet(test));
    }
}
