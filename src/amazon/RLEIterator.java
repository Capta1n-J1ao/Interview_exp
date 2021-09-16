package amazon;

/*
一开始想的使用List来存储所有元素，然后一个个调用，但是有test case是专门针对这个，会超时
下面这个题解和我的想法一模一样，可以看下超时的test case是怎么样的：
https://leetcode-cn.com/problems/rle-iterator/solution/shou-dao-liao-you-jian-suo-yi-dian-kai-liao-zhe-ti/
* */

public class RLEIterator {
    int index;
    int[] encoding;
    public RLEIterator(int[] encoding) {
        index = 0;
        this.encoding = encoding;
    }

    public int next(int n) {
        int res = -1;
//        这段代码不知道错在哪里，但是由于这道题目也不是很重要，所以也就没有debug
//        思路对了就行
//        while (n > encoding[index]){
//            n -= encoding[index];
//            index += 2;
//            if(index > encoding.length - 1) return -1;
//        }
//        if(n <= encoding[index]){
//            encoding[index] -= n;
//            res = encoding[index + 1];
//        }
        while (index < encoding.length && n > 0){
            if(n > encoding[index]){
                n -= encoding[index];
                index += 2;
            }else {
                encoding[index] -= n;
                n = 0;
                res = encoding[index + 1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {3,8,0,9,2,5};
        RLEIterator test1 = new RLEIterator(test);
        test1.next(2);
        test1.next(1);
        test1.next(1);
        test1.next(2);
    }
}
