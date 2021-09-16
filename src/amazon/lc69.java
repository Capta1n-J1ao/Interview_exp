package amazon;

/*
这道题目不用怀疑自己，确实是用二分法来做，而且Liweiwei的题解已经很清楚了
为什么这道题取右中位数也已经在题解里面说明了，这个就是有时候左中位数不行的时候换右中位数就行的题型
https://leetcode-cn.com/problems/sqrtx/solution/er-fen-cha-zhao-niu-dun-fa-python-dai-ma-by-liweiw/
而且有很tricky的case，比如x = 2147395599，那么如果用乘法来判断，那么会溢出，要想清楚

* */

public class lc69 {
    public int mySqrt(int x) {
        if(x < 2) return x;
        int left = 1, right = x / 2;
        while (left < right){
            int mid = left + (right - left + 1) / 2;
            if(mid > x / mid){
                right = mid - 1;
            }else left = mid;
        }
        return left;
    }
}
