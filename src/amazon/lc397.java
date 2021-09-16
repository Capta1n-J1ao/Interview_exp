package amazon;

/*
套路题，题解参考：
https://leetcode-cn.com/problems/integer-replacement/solution/java-di-gui-he-fei-di-gui-de-3chong-jie-ti-si-lu-b/
* */

public class lc397 {
    public int integerReplacement(int n) {
        if(n == Integer.MAX_VALUE) return 32;
        else if(n <= 3) return n - 1;
        else if(n % 2 == 0) return integerReplacement(n / 2) + 1;
        else return Math.min(integerReplacement(n - 1), integerReplacement(n + 1)) + 1;
    }

    public static void main(String[] args) {
        System.out.println(new lc397().integerReplacement(8));
    }
}
