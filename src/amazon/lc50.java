package amazon;

/*
用暴力法解决是最好想到的，但是用二分法最快，题解：
https://leetcode-cn.com/problems/powx-n/solution/50-powx-n-kuai-su-mi-qing-xi-tu-jie-by-jyd/
* */

public class lc50 {
//    public double myPow(double x, int n) {
//        if(n == 0 || x == 1) return 1;
//        double res = 1;
//        long lN = n;
//        if(lN > 0){
//            while (lN != 0){
//                res *= x;
//                lN--;
//            }
//        }else {
//            while (lN != 0){
//                res *= 1 / x;
//                lN++;
//            }
//        }
//        return res;
//    }

//    坑比较多，上面的这个写法会导致第三个test case有问题，详情参考题解
    public double myPow(double x, int n) {
        if(n == 0 || x == 1) return 1;
        double res = 1.0;
        long lN = n;
        if(lN < 0){
            x = 1 / x;
            lN = -lN;
        }
        while (lN > 0){
//            针对奇数的情况
            if((lN & 1) == 1) res *= x;
            x *= x;
            lN >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new lc50().myPow(2.1, 3));
//        System.out.println(new lc50().myPow(2, -2));
        System.out.println(new lc50().myPow(2, -2147483648));
    }
}
