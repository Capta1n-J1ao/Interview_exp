package amazon;

/*
这道题目有一个小提示，那就是如果K = 1，那么答案就是5 ~ 9，如果K = 2，答案是10 ~ 14

在一刷的时候其实就清楚了。
其实核心就是答案要么是5，要么是0，不会有其他答案
* */

public class lc793 {
    public int preimageSizeFZF(long K) {
        //10K的意思是，要有K个零，也就是最多最多就是有K个10相乘，
        // 比如100就是10*10,1000是10*10*10。比如说K=3，上界就是30，阶乘是30*29*28*..1肯定包含了3个10相乘。
        // 至于为什么加1，可以试着把1去掉，结果发现报错了，因为有K=0这种情况，+1就是为了这种情况
        long left = 0;
        long right = 10 * K + 1;
        while (left < right){
            long mid = left + (right - left)/2;
            //这句话我搞了很久才搞懂，由于上限值本来就设的有点大的关系
            //例如要求找K = 3，那么其实最多10*10*10就能满足提议，
            //换句话说顶多出现三个5就能解决问题
            //也就是说其实到15！的话妥妥解决问题了，这也呼应了上面备注里面说的
            //"事实上这一题x <= 5*K+1 也是过。"
            //而题目为了保险用了上限是hi = 10 * k，绰绰有余了
            long zerosOfMid = trailingZeroes(mid);
            if(zerosOfMid < K) left = mid + 1;
            else if(zerosOfMid > K) right = mid;
            else if(zerosOfMid == K) return 5;
        }
        return 0;
    }

    private long trailingZeroes(long n){
        int res = 0;
        while (n > 0){
            res += n / 5;
            n /= 5;
        }
        return res;
    }
}
