package amazon;

/*
这道题目主要的难点就是分类讨论，要把所有的情况分清楚真的非常难，此题解是分类最全的，
还有就是这道题目其实没有什么很难的思想，主要就是分类，如果能想出思路的话就不要做了，如果一定要写代码一定要预留一小时，又臭又长。
https://leetcode-cn.com/problems/find-the-closest-palindrome/solution/jian-dan-jie-fa-by-zwm-z/

把最核心部分选出来那就是：
几种可能存在的情况都获取出来，然后比对返回
1、前半段 翻转
2、前半段+1 翻转
3、前半段-1 翻转
4、位数-1 全是9
5、位数+1 首尾为1，其余为0

* */

public class lc564 {
    public String nearestPalindromic(String n) {
        String minusOneStr;
        String sameOneStr;
        String plusOneStr;
        Long minusOne;
        Long sameOne;
        Long plusOne;
        Long num = Long.valueOf(n);
        int numLen = n.length();
        String firstHalf;
        String minus;
        String plus;
//        Corner Case
        if (numLen == 1) {
            return String.valueOf(num - 1);
        }
//        求出    1、前半段 翻转
//               2、前半段+1 翻转
//               3、前半段-1 翻转
        if(numLen % 2 == 0){
            firstHalf = n.substring(0, numLen/2);
            minus = String.valueOf(Long.valueOf(firstHalf) - 1);
            plus = String.valueOf(Long.valueOf(firstHalf) + 1);
            minusOneStr = minus + new StringBuilder(minus).reverse().toString();
            sameOneStr = firstHalf + new StringBuilder(firstHalf).reverse().toString();
            plusOneStr = plus + new StringBuilder(plus).reverse().toString();
        }else {
            firstHalf = n.substring(0, numLen/2 + 1);
            minus = String.valueOf(Long.valueOf(firstHalf) - 1);
            plus = String.valueOf(Long.valueOf(firstHalf) + 1);
            minusOneStr = minus + new StringBuilder(minus).reverse().substring(1);
            sameOneStr = firstHalf + new StringBuilder(firstHalf).reverse().substring(1);
            plusOneStr = plus + new StringBuilder(plus).reverse().substring(1);
        }

//        例如n = 10000，那么就判断9999和minusOneStr 以及 10001和plusOneStr，哪个更符合
        String nineS = nineSeries(n);
        String zeroS = zeroSeries(n);
        if(Long.valueOf(minusOneStr) < Long.valueOf(nineS)) minusOneStr = nineS;
        if(Long.valueOf(plusOneStr) > Long.valueOf(zeroS)) plusOneStr = zeroS;
        Long minusOneDif = Math.abs(Long.valueOf(minusOneStr) - num);
        Long sameOneDif = Math.abs(Long.valueOf(sameOneStr) - num) == 0 ? Integer.MAX_VALUE : Math.abs(Long.valueOf(sameOneStr) - num);
        Long plusOneDif = Math.abs(Long.valueOf(plusOneStr) - num);
//        这里要注意顺序，比如对于10来说，应该出9而不是11，
//        也就是说差值相同的情况下要出较小的那个数，所以minusOneStr要最后更新
        String res = plusOneDif < sameOneDif ? plusOneStr : sameOneStr;
        Long temp = plusOneDif < sameOneDif ? plusOneDif : sameOneDif;
        res = temp < minusOneDif ? res : minusOneStr;
        return res;
    }

    public String nineSeries(String v){
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i < v.length(); i++) {
            buffer.append("9");
        }
        return buffer.toString();
    }
    public String zeroSeries(String v){
        StringBuffer buffer = new StringBuffer("1");
        for (int i = 1; i < v.length(); i++) {
            buffer.append("0");
        }
        buffer.append("1");
        return buffer.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new lc564().nearestPalindromic("9999"));
//        System.out.println(new lc564().nearestPalindromic("1"));
        System.out.println(new lc564().nearestPalindromic("10"));   //预期结果11
    }
}
