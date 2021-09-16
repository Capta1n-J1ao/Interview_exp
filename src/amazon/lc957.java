package amazon;

/*
首先针对题目来说有一个歧义的地方：
题目其实没有说清楚第一个数和最后一个数的状态，
这里补充一下就是无论第一个数和最后一个数旁边是什么，在第1天都会变成0（初始为第0天）

其次这道题目一定要自己写一下，有一个很tricky的地方

关于解答：
这道题目主要的问题就是要找到套路，也就是题解里面说的无论cells是怎么样，
14天是一个轮回，所以先做一个打印的代码找规律。
这里就牵涉到上面说的一个很tricky的点，那就是对于N的简化
根据之前有循环的题目来说直接 N % 14即可，但是这道题目有点特殊
提供的cell其实并不在循环里面，这里模拟一下循环
循环次数        cells代号
   0            原数组
   1              a
   2              b
   .
   .
   .
   13             n
   14             o
   15             a
   16             b
那如果写N % 14的时候就会有一个问题，也就是提交代码的时候有的问题，
那就是如果N是14的倍数，那么循环就不能展开，而是直接返回原数组，这是不对的，所以才有了下面的计算方式
* */

import java.util.Arrays;

public class lc957 {
    public int[] prisonAfterNDays(int[] cells, int N) {
//        下面这句话是这道题目的核心考点
        N = (N - 1) % 14 + 1;
        int cLen = cells.length;
        int[] copy = cells;
        for(int i = 0; i < N; i++){
            int[] curRes = new int[cLen];
            for(int k = 1; k < cLen - 1; k++){
                curRes[k] = (copy[k - 1] ^ copy[k + 1]) == 0 ? 1 : 0;
            }
            copy = curRes;
//            Debug专用
            System.out.println(Arrays.toString(curRes));
        }
        return copy;
    }

    public static void main(String[] args) {
        int[] test = {0,1,0,1,1,0,0,1};
//        int[] test1 = {1,0,0,1,0,0,0,1};
        int[] res = new lc957().prisonAfterNDays(test, 20);
//        int[] res = new lc957().prisonAfterNDays(test1, 826);
//        System.out.println(Arrays.toString(new lc957().prisonAfterNDays(test, 20)));
    }
}
