package amazon;

/*
想到了异或的办法，也就是对这个数组进行三次异或，这样得到的是不包含答案的所有东西的异或值
然后再乘一遍，就是包括答案的异或值。两个异或一下就是答案。但是好像没人这么写，自己实际操作以后发现这条路不对
因为异或三遍以后其实和异或一遍是一样的，这个一开始没搞清楚。

其实题解也差不多，用的也是位运算，具体可以看那个动画，很清晰
如果要实现O(N)的话要用数电逻辑，感觉没什么必要，所以用上面这个方法也就够了。
时间复杂度： O（n log(32)）
空间复杂度： O（1）
https://leetcode-cn.com/problems/single-number-ii/solution/shu-ju-jie-gou-he-suan-fa-kan-wan-ni-nen-v9qp/
* */

public class lc137 {
    public int singleNumber(int[] nums) {
        int numLen = nums.length, res = 0;
        if(numLen < 4) return nums[0];
        for(int i = 0; i < 32; i++){
            int digitCount = 0;
            for(int num : nums) digitCount += (num >> i) & 1;
//            位或是核心
            if(digitCount % 3 != 0) res |= 1 << i;
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] test = {2,2,3,2};
//        System.out.println(new lc137().singleNumber(test));

        int[] test = {0,1,0,1,0,1,99};
        System.out.println(new lc137().singleNumber(test));
    }
}
