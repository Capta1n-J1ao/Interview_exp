package amazon;

/*
Integer范围：
-2147483648  ~~ 2147483647
* */

public class lc7 {
    public int reverse(int x) {
        int res = 0;
        while (x != 0){
            int curDig = x % 10;
            x /= 10;
            if(res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && curDig > 7)) return 0;
            if(res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && curDig < -8)) return 0;
            res = res * 10 + curDig;
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new lc7().reverse(-210));
//        System.out.println(new lc7().reverse(0));
//        System.out.println(new lc7().reverse(123));
//        System.out.println(new lc7().reverse(1534236469));
        System.out.println(new lc7().reverse(-2147483648));
    }
}
