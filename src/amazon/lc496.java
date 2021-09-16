package amazon;

/*
lc496/lc503/lc556这三道题一起服用
下一个更大元素一共有三道题目，其中496/503思路类似，但是556思路完全不同，可以拿出来一起做一下当复习：
题解：
https://leetcode-cn.com/problems/next-greater-element-i/solution/zhan-xia-yi-ge-geng-da-yuan-su-i-by-demi-cumj/
* */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

public class lc496 {
    private Deque<Integer> stack = new ArrayDeque<>();
    private HashMap<Integer, Integer> hashMap = new HashMap<>();
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1Len = nums1.length, n2Len = nums2.length;
        stack.addFirst(nums2[0]);
        for(int i = 1; i < n2Len; i++){
//            注意这里要用while，写成if了，所以第一个testcase就不对了，要注意
            while (!stack.isEmpty() && nums2[i] > stack.peekFirst()){
                hashMap.putIfAbsent(stack.peekFirst(), nums2[i]);
                stack.pollFirst();
            }
            stack.addFirst(nums2[i]);
        }
//        方法一：
//        while (!stack.isEmpty()) hashMap.putIfAbsent(stack.pollFirst(), -1);
//
//        int[] res = new int[n1Len];
//
//        for(int i = 0; i < n1Len; i++) res[i] = hashMap.get(nums1[i]);

//        方法二：这个方法和lc503统一
        int[] res = new int[n1Len];
        Arrays.fill(res, -1);
        for(int i = 0; i < n1Len; i++) {
            if(hashMap.containsKey(nums1[i])) res[i] = hashMap.get(nums1[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test1 = {1,3,5,2,4};
        int[] test2 = {6,5,4,3,2,1,7};
        System.out.println(Arrays.toString(new lc496().nextGreaterElement(test1, test2)));
    }
}
