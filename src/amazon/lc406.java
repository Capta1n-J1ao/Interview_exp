package amazon;

/*
可以对比较器进行相关复习的题目， 973/215/23/692/179/406
参考题解：
https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/gen-ju-shen-gao-zhong-jian-dui-lie-tan-x-72fy/
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class lc406 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
//            记住compare函数看的是o1的siftup情况
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });
//        注意这个ArrayList的add的添加，之前有遇到过，本来在这个位置上的元素是后移的
        List<int[]> resList = new ArrayList<>();
        for(int[] p : people){
            resList.add(p[1], p);
        }
        return resList.toArray(new int[resList.size()][2]);
    }
}
