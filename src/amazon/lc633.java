package amazon;


/*
这道题目一开始很难想到用双指针这个办法，还是挺巧妙的
https://leetcode-cn.com/problems/sum-of-square-numbers/solution/ping-fang-shu-zhi-he-by-leetcode-solutio-8ydl/
* */
import java.util.HashSet;

public class lc633 {
    private HashSet<Integer> hashSet = new HashSet<>();
    public boolean judgeSquareSum(int c) {
        if(c <= 2) return true;
//        注意这里不能用int，因为下面是相乘，很可能超出int范围
//        注意几个数字类型的区别，int和long其实都是整数，只是Long的范围更大。
//        而double其实是带小数点后的小数的，所以是小数类型。
//        int left = 0, right = c / 2;
//        double left = 0, right = Math.sqrt(c);
        long left = 0, right = (long) Math.sqrt(c);
        while (left <= right){
            double curRes = left * left + right * right;
//            System.out.println("left= " + left);
//            System.out.println("right= " + right);
//            System.out.println(curRes);
            if(curRes > c) right--;
            else if(curRes < c) left++;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new lc633().judgeSquareSum(8));
//        System.out.println(new lc633().judgeSquareSum(4));
//        System.out.println(new lc633().judgeSquareSum(5));
        System.out.println(new lc633().judgeSquareSum(100000));
    }
}
