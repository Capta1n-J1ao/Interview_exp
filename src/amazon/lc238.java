package amazon;

/*
这次做虽然AC了，但是有更优化的版本，下次要记得
二刷的时候尝试了优化解法， 之前做优化方法是直接看的题解，所以做法是很漂亮的，
但是现在是直接自己想，那么就是在原来的基础上进行修改，那么就会有一个对齐的问题，
具体看代码就懂了，没那么漂亮，但是思路是连贯的
* */

import java.util.Arrays;

public class lc238 {
//    方法一，常规方法
//    public int[] productExceptSelf(int[] nums) {
//        int numLen = nums.length;
//        int[] res = new int[numLen];
//        int[] left = new int[numLen];
//        int[] right = new int[numLen];
//        int begin = 1;
//        for(int i = 0; i < numLen; i++){
//            begin = i == 0 ? nums[0] : begin * nums[i];
//            left[i] = begin;
//        }
//        begin = 1;
//        for(int i = numLen - 1; i >= 0; i--){
//            begin = i == numLen - 1 ? nums[numLen - 1] : begin * nums[i];
//            right[i] = begin;
//        }
//        for(int i = 0; i < numLen; i++){
//            int leftSide = i == 0 ? 1 : left[i - 1];
//            int rightSide = i == numLen - 1 ? 1 : right[i + 1];
//            res[i] = leftSide * rightSide;
//        }
//        return res;
//    }

//    二刷 2021/06/22
//    方法二，优化解法
    public int[] productExceptSelf(int[] nums) {
        int numLen = nums.length;
        if(numLen < 2) return nums;
        int[] res = new int[numLen];
        res[numLen - 1] = nums[numLen - 1];
//        注意在官方解法中其实和方法一是有不同的，是有错位的，需要自己写一遍才能够更加清楚
        for(int i = numLen - 2; i >= 0; i--){
            res[i] = res[i + 1] * nums[i];
        }
        int l2r = 1;
        for(int i = 0; i < numLen - 1; i++){
            res[i] = res[i + 1] * l2r;
            l2r *= nums[i];
        }
        res[numLen - 1] = l2r;
        return res;
    }


    public static void main(String[] args) {
        int[] test = {1,2,3,4};
        System.out.println(Arrays.toString(new lc238().productExceptSelf(test)));
    }
}
