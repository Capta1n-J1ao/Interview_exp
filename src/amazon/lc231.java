package amazon;

/*
题解：
https://leetcode-cn.com/problems/power-of-two/solution/power-of-two-er-jin-zhi-ji-jian-by-jyd/

还有这里贴一个类似于理论证明的东西：
充分性的通俗理解：假定n-1是多位二进制数。
正常情况下数n-1 加上1，只有最后一位变化（如果不进位的话），
那么(n-1)&n肯定不为0，如果进位的话，正常情况下也只会导致倒数第二位变化，
那么(n-1)&n肯定也不为0......只有所有的位都引起了进位，
也就是原来所有的位都是1，这样情况下，才会导致(n-1)&n==0，
这也就是n为2的幂。当n-1只有一位的时候，同样成立。

二刷用的以下题解的方法二，参考260想到的
https://leetcode-cn.com/problems/power-of-two/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-49/
* */

public class lc231 {
    public boolean isPowerOfTwo(int n) {
        return (n > 0) && (n & (n - 1)) == 0;
    }

//    方法二，都是些抖机灵的做法
//    public boolean isPowerOfTwo(int n) {
//        if(n <= 0) return false;
//        return Integer.highestOneBit(n) == n;
//    }
}
