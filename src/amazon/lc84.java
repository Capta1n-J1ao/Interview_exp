package amazon;

/*
建议三道题目一起做，建议先做11再做42最后在84，
三道题目是一个很好的难度递进的过程，对于单调栈的试用是非常灵活的，必须要秒做出来，很重要！

这道题目记得一定要从暴力解法开始想，因为后序的解法也是基于暴力法的思想的，
特别是对于当前height从两边扩散的暴力解法思路是优化解法思路的基础，如果直接看优化思路一定会卡住
https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/javade-5chong-jie-fa-xiao-lu-zui-gao-de-ji-bai-lia/

然后如果在没看懂的话可以看liweiwei的代码，以及哨兵的运用：
https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/
* */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class lc84 {
    public int largestRectangleArea(int[] heights) {
        int hLen = heights.length, res = 0;
        int[] temp = new int[hLen + 2];
//        这道题目还需要掌握对于数组copy的一种简单用法！
        System.arraycopy(heights, 0, temp, 1,hLen);
        heights = temp;
        hLen = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < hLen; i++){
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                int curIndex = stack.pollFirst();
                int left = stack.peekFirst();
//                此时的right其实就是i,curIndex是目前的最高值，
//                他被比他小的left和right(即i)夹住
                res = Math.max(res, (i - left - 1) * heights[curIndex]);
            }
            stack.addFirst(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {2,1,5,6,2,3};
        System.out.println(new lc84().largestRectangleArea(test));
    }
}
