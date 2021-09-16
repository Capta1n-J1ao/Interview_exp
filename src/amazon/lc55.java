package amazon;

/*
这道题目在dp/BFS/贪心里面纠结了很久，其实思路挺简单，
直接看官方题解的文字思路，然后就可以直接写代码了，记得别看代码就看思路！
https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/

两道题目都是跳跃游戏，可以放在一起做，先做lc55，再做lc45

* */

public class lc55 {
    public boolean canJump(int[] nums) {
        int numLen = nums.length;
        if(nums == null || numLen == 0) return false;
        int maxLen = 0;
        for(int i = 0; i < numLen; i++){
            maxLen = Math.max(maxLen, i + nums[i]);
            if(maxLen >= numLen - 1) return true;
            else if(maxLen == i) return false;
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] test = {3,2,1,0,4};
//        System.out.println(new lc55().canJump(test));
        int[] test = {2,3,1,1,4};
        System.out.println(new lc55().canJump(test));
    }
}
