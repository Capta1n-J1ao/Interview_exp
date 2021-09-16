package amazon;

/*
这道题目本质就是在找整个数组的最小值，所以一开始卡了一个bug做，并且非常快
但是题目并不希望这么做出来，而是要使用二分法
下面这个题解其实解答了为什么只需要判断mid和right的值即可：
https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-cha-zhao-wei-shi-yao-zuo-you-bu-dui-cheng-z/

但是其实我用了另一种办法也能够准确定位，但这种办法更像是面向数据编程，硬凑，其实题解里面说的方法很好
用二分法查找，需要始终将目标值（这里是最小值）套住，并不断收缩左边界或右边界。

左、中、右三个位置的值相比较，有以下几种情况，自己画一画就能够够想通了：

1. 左值 < 中值, 中值 < 右值 ：没有旋转，最小值在最左边，可以收缩右边界

2. 左值 > 中值, 中值 < 右值 ：有旋转，最小值在左半边，可以收缩右边界

3. 左值 < 中值, 中值 > 右值 ：有旋转，最小值在右半边，可以收缩左边界

4. 左值 > 中值, 中值 > 右值 ：单调递减，不可能出现

分析前面三种可能的情况，会发现情况1、2是一类，情况3是另一类。

如果中值 < 右值，则最小值在左半边，可以收缩右边界。
如果中值 > 右值，则最小值在右半边，可以收缩左边界。
通过比较中值与右值，可以确定最小值的位置范围，从而决定边界收缩的方向。

而情况1与情况3都是左值 < 中值，但是最小值位置范围却不同，这说明，如果只比较左值与中值，不能确定最小值的位置范围。

所以我们需要通过比较中值与右值来确定最小值的位置范围，进而确定边界收缩的方向。
* */

public class lc153 {
//    卡bug做法
//    public int findMin(int[] nums) {
//        int res = Integer.MAX_VALUE;
//        for(int num : nums){
//            res = Math.min(num, res);
//        }
//        return res;
//    }

    public int findMin(int[] nums) {
        int numLen = nums.length;
        int left = 0, right = numLen - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if(nums[left] > nums[right] && nums[mid] >= nums[left]) left = mid + 1;
            else right = mid;
        }
        return nums[left];
    }

    public static void main(String[] args){
//        int[] test = {3,1,2};
//        int[] test = {3,4,5,1,2};
//        int[] test = {2,1};
        int[] test = {4,5,6,7,0,1,2};
        System.out.println(new lc153().findMin(test));
    }
}
