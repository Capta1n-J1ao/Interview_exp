package amazon;

/*
下面这个视频讲的很清楚，但是代码是C++的，所以我自己整理成了java
这是一道思路难代码简单的题目
https://www.bilibili.com/video/BV1uJ411573j?from=search&seid=6404891353672077688
* */

public class lc233 {
    public int countDigitOne(int n) {
//        corner case，这个很tricky
        if(n < 0) return 0;
        char[] nChar = String.valueOf(n).toCharArray();
        int res = 0, nLen = nChar.length;
//        用     345 [Y] 79  做例子
//        index  012 3   45
//        注意这里的i其实是和位数一一对应的，i=1的时候其实对应的就是个位
        for(int i = 1; i <= nLen; i++){
//            得到  000 ~344 + [Y] + 00 ~ 99种可能性
            int left = n / (int) Math.pow(10, i);
            res += left * (int) Math.pow(10, i - 1);
//            得到345 + [Y] + 00 ~ 79的所有可能性
            int right = n % (int) Math.pow(10, i - 1);
//            这里和视频有出入，但是根据我的思路只能我这么写，否则不对
            int curDigit = nChar[nLen - i] - '0';
            if(curDigit > 1){
                res += (int) Math.pow(10, i - 1);
            }else if(curDigit == 1){
                res += right + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lc233().countDigitOne(13));
    }
}
