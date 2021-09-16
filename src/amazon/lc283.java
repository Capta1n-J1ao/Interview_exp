package amazon;

/*
这道题目一开始想的是双指针，就是把0和非0交换，但是实现方法一致没想通
其实因为实现的方法有点小聪明
题解可以参考这个，里面的方法的核心思想其实都是双指针
https://leetcode-cn.com/problems/move-zeroes/solution/san-chong-fang-shi-jie-jue-du-ji-bai-liao-100de-yo/
* */

public class lc283 {
    private int[] nums;
    public void moveZeroes(int[] nums) {
        this.nums = nums;
        if(nums == null || nums.length == 0) return;
//        zero左边要确保全部非0
        int numLen = nums.length, zero = 0;
        for(int i = 0; i <numLen; i++){
            if(nums[i] != 0){
                swap(i, zero);
                zero++;
            }
        }
    }
    private void swap(int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
