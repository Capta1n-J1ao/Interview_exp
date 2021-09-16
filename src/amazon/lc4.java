package amazon;

/*
这道题目用log(m + n)的方法实在太麻烦，而且细节太多，直接先用一个自己想到的方法先做
下次有空可以看下那个题解中的方法四，方法三不推荐，没有理论基础。预留1 - 2个小时
题解：
https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
* */

import java.util.Stack;

public class lc4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num1Len = nums1.length;
        int num2Len = nums2.length;
        int numLen = num1Len + num2Len;
        int left = 0, right = 0;
        int leftRes = -1, rightRes = -1;
//      一开始用的下面这句作为循环，其实不可行，还是应该规定循环次数，这样能够精确找到要的那个值，
//      用了while以后还要用if去控制循环退出，很容易错
//        while (left < num1Len || right < num2Len)
        for(int i = 0; i <= numLen / 2; i++){
            leftRes = rightRes;
//            这里面的(right >= num2Len || nums1[left] < nums2[right])有两个注意点，
//            1. 针对下面第三个case，要加right >= num2Len
//            2. 这两个或逻辑的顺序不能颠倒，也可以用第三个case验证
            if(left < num1Len && (right >= num2Len || nums1[left] < nums2[right]) ){
                rightRes = nums1[left];
                left++;
            }else {
                rightRes = nums2[right];
                right++;
            }
        }
        if(numLen % 2 == 0) return (leftRes + rightRes) / 2.0;
        else return rightRes;
    }

    public static void main(String[] args) {
//        int[] test1 = {1,3};
//        int[] test2 = {2};
//        System.out.println(new lc4().findMedianSortedArrays(test1, test2));


//        int[] test1 = {1,2};
//        int[] test2 = {3,4};
//        System.out.println(new lc4().findMedianSortedArrays(test1, test2));

        int[] test1 = {0,0};
        int[] test2 = {0,0};
        System.out.println(new lc4().findMedianSortedArrays(test1, test2));
    }
}
