package amazon;

/*
先看一下这个题解的思路：
https://leetcode-cn.com/problems/perfect-number/solution/hua-jie-suan-fa-507-wan-mei-shu-by-guanpengchn/
然后我感觉官解的代码更清晰一些：
https://leetcode-cn.com/problems/perfect-number/solution/wan-mei-shu-by-leetcode/
* */

public class lc507 {
    public boolean checkPerfectNumber(int num) {
        if(num < 2) return false;
        int res = 1;
        for(int i = 2; i * i <num; i++){
            if(num % i == 0){
                res += i;
                if(i * i != num) res += num / i;
            }
        }
        return res == num;
    }

    public static void main(String[] args) {
        System.out.println(new lc507().checkPerfectNumber(28));
    }
}
