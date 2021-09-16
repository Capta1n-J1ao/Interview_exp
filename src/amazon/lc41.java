package amazon;

/*
第一种做法应该是easy难度，没有做到O(1)的空间复杂度

直接看Liweiwei题解就知道了,这道题目就是典型的思路很难但是代码不难的一类题目：
https://leetcode-cn.com/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
* */

import java.util.HashSet;

public class lc41 {
//    方法一，虽然可以过，但是不符合O(1)的空间复杂度
//    private HashSet<Integer> hashSet = new HashSet<>();
//    public int firstMissingPositive(int[] nums) {
//        if(nums == null) return 0;
//        int numLen = nums.length, res = 1;
//        for(int num : nums){
//            hashSet.add(num);
//        }
//        for(int i = 1; i <= Integer.MAX_VALUE; i++){
//            if(hashSet.contains(i)) continue;
//            return i;
//        }
//        return res;
//    }

    private int[] nums;
    public int firstMissingPositive(int[] nums) {
        this.nums = nums;
        if(nums == null) return 0;
        int numLen = nums.length;
        for(int i = 0; i < numLen; i++){
/*
        注意这里就是整道题目的精髓，注意以下几点：
        1. 根据第一个test case来看，下面不能用if而是要用while,
           原因就是在于在进行到i排序的时候，其实都是在把除了i以外的数排清楚了
           而不是把自己排清楚，直到自己这里排到一个负数才算完
           不用担心排到负数不对，因为对于后面的i来说他会去纠正除了i以外的所有数
           所以到头来还是会得到正确值
        2. while判断的最后一个条件需要非常小心，
            这道题目有个陷阱那就是没有说数组里面没有重复元素，
            根据第三个test case可以知道会有重复元素，那么如果没有第三个判断
            或者第三个判断如我一开始写的那样会进入死循环
            而且要注意第三个判断条件是在写完swap以后才会想到的，这么一个顺序
            而不是一开始就能想到的，lc442也是碰到了这个问题，这个判断条件很容易错！
* */
//            if (0 < nums[i] && nums[i] <= numLen && nums[i] != i + 1){
            while (0 < nums[i] && nums[i] <= numLen && nums[i] != nums[nums[i] - 1]){
                swap(i, nums[i] - 1);
            }
        }

        for(int i = 0; i < numLen; i++){
            if(nums[i] != i + 1) return i + 1;
        }
//        最后这个返回我一直没有想清楚，看了解答以后才想清楚
        return numLen + 1;
    }

    private void swap(int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] test = {3,4,-1,1};
        System.out.println(new lc41().firstMissingPositive(test));

//        int[] test = {1,2,0};
//        System.out.println(new lc41().firstMissingPositive(test));

//        int[] test = {1,1};
//        System.out.println(new lc41().firstMissingPositive(test));
    }
}
