package amazon;

/*
一开始就想了一个比较简单的办法，有点像官解的方法一，但是要注意这道题目确实有长方形为一个点的corner case需要排除
还要考虑到某一个rec在另一个rec里面完全包围的情况，所以常规思路要用反向方法。

看了方法二以后觉得面试可能倾向于用这种办法，所以把两种方法都写一下：
https://leetcode-cn.com/problems/rectangle-overlap/solution/ju-xing-zhong-die-by-leetcode-solution/
* */

public class lc836 {
//    方法一，要用排除法，否则分类情况太多，参考上面的提示
//    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
//        if(rec1[0] == rec1[2] || rec1[1] == rec1[3] || rec2[0] == rec2[2] || rec2[1] == rec2[3]) return false;
//        int x1 = rec2[0], y1 = rec2[1], x2 = rec2[2], y2 = rec2[3];
//        return !(x1 >= rec1[2] || x2 <= rec1[0] || y1 >= rec1[3] || y2 <= rec1[1]);
//    }

//    方法二：映射法
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return (Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2])) &&
                (Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]));
    }
}
