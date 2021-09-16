package amazon;

/*
对应lc384
这道题目两个考点：
1. 熟悉Random类的用法
2. 进一步理解数组.clone的含义
这道题目感觉是熟悉random这个用法的，不能用Math.random而是random.nextInt的原因就是生成的随机数需要有一个范围：
int nextInt()            //随机返回一个int型整数
int nextInt(int num)         //随机返回一个值在[0,num)的int类型的整数,包括0不包括num

nextInt能接受一个整数作为它所产生的随机整数的上限,下限为零，
若要达到非零下限的效果，必须把上限减去下限的结果传给 nextInt( )，然后把下限加入 nextInt( ) 返回的整数。
* */

import java.util.Random;

public class Solution {
    private int[] nums, res;
//    这个random是单独的一个类，要记得！
    private Random radomIndex;
    public Solution(int[] nums) {
        this.nums = nums;
        radomIndex = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int numLen = nums.length;
        if(numLen == 0 || nums == null) return null;
//        必须要用这步，否则相当于在修改nums，要新创建一个res
        res = nums.clone();
//        其实i的起始无所谓，要注意nextInt(n)这个方法的含义是[0,n)，左边取不到
        for(int i = 0; i < numLen; i++){
            int index = radomIndex.nextInt(i + 1);
            swap(i, index);
        }
        return res;
    }

    private void swap(int a, int b){
        int temp = res[a];
        res[a] = res[b];
        res[b] = temp;
    }
}
