package amazon;

/*
在做这道题目的时候不用考虑到数字会很大，考虑到hundred billion这个量级就够了，
不用考虑billion billion的情况
这道题目主要难在思路，实现的话还是挺容易的，还有就是要熟悉string的两个命令
1. string.trim
2. append其实是可以连续使用的，比如.append().append()...可以无限接下去
代码的话就参考我写的这个，我参考的leetcode上面的那个也没注释，但是也没必要注释，都看得懂
* */

public class lc273 {
    String[] one2Twenty = {"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen","Twenty"};
    //    String[] mid = {"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    String[] Tens = {"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    public String numberToWords(int num) {
        if(num == 0) return one2Twenty[0];
        int temp = num;
        int step1 = temp % 1000;
        temp /= 1000;
        int step2 = temp % 1000;
        temp /= 1000;
        int step3 = temp % 1000;
        temp /= 1000;
        int step4 = temp % 1000;
        temp /= 1000;
        StringBuilder res = new StringBuilder();
        if(step4 != 0){
            res.append(convert(step4)).append(" Billion ");
        }
        if(step3 != 0){
            res.append(convert(step3)).append(" Million ");
        }
        if(step2 != 0){
            res.append(convert(step2)).append(" Thousand ");
        }
        res.append(convert(step1));
        return res.toString().trim();
    }

    private String convert(int num){
        StringBuilder curRes = new StringBuilder();
        int hundreds = num / 100;
        int twos = num % 100;
        int digit = twos % 10;
        int tens = twos / 10;
        if(hundreds != 0) curRes.append(one2Twenty[hundreds]).append(" Hundred ");
        if(0 < twos && twos <= 20) curRes.append(one2Twenty[twos]);
        else if(twos > 20 && (twos % 10 != 0)){
            curRes.append(Tens[tens]).append(" ").append(one2Twenty[digit]);
        }else {
            curRes.append(Tens[tens]).append(" ");
        }
        return curRes.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(new lc273().numberToWords(40));
        System.out.println(new lc273().numberToWords(100));
    }
}