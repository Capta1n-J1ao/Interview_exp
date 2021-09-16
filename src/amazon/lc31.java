package amazon;

/*
lc31和lc60可以放在一起做，因为都是有一种排序的感觉，虽然方法完全不一样
主要是理解题目非常困难：
我们可以将该问题形式化地描述为：给定若干个数字，将其组合为一个整数。
如何将这些数字重新排列，以得到下一个更大的整数。如 123 下一个更大的数为 132。
如果没有更大的整数，则输出最小的整数。

这个题解思路比较清楚，但是代码还是自己写的清楚
https://leetcode-cn.com/problems/next-permutation/solution/20201110mu-qian-ti-jie-qu-mei-you-chu-xian-guo-de-/
* */

import java.util.Arrays;

public class lc31 {
    private int[] nums;
    public void nextPermutation(int[] nums) {
        this.nums = nums;
        int numLen = nums.length;
//        以 1,6,3,7,4,2 为例
        for(int i = numLen - 1; i > 0; i--){
//            通过此判断条件，可以找到定位到nums[i - 1] = 3
            if(nums[i - 1] < nums[i]){
//                这个条件就是在找3右边的数里面哪个是比3大的数里面最小的那个
//                因为3右边的数字一定是降序排列，所以k从最小位开始往前找即可
                for(int k = numLen - 1; k >= i; k--){
                    if(nums[i - 1] < nums[k]){
                        swap(i - 1, k);
                        Arrays.sort(nums, i, numLen);
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);
    }

    private void swap(int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
//        int[] test = {1,2,4,3};
//        new lc31().nextPermutation(test);
//        System.out.println(Arrays.toString(test));

//        int[] test = {1,5,2,4,3,2};
//        new lc31().nextPermutation(test);
//        System.out.println(Arrays.toString(test));

//        int[] test = {1,6,3,7,4,2};
        int[] test = {1,2,3};
        new lc31().nextPermutation(test);
        System.out.println(Arrays.toString(test));
    }
}
