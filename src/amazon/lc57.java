package amazon;

/*
这个思路和我的很像，其实思路不难：
https://leetcode-cn.com/problems/insert-interval/solution/liang-chong-fang-shi-jie-jue-zui-hao-de-ji-bai-lia/
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals == null || intervals.length == 0)  return new int[][] {newInterval};
        int iLen = intervals.length;
        List<int[]> res = new ArrayList<>();
        int left = newInterval[0], right = newInterval[1];
        int i = 0, k = iLen - 1;
//        逼近左边界
        while (i < iLen && intervals[i][1] < left) res.add(intervals[i++]);
//        逼近右边界,而且这里很巧妙的利用了ArrayList.add(index, int[])的特性
//        Inserts the specified element at the specified position in this list.
//        Shifts the element currently at that position (if any)
//        and any subsequent elements to the right
        while (k >= 0 && intervals[k][0] > right) res.add(i, intervals[k--]);
        int[] mergeRes = new int[2];
        mergeRes[0] = i == iLen ? newInterval[0] : Math.min(intervals[i][0], newInterval[0]);
        mergeRes[1] = k == -1 ? newInterval[1] : Math.max(intervals[k][1], newInterval[1]);
        res.add(i, mergeRes);

        int resLen = res.size();
        int[][] arrayRes = new int[resLen][2];
        for(int count = 0; count < resLen; count++) arrayRes[count] = res.get(count);
        return arrayRes;
    }

    public static void main(String[] args) {
        int[][] test = {{1,3},{6,9}};
        int[] plugIn = {2,5};
        int[][] res = new lc57().insert(test, plugIn);
        for (int[] r : res){
            System.out.println(Arrays.toString(r));
        }
    }
}
