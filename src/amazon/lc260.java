package amazon;

/*
这个题解讲的最清楚，还是不懂的话看下代码就行：
https://leetcode-cn.com/problems/single-number-iii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-8/
* */

public class lc260 {
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int tempRes = 0;
        for(int num : nums){
            tempRes ^= num;
        }
        //这个Xor1的作用非常重要，他其实相当于在找两个只出现一次的数字的不同位的最高位，
        // 换句话也就是说，如果是例子里面的011和101,他们是在最高位就已经不同了，
        // 那么就把最高位以0开头的和以1开头的分开，这样就变成了两组最高位分别为0和1的数组，
        // 并且每组之中只有一个数字只出现了一次，变成了两道lc136
        int xor1 = Integer.highestOneBit(tempRes);
//        int xor1 = Integer.lowestOneBit(tempRes);
        for(int num : nums){
            //这个很容易错
            //if((xor1 ^ num) == 0){
            if((xor1 & num) == 0){
                //这个也容易错
                //res[0] ^= num;
                res[0] ^= num;
            }
        }
        res[1] = tempRes ^ res[0];
        return res;
    }

    public static void main(String[] args) {
        int[] test = {1,2,1,3,2,5};
        System.out.println(new lc260().singleNumber(test));
    }
}
